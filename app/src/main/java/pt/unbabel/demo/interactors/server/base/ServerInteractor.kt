package pt.unbabel.demo.interactors.server.base

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pt.unbabel.demo.BuildConfig
import pt.unbabel.demo.R
import pt.unbabel.demo.UnbabelApplication
import pt.unbabel.demo.interactors.interfaces.base.IBaseInteractor
import pt.unbabel.demo.interactors.listeners.base.IInteractorListener
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    override var requestContextGroup: String? = null

    override lateinit var interactorListener: IL

    override fun cancelAllRunningRequests() {
    }

    override fun retryFailedRequests() {
    }

}