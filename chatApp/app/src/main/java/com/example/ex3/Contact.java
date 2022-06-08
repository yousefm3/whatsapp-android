package com.example.ex3;

import android.widget.ImageView;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    private String UserName;
    private String FriendUserName;
    private ImageView image;

    public Contact(int id, String username, String friendusername) {
        Id = id;
        UserName = username;
        FriendUserName = friendusername;
    }
    public Contact(int id, String username, ImageView image) {
        Id = id;
        this.image = image;
        UserName = username;
    }
    public Contact(int id, String username) {
        Id = id;
        UserName = username;
    }
    public Contact(){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFriendUserName() {
        return FriendUserName;
    }

    public void setFriendUserName(String friendUserName) {
        FriendUserName = friendUserName;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "Id=" + Id +
                ", UserName='" + UserName + '\'' +
                ", FriendUserName='" + FriendUserName + '\'' +
                '}';
    }
}