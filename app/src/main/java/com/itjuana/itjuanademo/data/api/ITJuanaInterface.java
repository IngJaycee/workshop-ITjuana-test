package com.itjuana.itjuanademo.data.api;

import com.itjuana.itjuanademo.data.room.entity.MessageEntity;
import com.itjuana.itjuanademo.data.room.entity.UserEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

import okhttp3.ResponseBody;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by jutorres on 11/2019.
 * Insulet Corporation
 * Andromeda
 */
public interface ITJuanaInterface {
    @GET("user")
    public Single<List<UserEntity>> getAllUsers();

    @POST("user")//{"id":1,"name":"Alexis","description":"ITjuana Developer"}
    public Observable<Response<ResponseBody>> addUser(@Body() UserEntity user);

    @GET("comments")
    public Single<List<MessageEntity>> getAllComments();

    @PUT("comments")//{"body":"alex comment","name":"Alexis"}
    public Observable<Response<ResponseBody>> addComment(@Body() MessageEntity comment);
}
