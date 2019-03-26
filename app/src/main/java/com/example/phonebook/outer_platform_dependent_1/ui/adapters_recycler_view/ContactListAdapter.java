package com.example.phonebook.outer_platform_dependent_1.ui.adapters_recycler_view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.phonebook.App;
import com.example.phonebook.R;
import com.example.phonebook.middle_domain_2.presenter.output_ports.IAdapterContactsListener;
import com.example.phonebook.inner_model_3.ContactShortInfo;
import com.example.phonebook.middle_domain_2.presenter.input_ports.IPresenterContactList;

import javax.inject.Inject;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder> implements IAdapterContactsListener {
    @Inject
    IPresenterContactList presenterContactList;

    public ContactListAdapter() {
        App.getComponent().inject(this);
        presenterContactList.setAdapterListener(this);
    }

    @Override
    public void onUpdate() {
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.contact_list_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        viewHolder.fillItemViews(position);
    }

    @Override
    public int getItemCount() {
        return presenterContactList.getItemCount();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout whole_item;
        TextView name;
        TextView phone;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews(itemView);
            whole_item.setOnClickListener(v -> {
                int pos = getAdapterPosition();
                if (pos != RecyclerView.NO_POSITION)
                presenterContactList.onListItemClick(pos);
            });
        }

        private void initViews(View itemView) {
            whole_item = itemView.findViewById(R.id.whole_item);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);

        }

        public void fillItemViews(int position) {
            ContactShortInfo contactListItem = presenterContactList.getContactListItem(position);
            if (contactListItem != null) {
                name.setText(contactListItem.getName());
                phone.setText(contactListItem.getPhoneNo());
            } else {
                name.setText("");
                phone.setText("");
            }
        }
    }
}
