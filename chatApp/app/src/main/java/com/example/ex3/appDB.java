package com.example.ex3;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Contact.class}, version = 1)
public abstract class appDB extends RoomDatabase{
    public abstract contactDao contactDao();
}
