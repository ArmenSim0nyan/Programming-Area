package com.example.programmingarea.dataclass;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CodeExecutionResponse {
    @SerializedName("output")
    String output;
    @SerializedName("statusCode")
    int statusCode;
    @SerializedName("memory")
    String memory;
    @SerializedName("cpuTime")
    String cpuTime;
    @SerializedName("compilationStatus")
    Object compilationStatus;
}
