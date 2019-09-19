package pt.unbabel.demo.utils

import android.os.Bundle
import android.os.Parcelable
import android.util.SparseArray
import kotlin.reflect.KProperty

/**
 * Created by Ricardo Neves on 19/09/2019.
 */

open class InstanceStateProvider<T>(private val savable: Bundle) {

    private var cache: T? = null

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        cache = value
        if (value == null) {
            savable.remove(property.name)
            return
        }

        @Suppress("UNCHECKED_CAST")
        when (value) {
            is Int -> savable.putInt(property.name, value)
            is Long -> savable.putLong(property.name, value)
            is Float -> savable.putFloat(property.name, value)
            is Double -> savable.putDouble(property.name, value)
            is String -> savable.putString(property.name, value)
            is Boolean -> savable.putBoolean(property.name, value)
            is Bundle -> savable.putBundle(property.name, value)
            is Parcelable -> savable.putParcelable(property.name, value)
            is ArrayList<*> -> savable.putParcelableArrayList(property.name, value as? ArrayList<Parcelable>)
            is Array<*> -> savable.putParcelableArray(property.name, value as? Array<Parcelable>)
            is SparseArray<*> -> savable.putSparseParcelableArray(property.name, value as SparseArray<out Parcelable>)
            else -> throw NoSuchElementException("Intent extra ${property.name} has wrong type")
        }
    }

    @Suppress("UNCHECKED_CAST")
    protected fun getAndCache(key: String): T? = cache ?: (savable.get(key) as T?).apply { cache = this }

    class Nullable<T>(savable: Bundle) : InstanceStateProvider<T>(savable) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T? = getAndCache(property.name)
    }

    class NotNull<T>(savable: Bundle, private val defaultValue: T) : InstanceStateProvider<T>(savable) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T = getAndCache(property.name) ?: defaultValue
    }
}