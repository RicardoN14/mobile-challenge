package pt.unbabel.demo.ui.screens

import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.screen_posts.*
import pt.unbabel.demo.R
import pt.unbabel.demo.adapters.PostsAdapter
import pt.unbabel.demo.alias.PresentersArrayList
import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.entities.requests.RequestError
import pt.unbabel.demo.extensions.setLinearAdapter
import pt.unbabel.demo.http.entities.PostResponseData
import pt.unbabel.demo.presenters.implementations.posts.PostsPresenter
import pt.unbabel.demo.presenters.interfaces.posts.IPostsPresenter
import pt.unbabel.demo.presenters.listeners.posts.IPostsPresenterListener

/**
 * Created by Ricardo Neves on 17/09/2019.
 */
class PostsScreen : ExecuteRequestScreen(), IPostsPresenterListener {

    companion object {
        private const val REQUEST_POSTS_ID = "PostsScreen::RequestPostsId"
    }

    private lateinit var postsPresenter: IPostsPresenter

    private var postsResponseData: ArrayList<PostResponseData>? by instanceState()
    private var postsAdapter: PostsAdapter? = null

    override fun initPresenters(presenters: PresentersArrayList, requestContextGroup: String) {
        postsPresenter = PostsPresenter(this, requestContextGroup)
        presenters += postsPresenter
    }

    override fun getExecuteRequestLayoutResourceId() = R.layout.screen_posts

    override fun doExecuteRequestInitializations(savedInstanceState: Bundle?) {
        initPostAdapter()

        postsResponseData?.let {
            onRequestPostsSuccess(it)
        } ?: postsPresenter.requestPosts(
            RequestConfig(
                requestId = REQUEST_POSTS_ID,
                showErrorStateView = true
            )
        )
    }

    override fun onRequestPostsSuccess(postsResponseData: ArrayList<PostResponseData>) {
        errorStateManager.hide()
        this.postsResponseData = postsResponseData
        postsAdapter?.updateItems(postsResponseData)
    }

    private fun initPostAdapter() {
        postsAdapter = PostsAdapter(this).apply {
            postsRecyclerView.setLinearAdapter(
                this,
                itemDecoration = DividerItemDecoration(
                    this@PostsScreen,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun showError(requestConfig: RequestConfig, requestError: RequestError) {
        if(requestConfig.requestId == REQUEST_POSTS_ID && postsResponseData != null){
            // Don't show error when we have information to show
            return
        }
        super.showError(requestConfig, requestError)
    }

}