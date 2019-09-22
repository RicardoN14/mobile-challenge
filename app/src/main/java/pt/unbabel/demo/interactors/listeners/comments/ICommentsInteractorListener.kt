package pt.unbabel.demo.interactors.listeners.comments

import pt.unbabel.demo.http.entities.CommentResponseData
import pt.unbabel.demo.interactors.listeners.base.IInteractorListener

interface ICommentsInteractorListener : IInteractorListener {
    fun onRequestCommentsSuccess(commentsResponseData: ArrayList<CommentResponseData>)
}