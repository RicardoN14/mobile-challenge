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
    val requestContextGroup: String
    var interactor: I

    override fun showLoader() {
        presenterListener.showLoader()
    }

    override fun hideLoader() {
        presenterListener.hideLoader()
    }

    override fun showError(requestConfig: RequestConfig, requestError: RequestError) {
        presenterListener.showError(requestConfig, requestError)
    }

    override fun showRootView() {
        presenterListener.showRootView()
    }

    override fun hideRootView() {
        presenterListener.hideRootView()
    }

    fun cancelAllRunningRequests() {
        interactor.cancelAllRunningRequests()
    }

    fun retryFailedRequests(){
        interactor.retryFailedRequests()
    }
	
}