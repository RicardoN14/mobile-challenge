package pt.unbabel.demo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import pt.unbabel.demo.ui.screens.Screen

/**
 * Created by Ricardo Neves on 15/09/2019$.
 */
abstract class RecyclerViewBaseAdapter<in I, S: Screen, VH : RecyclerViewBaseAdapter.RecyclerViewBaseViewHolder<I, S>>(
    protected val screen: S,
    private var items: List<I>)
    : RecyclerView.Adapter<VH>() {

    abstract fun getItemLayoutRscId(): Int
    abstract fun createViewHolder(screen: S, view: View): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(screen).inflate(getItemLayoutRscId(), parent, false)
        return createViewHolder(screen, v)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(items[position], position)
    }


    abstract class RecyclerViewBaseViewHolder<in I, S: Screen>(val screen: S, override val containerView: View)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {

        abstract fun bind(item: I, position: Int)

    }

    fun updateItems(items: List<I>){
        this.items = items
        notifyDataSetChanged()
    }
}