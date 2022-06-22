package com.example.ex3.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.ex3.AppDB;
import com.example.ex3.Contact;
import com.example.ex3.ContactWithMessages;
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
    private String contactName;
    private String contactImage;

    public ChatRepository(String contact, String image) {
        dao = loginActivity.userDao;
        contactName = contact;
        ChatData = new ChatData();
        api = new ContactAPI();
        contactImage = image;
    }


    class ChatData extends MutableLiveData<List<Message>> {


        public ChatData() {
            super();
            ContactWithMessages t = dao.getMessages(contactName);
            if(t != null)
                setValue(t.messages);
            else {
                setValue(new LinkedList<>());
            }
        }

        @Override
        protected void onActive() {
            super.onActive();

            new Thread(() ->
            {
                if(dao.getMessages(loginActivity.userName) != null)
                    ChatData.postValue(dao.getMessages(loginActivity.userName).messages);

            }).start();

        }
    }
    public MutableLiveData<List<Message>> getAll() {
        return ChatData;
    }

}