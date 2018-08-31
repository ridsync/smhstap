package com.rasset.shmstab.network;

import com.rasset.shmstab.network.res.BaseModel;

/**
 * @author okc
 * @version 1.0
 * @see
 * @since 2015-12-19.
 */
public interface OnNetworkListener{
    public void onNetSuccess(BaseModel data, int nReqType);
    public void onNetFail(int retCode, String strErrorMsg, int nReqType);

    public void onProgresStart(int nReqType);
    public void onProgresStop(int nReqType);
}
