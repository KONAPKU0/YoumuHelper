package ml.youmu.helper.base.model

import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 *@Author weixuan
 *@Date 2023/7/14
 *@Description
 **/
@Parcelize
@Serializable
data class NavCommon(val name:String = "",val id:String = "",val phone:String = "",val data:String = "",val lat :String ="",val lng :String ="",val third :String ="") : Parcelable{
    override fun toString(): String {
        return Uri.encode(Json.encodeToString(this))
    }
}

class NavCommonType : NavType<NavCommon>(isNullableAllowed = false) {

    override fun get(bundle: Bundle, key: String): NavCommon? =
        bundle.getParcelable(key)

    override fun put(bundle: Bundle, key: String, value: NavCommon) =
        bundle.putParcelable(key, value)

    override fun parseValue(value: String): NavCommon {
        return Json.decodeFromString(value)
    }

}