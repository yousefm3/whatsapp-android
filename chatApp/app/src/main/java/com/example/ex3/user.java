package com.example.ex3;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class user {
    @NonNull
    @PrimaryKey
    private String username;
    private String displayName;
    private int image;
    private String server;
    private String token;

    public user(String username, String displayName, int image, String server, String token) {
        this.username = username;
        this.displayName = displayName;
        this.image = image;
        this.server = server;
        this.token = token;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getServer() {
        return server;
    }

    public String getToken() {
        return token;
    }

    public void setServer(String server) {
        this.server = server;
    }
}
