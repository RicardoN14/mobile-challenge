package pt.unbabel.demo.entities.requests

/**
 * Created by Ricardo Neves on 16/09/2019$.
 */

class RequestError(val requestErrorType: RequestErrorType,
                   val responseCode: Int = 0)