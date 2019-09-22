package pt.unbabel.demo.presenters.listeners.base
 
import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.entities.requests.RequestError

/**
 * Created by Ricardo Neves on 16/09/2019.
 */

interface IPresenterListener {
    fun showLoader(requestConfig: RequestConfig)
    fun hideLoader(requestConfig: RequestConfig)
    fun showRootView(requestConfig: RequestConfig)
    fun hideRootView(requestConfig: RequestConfig)
    fun showError(requestConfig: RequestConfig, requestError: RequestError)
}