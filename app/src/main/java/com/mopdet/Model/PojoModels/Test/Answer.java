package com.mopdet.Model.PojoModels.Test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Answer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("test_id")
    @Expose
    private Integer testId;
    @SerializedName("question_id")
    @Expose
    private Integer questionId;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("point")
    @Expose
    private Integer point;

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

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

}
