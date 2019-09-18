package pt.unbabel.demo.alias

import pt.unbabel.demo.interactors.interfaces.base.IBaseInteractor
import pt.unbabel.demo.interactors.listeners.base.IInteractorListener
import pt.unbabel.demo.presenters.interfaces.base.IBasePresenter
import pt.unbabel.demo.presenters.listeners.base.IPresenterListener

/**
 * Created by Ricardo Neves on 17/09/2019.
 */

typealias PresentersArrayList = ArrayList<IBasePresenter<IPresenterListener,
        out IInteractorListener, out IBaseInteractor<out IInteractorListener>>>