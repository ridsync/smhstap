package com.rasset.shmstab.network.res

import com.rasset.shmstab.model.ContentsInfo
import com.rasset.shmstab.model.DiagnoseInfo
import com.rasset.shmstab.model.ResultInfo
import com.rasset.shmstab.model.UserInfo
import java.util.ArrayList

/**
 * Created by devok on 2018-08-31.
 */
open class BaseModel {
    var resVal: Int = 0
    var resMsg: String? = null
}

data class ResCustomerList(var userTotalCount: Int, var list: ArrayList<DiagnoseInfo>?) : BaseModel()

data class ResDiagnoseInfo(var list: ArrayList<DiagnoseInfo>) : BaseModel()

data class ResContentList(var list: ArrayList<ContentsInfo>) : BaseModel()

data class ResUserLogin(var userInfo:UserInfo?) : BaseModel()

data class ResRichResult(var resultInfo: ResultInfo?) : BaseModel()