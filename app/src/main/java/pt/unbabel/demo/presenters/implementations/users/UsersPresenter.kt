package pt.unbabel.demo.presenters.implementations.users

import pt.unbabel.demo.UnbabelApplication
import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.extensions.toPostsResponseData
import pt.unbabel.demo.extensions.toUserResponseData
import pt.unbabel.demo.extensions.toUsersDbEntity
import pt.unbabel.demo.extensions.toUsersResponseData
import pt.unbabel.demo.http.entities.UserResponseData
import pt.unbabel.demo.injections.interactors.InteractorComponent
import pt.unbabel.demo.interactors.interfaces.users.IUsersInteractor
import pt.unbabel.demo.interactors.listeners.users.IUsersInteractorListener
import pt.unbabel.demo.presenters.implementations.base.BasePresenter
import pt.unbabel.demo.presenters.interfaces.users.IUsersPresenter
import pt.unbabel.demo.presenters.listeners.users.IUsersPresenterListener

class UsersPresenter(
    presenterListener: IUsersPresenterListener,
    requestContextGroup: String
) : BasePresenter<IUsersPresenterListener, IUsersInteractorListener, IUsersInteractor>(
    presenterListener,
    requestContextGroup
), IUsersPresenter, IUsersInteractorListener {

    private var userId: Int? = null

    override fun requestUser(userId: Int, requestConfig: RequestConfig) {
        internalRequestUsers(userId, requestConfig)
    }

    override fun requestUsers(requestConfig: RequestConfig) {
        internalRequestUsers(requestConfig = requestConfig)
    }

    private fun internalRequestUsers(userId: Int? = null, requestConfig: RequestConfig) {
        this.userId = userId
        tryGetUsersFromDb()
        interactor.requestUsers(requestConfig)
    }

    private fun tryGetUsersFromDb() {
        userId?.let { userId ->
            UnbabelApplication.instance.databaseManager.getUserById(userId){
                presenterListener.onRequestUserSuccess(it.toUserResponseData())
            }
        } ?: run {
            UnbabelApplication.instance.databaseManager.gerAllUsers{
                onRequestUsersSuccess(it.toUsersResponseData())
            }
        }
    }

    override fun onRequestUsersSuccess(usersResponseData: ArrayList<UserResponseData>) {
        userId?.let { userId ->
            presenterListener.onRequestUserSuccess(usersResponseData.find {
                it.id == userId
            })
        } ?: run {
            presenterListener.onRequestUsersSuccess(usersResponseData)
        }
        UnbabelApplication.instance.databaseManager.replaceUsers(usersResponseData.toUsersDbEntity())
    }

    override fun injectDependencies(component: InteractorComponent) {
        component.inject(this)
    }

    override fun getInteractorListener() = this

}