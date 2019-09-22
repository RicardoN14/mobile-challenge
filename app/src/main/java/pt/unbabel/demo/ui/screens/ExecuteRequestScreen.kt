package pt.unbabel.demo.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.loader.*
import kotlinx.android.synthetic.main.screen_execute_request.*
import pt.unbabel.demo.R
import pt.unbabel.demo.alias.PresentersArrayList
import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.entities.requests.RequestError
import pt.unbabel.demo.extensions.gone
import pt.unbabel.demo.extensions.visible
import pt.unbabel.demo.helpers.handleError
import pt.unbabel.demo.managers.ErrorStateManager
import pt.unbabel.demo.presenters.listeners.base.IPresenterListener
import pt.unbabel.demo.utils.DLog

/**
 * Created by Ricardo Neves on 18/09/2019.
 */
abstract class ExecuteRequestScreen : Screen(), IPresenterListener {

    companion object {
        const val ERROR_DIALOG_ID = "ExecuteRequestScreen::ErrorDialogId"
    }

    private lateinit var implementationRootView: View
    private lateinit var presenters: PresentersArrayList

    /** Managers **/
    protected open val errorStateManager by lazy {
        ErrorStateManager(this, screenExecuteRequestErrorStateView,
            onRetryButtonClickedAction = { onRetryButtonClicked() })
    }

    protected abstract fun initPresenters(presenters: PresentersArrayList, requestContextGroup: String)
    protected abstract fun getExecuteRequestLayoutResourceId(): Int
    protected abstract fun doExecuteRequestInitializations(savedInstanceState: Bundle?)

    final override fun doInitializations(savedInstanceState: Bundle?) {
        presenters = ArrayList()
        initPresenters(presenters, getRequestContextGroup())

        implementationRootView = LayoutInflater.from(this).inflate(getExecuteRequestLayoutResourceId(),
            screenExecuteRequestRootView, false)

        screenExecuteRequestRootView.addView(implementationRootView, 0)

        doExecuteRequestInitializations(savedInstanceState)
    }

    final override fun getLayoutResourceId() = R.layout.screen_execute_request

    protected open fun getRequestContextGroup() = toString()

    override fun showLoader(requestConfig: RequestConfig) {
        loader?.visibility = View.VISIBLE
    }

    override fun hideLoader(requestConfig: RequestConfig) {
        loader?.visibility = View.GONE
    }

    private fun cancelAllRunningRequests() {
        presenters.forEach {
            it.cancelAllRunningRequests()
        }
    }

    private fun onRetryButtonClicked() {
        errorStateManager.hide()
        presenters.forEach {
            it.retryFailedRequests()
        }
    }

    override fun showError(requestConfig: RequestConfig, requestError: RequestError) {
        DLog.e(logTag, "requestConfig = $requestConfig requestError = $requestError")
        handleError(this, requestConfig, requestError, errorStateManager)
    }

    override fun hideRootView(requestConfig: RequestConfig) {
        implementationRootView.gone()
        screenExecuteRequestErrorStateView.gone()
    }

    override fun showRootView(requestConfig: RequestConfig) {
        implementationRootView.visible()
        if (errorStateManager.isShowingError) {
            screenExecuteRequestErrorStateView.visible()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        cancelAllRunningRequests()
    }

}