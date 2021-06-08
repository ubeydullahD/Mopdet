package com.mopdet.Model.PojoModels.Test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MobdetTest {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("test_name")
    @Expose
    private String testName;
    @SerializedName("explanation")
    @Expose
    private String explanation;
    @SerializedName("questions")
    @Expose
    private List<Question> questions = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

}
