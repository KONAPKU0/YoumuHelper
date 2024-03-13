package ml.youmu.helper.ext

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.core.content.ContextCompat
import com.hjq.toast.Toaster

/**
 *@Author weixuan
 *@Date 2023/6/7
 *@Description
 **/

lateinit var appContext: Application
    internal set

inline val packageName: String get() = appContext.packageName

fun stringRes(id: Int): String {
    return runCatching {
        (currentActivity ?: appContext).getString(id)
    }.getOrElse {
        ""
    }
}
fun colorRes(resId: Int): Int = ContextCompat.getColor((currentActivity ?: appContext),resId)
fun drawableRes(resId: Int): Drawable? = ContextCompat.getDrawable((currentActivity ?: appContext),resId)

fun String.toast() {
    if (this.isNotBlank()) Toaster.show(this)
}

val String.color
    get() = Color(android.graphics.Color.parseColor(this))



