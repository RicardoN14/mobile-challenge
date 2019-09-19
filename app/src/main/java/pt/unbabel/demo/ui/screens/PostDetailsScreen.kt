package pt.unbabel.demo.ui.screens

import android.os.Bundle
import org.jetbrains.anko.toast
import pt.unbabel.demo.R
import pt.unbabel.demo.alias.PresentersArrayList
import pt.unbabel.demo.http.entities.PostResponseData

/**
 * Created by Ricardo Neves on 19/09/2019.
 */
class PostDetailsScreen : ExecuteRequestScreen() {

    companion object{
        const val POST_RESPONSE_DATA_EXTRA_KEY = "PostDetailsScreen::PostResponseDataExtraKey"
    }

    private val postResponseData: PostResponseData by lazy{
        intent.getParcelableExtra<PostResponseData>(POST_RESPONSE_DATA_EXTRA_KEY)
    }

    override fun initPresenters(presenters: PresentersArrayList, requestContextGroup: String) {
    }

    override fun getExecuteRequestLayoutResourceId() = R.layout.screen_post_details

    override fun doExecuteRequestInitializations(savedInstanceState: Bundle?) {
        toast("userId = ${postResponseData.userId}")
    }

}