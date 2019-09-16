package pt.unbabel.demo.presenters.interfaces.posts

import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.interactors.interfaces.posts.IPostsInteractor
import pt.unbabel.demo.interactors.listeners.posts.IPostsInteractorListener
import pt.unbabel.demo.presenters.interfaces.base.IBasePresenter
import pt.unbabel.demo.presenters.listeners.posts.IPostsPresenterListener

interface IPostsPresenter :
    IBasePresenter<IPostsPresenterListener, IPostsInteractorListener, IPostsInteractor> {

    fun requestPosts(requestConfig: RequestConfig? = null)

}