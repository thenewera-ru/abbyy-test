package com.example.abbyyfetchingapi.Model;

import com.google.gson.annotations.SerializedName;

public class Answer {

    @SerializedName("answer_id")
    public int answerId;

    @SerializedName("is accepted")
    public boolean accepted;

    int score;

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", accepted=" + accepted +
                ", score=" + score +
                '}';
    }
}
