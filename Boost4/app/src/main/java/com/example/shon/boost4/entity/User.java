package com.example.shon.boost4.entity;

public class User {

    private String mUid;
    private String mName;
    private String mEmail;
    private String mLastLoc;

    public User(){

    }

    public User(String uid, String name, String email, String lastLoc) {
        this.mUid = uid;
        this.mName = name;
        this.mEmail = email;
        this.mLastLoc = lastLoc;
    }

    public String getUid() {
        return mUid;
    }

    public void setUid(String mUid) {
        this.mUid = mUid;
    }

    public String getName() {
        return mName;
    }

    public void setName(String mName) {
        this.mName = mName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    public String getLastLoc() {
        return mLastLoc;
    }

    public void setLastLoc(String mLastLoc) {
        this.mLastLoc = mLastLoc;
    }
}
