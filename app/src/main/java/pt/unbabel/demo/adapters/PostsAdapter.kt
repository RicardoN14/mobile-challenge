package pt.unbabel.demo.adapters

import android.view.View
import kotlinx.android.synthetic.main.post_item.*
import org.jetbrains.anko.startActivity
import pt.unbabel.demo.R
import pt.unbabel.demo.http.entities.PostResponseData
import pt.unbabel.demo.ui.screens.PostDetailsScreen
import pt.unbabel.demo.ui.screens.PostsScreen

/**
 * Created by Ricardo Neves on 19/09/2019.
 */
class PostsAdapter(screen: PostsScreen, items: ArrayList<PostResponseData>) :
    RecyclerViewBaseAdapter<PostResponseData, PostsScreen, PostsAdapter.PostsViewHolder>(
        screen,
        items
    ) {

    override fun getItemLayoutRscId() = R.layout.post_item

    override fun createViewHolder(screen: PostsScreen, view: View) = PostsViewHolder(screen, view)

    inner class PostsViewHolder(screen: PostsScreen, view: View) :
        RecyclerViewBaseAdapter.RecyclerViewBaseViewHolder<PostResponseData, PostsScreen>(
            screen,
            view
        ) {

        override fun bind(item: PostResponseData, position: Int) {
            postItemBody.text = item.body
            postItemTitle.text = item.title
            postItem.setOnClickListener {
                screen.startActivity<PostDetailsScreen>(
                    PostDetailsScreen.POST_RESPONSE_DATA_EXTRA_KEY to item
                )
            }
        }

    }


}