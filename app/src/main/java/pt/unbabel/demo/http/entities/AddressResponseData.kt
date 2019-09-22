package pt.unbabel.demo.http.entities

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ricardo Neves on 16/09/2019.
 */
@Parcelize
data class AddressResponseData(
    @SerializedName("street") val street: String?,
    @SerializedName("suite") val suite: String?,
    @SerializedName("city") val city: String?,
    @SerializedName("zipcode") val zipCode: String?,
    @SerializedName("geo") val geo: GeoResponseData?
) : ResponseData()