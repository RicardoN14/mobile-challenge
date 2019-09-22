package pt.unbabel.demo.http.entities

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ricardo Neves on 21/09/2019.
 */
@Parcelize
data class CommentResponseData(
    @SerializedName("postId") val postId: Int?,
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("body") val description: String?
) : ResponseData()