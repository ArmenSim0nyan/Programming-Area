package com.example.programmingarea.dataclass;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuizGame {
    private String firstPlayer;
    private String secondPlayer;
    private String programmingLanguage;
    private String firstAnswer;
    private String secondAnswer;
    private String thirdAnswer;

    public Map<String, Object> toMap() {
        Map<String, Object> temp = new HashMap<>();

        temp.put(firstPlayer.getClass().getName(), firstPlayer);
        temp.put(secondPlayer.getClass().getName(), secondPlayer);
        temp.put(programmingLanguage.getClass().getName(), programmingLanguage);
        temp.put(firstAnswer.getClass().getName(), firstAnswer);
        temp.put(secondAnswer.getClass().getName(), secondAnswer);
        temp.put(thirdAnswer.getClass().getName(), thirdAnswer);

        return temp;
    }
}
