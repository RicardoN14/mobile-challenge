package pt.unbabel.demo.extensions

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Ricardo Neves on 16/09/2019.
 */

fun RecyclerView.setLinearAdapter(adapter: RecyclerView.Adapter<*>,
                                  orientation: Int = RecyclerView.VERTICAL,
                                  reverseLayout: Boolean = false,
                                  itemDecoration: RecyclerView.ItemDecoration? = null) {

    this.layoutManager = LinearLayoutManager(context, orientation, reverseLayout)

    itemDecoration?.run {
        addItemDecoration(this)
    }

    this.adapter = adapter

}