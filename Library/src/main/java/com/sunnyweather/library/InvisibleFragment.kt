package com.sunnyweather.library

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
 * @author :created by
 * 时间：2024/6/4 16:28
 * 描述：
 */
//typealias 关键字可以用于给任意类型指定一个别名
typealias PermissionCallback = (Boolean,List<String>)->Unit

class InvisibleFragment : Fragment(){
    private var callBack :PermissionCallback?=null


    fun requestNow(cb:PermissionCallback,vararg permission:String){
        callBack = cb
        requestPermissions(permission,1)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if(requestCode == 1){
            val deniedList = ArrayList<String>()
            for ((index,result) in grantResults.withIndex()){
                if(result!= PackageManager.PERMISSION_GRANTED){
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callBack?.let { it(allGranted,deniedList) }
        }
    }
}