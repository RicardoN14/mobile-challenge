package pt.unbabel.demo.extensions

import android.animation.Animator
import android.view.ViewPropertyAnimator

/**
 * Created by Ricardo Neves on 16/09/2019.
 */

fun ViewPropertyAnimator.setEndAnimation(endAnimationAction: (Animator?) -> Unit): ViewPropertyAnimator {

    setListener(object : Animator.AnimatorListener {
        override fun onAnimationRepeat(p0: Animator?) {
        }

        override fun onAnimationEnd(p0: Animator?) {
            endAnimationAction(p0)
        }

        override fun onAnimationCancel(p0: Animator?) {
        }

        override fun onAnimationStart(p0: Animator?) {
        }

    })

    return this
}