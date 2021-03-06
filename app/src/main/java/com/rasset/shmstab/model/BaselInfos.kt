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
                            @SerializedName("richSurveyId")
                            var richSurveyId: Long=11111,
                            @SerializedName("dianoseDetailId")
                            var diagnoseDetailId: Long=0,
                            var applyPart:Long = 0, // (11:부동산재테크 (매도/매수), 21: 토지/개발(MD), 31: 부동산 세금, 41:홈스테이징/인테리어, 51: 건축/리모델링(CM), 61: 주
                            var diagnoseType: String?=null)

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
                                 var sellPurpose:Long = 0,
                                 var minAmount:String?=null,
                                 var maxAmount:String?=null,
                                 var sellTime:String?=null,
                                 var consultPart01:Long=0,
                                 var consultPart02:Long=0,
                                 var consultPart03:Long=0,
                                 var consultPart04:Long=0,
                                 var consultPart05:Long=0) : DiagnoseBaseInfo()

data class DiagnoseAssetBuyInfo(var buyItemType: String?=null,
                                var buyArea: String?=null,
                                var buyTime:String?=null,
                                var investPurpose:String?=null,
                                var cashAmount:String?=null,
                                var considerations:String?=null,
                                var consultPart11:Long=0,
                                var consultPart12:Long=0,
                                var consultPart13:Long=0,
                                var consultPart14:Long=0,
                                var consultPart15:Long=0) : DiagnoseBaseInfo()

data class DiagnoseMDInfo(var address: String?=null,
                          var mdReason:String?=null,
                          var mdQuestion:String?=null,
                          var mdBuyCash:String?=null,
                          var mdExperienced:String?=null,
                          var mdPurchase:String?=null,
                          var consultYn:String?=null) : DiagnoseBaseInfo()

data class DiagnoseTaxAssetInfo(var itemType: String?=null,
                                var ownerHouse: String?=null,
                                var areaType: String?=null,
                                var holdType:String?=null,
                                var priceType:String?=null,
                                var consultPart01:Long=0,
                                var consultPart02:Long=0,
                                var consultPart03:Long=0,
                                var consultPart04:Long=0,
                                var consultPart05:Long=0,
                                var consultPart06:Long=0,
                                var consultEtc01: String?=null,
                                var consultYn:Long=0) : DiagnoseBaseInfo()


data class DiagnoseTaxFarmInfo(var farmRealType: String?=null,
                               var farmUnchangeType: String?=null,
                               var farmPurposeType: String?=null,
                               var farmExcludeTye:String?=null,
                               var farmPeriodType:String?=null,
                               var farmTransType: String?=null,
                               var farmConsultYn:String?=null) : DiagnoseBaseInfo()

data class DiagnoseHomeInteInfo(var address:String?=null,
                                var sizeType:String?=null,
                                var costType:String?=null,
                                var styleType:String?=null,
                                var buildType:String?=null,
                                var consultYn:Long=0) : DiagnoseBaseInfo()

data class DiagnoseCMInfo(var itemType:String?=null,
                          var address:String?=null,
                          var constructType:String?=null,
                          var purposeType:String?=null,
                          var expenseType:String?=null,
                          var troubleType:String?=null,
                          var consultYn:Long=0,
                          var consultEtc01:String?=null,
                          var consultEtc02:String?=null) : DiagnoseBaseInfo()

data class DiagnoseManagementInfo(var itemType:String?=null,
                          var address:String?=null,
                          var propertyType:String?=null,
                          var hlCountType:String?=null,
                          var estimateType:String?=null,
                          var leaseType:String?=null,
                          var consultYn:Long=0,
                          var consultEtc01:String?=null,
                          var consultEtc02:String?=null) : DiagnoseBaseInfo()

// 부자지수
data class ResultInfo(var resultRichType:String="A",
                      var firstScore:Int=0,
                      var secondScore:Int=0,
                      var thirdScore:Int=0,
                      var fourthScore:Int=0,
                      var fifthScore:Int=0) : BaseInfo()

data class DiagRichStepFirst(var ages: String?=null,
                          var totalAsset:String?=null,
                          var rateAsset:String?=null) : DiagnoseBaseInfo()

data class DiagRichStepSecond(var consider: String?=null,
                             var assetMethod:String?=null,
                             var assetRate:String?=null,
                              var assetScale:String?=null,
                              var fintechEdu:String?=null,
                              var buyRasset:String?=null,
                              var buyHomeType:String?=null) : DiagnoseBaseInfo()

data class DiagRichStepThird(var whenBuyRasset: String?=null,
                             var whenHomeType:String?=null,
                             var considerType:String?=null,
                             var expEdu:String?=null,
                             var taxEdu:String?=null,
                             var taxField:String?=null,
                             var taxFintechEdu:String?=null) : DiagnoseBaseInfo()

data class DiagRichStepFourth(var lonRate: String?=null,
                             var cocernType:String?=null) : DiagnoseBaseInfo()

data class DiagRichStepFifth(var tryEstateType: String?=null,
                              var lottoEstateType:String?=null) : DiagnoseBaseInfo()

data class DiagRichDTO(var item11:Int=0,
                       var item12:Int=0,
                       var item13:Int=0,
                       var item21: Int=0,
                       var item22:Int=0,
                       var item23:Int=0,
                       var item24:Int=0,
                       var item25:Int=0,
                       var item26:Int=0,
                       var item27:Int=0,
                       var item31: Int=0,
                       var item32:Int=0,
                       var item33:Int=0,
                       var item34:Int=0,
                       var item35:Int=0,
                       var item36:Int=0,
                       var item37:Int=0,
                       var item41: Int=0,
                       var item42:Int=0,
                       var item51: Int=0,
                       var item52:Int=0) : DiagnoseBaseInfo()
