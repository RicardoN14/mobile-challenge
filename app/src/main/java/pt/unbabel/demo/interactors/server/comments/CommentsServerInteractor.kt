package pt.unbabel.demo.interactors.server.comments

import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.http.entities.CommentResponseData
import pt.unbabel.demo.http.entities.PostResponseData
import pt.unbabel.demo.http.managers.RequestsManager
import pt.unbabel.demo.http.retrofit.CommentsService
import pt.unbabel.demo.interactors.interfaces.comments.ICommentsInteractor
import pt.unbabel.demo.interactors.listeners.comments.ICommentsInteractorListener
import pt.unbabel.demo.interactors.server.base.ServerInteractor
import retrofit2.Call

class CommentsServerInteractor : ServerInteractor<ICommentsInteractorListener>(),
    ICommentsInteractor {

    companion object{
        private const val REQUEST_COMMENTS_ID = "CommentsServerInteractor::RequestCommentsId"

        val commentsService: CommentsService by lazy{
            retrofit.create(CommentsService::class.java)
        }
    }

    override fun requestComments(requestConfig: RequestConfig) {
        executeRequest(REQUEST_COMMENTS_ID, requestConfig, commentsService.getComments()){
            interactorListener.onRequestCommentsSuccess(it)
        }
    }

    override fun retryRequest(failedRequestInfo: RequestsManager.FailedRequestInfo) {
        if(failedRequestInfo.id == REQUEST_COMMENTS_ID){
            requestComments(failedRequestInfo.requestConfig)
        }
    }

}