package com.example.pratik.retrofit;

import com.example.pratik.retrofit.Pojo.AddPostRequest;
import com.example.pratik.retrofit.Pojo.AddPostResponse;
import com.example.pratik.retrofit.Pojo.UserDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Pratik on 29-Nov-16.
 */

public interface RetrofitArrayAPI {
    @GET("/users")
    Call<List<UserDetail>> getStudentDetails();

    @POST("/posts")
    Call<AddPostResponse> addPost(@Body AddPostRequest addPostRequest);
    /*@FormUrlEncoded
    @POST("/posts")
    Call<AddPostResponse> addPost(@Field("title") String title, @Field(""));*/

    /*@GET("/comments")
    Call<List<UserDetail>> getComments(@Query("postId") String postId);*/

    /*@GET("/posts/{postId}/comments")
    Call<List<UserDetail>> getComments(@Path("postId") String postId);*/



}
