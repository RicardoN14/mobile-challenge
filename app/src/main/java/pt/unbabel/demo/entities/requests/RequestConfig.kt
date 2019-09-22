package pt.unbabel.demo.entities.requests

/**
 * Created by Ricardo Neves on 16/09/2019.
 */

/**
 * requestId: Id for the request - must be unique for each requestContext
 * showLoader: If true, the loader is visible when request is running
 * showError: If true, handle error is done. If false the error is never shown to the user.
 * showErrorStateView: If true the error (if showError = true) is shown in layout with retry button
 *     If false is shown in dialog
 * canRetry: If true the request can be repeated
 */
class RequestConfig(var requestId: String,
                    var showLoader: Boolean = true,
                    var showError: Boolean = true,
                    var showErrorStateView: Boolean = false,
                    var canRetry: Boolean = true)