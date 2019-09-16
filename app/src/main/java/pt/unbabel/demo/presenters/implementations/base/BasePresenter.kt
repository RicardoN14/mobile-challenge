package pt.unbabel.demo.presenters.implementations.base

import pt.unbabel.demo.injections.interactors.DaggerInteractorComponent
import pt.unbabel.demo.injections.interactors.InteractorComponent
import pt.unbabel.demo.interactors.interfaces.base.IBaseInteractor
import pt.unbabel.demo.interactors.listeners.base.IInteractorListener
import pt.unbabel.demo.presenters.interfaces.base.IBasePresenter
import pt.unbabel.demo.presenters.listeners.base.IPresenterListener
//import pt.unbabel.demo.injections.interactors.DaggerInteractorComponent
import javax.inject.Inject

/**
 * Created by Ricardo Neves on 16/09/2019$.
 */

abstract class BasePresenter<out PL : IPresenterListener, IL : IInteractorListener,
        I : IBaseInteractor<IL>>(override val presenterListener: PL,
                                 override val requestContextGroup: String)
    : IBasePresenter<PL, IL, I>, IInteractorListener {

    protected val logTag: String = javaClass.simpleName

    @Inject override lateinit var interactor: I // Injected by Dagger

    abstract fun injectDependencies(component: InteractorComponent)
    abstract fun getInteractorListener(): IL

    init {
        init()
    }

    private fun init() {
        injectDependencies(DaggerInteractorComponent.create())
        interactor.interactorListener = getInteractorListener()
        interactor.requestContextGroup = requestContextGroup
    }

}