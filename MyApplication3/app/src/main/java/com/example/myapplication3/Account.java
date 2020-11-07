package com.example.activityapplication;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

import java.io.Serializable;


public class Account implements Parcelable, Serializable {
    //static final long serialVersionUID = -6343169151696340687L;
    private String userName;
    private String gender;
    private int age;

    @NonNull
    @Override
    public String toString() {
        return "Account[userName=" +userName +
                ", gender=" + gender +
                ", age=" +age + "]";
    }

    public Account(String name, String gender, int age) {
        this.userName = name;
        this.gender = gender;
        this.age = age;
    }

    public Account(Parcel in) {
        userName = in.readString();
        gender = in.readString();
        age = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeString(gender);
        dest.writeInt(age);
    }

    public static Parcelable.Creator<Account> CREATOR = new Parcelable.Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel source) {
            return new Account(source);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

}
