package com.example.ex3.api;

import androidx.lifecycle.MutableLiveData;

import com.example.ex3.Contact;
import com.example.ex3.MyApplication;
import com.example.ex3.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ContactAPI {
 Retrofit retrofit;
 WebServiceAPI webServiceAPI;
 public static List<Contact> contacts;

  public ContactAPI() {
  retrofit = new Retrofit.Builder()
  .baseUrl(MyApplication.context.getString(R.string.BaseUrl))
  .addConverterFactory(GsonConverterFactory.create())
  .build();
  webServiceAPI = retrofit.create(WebServiceAPI.class);
  }
   public List<Contact> get(String user) {
       Call<List<Contact>> call = webServiceAPI.getContacts(user);
       call.enqueue(new Callback<List<Contact>>() {
       @Override
       public void onResponse(Call<List<Contact>> call, Response<List<Contact>> response) {
         contacts = response.body();
       }

       @Override
       public void onFailure(Call<List<Contact>> call, Throwable t) {}
       });
       return contacts;
  }
 }
