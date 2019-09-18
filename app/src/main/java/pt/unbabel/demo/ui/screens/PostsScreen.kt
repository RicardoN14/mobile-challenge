package pt.unbabel.demo.ui.screens

import android.os.Bundle
import pt.unbabel.demo.R
import pt.unbabel.demo.alias.PresentersArrayList
import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.entities.requests.RequestError
import pt.unbabel.demo.http.entities.PostResponseData
import pt.unbabel.demo.presenters.implementations.posts.PostsPresenter
import pt.unbabel.demo.presenters.interfaces.posts.IPostsPresenter
import pt.unbabel.demo.presenters.listeners.posts.IPostsPresenterListener

/**
 * Created by Ricardo Neves on 17/09/2019.
 */
class PostsScreen : ExecuteRequestScreen(), IPostsPresenterListener {

    private lateinit var postsPresenter: IPostsPresenter

    override fun initPresenters(presenters: PresentersArrayList, requestContextGroup: String) {
        postsPresenter = PostsPresenter(this, requestContextGroup)
        presenters += postsPresenter
    }

    override fun getExecuteRequestLayoutResourceId() = R.layout.screen_posts

    override fun doExecuteRequestInitializations(savedInstanceState: Bundle?) {
        postsPresenter.requestPosts(RequestConfig(showErrorStateView = true))
    }

    override fun onRequestPostsSuccess(postsResponseData: ArrayList<PostResponseData>) {
        // TODO
    }
}