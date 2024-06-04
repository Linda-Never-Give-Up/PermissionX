package com.sunnyweather.library

import androidx.fragment.app.FragmentActivity

/**
 * @author :created by
 * 时间：2024/6/4 16:47
 * 描述：单例类 对外接口
 */
object PermissionX {
    private const val TAG = "InvisibleFragment"

    fun request(activity:FragmentActivity,vararg permission: String,callback:PermissionCallback){
        val fragmentManager = activity.supportFragmentManager
        val existedFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if(existedFragment!=null){
            existedFragment as InvisibleFragment
        }else{
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment,TAG).commitNow()
            invisibleFragment
        }
        // *讲一个数组转换成可变长度参数传递过去
        fragment.requestNow(callback,*permission)
    }
}