package com.example.ex3.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.ex3.Contact;
import com.example.ex3.R;
import com.example.ex3.RecyclerViewItem;


import java.util.List;

public class contactsListAdapter extends RecyclerView.Adapter<contactsListAdapter.contactViewHolder> {
    public static List<Contact> contacts;
    private final LayoutInflater mInflater;
    private RecyclerViewItem recyclerViewItem;
    public contactsListAdapter(Context context,RecyclerViewItem recyclerViewItem) {
        mInflater = LayoutInflater.from(context);
        this.recyclerViewItem = recyclerViewItem;
    }
    class contactViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final ImageView contactImage;
        private final RelativeLayout layout;

        private contactViewHolder(View itemView, RecyclerViewItem recyclerViewItem) {
            super(itemView);
            name = itemView.findViewById(R.id.nameofuser);
            contactImage = itemView.findViewById(R.id.imageviewofuser);
            layout = itemView.findViewById(R.id.layout);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewItem != null){
                        int pos = getAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION){
                            recyclerViewItem.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }


    @Override
    public contactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.chatviewlayout, parent, false);
        return new contactViewHolder(itemView,recyclerViewItem);
    }

    @Override
    public void onBindViewHolder(contactViewHolder holder, int position) {
        if (contacts != null) {
            Contact current = contacts.get(position);
            holder.name.setText(current.getContactDisplayName());
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