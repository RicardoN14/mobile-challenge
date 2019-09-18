package pt.unbabel.demo.interactors.mocks.base

import android.os.Handler
import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.interactors.interfaces.base.IBaseInteractor
import pt.unbabel.demo.interactors.listeners.base.IInteractorListener

/**
 * Created by Ricardo Neves on 16/09/2019.
 */

abstract class MockInteractor<IL : IInteractorListener> : IBaseInteractor<IL> {

    override var requestContextGroup: String? = javaClass.toString()
    override lateinit var interactorListener: IL

    protected fun executeWithDelay(runnable: () -> Unit, delay: Long = 500,
                                   requestConfig: RequestConfig?) {
        
        val reqConfig = requestConfig ?: RequestConfig()

        if (reqConfig.showLoader) {
            interactorListener.showLoader()
        }

        if (reqConfig.hideRootView) {
            interactorListener.hideRootView()
        }

        Handler().postDelayed({
            try {

                if (reqConfig.showLoader) {
                    interactorListener.hideLoader()
                }

                if (reqConfig.hideRootView) {
                    interactorListener.showRootView()
                }


                runnable()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, delay)
    }
	
	override fun cancelAllRunningRequests() {
        // TODO
    }

    override fun retryFailedRequests() {
        // TODO
    }
	
}