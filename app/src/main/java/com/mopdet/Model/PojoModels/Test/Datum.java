package com.mopdet.Model.PojoModels.Test;

public class Datum {

    @SerializedName("MobdetTest")
    @Expose
    private MobdetTest mobdetTest;

    public MobdetTest getMobdetTest() {
        return mobdetTest;
    }

    public void setMobdetTest(MobdetTest mobdetTest) {
        this.mobdetTest = mobdetTest;
    }

}
