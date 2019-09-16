package pt.unbabel.demo.utils

import android.util.Log
import pt.unbabel.demo.BuildConfig

/**
 * Created by Ricardo Neves on 15/09/2019$.
 */
class DLog {

    companion object {
        private val LOG = BuildConfig.DEBUG

        fun i(tag: String, string: String) {
            if (LOG) {
                Log.i(tag, string)
            }
        }

        fun e(tag: String, string: String) {
            if (LOG) {
                Log.e(tag, string)
            }
        }

        fun d(tag: String, string: String) {
            if (LOG) {
                Log.d(tag, string)
            }
        }

        fun v(tag: String, string: String) {
            if (LOG) {
                Log.d(tag, string)
            }
        }

        fun w(tag: String, string: String) {
            if (LOG) {
                Log.d(tag, string)
            }
        }
    }

}