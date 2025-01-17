package pt.unbabel.demo.ui.screens

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.screen_base.*
import pt.unbabel.demo.R
import pt.unbabel.demo.entities.dialogs.DialogInfo
import pt.unbabel.demo.interfaces.IDialogListener
import pt.unbabel.demo.managers.DialogManager
import pt.unbabel.demo.utils.DLog
import pt.unbabel.demo.utils.InstanceStateProvider

/**
 * Created by Ricardo Neves on 15/09/2019$.
 */
abstract class Screen : AppCompatActivity(), IDialogListener {

    companion object{
        private const val SAVABLE_KEY = "SavableKey"
    }

    protected val logTag: String = javaClass.simpleName

    val inflater: LayoutInflater by lazy {
        LayoutInflater.from(this)
    }

    private val dialogManager by lazy {
        DialogManager(this)
    }

    private val savable = Bundle()

    var isInBackground = false
        private set

    /**
     * Implementations must return here the Screen layout resource file
     * */
    abstract fun getLayoutResourceId(): Int

    /**
     * Implementations must use this function to
     * do the initializations (for example add listeners to views)
     *
     * This is called after the layout inflate
     */
    abstract fun doInitializations(savedInstanceState: Bundle?)

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DLog.d(logTag, "onCreate bundle = $savedInstanceState")

        setContentView(R.layout.screen_base)

        inflateLayout()

        savedInstanceState?.let {
            savable.putAll(it.getBundle(SAVABLE_KEY))
        }

        doInitializations(savedInstanceState)
    }

    private fun inflateLayout() {
        val view = LayoutInflater.from(this).inflate(getLayoutResourceId(),
            screenRootLayout, false)

        screenRootLayout.addView(view, 0, screenRootLayout.layoutParams)
    }

    override fun onStart() {
        super.onStart()
        DLog.d(logTag, "onStart")
    }

    override fun onResume() {
        super.onResume()
        DLog.d(logTag, "onResume")
        isInBackground = false
    }

    override fun onPause() {
        super.onPause()
        DLog.d(logTag, "onPause")
    }

    override fun onStop() {
        super.onStop()
        isInBackground = true
        DLog.d(logTag, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        DLog.d(logTag, "onDestroy")
    }

    /** Dialog Public Functions **/
    fun showDialog(dialogId: String, dialogInfo: DialogInfo, extras: Bundle? = null,
                   dialogListener: IDialogListener? = null) {
        dialogManager.showDialog(dialogId, dialogInfo,
            dialogListener ?: this, extras)
    }

    fun hideCurrentDialog(){
        dialogManager.hideDialog(false)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBundle(SAVABLE_KEY, savable)
        super.onSaveInstanceState(outState)
    }

    fun <T> instanceState() = InstanceStateProvider.Nullable<T>(savable)
    fun <T> instanceState(defaultValue: T) = InstanceStateProvider.NotNull(savable, defaultValue)

    /**
     * This function must be override if implementations
     * need to do something in reaction to Back Pressed action and should return true if
     * the event was consumed **/
    protected open fun doOnBackPressed(): Boolean = false

    override fun onBackPressed() {
        if (dialogManager.isShowingDialog()) {
            dialogManager.hideDialog(true)
        } else {
            if (!doOnBackPressed()) {
                super.onBackPressed()
            }
        }
    }
}