package pt.unbabel.demo.managers

import pt.unbabel.demo.ui.screens.Screen

/**
 * Created by Ricardo Neves on 16/09/2019.
 */
abstract class BaseManager<out S : Screen> (val screen : S){

    protected val logTag: String = javaClass.simpleName

}