package com.rasset.shmstab.network.protocol

/**
 * Created by devok on 2018-09-03.
 */

class ReqType {
    companion object {

        const val REQUEST_TYPE_GET_APIBASE_URL = 99991

        const val REQUEST_TYPE_GET_USER_LIST = 700000
        const val REQUEST_TYPE_GET_USER_LIST_ALL = REQUEST_TYPE_GET_USER_LIST + 1

    }
}