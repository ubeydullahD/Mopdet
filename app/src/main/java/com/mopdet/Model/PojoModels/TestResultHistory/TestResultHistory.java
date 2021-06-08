package com.mopdet.Model.PojoModels.TestResultHistory;

public class TestResultHistory {

    @SerializedName("testHistory")
    @Expose
    private List<TestHistory> testHistory = null;

    public List<TestHistory> getTestHistory() {
        return testHistory;
    }

    public void setTestHistory(List<TestHistory> testHistory) {
        this.testHistory = testHistory;
    }

}
