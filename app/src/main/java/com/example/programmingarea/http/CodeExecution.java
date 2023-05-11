package com.example.programmingarea.http;

import com.example.programmingarea.dataclass.CodeExecutionBody;
import com.example.programmingarea.dataclass.CodeExecutionResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CodeExecution {
    @POST("execute")
    public Call<CodeExecutionResponse> executeCode(@Body CodeExecutionBody body);
}
