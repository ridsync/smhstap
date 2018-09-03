package com.rasset.shmstab.model

import java.util.*

/**
 * Created by devok on 2018-08-31.
 */
open class BaseInfo {
    var seq: Int = 0
}

data class ContentsInfo(var title: String = "",
                        var imgPath: String = "",
                        var regDate: Long) : BaseInfo()