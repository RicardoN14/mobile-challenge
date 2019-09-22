package pt.unbabel.demo.interactors.listeners.users

import pt.unbabel.demo.http.entities.UserResponseData
import pt.unbabel.demo.interactors.listeners.base.IInteractorListener

interface IUsersInteractorListener : IInteractorListener {
    fun onRequestUsersSuccess(commentsResponseData: ArrayList<UserResponseData>)
}