package com.example.chatApp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1)
public abstract class appDB extends RoomDatabase{
    public abstract contactDao contactDao();
}
