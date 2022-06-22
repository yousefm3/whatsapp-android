package com.example.ex3;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact {
    @NonNull
    @PrimaryKey
    private String contactUsername;
    private String creatorUsername;
    private String contactDisplayName;
    private String contactServer;
    private String imageID;

    public Contact(String contactUsername, String creatorUsername,
                   String contactDisplayName, String contactServer, String imageID) {
        this.contactUsername = contactUsername;
        this.creatorUsername = creatorUsername;
        this.contactDisplayName = contactDisplayName;
        this.contactServer = contactServer;
        this.imageID = imageID;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getContactUsername() {
        return contactUsername;
    }

    public void setContactUsername(String contactUsername) {
        this.contactUsername = contactUsername;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public String getContactDisplayName() {
        return contactDisplayName;
    }

    public void setContactDisplayName(String contactDisplayName) {
        this.contactDisplayName = contactDisplayName;
    }

    public String getContactServer() {
        return contactServer;
    }

    public void setContactServer(String contactServer) {
        this.contactServer = contactServer;
    }
}


