package com.example.ex3.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ex3.AppDB;
import com.example.ex3.Contact;
import com.example.ex3.api.ContactAPI;
import com.example.ex3.loginActivity;
import com.example.ex3.userDao;
import com.example.ex3.userWithContacts;

import java.util.LinkedList;
import java.util.List;

public class ContactsRepository {
    private userDao dao;
    private ContactListData ContactListData;
    private ContactAPI api;

    public ContactsRepository() {
        dao = loginActivity.db.userDao();
        ContactListData = new ContactListData();
        api = new ContactAPI();

    }


    class ContactListData extends MutableLiveData<List<Contact>> {


        public ContactListData() {
            super();
            if (dao.getContacts2() != null)
                setValue(dao.getContacts2());
        }

        @Override
        protected void onActive() {
            super.onActive();

            new Thread(() ->
            {
                if (dao.getContacts(loginActivity.userName) != null)
                    ContactListData.postValue(dao.getContacts(loginActivity.userName).contacts);

            }).start();

        }
    }

    public LiveData<List<Contact>> getAll() {
        return ContactListData;
    }

}