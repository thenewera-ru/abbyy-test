package com.example.abbyyfetchingapi.Services;

import com.example.abbyyfetchingapi.Model.Answer;
import com.example.abbyyfetchingapi.Model.Question;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface StackoverflowAPI {

    String BASEURL = "https://api.stackexchange.com";




    @GET("/2.2/questions?order=desc&sort=votes&site=stackoverflow&tagged=android&filter=withbody")
    Call<ListWrapper<Question>> getQuestions();

    @GET("/2.2/questions/{id}/answers?order=desc&sort=votes&site=stackoverflow")
    Call<ListWrapper<Answer>> getAnswersForQuestion(@Path("id") String questionId);

}
