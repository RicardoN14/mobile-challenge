package pt.unbabel.demo.interactors.listeners.posts

import pt.unbabel.demo.http.entities.PostResponseData
import pt.unbabel.demo.interactors.listeners.base.IInteractorListener

interface IPostsInteractorListener : IInteractorListener {
    fun onRequestPostsSuccess(postsResponseData: ArrayList<PostResponseData>)
}