package com.mopdet.Model.PojoModels.BaseTest;

public class Datum {

    @SerializedName("test_id")
    @Expose
    private Integer testId;
    @SerializedName("test_name")
    @Expose
    private String testName;

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

}
