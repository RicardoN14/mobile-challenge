package pt.unbabel.demo.presenters.interfaces.base
 
import pt.unbabel.demo.interactors.interfaces.base.IBaseInteractor
import pt.unbabel.demo.interactors.listeners.base.IInteractorListener
import pt.unbabel.demo.presenters.listeners.base.IPresenterListener
import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.entities.requests.RequestError

/**
 * Created by Ricardo Neves on 16/09/2019.
 */

interface IBasePresenter<out PL : IPresenterListener, IL : IInteractorListener,
        I : IBaseInteractor<IL>> : IInteractorListener {

    val presenterListener: PL
    val requestContext: String
    var interactor: I

    override fun showLoader(requestConfig: RequestConfig) {
        presenterListener.showLoader(requestConfig)
    }

    override fun hideLoader(requestConfig: RequestConfig) {
        presenterListener.hideLoader(requestConfig)
    }

    override fun showError(requestConfig: RequestConfig, requestError: RequestError) {
        presenterListener.showError(requestConfig, requestError)
    }

    override fun showRootView(requestConfig: RequestConfig) {
        presenterListener.showRootView(requestConfig)
    }

    override fun hideRootView(requestConfig: RequestConfig) {
        presenterListener.hideRootView(requestConfig)
    }

    fun cancelAllRunningRequests() {
        interactor.cancelAllRunningRequests()
    }

    fun retryFailedRequests(){
        interactor.retryFailedRequests()
    }
	
}