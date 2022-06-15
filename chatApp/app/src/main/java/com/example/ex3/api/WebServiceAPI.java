package com.example.ex3.api;

import com.example.ex3.Contact;
import com.example.ex3.user;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WebServiceAPI {
 @GET("contacts")
 Call<List<Contact>> getContacts();

 @GET("users")
 Call<List<user>> getUsers();

 @POST("contacts")
 Call<Void> createContact(@Body Contact post);

 @DELETE("contacts/{id}")
 Call<Void> deleteContact(@Path("id") int id);
 }