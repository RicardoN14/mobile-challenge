package pt.unbabel.demo.presenters.listeners.comments

import pt.unbabel.demo.http.entities.CommentResponseData
import  pt.unbabel.demo.presenters.listeners.base.IPresenterListener

interface ICommentsPresenterListener : IPresenterListener {
    fun onRequestCommentsSuccess(commentsResponseData: ArrayList<CommentResponseData>)
}