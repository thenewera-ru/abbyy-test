package com.example.abbyyfetchingapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.abbyyfetchingapi.Adapter.RecyclerViewAdapter;
import com.example.abbyyfetchingapi.Model.Answer;
import com.example.abbyyfetchingapi.Model.ApiError;
import com.example.abbyyfetchingapi.Model.FakeDataProvider;
import com.example.abbyyfetchingapi.Model.Question;
import com.example.abbyyfetchingapi.Services.ListWrapper;
import com.example.abbyyfetchingapi.Services.ServiceGenerator;
import com.example.abbyyfetchingapi.Services.StackoverflowAPI;
import com.example.abbyyfetchingapi.helpers.ErrorUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter.Factory.*;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity {


    // network
    private StackoverflowAPI api;



    // ui
    private Spinner questionsSpinner;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        questionsSpinner = (Spinner)findViewById(R.id.questions_spinner);
        questionsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                Toast.makeText(HomeActivity.this, "spinner item selected", Toast.LENGTH_SHORT).show();
                Question q = (Question)parent.getAdapter().getItem(i);
                api.getAnswersForQuestion(q.questionId).enqueue(answersCallback);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        recyclerView = (RecyclerView)findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));



        api = ServiceGenerator.createService(StackoverflowAPI.class);
        api.getQuestions().enqueue(questionsCallback);
    }


    // Callback for question
    Callback<ListWrapper<Question>> questionsCallback = new Callback<ListWrapper<Question>>() {
        @Override
        public void onResponse(Call<ListWrapper<Question>> call, Response<ListWrapper<Question>> response) {
            if (response.isSuccessful()) {
                ListWrapper<Question> questions = response.body();
                ArrayAdapter<Question> arrayAdapter = new ArrayAdapter<>(HomeActivity.this, android.R.layout.simple_spinner_dropdown_item, questions.items);
                questionsSpinner.setAdapter(arrayAdapter);
            } else {
                ApiError error = ErrorUtils.parseError(response, ServiceGenerator.getRetrofit());
                Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ListWrapper<Question>> call, Throwable throwable) {
            Toast.makeText(HomeActivity.this, "No internet connection...", Toast.LENGTH_SHORT).show();
        }
    };

    // Callback for answers

    Callback<ListWrapper<Answer>> answersCallback = new Callback<ListWrapper<Answer>>() {
        @Override
        public void onResponse(Call<ListWrapper<Answer>> call, Response<ListWrapper<Answer>> response) {
            if (response.isSuccessful()) {
                List<Answer> data = new ArrayList<>();
                data.addAll(response.body().items);

                // Binding the data to recyclerView
                recyclerView.setAdapter(new RecyclerViewAdapter(data));
            } else {
                ApiError error = ErrorUtils.parseError(response, ServiceGenerator.getRetrofit());
                Toast.makeText(HomeActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ListWrapper<Answer>> call, Throwable throwable) {
            Toast.makeText(HomeActivity.this, "No internet connection...", Toast.LENGTH_SHORT).show();
        }
    };

}
