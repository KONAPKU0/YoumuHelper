package ml.youmu.helper.ext

import android.app.Activity
import android.app.Application
import android.os.Bundle
import java.util.LinkedList

/**
 *@Author weixuan
 *@Date 2023/6/7
 *@Description
 **/

private val activityList = LinkedList<Activity>()

//app当前显示的Activity
val currentActivity: Activity? get() = if (activityList.isEmpty()) null else activityList.last

/**
 * 添加Activity入栈
 * @param activity Activity
 */
fun addActivity(activity: Activity) {
    activityList.add(activity)
}

/**
 * 关闭Activity出栈
 * @param activity Activity
 */
fun finishActivity(activity: Activity) {
    if (!activity.isFinishing) {
        activity.finish()
    }
    activityList.remove(activity)
}

/**
 * 从栈移除activity 不会finish
 * @param activity Activity
 */
fun removeActivity(activity: Activity) {
    activityList.remove(activity)
}

/**
 * 关闭Activity出栈
 * @param cls Class<*>
 */
fun finishActivity(cls: Class<*>) {
    if (activityList.isEmpty()) return
    val index = activityList.indexOfFirst { it.javaClass == cls }
    if (index == -1) return
    if (!activityList[index].isFinishing) {
        activityList[index].finish()
    }
    activityList.removeAt(index)
}

/**
 * 查询是否存在Activity
 *
 * @param cls
 * @return
 */
fun findListActivityIn(cls: Class<*>):Boolean {
    if (activityList.isEmpty()) return false
    val index = activityList.indexOfFirst { it.javaClass == cls }
    if (index == -1) return false
    return true
}

/**
 * 关闭所有的Activity 全部出栈
 */
fun finishAllActivity() {
    activityList.forEach {
        if (!it.isFinishing) {
            it.finish()
        }
    }
    activityList.clear()
}

class KtxActivityLifecycleCallbacks : Application.ActivityLifecycleCallbacks {

    override fun onActivityPaused(activity: Activity) {
//        LogCat.d(activity.javaClass.simpleName+"onActivityPaused")
    }

    override fun onActivityStarted(p0: Activity) {

    }

    override fun onActivityDestroyed(activity: Activity) {
        removeActivity(activity)
    }

    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
    }

    override fun onActivityStopped(p0: Activity) {
    }

    override fun onActivityCreated(activity: Activity, p1: Bundle?) {
//        LogCat.d(activity.javaClass.simpleName+"onActivityCreated")
        addActivity(activity)
    }

    override fun onActivityResumed(activity: Activity) {
//        LogCat.d(activity.javaClass.simpleName+"onActivityResumed")
    }

}