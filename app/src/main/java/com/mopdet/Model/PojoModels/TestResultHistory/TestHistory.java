package com.mopdet.Model.PojoModels.TestResultHistory;

public class TestHistory {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("test_id")
    @Expose
    private Integer testId;
    @SerializedName("test_name")
    @Expose
    private String testName;
    @SerializedName("score")
    @Expose
    private Integer score;
    @SerializedName("test_created_at")
    @Expose
    private String testCreatedAt;
    @SerializedName("test_sonuc_text")
    @Expose
    private String testSonucText;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getTestCreatedAt() {
        return testCreatedAt;
    }

    public void setTestCreatedAt(String testCreatedAt) {
        this.testCreatedAt = testCreatedAt;
    }

    public String getTestSonucText() {
        return testSonucText;
    }

    public void setTestSonucText(String testSonucText) {
        this.testSonucText = testSonucText;
    }

}
