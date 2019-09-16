package pt.unbabel.demo.interactors.interfaces.base
 
import pt.unbabel.demo.interactors.listeners.base.IInteractorListener

/**
 * Created by Ricardo Neves on 16/09/2019$.
 */

interface IBaseInteractor<IL : IInteractorListener>{

    var requestContextGroup: String?
    var interactorListener: IL

    fun getRequestContext() = toString()

    fun cancelAllRunningRequests()
    fun retryFailedRequests()

}