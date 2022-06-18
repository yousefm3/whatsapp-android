package com.example.ex3.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ex3.AppDB;
import com.example.ex3.Contact;
import com.example.ex3.Message;
import com.example.ex3.api.ContactAPI;
import com.example.ex3.loginActivity;
import com.example.ex3.userDao;

import java.util.LinkedList;
import java.util.List;

public class ChatRepository {
    private userDao dao;
    private ChatData ChatData;
    private ContactAPI api;

    public ChatRepository() {
        dao = loginActivity.db.userDao();
        ChatData = new ChatData();
        api = new ContactAPI();

    }


    class ChatData extends MutableLiveData<List<Message>> {


        public ChatData() {
            super();
            if(dao.getMessages(loginActivity.userName).messages.size() > 0)
                setValue(dao.getMessages(loginActivity.userName).messages);
            else {
                setValue(new LinkedList<>());
            }
        }

        @Override
        protected void onActive() {
            super.onActive();

            new Thread(() ->
            {
                ChatData.postValue(dao.getMessages(loginActivity.userName).messages);

            }).start();

        }
    }
    public LiveData<List<Message>> getAll() {
        return ChatData;
    }

}