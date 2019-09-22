package pt.unbabel.demo.presenters.implementations.users

import pt.unbabel.demo.entities.requests.RequestConfig
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
        this.userId = userId
        interactor.requestUsers(requestConfig)
    }

    override fun requestUsers(requestConfig: RequestConfig) {
        userId = null
        interactor.requestUsers(requestConfig)
    }

    override fun onRequestUsersSuccess(commentsResponseData: ArrayList<UserResponseData>) {
        userId?.let { id ->
            presenterListener.onRequestUserSuccess(commentsResponseData.find {
                it.id == id
            })
        } ?: run {
            presenterListener.onRequestUsersSuccess(commentsResponseData)
        }
    }

    override fun injectDependencies(component: InteractorComponent) {
        component.inject(this)
    }

    override fun getInteractorListener() = this

}