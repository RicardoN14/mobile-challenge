package pt.unbabel.demo.ui.screens

import android.os.Bundle
import pt.unbabel.demo.R
import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.entities.requests.RequestError
import pt.unbabel.demo.http.entities.PostResponseData
import pt.unbabel.demo.presenters.implementations.posts.PostsPresenter
import pt.unbabel.demo.presenters.interfaces.posts.IPostsPresenter
import pt.unbabel.demo.presenters.listeners.posts.IPostsPresenterListener

/**
 * Created by Ricardo Neves on 17/09/2019$.
 */
class PostsScreen : Screen(), IPostsPresenterListener {

    override fun showLoader() {
    }

    override fun hideLoader() {
    }

    override fun showRootView() {
    }

    override fun hideRootView() {
    }

    override fun showError(requestConfig: RequestConfig, requestError: RequestError) {
    }

    private val postsPresenter: IPostsPresenter by lazy{
        PostsPresenter(this, this.toString())
    }

    override fun getLayoutResourceId() = R.layout.screen_posts

    override fun doInitializations(savedInstanceState: Bundle?) {
        postsPresenter.requestPosts()
    }

    override fun onRequestPostsSuccess(postsResponseData: ArrayList<PostResponseData>) {
        // TODO
    }
}