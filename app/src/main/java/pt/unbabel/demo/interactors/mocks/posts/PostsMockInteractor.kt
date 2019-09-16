package pt.unbabel.demo.interactors.mocks.posts

import pt.unbabel.demo.entities.requests.RequestConfig
import pt.unbabel.demo.http.entities.PostResponseData
import pt.unbabel.demo.interactors.interfaces.posts.IPostsInteractor
import pt.unbabel.demo.interactors.listeners.posts.IPostsInteractorListener
import pt.unbabel.demo.interactors.mocks.base.MockInteractor

class PostsMockInteractor : MockInteractor<IPostsInteractorListener>(), IPostsInteractor {

    override fun requestPosts(requestConfig: RequestConfig?) {
        executeWithDelay({
            interactorListener.onRequestPostsSuccess(getPostsResponseData())
        }, requestConfig = requestConfig)
    }

    private fun getPostsResponseData(): ArrayList<PostResponseData> {
        return arrayListOf<PostResponseData>().apply{
            for(i in 1..10) {
                add(PostResponseData(i, i, "title$i", "body$i"))
            }
        }
    }
}