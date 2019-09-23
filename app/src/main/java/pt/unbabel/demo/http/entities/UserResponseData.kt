package pt.unbabel.demo.http.entities

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Ricardo Neves on 21/09/2019.
 */
@Parcelize
data class UserResponseData(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("address") val address: AddressResponseData? = null,
    @SerializedName("phone") val phone: String?,
    @SerializedName("website") val website: String?,
    @SerializedName("company") val company: CompanyResponseData? = null
) : ResponseData()