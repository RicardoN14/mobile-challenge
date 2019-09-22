package pt.unbabel.demo.views

import android.content.Context
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.information_view_item.view.*
import pt.unbabel.demo.R
import pt.unbabel.demo.extensions.gone
import pt.unbabel.demo.extensions.visible

/**
 * Created by ricardo.neves on 22/09/2019.
 */
open class InformationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        createLayout(context)
        applyAttributes(context, attrs)
    }

    protected open fun afterLayoutCreatedAndAttributesApplied() {}

    private fun createLayout(context: Context) {
        val layoutInflater = LayoutInflater.from(context)
        layoutInflater.inflate(R.layout.information_view_item, this, true)
    }

    private fun applyAttributes(context: Context, attrs: AttributeSet?) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.InformationView)

        try {

            setLabel(typedArray.getString(R.styleable.InformationView_IFV_label))
            setValue(typedArray.getString(R.styleable.InformationView_IFV_value))

            setEllipsizeLabel(
                typedArray.getBoolean(
                    R.styleable.InformationView_IFV_ellipsizeLabel,
                    false
                )
            )

            val resources = context.resources

            val defaultLabelSize =
                resources.getDimensionPixelSize(R.dimen.information_view_default_label_size)
            val defaultValueSize =
                resources.getDimensionPixelSize(R.dimen.information_view_default_value_size)

            setLabelSize(
                typedArray.getDimensionPixelSize(
                    R.styleable.InformationView_IFV_labelSize,
                    defaultLabelSize
                ).toFloat()
            )
            setValueSize(
                typedArray.getDimensionPixelSize(
                    R.styleable.InformationView_IFV_valueSize,
                    defaultValueSize
                ).toFloat()
            )

            val defaultValueColor = ContextCompat.getColor(context, R.color.colorDarkSlateBlue)

            typedArray.getColor(R.styleable.InformationView_IFV_labelColor, 0).let {
                if (it != 0) {
                    setLabelColor(it)
                }
            }

            setValueColor(
                typedArray.getColor(
                    R.styleable.InformationView_IFV_valueColor,
                    defaultValueColor
                )
            )
        } finally {
            typedArray.recycle()
        }

        afterLayoutCreatedAndAttributesApplied()
    }

    fun setLabel(label: String?) {
        informationViewItemLabel.text = label

        if (label == null) {
            informationViewItemLabel.gone()
        } else {
            informationViewItemLabel.visible()
        }
    }

    private fun setEllipsizeLabel(shouldEllipsize: Boolean) {
        if (shouldEllipsize) {
            informationViewItemLabel.ellipsize = TextUtils.TruncateAt.END
            informationViewItemLabel.maxLines = 1
        }
    }

    fun getLabel(): String {
        return informationViewItemLabel.text.toString()
    }

    fun getValue(): String = informationViewItemValue.text.toString()

    fun setValue(value: String?) {
        informationViewItemValue.text = value
        if (value == null) {
            informationViewItemValue.gone()
        } else {
            informationViewItemValue.visible()
        }
    }

    fun setLabelColor(color: Int) {
        informationViewItemLabel.setTextColor(color)
    }

    fun setValueColor(color: Int) {
        informationViewItemValue.setTextColor(color)
    }

    fun setLabelSize(size: Float, unitType: Int = TypedValue.COMPLEX_UNIT_PX) {
        informationViewItemLabel.setTextSize(unitType, size)
    }

    fun setValueSize(size: Float, unitType: Int = TypedValue.COMPLEX_UNIT_PX) {
        informationViewItemValue.setTextSize(unitType, size)
    }

}