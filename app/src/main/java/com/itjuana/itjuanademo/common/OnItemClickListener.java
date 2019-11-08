package com.itjuana.itjuanademo.common;

import com.itjuana.itjuanademo.data.room.entity.MessageEntity;
import com.itjuana.itjuanademo.data.room.entity.UserEntity;

/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */
public interface OnItemClickListener {
    interface OnMessageClickedListener {
        void OnClick(MessageEntity messageEntity);
    }

    interface OnUserClickedListener {
        void OnCLick(UserEntity userEntity);
    }
}
