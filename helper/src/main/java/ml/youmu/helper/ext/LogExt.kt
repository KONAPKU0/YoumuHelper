package ml.youmu.helper.ext

import com.drake.logcat.LogCat

fun String.logD(tag:String="LogCat",occurred: Throwable? = Exception()){
    LogCat.d(this,tag,occurred = occurred)
}

fun Throwable.logE(tag:String="LogCat"){
    LogCat.e(this,tag,this)
}