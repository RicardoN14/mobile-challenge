package pt.unbabel.demo.http.entities

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ricardo Neves on 16/09/2019.
 */
@Parcelize
data class GeoResponseData(
    @SerializedName("lat") val street: Double?,
    @SerializedName("lng") val suite: Double?
) : ResponseData()