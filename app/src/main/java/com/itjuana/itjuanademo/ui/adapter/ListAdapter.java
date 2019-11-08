package com.itjuana.itjuanademo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itjuana.itjuanademo.R;
import com.itjuana.itjuanademo.common.OnItemClickListener.OnMessageClickedListener;
import com.itjuana.itjuanademo.data.room.entity.MessageEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<MessageEntity> list;
    private OnMessageClickedListener listener;

    public ListAdapter(List<MessageEntity> list, OnMessageClickedListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, null, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bindItems(list.get(position), listener);

    }

    public void setData(List<MessageEntity> newList) {
        if (newList != null) {
            this.list = newList;
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.linearItem)
        LinearLayout linearItem;

        @BindView(R.id.tvUser)
        TextView tvUser;

        @BindView(R.id.tvMessage)
        TextView tvMessage;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItems(MessageEntity messageEntity, OnMessageClickedListener onMessageClickedListener) {
            tvUser.setText(messageEntity.getName());
            tvMessage.setText(messageEntity.getBody());
            itemView.setOnClickListener(v -> {
                onMessageClickedListener.OnClick(messageEntity);
            });


        }
    }
}
