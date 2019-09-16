package pt.unbabel.demo.interactors.server.base

import pt.unbabel.demo.R
import pt.unbabel.demo.UnbabelApplication
import pt.unbabel.demo.interactors.interfaces.base.IBaseInteractor
import pt.unbabel.demo.interactors.listeners.base.IInteractorListener
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Ricardo Neves on 16/09/2019$.
 */

abstract class ServerInteractor<IL : IInteractorListener> : IBaseInteractor<IL> {

    companion object{

        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(UnbabelApplication.getString(R.string.server_endpoint))
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

    /** Properties **/

    protected val tag: String = javaClass.simpleName

    override var requestContextGroup: String? = null

    override lateinit var interactorListener: IL

    override fun cancelAllRunningRequests() {
        // TODO
    }

    override fun retryFailedRequests() {
        // TODO
    }

}