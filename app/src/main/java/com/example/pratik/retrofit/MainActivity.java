package com.example.pratik.retrofit;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pratik.retrofit.Pojo.AddPostRequest;
import com.example.pratik.retrofit.Pojo.AddPostResponse;
import com.example.pratik.retrofit.Pojo.UserDetail;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    String url = "https://jsonplaceholder.typicode.com";
    CustomBaseAdapter customBaseAdapter;
    DatabaseHandler databaseHandler;
    ListView listView;
    ProgressDialog mProgressDialog;


    EditText edt_title, edt_body, edt_userId;
    Button btn_addPost;
    TextView tv_addPostResult;

    //    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list_view);
        databaseHandler = new DatabaseHandler(MainActivity.this);

        edt_title = (EditText) findViewById(R.id.edt_title);
        edt_body = (EditText) findViewById(R.id.edt_body);
        edt_userId = (EditText) findViewById(R.id.edt_userId);
        btn_addPost = (Button) findViewById(R.id.btn_addPost);
        tv_addPostResult = (TextView) findViewById(R.id.tv_addPostResult);

        btn_addPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAddPostAPI();
            }
        });

        if (isNetworkAvailable()) {
            if (true)
                return;

            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Android JSON Parse Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitArrayAPI service = retrofit.create(RetrofitArrayAPI.class);

            Call<List<UserDetail>> call = service.getStudentDetails();

            call.enqueue(new Callback<List<UserDetail>>() {
                @Override
                public void onResponse(Call<List<UserDetail>> call, Response<List<UserDetail>> response) {
                    mProgressDialog.dismiss();
                    List<UserDetail> userDetails = response.body();
                    databaseHandler.addContact(userDetails);
                    databaseHandler.getAllContacts();
                    customBaseAdapter = new CustomBaseAdapter(MainActivity.this, userDetails);
                    listView.setAdapter(customBaseAdapter);
                    customBaseAdapter.notifyDataSetChanged();


                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            UserDetail userDetail = (UserDetail) customBaseAdapter.getItem(position);
                            CustomBaseAdapter customBaseAdapter = (CustomBaseAdapter) parent.getAdapter();
                            customBaseAdapter.getItem(position);
                            String s1 = userDetail.getAddress().getGeo().getLat();
                            String s2 = userDetail.getAddress().getGeo().getLng();
                            Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=-" + s1 + "," + s2 + " (" + userDetail.getName() + ")"));
                            startActivity(intent);
                            Toast.makeText(MainActivity.this, "lat" + s1 + "lng" + s2, Toast.LENGTH_SHORT).show();
                        }
                    });
                    customBaseAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<List<UserDetail>> call, Throwable t) {

                }
            });
        } else {
            List<UserDetail> userDetails = databaseHandler.getAllContacts();
            customBaseAdapter = new CustomBaseAdapter(MainActivity.this, userDetails);
            listView.setAdapter(customBaseAdapter);
            customBaseAdapter.notifyDataSetChanged();


            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    UserDetail userDetail = (UserDetail) customBaseAdapter.getItem(position);
                    CustomBaseAdapter customBaseAdapter = (CustomBaseAdapter) parent.getAdapter();
                    customBaseAdapter.getItem(position);
                    String s1 = userDetail.getAddress().getGeo().getLat();
                    String s2 = userDetail.getAddress().getGeo().getLng();
                    Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=-" + s1 + "," + s2 + " (" + userDetail.getName() + ")"));
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "lat" + s1 + "lng" + s2, Toast.LENGTH_SHORT).show();
                }
            });
            customBaseAdapter.notifyDataSetChanged();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


    private void callAddPostAPI() {
        if (isNetworkAvailable()) {

            mProgressDialog = new ProgressDialog(MainActivity.this);
            // Set progressdialog title
            mProgressDialog.setTitle("Android JSON Parse Tutorial");
            // Set progressdialog message
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            // Show progressdialog
            mProgressDialog.show();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            RetrofitArrayAPI service = retrofit.create(RetrofitArrayAPI.class);

            AddPostRequest addPostRequest = new AddPostRequest(edt_title.getText().toString().trim(),
                    edt_body.getText().toString().trim(),
                    Long.parseLong(edt_userId.getText().toString().trim()));
            Call<AddPostResponse> call = service.addPost(addPostRequest);
            call.enqueue(new Callback<AddPostResponse>() {
                @Override
                public void onResponse(Call<AddPostResponse> call, Response<AddPostResponse> response) {
                    mProgressDialog.dismiss();
                    if (response != null && response.body() != null) {
                        AddPostResponse addPostResponse = response.body();

                        tv_addPostResult.setText("Result ---> \nTitle - " + addPostResponse.getTitle() +
                                "\nBody - " + addPostResponse.getBody() +
                                "\nPost Id - " + addPostResponse.getId());
                    }
                }

                @Override
                public void onFailure(Call<AddPostResponse> call, Throwable t) {
                    mProgressDialog.dismiss();
                }
            });
        }
    }
}
