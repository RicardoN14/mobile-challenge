package pt.unbabel.demo.interactors.interfaces.users

import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.interactors.interfaces.base.IBaseInteractor
import pt.unbabel.demo.interactors.listeners.users.IUsersInteractorListener

interface IUsersInteractor : IBaseInteractor<IUsersInteractorListener> {
    fun requestUsers(requestConfig: RequestConfig)
}