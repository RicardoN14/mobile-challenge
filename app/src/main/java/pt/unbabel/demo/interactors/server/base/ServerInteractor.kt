package pt.unbabel.demo.interactors.server.base

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pt.unbabel.demo.BuildConfig
import pt.unbabel.demo.R
import pt.unbabel.demo.UnbabelApplication
import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.entities.requests.RequestError
import pt.unbabel.demo.entities.requests.RequestErrorType
import pt.unbabel.demo.http.managers.RequestsManager
import pt.unbabel.demo.interactors.interfaces.base.IBaseInteractor
import pt.unbabel.demo.interactors.listeners.base.IInteractorListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLException

/**
 * Created by Ricardo Neves on 16/09/2019.
 */

abstract class ServerInteractor<IL : IInteractorListener> : IBaseInteractor<IL> {

    companion object {

        private val logging by lazy {
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }

        private val httpClient by lazy {
            OkHttpClient.Builder().apply {
                addInterceptor(logging)
            }
        }

        val requestsManager by lazy {
            RequestsManager()
        }

        val retrofit: Retrofit by lazy {
            val builder = Retrofit.Builder()
                .baseUrl(UnbabelApplication.getString(R.string.server_endpoint))
                .addConverterFactory(GsonConverterFactory.create())

            if (BuildConfig.DEBUG) {
                builder.client(httpClient.build())
            }

            builder.build()
        }
    }

    /** Properties **/

    protected val tag: String = javaClass.simpleName

    override lateinit var requestContext: String
    override lateinit var interactorListener: IL

    protected abstract fun retryRequest(failedRequestInfo: RequestsManager.FailedRequestInfo)

    override fun cancelAllRunningRequests() {
        requestsManager.getRunningRequests(requestContext)?.forEach {
            it.request.cancel()
        }
    }

    override fun retryFailedRequests() {
        requestsManager.apply {
            getFailedRequests(requestContext)?.forEach {
                retryRequest(it)
            }

            removeFailedRequests(requestContext)
        }
    }

    /**
     * This MUST be called to execute the request
     * */
    protected fun <R> executeRequest(
        id: String,
        requestConfig: RequestConfig,
        call: Call<R>,
        requestListener: (R) -> Unit
    ) {

        if (requestConfig.showLoader) {
            interactorListener.showLoader(requestConfig)
        }

        call.apply {
            requestsManager.addRunningRequest(
                requestContext,
                RequestsManager.RequestInfo(requestConfig, this)
            )
            enqueue(object : RequestListener<R>(id, requestConfig) {
                override fun onResponseSuccess(response: R) {
                    requestListener(response)
                }
            })
        }

    }

    abstract inner class RequestListener<R>(
        val id: String,
        val requestConfig: RequestConfig
    ) : Callback<R> {

        abstract fun onResponseSuccess(response: R)

        override fun onFailure(call: Call<R>, t: Throwable) {

            requestsManager.removeRunningRequest(requestContext, requestConfig.requestId)
            tryAddRequestToRetry(id, requestConfig)

            if (call.isCanceled) {
                return
            }

            tryHideLoader(requestConfig)

            val errorType = when (t) {
                is SSLException -> RequestErrorType.SSL_ERROR
                is SocketTimeoutException -> RequestErrorType.TIMEOUT_ERROR
                else -> RequestErrorType.INTERNET_CONNECTION_ERROR
            }

            interactorListener.showError(
                requestConfig,
                RequestError(errorType)
            )
        }

        override fun onResponse(call: Call<R>, response: Response<R>) {

            // request not running anymore
            requestsManager.removeRunningRequest(requestContext, requestConfig.requestId)

            if (call.isCanceled) {
                return
            }

            tryHideLoader(requestConfig)

            if (response.isSuccessful) {
                response.body()?.let {
                    onResponseSuccess(it)
                } ?: run {
                    interactorListener.showError(
                        requestConfig,
                        RequestError(RequestErrorType.PARSE_ERROR)
                    )
                }

            } else {
                tryAddRequestToRetry(id, requestConfig)
                interactorListener.showError(
                    requestConfig,
                    RequestError(
                        RequestErrorType.SERVER_ERROR,
                        responseCode = response.code()
                    )
                )
            }

        }

    }

    private fun tryAddRequestToRetry(
        id: String,
        requestConfig: RequestConfig
    ) {
        if (requestConfig.canRetry) {
            requestsManager.addFailedRequest(
                requestContext,
                RequestsManager.FailedRequestInfo(id, requestConfig)
            )
        }
    }

    /**
     * The loader can only be hidden if there is no request running that needs it
     * */
    private fun tryHideLoader(requestConfig: RequestConfig) {

        if (!requestConfig.showLoader) {
            return
        }

        requestsManager.getRunningRequests(requestContext)?.forEach {
            if (it.requestConfig.showLoader) {
                // There is at least one request running that need the loader, lets not hide it!
                return
            }
        }

        interactorListener.hideLoader(requestConfig)

    }

}