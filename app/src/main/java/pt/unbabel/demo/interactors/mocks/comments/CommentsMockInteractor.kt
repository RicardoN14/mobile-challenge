package pt.unbabel.demo.interactors.mocks.comments

import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.http.entities.CommentResponseData
import pt.unbabel.demo.interactors.interfaces.comments.ICommentsInteractor
import pt.unbabel.demo.interactors.listeners.comments.ICommentsInteractorListener
import pt.unbabel.demo.interactors.mocks.base.MockInteractor

class CommentsMockInteractor : MockInteractor<ICommentsInteractorListener>(), ICommentsInteractor {

    override fun requestComments(requestConfig: RequestConfig) {
        executeWithDelay({
            interactorListener.onRequestCommentsSuccess(getCommentsResponseData())
        }, requestConfig = requestConfig)
    }

    private fun getCommentsResponseData() = arrayListOf<CommentResponseData>().apply {

        for (i in 1..5) {
            add(CommentResponseData(1, i, "name$i", "email$i", "description$i"))
        }

        for (i in 6..10) {
            add(CommentResponseData(2, i, "name$i", "email$i", "description$i"))
        }
    }

}