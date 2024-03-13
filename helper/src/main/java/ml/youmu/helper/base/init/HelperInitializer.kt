package ml.youmu.helper.base.init

import android.app.Application
import android.content.Context
import androidx.startup.Initializer
import ml.youmu.helper.BuildConfig
import com.drake.logcat.LogCat
import com.hjq.toast.Toaster
import com.tencent.mmkv.MMKV
import ml.youmu.helper.ext.KtxActivityLifecycleCallbacks
import ml.youmu.helper.ext.appContext

class HelperInitializer: Initializer<Unit> {
    override fun create(context: Context) {

    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> {
        return mutableListOf()
    }

    private fun activityManage(context: Context){
        LogCat.setDebug(BuildConfig.DEBUG)
        appContext = context as Application
        appContext.registerActivityLifecycleCallbacks(KtxActivityLifecycleCallbacks())
        Toaster.init(context)
        MMKV.initialize(context)
    }
}