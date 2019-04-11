package com.example.abbyyfetchingapi.helpers;

import com.example.abbyyfetchingapi.HomeActivity;
import com.example.abbyyfetchingapi.MainActivity;
import com.example.abbyyfetchingapi.Model.ApiError;
import com.example.abbyyfetchingapi.Services.ServiceGenerator;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ErrorUtils {

    public static ApiError parseError(Response<?> response, Retrofit retrofit) {
        Converter<ResponseBody, ApiError> converter =
                retrofit.responseBodyConverter(ApiError.class, new Annotation[0]);
        ApiError ans;
        try {
            ans = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }

        return ans;
    }
}
