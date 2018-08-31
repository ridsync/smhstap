package com.rasset.shmstab.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Created by andman on 2015-12-03.
 */
@Data @ToString @EqualsAndHashCode(callSuper = false)
public class ContentInfo {

    public long list_type;
    public long list_seq;
    public long id;
    public long user_id;
    @SerializedName("title")
    public String title;// userName
    @SerializedName("imgPath")
    public String imgPath;// userName
    @SerializedName("date")
    public String regDate;// userName

}