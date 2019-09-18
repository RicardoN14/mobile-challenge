package pt.unbabel.demo.http.entities

import com.google.gson.annotations.SerializedName

/**
 * Created by Ricardo Neves on 16/09/2019.
 */
data class PostResponseData(
    @SerializedName("userId") val userId: Int?,
    @SerializedName("id") val id: Int?,
    @SerializedName("title") val title: String?,
    @SerializedName("body") val body: String?)