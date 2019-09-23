package pt.unbabel.demo.ui.screens

import android.os.Bundle
import kotlinx.android.synthetic.main.screen_post_details.*
import org.jetbrains.anko.toast
import pt.unbabel.demo.R
import pt.unbabel.demo.alias.PresentersArrayList
import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.http.entities.CommentResponseData
import pt.unbabel.demo.http.entities.PostResponseData
import pt.unbabel.demo.http.entities.UserResponseData
import pt.unbabel.demo.presenters.implementations.comments.CommentsPresenter
import pt.unbabel.demo.presenters.implementations.users.UsersPresenter
import pt.unbabel.demo.presenters.interfaces.comments.ICommentsPresenter
import pt.unbabel.demo.presenters.interfaces.users.IUsersPresenter
import pt.unbabel.demo.presenters.listeners.comments.ICommentsPresenterListener
import pt.unbabel.demo.presenters.listeners.users.IUsersPresenterListener

/**
 * Created by Ricardo Neves on 19/09/2019.
 */
class PostDetailsScreen : ExecuteRequestScreen(),
    ICommentsPresenterListener, IUsersPresenterListener {

    companion object {
        const val POST_RESPONSE_DATA_EXTRA_KEY = "PostDetailsScreen::PostResponseDataExtraKey"

        private const val REQUEST_COMMENTS_ID = "PostDetailsScreen::RequestCommentsId"
        private const val REQUEST_USER_ID = "PostDetailsScreen::RequestUserId"
    }

    private var commentsResponseData: ArrayList<CommentResponseData>? by instanceState()
    private var userResponseData: UserResponseData? by instanceState()

    private lateinit var commentsPresenter: ICommentsPresenter
    private lateinit var usersPresenter: IUsersPresenter

    private val postResponseData: PostResponseData by lazy {
        intent.getParcelableExtra<PostResponseData>(POST_RESPONSE_DATA_EXTRA_KEY)
    }

    override fun initPresenters(presenters: PresentersArrayList, requestContextGroup: String) {
        commentsPresenter = CommentsPresenter(this, requestContextGroup)
        usersPresenter = UsersPresenter(this, requestContextGroup)
        presenters += commentsPresenter
        presenters += usersPresenter
    }

    override fun getExecuteRequestLayoutResourceId() = R.layout.screen_post_details

    override fun doExecuteRequestInitializations(savedInstanceState: Bundle?) {
        postDetailsInformationCardView.setTitle(postResponseData.title)
        postDetailsDescriptionInformationView.setValue(postResponseData.description)

        commentsResponseData?.let {
            onRequestCommentsSuccess(it)
        } ?: run {
            commentsPresenter.requestComments(
                postResponseData.id, RequestConfig(
                    requestId = REQUEST_COMMENTS_ID,
                    showError = false
                )
            )
        }

        userResponseData?.let {
            onRequestUserSuccess(it)
        } ?: run {
            postResponseData.userId?.let {
                usersPresenter.requestUser(
                    it, RequestConfig(
                        requestId = REQUEST_USER_ID,
                        showError = false
                    )
                )
            }
        }

    }

    override fun onRequestCommentsSuccess(commentsResponseData: ArrayList<CommentResponseData>) {
        this.commentsResponseData = commentsResponseData
        postDetailsNumberOfCommentsInformationView.setValue(commentsResponseData.size.toString())
    }

    override fun onRequestUserSuccess(userResponseData: UserResponseData?) {
        this.userResponseData = userResponseData
        postDetailsAuthorInformationView.setValue(userResponseData?.name)
    }

}