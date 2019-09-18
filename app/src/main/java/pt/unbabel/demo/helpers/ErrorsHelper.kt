package pt.unbabel.demo.helpers

import pt.unbabel.demo.R
import pt.unbabel.demo.entities.dialogs.DialogInfo
import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.entities.requests.RequestError
import pt.unbabel.demo.entities.requests.RequestErrorType
import pt.unbabel.demo.http.entities.HeaderResponseData
import pt.unbabel.demo.managers.ErrorStateManager
import pt.unbabel.demo.ui.screens.ExecuteRequestScreen
import pt.unbabel.demo.ui.screens.Screen

/**
 * Created by Ricardo Neves on 18/09/2019.
 */

fun handleError(screen: Screen, requestConfig: RequestConfig,
                requestError: RequestError, errorStateManager: ErrorStateManager
) {

    if (!requestConfig.showError) {
        return
    }

    val message = when (requestError.requestErrorType) {
        RequestErrorType.SERVER_ERROR -> {

            val headerResponseData = requestError.headerResponseData

            if (headerResponseData != null && tryHandleSpecificErrorCode(screen,
                    requestError.headerResponseData)) {
                return
            }

            screen.getString(R.string.error_server_message, requestError.responseCode)
        }

        RequestErrorType.TIMEOUT_ERROR -> {
            screen.getString(R.string.error_timeout_message)
        }

        RequestErrorType.PARSE_ERROR -> {
            screen.getString(R.string.error_parse_message)
        }

        RequestErrorType.SSL_ERROR -> {
            screen.getString(R.string.error_ssl_connection_message)
        }

        else -> /* RequestErrorType.INTERNET_CONNECTION_ERROR */ {
            screen.getString(R.string.error_internet_connection_message)
        }
    }

    handleError(screen, requestConfig, message, errorStateManager)
}

/** Return true if specific error code was consumed **/
fun tryHandleSpecificErrorCode(screen: Screen, headerResponseData: HeaderResponseData): Boolean {
    return false
}

fun showDialogError(screen: Screen, message: String?,
                    dialogId: String, isCancelable: Boolean = true) {
    screen.showDialog(dialogId, DialogInfo(
        positiveLabel = screen.getString(R.string.error_dialog_ok),
        title = screen.getString(R.string.error_dialog_title),
        message = message,
        isCancelable = isCancelable)
    )
}

fun handleError(screen: Screen, requestConfig: RequestConfig,
                message: String, errorStateManager: ErrorStateManager) {
    if (requestConfig.showErrorStateView) {
        errorStateManager.setMessage(message)
        errorStateManager.show()
    } else {
        showDialogError(screen, message, ExecuteRequestScreen.ERROR_DIALOG_ID)
    }
}