package pt.unbabel.demo.http.retrofit

import pt.unbabel.demo.http.entities.CommentResponseData
import pt.unbabel.demo.http.entities.PostResponseData
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Ricardo Neves on 16/09/2019.
 */
interface CommentsService {

    @GET("comments")
    fun getComments(): Call<ArrayList<CommentResponseData>>

}