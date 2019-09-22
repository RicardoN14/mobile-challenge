package pt.unbabel.demo.interactors.server.users

import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.http.managers.RequestsManager
import pt.unbabel.demo.http.retrofit.UsersService
import pt.unbabel.demo.interactors.interfaces.users.IUsersInteractor
import pt.unbabel.demo.interactors.listeners.users.IUsersInteractorListener
import pt.unbabel.demo.interactors.server.base.ServerInteractor

class UsersServerInteractor : ServerInteractor<IUsersInteractorListener>(), IUsersInteractor {

    companion object{
        private const val REQUEST_USERS_ID = "UsersServerInteractor::RequestUserId"

        val usersService: UsersService by lazy{
            retrofit.create(UsersService::class.java)
        }
    }

    override fun requestUsers(requestConfig: RequestConfig) {
        executeRequest(REQUEST_USERS_ID, requestConfig, usersService.getUsers()){
            interactorListener.onRequestUsersSuccess(it)
        }
    }

    override fun retryRequest(failedRequestInfo: RequestsManager.FailedRequestInfo) {
        if(failedRequestInfo.id == REQUEST_USERS_ID){
            requestUsers(failedRequestInfo.requestConfig)
        }
    }

}