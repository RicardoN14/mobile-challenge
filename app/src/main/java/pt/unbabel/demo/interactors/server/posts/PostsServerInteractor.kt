package pt.unbabel.demo.interactors.server.posts

import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.entities.requests.RequestError
import pt.unbabel.demo.entities.requests.RequestErrorType
import pt.unbabel.demo.http.entities.PostResponseData
import pt.unbabel.demo.http.retrofit.PostsService
import pt.unbabel.demo.interactors.interfaces.posts.IPostsInteractor
import pt.unbabel.demo.interactors.listeners.posts.IPostsInteractorListener
import pt.unbabel.demo.interactors.server.base.ServerInteractor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsServerInteractor : ServerInteractor<IPostsInteractorListener>(), IPostsInteractor {

    companion object {
        val postsService: PostsService by lazy {
            retrofit.create(PostsService::class.java)
        }
    }

    /** Save object when an error occur to be used for the retry **/
    private var requestPostsRequestConfig: RequestConfig? = null

    /** Running requests **/
    private var requestPostsCall: Call<ArrayList<PostResponseData>>? = null

    override fun requestPosts(requestConfig: RequestConfig?) {
        interactorListener.showLoader()

        postsService.getPosts().apply {
            requestPostsCall = this
            enqueue(getRequestPostsListener(requestConfig))
        }
    }

    private fun getRequestPostsListener(requestConfig: RequestConfig?) =
        object : Callback<ArrayList<PostResponseData>> {
            override fun onFailure(call: Call<ArrayList<PostResponseData>>, t: Throwable) {
                requestPostsCall = null // not running anymore

                // TODO: make this generic for all requests

                if (call.isCanceled) {
                    return
                }

                requestPostsRequestConfig = requestConfig
                interactorListener.hideLoader()
                interactorListener.showError(
                    requestConfig ?: RequestConfig(),
                    RequestError(RequestErrorType.INTERNET_CONNECTION_ERROR)
                )
            }

            override fun onResponse(
                call: Call<ArrayList<PostResponseData>>,
                response: Response<ArrayList<PostResponseData>>
            ) {
                requestPostsCall = null // not running anymore

                // TODO: make this generic for all requests

                if (call.isCanceled) {
                    return
                }

                interactorListener.hideLoader()

                if (response.isSuccessful) {
                    requestPostsRequestConfig = null
                    interactorListener.onRequestPostsSuccess(response.body() ?: arrayListOf())
                } else {
                    requestPostsRequestConfig = requestConfig
                    interactorListener.showError(
                        requestConfig ?: RequestConfig(),
                        RequestError(
                            RequestErrorType.SERVER_ERROR,
                            responseCode = response.code()
                        )
                    )
                }
            }

        }

    override fun retryFailedRequests() {
        super.retryFailedRequests()
        requestPostsRequestConfig?.let {
            requestPosts(it)
        }
    }

    override fun cancelAllRunningRequests() {
        super.cancelAllRunningRequests()
        requestPostsCall?.cancel()
    }

}