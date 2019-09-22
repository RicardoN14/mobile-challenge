package pt.unbabel.demo.presenters.implementations.posts

import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.http.entities.PostResponseData
import pt.unbabel.demo.injections.interactors.InteractorComponent
import pt.unbabel.demo.interactors.interfaces.posts.IPostsInteractor
import pt.unbabel.demo.interactors.listeners.posts.IPostsInteractorListener
import pt.unbabel.demo.presenters.implementations.base.BasePresenter
import pt.unbabel.demo.presenters.interfaces.posts.IPostsPresenter
import pt.unbabel.demo.presenters.listeners.posts.IPostsPresenterListener

class PostsPresenter(
    presenterListener: IPostsPresenterListener,
    requestContextGroup: String
) : BasePresenter<IPostsPresenterListener, IPostsInteractorListener, IPostsInteractor>(
    presenterListener,
    requestContextGroup
), IPostsPresenter, IPostsInteractorListener {

    override fun injectDependencies(component: InteractorComponent) {
        component.inject(this)
    }

    override fun getInteractorListener() = this

    override fun requestPosts(requestConfig: RequestConfig) {
        interactor.requestPosts(requestConfig)
    }

    override fun onRequestPostsSuccess(postsResponseData: ArrayList<PostResponseData>) {
        presenterListener.onRequestPostsSuccess(postsResponseData)
    }

}