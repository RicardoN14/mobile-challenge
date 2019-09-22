package pt.unbabel.demo.presenters.interfaces.comments

import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.interactors.interfaces.comments.ICommentsInteractor
import pt.unbabel.demo.interactors.listeners.comments.ICommentsInteractorListener
import pt.unbabel.demo.presenters.interfaces.base.IBasePresenter
import pt.unbabel.demo.presenters.listeners.comments.ICommentsPresenterListener

interface ICommentsPresenter :
    IBasePresenter<ICommentsPresenterListener, ICommentsInteractorListener, ICommentsInteractor> {

    fun requestComments(postId: Int? = null, requestConfig: RequestConfig)

}