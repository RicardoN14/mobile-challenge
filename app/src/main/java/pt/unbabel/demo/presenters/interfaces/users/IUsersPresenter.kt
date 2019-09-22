package pt.unbabel.demo.presenters.interfaces.users

import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.interactors.interfaces.users.IUsersInteractor
import pt.unbabel.demo.interactors.listeners.users.IUsersInteractorListener
import pt.unbabel.demo.presenters.interfaces.base.IBasePresenter
import pt.unbabel.demo.presenters.listeners.users.IUsersPresenterListener

interface IUsersPresenter :
    IBasePresenter<IUsersPresenterListener, IUsersInteractorListener, IUsersInteractor> {

    fun requestUsers(requestConfig: RequestConfig)
    fun requestUser(userId: Int, requestConfig: RequestConfig)

}