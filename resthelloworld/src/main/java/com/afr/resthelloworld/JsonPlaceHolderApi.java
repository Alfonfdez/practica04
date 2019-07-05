package com.afr.resthelloworld;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {

    @GET("posts")
    Call<List<Post>> getPosts();
    //Resuelve la dirección URL: https://jsonplaceholder.typicode.com/posts

    @GET("posts/{id}/comments")
    Call<List<Comment>> getComments(@Path("id") int postId);
    //Resuelve la dirección URL: https://jsonplaceholder.typicode.com/posts/10/comments

    @GET("posts")
    Call<List<Post>> getPosts(@Query("userId") int userId);
    //Resuelve la dirección URL: https://jsonplaceholder.typicode.com/posts?userId=10

    @POST("posts/{id}/comments")
    Call<Post> createPost(@Body Post post);

}
