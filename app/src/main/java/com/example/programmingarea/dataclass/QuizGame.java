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
    private String fourthAnswer;
    private Integer firstQuestId;
    private Integer secondQuestId;
    private Integer thirdQuestId;
    private Integer fourthQuestId;



    public Map<String, Object> toMap() {
        Map<String, Object> temp = new HashMap<>();

        temp.put(firstPlayer.getClass().getName(), firstPlayer);
        temp.put(secondPlayer.getClass().getName(), secondPlayer);
        temp.put(programmingLanguage.getClass().getName(), programmingLanguage);
        temp.put(firstAnswer.getClass().getName(), firstAnswer);
        temp.put(secondAnswer.getClass().getName(), secondAnswer);
        temp.put(thirdAnswer.getClass().getName(), thirdAnswer);
        temp.put(fourthAnswer.getClass().getName(), fourthAnswer);
        temp.put(firstQuestId.getClass().getName(), firstQuestId);
        temp.put(secondQuestId.getClass().getName(), secondQuestId);
        temp.put(thirdQuestId.getClass().getName(), thirdQuestId);
        temp.put(fourthQuestId.getClass().getName(), fourthQuestId);

        return temp;
    }
}
