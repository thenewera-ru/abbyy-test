package com.example.abbyyfetchingapi.Model;

import java.util.ArrayList;
import java.util.List;

public class FakeDataProvider {

    public static List<Question> getQuestions() {
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            Question q = new Question();
            q.questionId = String.valueOf(i) + " id";
            q.title = String.valueOf(i) + " title";
            q.body = String.valueOf(i) + " body";
            questions.add(q);
        }
        return questions;
    }

    public static List<Answer> getAnswers() {
        List<Answer> answers = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            Answer ans = new Answer();
            ans.answerId = i;
            ans.accepted = false;
            ans.score = i;
            answers.add(ans);
        }
        return answers;
    }
}
