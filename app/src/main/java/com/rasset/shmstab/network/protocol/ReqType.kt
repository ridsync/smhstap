package com.rasset.shmstab.network.protocol

/**
 * Created by devok on 2018-09-03.
 */

class ReqType {
    companion object {

        const val REQUEST_TYPE_GET_APIBASE_URL = 99991

        const val REQUEST_TYPE_POST_USER_LOGIN = 700000
        const val REQUEST_TYPE_GET_CUSOMER_LIST = REQUEST_TYPE_POST_USER_LOGIN + 1

    }
}