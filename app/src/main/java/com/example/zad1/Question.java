package com.example.zad1;

public class Question {

    private int questionId;
    private boolean trueQuestion;

    public Question(int questionId, boolean trueQuestion){
        this.questionId = questionId;
        this.trueQuestion = trueQuestion;
    }

    public boolean isTrueAnswer(){
        return this.trueQuestion;
    }

    public int getQuestionId(){
        return this.questionId;
    }
}
