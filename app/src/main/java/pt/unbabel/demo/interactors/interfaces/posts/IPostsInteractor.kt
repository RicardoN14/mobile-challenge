package pt.unbabel.demo.interactors.interfaces.posts

import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.interactors.interfaces.base.IBaseInteractor
import pt.unbabel.demo.interactors.listeners.posts.IPostsInteractorListener

interface IPostsInteractor : IBaseInteractor<IPostsInteractorListener> {
    fun requestPosts(requestConfig: RequestConfig)
}