package pt.unbabel.demo.interactors.server.posts

import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.entities.requests.RequestError
import pt.unbabel.demo.entities.requests.RequestErrorType
import pt.unbabel.demo.http.entities.PostResponseData
import pt.unbabel.demo.http.managers.RequestsManager
import pt.unbabel.demo.http.retrofit.PostsService
import pt.unbabel.demo.interactors.interfaces.posts.IPostsInteractor
import pt.unbabel.demo.interactors.listeners.posts.IPostsInteractorListener
import pt.unbabel.demo.interactors.server.base.ServerInteractor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.TimeoutException
import javax.net.ssl.SSLException

class PostsServerInteractor : ServerInteractor<IPostsInteractorListener>(), IPostsInteractor {

    companion object {
        private const val REQUEST_POSTS_ID = "PostsServerInteractor::RequestPostsId"

        val postsService: PostsService by lazy {
            retrofit.create(PostsService::class.java)
        }
    }

    override fun requestPosts(requestConfig: RequestConfig) {
        executeRequest(REQUEST_POSTS_ID, requestConfig, postsService.getPosts()){
            interactorListener.onRequestPostsSuccess(it)
        }
    }

    override fun retryRequest(failedRequestInfo: RequestsManager.FailedRequestInfo) {
        if(failedRequestInfo.id == REQUEST_POSTS_ID){
            requestPosts(failedRequestInfo.requestConfig)
        }
    }

}