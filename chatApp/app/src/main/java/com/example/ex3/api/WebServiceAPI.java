package com.example.ex3.api;

import com.example.ex3.Contact;
import com.example.ex3.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {
 @POST("register")
 Call<String> register(@Body user u);

 @POST("login")
 Call<String> login(@Body user u);

 @GET("contacts")
 Call<List<Contact>> getContacts(@Body String username);

 @GET("users")
 Call<List<user>> getUsers();

 @POST("contacts")
 Call<Void> createContact(@Body Contact post);

 @DELETE("contacts/{id}")
 Call<Void> deleteContact(@Path("id") int id);
}