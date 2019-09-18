package pt.unbabel.demo.presenters.listeners.base
 
import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.entities.requests.RequestError

/**
 * Created by Ricardo Neves on 16/09/2019.
 */

interface IPresenterListener {
    fun showLoader()
    fun hideLoader()
    fun showRootView()
    fun hideRootView()
    fun showError(requestConfig: RequestConfig, requestError: RequestError)
}

