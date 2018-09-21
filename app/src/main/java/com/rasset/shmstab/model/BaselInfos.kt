package com.rasset.shmstab.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by devok on 2018-08-31.
 */
open class BaseInfo {
    var seq: Int = 0
}


data class UserInfo(@SerializedName("user_id")var userId: Long = 0, var userName: String?,var photoImgPath: String?) {

}

data class ContentsInfo(var title: String = "",
                        var imgPath: String = "",
                        var regDate: Long) : BaseInfo()

data class CustomerInfo(var idx: Int = 0,
                        @SerializedName("dianoseId") // 서버에선 신청건리스트로 내려줌젠장 욕나오네
                        var diagnoseId: Long=0,
                        var customerName: String?=null,
                        var photoImgPath: String?=null,
                        var customerLevel: Long = 0) : BaseInfo()

data class DiagnoseInfo(var idx: Int = 0,
                        @SerializedName("dianoseId")
                        var diagnoseId: Long=0,
                        @SerializedName("dianoseDetailId")
                        var diagnoseDetailId: Long=0,
                        var customerId: Long=0,
                        var customerName: String?=null,
                        var photoImgPath: String?=null,
                        var customerLevel: Long = 0,
                        var customerPhone: String?=null,
                        @SerializedName("bookingDate")
                        var consultingDate:String?=null,
                        @SerializedName("startTime")
                        var consultingTimeStart:String?=null,
                        @SerializedName("endTime")
                        var consultingTime:String?=null,
                        var applyPart:Long = 0, // 11 매도/매수
                        var contents:String?=null) : BaseInfo()