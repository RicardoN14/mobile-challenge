package pt.unbabel.demo.extensions

import android.view.View

/**
 * Created by Ricardo Neves on 16/09/2019.
 */

fun View.gone() {
    this.visibility = View.GONE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}