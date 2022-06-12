package com.example.ex3;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class userWithContacts {
    @Embedded
    public user user;
    @Relation(
            parentColumn = "username",
            entityColumn = "creatorUsername"
    )
    public List<Contact> contacts;
}
