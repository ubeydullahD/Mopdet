package com.mopdet.Model.PojoModels.Test;

public class Question {

    @SerializedName("mDQuestion")
    @Expose
    private MDQuestion mDQuestion;
    @SerializedName("answer")
    @Expose
    private List<Answer> answer = null;

    public MDQuestion getmDQuestion() {
        return mDQuestion;
    }

    public void setmDQuestion(MDQuestion mDQuestion) {
        this.mDQuestion = mDQuestion;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }

}
