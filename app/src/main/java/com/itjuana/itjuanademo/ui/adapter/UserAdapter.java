package com.itjuana.itjuanademo.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.itjuana.itjuanademo.R;
import com.itjuana.itjuanademo.common.OnItemClickListener.OnUserClickedListener;
import com.itjuana.itjuanademo.data.room.entity.UserEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ListViewHolder> {

    private List<UserEntity> list;
    private OnUserClickedListener listener;

    public UserAdapter(List<UserEntity> list, OnUserClickedListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, null, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.bindItems(list.get(position), listener);

    }

    public void setData(List<UserEntity> newList) {
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

        @BindView(R.id.tvUser)
        TextView tvUser;

        @BindView(R.id.tvMessage)
        TextView tvMessage;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindItems(UserEntity userEntity, OnUserClickedListener onMessageClickedListener) {
            tvUser.setText(userEntity.getName());
            itemView.setOnClickListener(v -> {
                onMessageClickedListener.OnCLick(userEntity);
            });

        }

    }
}
