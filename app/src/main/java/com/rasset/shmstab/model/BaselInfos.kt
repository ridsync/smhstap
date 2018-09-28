package com.rasset.shmstab.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * Created by devok on 2018-08-31.
 */
open class BaseInfo {
//    @Expose var seq: Int = 0
}


data class UserInfo(@SerializedName("user_id")var userId: Long = 0, var userName: String?,var photoImgPath: String?) {

}

data class ContentsInfo(var title: String = "",
                        var imgPath: String = "",
                        var regDate: Long) : BaseInfo()

open class DiagnoseBaseInfo(@SerializedName("dianoseId")
                            var diagnoseId: Long=0,
                            @SerializedName("dianoseDetailId")
                            var diagnoseDetailId: Long=0,
                            var applyPart:Long = 0, // (11:부동산재테크 (매도/매수), 21: 토지/개발(MD), 31: 부동산 세금, 41:홈스테이징/인테리어, 51: 건축/리모델링(CM), 61: 주
                            var diagnoseType: Long=0
                            ) : BaseInfo()

// 진단정보List
data class DiagnoseInfo(var customerId: Long=0,
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
                        var contents:String?=null) : DiagnoseBaseInfo()

// Update Diaginfos
data class DiagnoseAssetSellInfo(var itemType: String?=null,
                                var address: String?=null,
                                var buyYear: String?=null,
                                var sellPurpose:String?=null,
                                var minAmount:String?=null,
                                var maxAmount:String?=null,
                                var sellTime:String?=null,
                                var consultPart01:Long=0,
                                var consultPart02:Long=0,
                                var consultPart03:Long=0,
                                var consultPart04:Long=0,
                                var consultPart05:Long=0) : DiagnoseBaseInfo()

data class DiagnoseAssetBuyInfo(var customerId: Long=0,
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
                                var contents:String?=null) : DiagnoseBaseInfo()

data class DiagnoseCMInfo(var customerId: Long=0,
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
                        var contents:String?=null) : DiagnoseBaseInfo()

data class DiagnoseTaxAssetInfo(var customerId: Long=0,
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
                                var contents:String?=null) : DiagnoseBaseInfo()


data class DiagnoseTaxFalmInfo(var customerId: Long=0,
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
                                var contents:String?=null) : DiagnoseBaseInfo()

