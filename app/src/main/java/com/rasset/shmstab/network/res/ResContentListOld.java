package com.rasset.shmstab.network.res;

import com.rasset.shmstab.model.ContentsInfo;

import java.util.ArrayList;

/**
 * Created by andman on 2015-12-03.
 */
public class ResContentListOld extends BaseModel {

private ArrayList<ContentsInfo> list;

public ArrayList<ContentsInfo> getlist(){
        return list;
        }

public void setlist(ArrayList<ContentsInfo> list) {
        this.list = list;
        }
        }