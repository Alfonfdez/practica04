package com.afr.resthelloworld;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // I - Declarar las variables
    private TextView textViewResult;
    private JsonPlaceHolderApi jsonPlaceHolderApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = (TextView) findViewById(R.id.text_view_result);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com").addConverterFactory(GsonConverterFactory.create()).build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        getComments();
    }

    private void getComments(){
        Call<List<Comment>> call = jsonPlaceHolderApi.getComments(3);

        call.enqueue(new CallBack<List<Comment>>(){

            @Override
            public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response){

                if(!response.isSuccesful()){
                    textViewResult.setText("Code: "+ response.code());
                    return;
                }

                List<Comment> comments = response.body();

                for(Comment comment: comments){
                    String content = "";
                    content += "ID: " + comment.getId() + "\n";
                    content += "Post_ID: " + comment.getPostId() + "\n";
                    content += "ID: " + comment.getName() + "\n";
                    content += "ID: " + comment.getEmail() + "\n";
                    content += "ID: " + comment.getText() + "\n\n";

                    textViewResult.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Comment>> call, Throwable t){
                textViewResult.setText(t.getMessage());
            }
        });

    }

}
