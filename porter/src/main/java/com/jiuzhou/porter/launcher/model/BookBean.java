/*
 *  Copyright (c) 2016.  Project Launcher
 *  Source BookBean
 *  Author 沈煜
 *  此源码及相关文档等附件由 沈煜 编写，作者保留所有权利
 *  使用必须注明出处。
 *  The code and documents is write by the author. All rights are reserved.
 *  Use must indicate the source.
 *
 */

package com.jiuzhou.porter.launcher.model;


import android.os.Parcel;
import android.os.Parcelable;

public class BookBean implements Parcelable{
    private String name;
    private int icon;
    private String path;

    public BookBean() {
    }

    public BookBean(String name, int icon, String path) {
        this.name = name;
        this.icon = icon;
        this.path = path;
    }

    protected BookBean(Parcel in) {
        name = in.readString();
        icon = in.readInt();
        path = in.readString();
    }

    public static final Creator<BookBean> CREATOR = new Creator<BookBean>() {
        @Override
        public BookBean createFromParcel(Parcel in) {
            return new BookBean(in);
        }

        @Override
        public BookBean[] newArray(int size) {
            return new BookBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(icon);
        dest.writeString(path);
    }
}
