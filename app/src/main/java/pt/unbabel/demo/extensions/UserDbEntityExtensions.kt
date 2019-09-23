package pt.unbabel.demo.extensions

import pt.unbabel.demo.databases.entities.UserDbEntity
import pt.unbabel.demo.http.entities.UserResponseData

/**
 * Created by Ricardo Neves on 23/09/2019.
 */

fun UserDbEntity?.toUserResponseData(): UserResponseData? {
    return this?.let {
        UserResponseData(
            it.id, it.name, it.username, it.email,
            phone = it.phone, website = it.website)
    }
}