package pt.unbabel.demo.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.widget.NestedScrollView
import kotlinx.android.synthetic.main.error_state_view.view.*
import pt.unbabel.demo.R

/**
 * Created by Ricardo Neves on 18/09/2019.
 */
class ErrorStateView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
                                               defStyleAttr: Int = 0)
    : NestedScrollView(context, attrs, defStyleAttr) {

    init {
        createLayout(context)
        applyAttributes(context, attrs)
        isFillViewport = true
    }

    private fun createLayout(context: Context) {
        LayoutInflater.from(context).inflate(R.layout.error_state_view, this, true)
    }

    private fun applyAttributes(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ErrorStateView)

        try {
            setMessage(typedArray.getString(R.styleable.ErrorStateView_ESV_message))
        } finally {
            typedArray.recycle()
        }
    }

    private fun setMessage(message: String?) {
        errorStateMessageLabel.text = message
    }

}