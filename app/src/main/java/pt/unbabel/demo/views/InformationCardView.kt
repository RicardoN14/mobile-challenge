package pt.unbabel.demo.views

import android.content.Context
import android.text.Spanned
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.information_card_view_header.view.*
import pt.unbabel.demo.R
import pt.unbabel.demo.extensions.gone
import pt.unbabel.demo.extensions.visible

/**
 * Created by ricardo.neves on 22/09/2019.
 */
class InformationCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private val tag = javaClass.simpleName

    init {
        createLayout(context)
        applyAttributes(context, attrs)
    }

    private fun createLayout(context: Context) {
        orientation = VERTICAL
        LayoutInflater.from(context).apply {
            inflate(R.layout.information_card_view_header, this@InformationCardView, true)
        }
    }

    private fun applyAttributes(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.InformationCardView)

        try {
            setTitle(typedArray.getString(R.styleable.InformationCardView_IFCV_title))
            setIcon(typedArray.getResourceId(R.styleable.InformationCardView_IFCV_icon, 0))
            setIconPadding(
                typedArray.getDimensionPixelSize(
                    R.styleable.InformationCardView_IFCV_iconPadding,
                    0
                )
            )

            val defaultTitleSize =
                resources.getDimensionPixelSize(R.dimen.information_card_view_default_title_size)
            val defaultTitleColor = ContextCompat.getColor(context, R.color.colorPrimary)

            setTitleSize(
                typedArray.getDimensionPixelSize(
                    R.styleable.InformationCardView_IFCV_titleSize,
                    defaultTitleSize
                ).toFloat()
            )
            setTitleColor(
                typedArray.getColor(
                    R.styleable.InformationCardView_IFCV_titleColor,
                    defaultTitleColor
                )
            )
        } finally {
            typedArray.recycle()
        }
    }

    fun setTitle(title: String?) {
        if (title.isNullOrEmpty()) {
            informationCardViewTitle.gone()
        } else {
            informationCardViewTitle.visible()
            informationCardViewTitle.text = title
        }
    }

    fun setTitle(title: Spanned) {
        if (title.isBlank()) {
            informationCardViewTitle.gone()
        } else {
            informationCardViewTitle.visible()
            informationCardViewTitle.text = title
        }
    }

    fun setIcon(@DrawableRes iconRscId: Int) {
        informationCardViewTitle.setCompoundDrawablesWithIntrinsicBounds(iconRscId, 0, 0, 0)
    }

    fun setIconPadding(padding: Int) {
        informationCardViewTitle.compoundDrawablePadding = padding
    }

    fun setTitleSize(size: Float, unitType: Int = TypedValue.COMPLEX_UNIT_PX) {
        informationCardViewTitle.setTextSize(unitType, size)
    }

    fun setTitleColor(color: Int) {
        informationCardViewTitle.setTextColor(color)
    }

}