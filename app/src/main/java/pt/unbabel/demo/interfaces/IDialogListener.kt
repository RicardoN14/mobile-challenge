package pt.unbabel.demo.interfaces

import android.os.Bundle
import android.view.View

/**
 * Created by Ricardo Neves on 16/09/2019.
 */
interface IDialogListener {

    /**
     * Return true if the dialog should be dismissed after user interaction
     **/
    fun onDialogPositiveButtonClicked(id: String, customView: View?, extras: Bundle? = null) = true

    /**
     * Return true if the dialog should be dismissed after user interaction
     **/
    fun onDialogNegativeButtonClicked(id: String, customView: View?, extras: Bundle? = null) = true

    fun onDialogInitCustomView(id: String, customView: View, extras: Bundle? = null) {}

    fun onDialogRootViewClicked(id: String, customView: View?, extras: Bundle? = null) {}

}