package pt.unbabel.demo.http.managers

import pt.unbabel.demo.entities.requests.RequestConfig
import retrofit2.Call

/**
 * Created by Ricardo Neves on 21/09/2019.
 */
class RequestsManager {

    data class RequestInfo(val requestConfig: RequestConfig, val request: Call<*>)
    data class FailedRequestInfo(val id: String, val requestConfig: RequestConfig)

    /**
     * <RequestContext><<RequestId, RequestInfo>>
     * */
    private val runningRequests: HashMap<String, HashMap<String, RequestInfo>> = HashMap()

    /**
     * Only contains the requests that failed and can be repeated
     * <RequestContext><List<FailedRequestInfo>>
     * */
    private val failedRequests: HashMap<String, ArrayList<FailedRequestInfo>> = HashMap()

    fun addRunningRequest(requestContext: String, requestInfo: RequestInfo) {

        var requests = runningRequests[requestContext]

        if (requests == null) {
            requests = HashMap()
        }

        requests[requestInfo.requestConfig.requestId] = requestInfo

        runningRequests[requestContext] = requests
    }

    fun getRunningRequests(requestContext: String): Collection<RequestInfo>? {
        return runningRequests[requestContext]?.values
    }

    fun removeRunningRequest(requestContext: String, requestId: String) {
        runningRequests[requestContext]?.remove(requestId)
    }

    fun addFailedRequest(requestContext: String, failedRequestInfo: FailedRequestInfo) {

        var requests = failedRequests[requestContext]

        if (requests == null) {
            requests = arrayListOf()
        }

        requests.add(failedRequestInfo)

        failedRequests[requestContext] = requests
    }

    fun removeFailedRequest(requestContext: String, failedRequestInfo: FailedRequestInfo) {
        failedRequests[requestContext]?.remove(failedRequestInfo)
    }

    fun removeFailedRequests(requestContext: String){
        failedRequests.remove(requestContext)
    }

    fun getFailedRequests(requestContext: String): ArrayList<FailedRequestInfo>? {
        return failedRequests[requestContext]
    }

}