package pt.unbabel.demo.interactors.mocks.base

import android.os.Handler
import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.interactors.interfaces.base.IBaseInteractor
import pt.unbabel.demo.interactors.listeners.base.IInteractorListener

/**
 * Created by Ricardo Neves on 16/09/2019.
 */

abstract class MockInteractor<IL : IInteractorListener> : IBaseInteractor<IL> {

    override var requestContext = javaClass.toString()
    override lateinit var interactorListener: IL

    protected fun executeWithDelay(
        runnable: () -> Unit, delay: Long = 500,
        requestConfig: RequestConfig
    ) {

        if (requestConfig.showLoader) {
            interactorListener.showLoader(requestConfig)
        }

        Handler().postDelayed({
            try {

                if (requestConfig.showLoader) {
                    interactorListener.hideLoader(requestConfig)
                }

                runnable()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, delay)
    }

    override fun cancelAllRunningRequests() {
        // empty for mocks implementation
    }

    override fun retryFailedRequests() {
        // empty for mocks implementation
    }

}