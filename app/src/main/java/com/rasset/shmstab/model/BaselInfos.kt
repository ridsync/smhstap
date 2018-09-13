package com.rasset.shmstab.model

import java.util.*

/**
 * Created by devok on 2018-08-31.
 */
open class BaseInfo {
    var seq: Int = 0
}


data class UserInfo(var userId: Long = 0, var userName: String = "",var photoImgPath: String) {

}

data class ContentsInfo(var title: String = "",
                        var imgPath: String = "",
                        var regDate: Long) : BaseInfo()

data class CustomerInfo(var idx: Int = 0,
                        var userId: String?=null,
                        var userName: String?=null,
                        var userLevel: Long = 0,
                        var userPhone: String?=null,
                        var consultingDate:Long = 0,
                        var consultingTime:Long = 0,
                        var diagField:String?=null,
                        var question:String?=null) : BaseInfo()