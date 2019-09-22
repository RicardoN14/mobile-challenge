package pt.unbabel.demo.interactors.interfaces.comments

import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.interactors.interfaces.base.IBaseInteractor
import pt.unbabel.demo.interactors.listeners.comments.ICommentsInteractorListener

interface ICommentsInteractor : IBaseInteractor<ICommentsInteractorListener> {
    fun requestComments(requestConfig: RequestConfig)
}