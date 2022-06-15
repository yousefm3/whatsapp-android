package com.example.ex3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ex3.Contact;
import com.example.ex3.R;


import java.util.List;

public class contactsListAdapter extends RecyclerView.Adapter<contactsListAdapter.contactViewHolder> {


    class contactViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView contactImage;
        private contactViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameofuser);
            contactImage = itemView.findViewById(R.id.imageviewofuser);
        }

    }
    private List<Contact> contacts;
    private final LayoutInflater mInflater;
    public contactsListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public contactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.chatviewlayout, parent, false);
        return new contactViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(contactViewHolder holder, int position) {
        if (contacts != null) {
            Contact current = contacts.get(position);
            holder.name.setText(current.getContactDisplayName());
            holder.contactImage.setImageResource(current.getImageID());
        }
    }

    public List<Contact> getContacts() {
        return contacts;
    }
    public void setContacts(List<Contact> s) {
        contacts = s;
        notifyDataSetChanged();
    }
    public int getItemCount() {
        if(contacts != null) {
            return contacts.size();
        }
        return 0;
    }
}