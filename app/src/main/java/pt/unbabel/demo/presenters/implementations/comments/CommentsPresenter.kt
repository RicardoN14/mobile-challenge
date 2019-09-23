package pt.unbabel.demo.presenters.implementations.comments

import pt.unbabel.demo.UnbabelApplication
import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.extensions.toCommentsDbEntity
import pt.unbabel.demo.extensions.toCommentsResponseData
import pt.unbabel.demo.http.entities.CommentResponseData
import pt.unbabel.demo.injections.interactors.InteractorComponent
import pt.unbabel.demo.interactors.interfaces.comments.ICommentsInteractor
import pt.unbabel.demo.interactors.listeners.comments.ICommentsInteractorListener
import pt.unbabel.demo.presenters.implementations.base.BasePresenter
import pt.unbabel.demo.presenters.interfaces.comments.ICommentsPresenter
import pt.unbabel.demo.presenters.listeners.comments.ICommentsPresenterListener

class CommentsPresenter(
    presenterListener: ICommentsPresenterListener,
    requestContextGroup: String
) : BasePresenter<ICommentsPresenterListener, ICommentsInteractorListener, ICommentsInteractor>(
    presenterListener,
    requestContextGroup
), ICommentsPresenter, ICommentsInteractorListener {

    private var postId: Int? = null

    override fun requestComments(postId: Int?, requestConfig: RequestConfig) {
        this.postId = postId
        tryGetCommentsFromDb()
        interactor.requestComments(requestConfig)
    }

    private fun tryGetCommentsFromDb() {
        postId?.let { postId ->
            UnbabelApplication.instance.databaseManager.getAllPostComments(postId) {
                onRequestCommentsSuccess(it.toCommentsResponseData())
            }
        } ?: run {
            UnbabelApplication.instance.databaseManager.getAllComments {
                onRequestCommentsSuccess(it.toCommentsResponseData())
            }
        }
    }

    override fun onRequestCommentsSuccess(commentsResponseData: ArrayList<CommentResponseData>) {
        presenterListener.onRequestCommentsSuccess(filterComments(commentsResponseData))
        UnbabelApplication.instance.databaseManager.replaceComments(commentsResponseData.toCommentsDbEntity())
    }

    private fun filterComments(commentsResponseData: ArrayList<CommentResponseData>): ArrayList<CommentResponseData> {
        return postId?.let { postId ->
            commentsResponseData.filter {
                it.postId == postId
            } as ArrayList<CommentResponseData>
        } ?: commentsResponseData
    }

    override fun injectDependencies(component: InteractorComponent) {
        component.inject(this)
    }

    override fun getInteractorListener() = this

}