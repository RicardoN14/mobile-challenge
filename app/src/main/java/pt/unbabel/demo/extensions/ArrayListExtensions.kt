package pt.unbabel.demo.extensions

import pt.unbabel.demo.databases.entities.CommentDbEntity
import pt.unbabel.demo.databases.entities.PostDbEntity
import pt.unbabel.demo.databases.entities.UserDbEntity
import pt.unbabel.demo.http.entities.CommentResponseData
import pt.unbabel.demo.http.entities.PostResponseData
import pt.unbabel.demo.http.entities.UserResponseData

/**
 * Created by Ricardo Neves on 22/09/2019.
 */

fun ArrayList<PostResponseData>.toPostsDbEntity(): Array<PostDbEntity> {
    return map {
        PostDbEntity(it.userId, it.id, it.title, it.description)
    }.toTypedArray()
}

fun ArrayList<CommentResponseData>.toCommentsDbEntity(): Array<CommentDbEntity> {
    return map {
        CommentDbEntity(it.postId, it.id, it.name, it.email, it.description)
    }.toTypedArray()
}

fun ArrayList<UserResponseData>.toUsersDbEntity(): Array<UserDbEntity> {
    return map {
        UserDbEntity(it.id, it.name, it.username, it.email, it.phone, it.website)
    }.toTypedArray()
}

fun ArrayList<PostDbEntity>.toPostsResponseData(): ArrayList<PostResponseData> {
    return map {
        PostResponseData(it.userId, it.id, it.title, it.description)
    } as ArrayList<PostResponseData>
}

fun ArrayList<CommentDbEntity>.toCommentsResponseData(): ArrayList<CommentResponseData> {
    return map {
        CommentResponseData(it.postId, it.id, it.name, it.email, it.description)
    } as ArrayList<CommentResponseData>
}

fun ArrayList<UserDbEntity>.toUsersResponseData(): ArrayList<UserResponseData> {
    return map {
        UserResponseData(
            it.id, it.name, it.username, it.email,
            phone = it.phone, website = it.website)
    } as ArrayList<UserResponseData>
}