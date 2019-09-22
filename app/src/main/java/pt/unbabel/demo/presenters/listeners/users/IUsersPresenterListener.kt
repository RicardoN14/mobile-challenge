package pt.unbabel.demo.presenters.listeners.users

import pt.unbabel.demo.http.entities.UserResponseData
import  pt.unbabel.demo.presenters.listeners.base.IPresenterListener

interface IUsersPresenterListener : IPresenterListener {
    fun onRequestUsersSuccess(commentsResponseData: ArrayList<UserResponseData>){}
    fun onRequestUserSuccess(userResponseData: UserResponseData?){}
}