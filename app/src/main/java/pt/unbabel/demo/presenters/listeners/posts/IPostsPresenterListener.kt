package pt.unbabel.demo.presenters.listeners.posts

import pt.unbabel.demo.http.entities.PostResponseData
import  pt.unbabel.demo.presenters.listeners.base.IPresenterListener

interface IPostsPresenterListener : IPresenterListener {
    fun onRequestPostsSuccess(postsResponseData: ArrayList<PostResponseData>)
}