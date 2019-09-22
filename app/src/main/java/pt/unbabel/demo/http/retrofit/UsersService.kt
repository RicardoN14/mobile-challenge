package pt.unbabel.demo.http.retrofit

import pt.unbabel.demo.http.entities.UserResponseData
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by Ricardo Neves on 16/09/2019.
 */
interface UsersService {

    @GET("users")
    fun getUsers(): Call<ArrayList<UserResponseData>>

}