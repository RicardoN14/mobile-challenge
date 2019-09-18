package pt.unbabel.demo.entities.dialogs

import android.os.Parcelable
import androidx.annotation.LayoutRes
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ricardo Neves on 16/09/2019.
 */

@Parcelize
class DialogInfo(val positiveLabel: String? = null,
                 val negativeLabel: String? = null,
                 val title: String,
                 val message: String? = null,
                 val isCancelable: Boolean = true,
                 @LayoutRes val customViewLayoutRscId: Int? = 0) : Parcelable