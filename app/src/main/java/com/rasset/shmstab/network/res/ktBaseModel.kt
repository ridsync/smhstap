package com.rasset.shmstab.network.res

import com.rasset.shmstab.model.ContentsInfo
import java.util.ArrayList

/**
 * Created by devok on 2018-08-31.
 */
open class BaseModel {
    var resVal: Int = 0
    var resMsg: String? = null
}

data class ResContentList(var list: ArrayList<ContentsInfo>) : BaseModel() {

}
