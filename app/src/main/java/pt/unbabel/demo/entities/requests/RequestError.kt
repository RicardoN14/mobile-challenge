package pt.unbabel.demo.entities.requests

import pt.unbabel.demo.http.entities.HeaderResponseData

/**
 * Created by Ricardo Neves on 16/09/2019.
 */

class RequestError(val requestErrorType: RequestErrorType,
                   val headerResponseData: HeaderResponseData? = null,
                   val responseCode: Int = -1)