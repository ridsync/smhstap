package com.rasset.shmstab.network;

import com.rasset.shmstab.network.task.NetworkTask;

/**
 * @author okc
 * @version 1.0
 * @see
 * @since 2015-12-20.
 */
public interface OnNetworkStateListener {
    public void onStateSucess(int reqType, NetworkTask<?> task);
    public void onStateFail(int retCode, int reqType, NetworkTask<?> task);
}
