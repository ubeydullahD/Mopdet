package com.mopdet.Model.PojoModels.Test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MDQuestion {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("test_id")
    @Expose
    private Integer testId;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("question_id")
    @Expose
    private Integer questionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

}
