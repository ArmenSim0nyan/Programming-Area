package com.example.programmingarea.dataclass;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@Getter
@Setter
public class CodeExecutionBody {
    private String script;
    private String language;
    private String versionIndex;
    private String clientId;
    private String clientSecret;
}
