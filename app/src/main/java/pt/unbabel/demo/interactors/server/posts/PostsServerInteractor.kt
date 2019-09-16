package pt.unbabel.demo.interactors.server.posts

import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.http.entities.PostResponseData
import pt.unbabel.demo.http.retrofit.PostsService
import pt.unbabel.demo.interactors.interfaces.posts.IPostsInteractor
import pt.unbabel.demo.interactors.listeners.posts.IPostsInteractorListener
import pt.unbabel.demo.interactors.server.base.ServerInteractor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsServerInteractor : ServerInteractor<IPostsInteractorListener>(), IPostsInteractor {

    companion object{
        val postsService: PostsService by lazy{
            retrofit.create(PostsService::class.java)
        }
    }

    override fun requestPosts(requestConfig: RequestConfig?) {

        postsService.getPosts().enqueue(object: Callback<ArrayList<PostResponseData>>{
            override fun onFailure(call: Call<ArrayList<PostResponseData>>, t: Throwable) {
                // TODO show error
            }

            override fun onResponse(
                call: Call<ArrayList<PostResponseData>>,
                response: Response<ArrayList<PostResponseData>>
            ) {
                interactorListener.onRequestPostsSuccess(response.body() ?: arrayListOf())
            }

        })

    }

}