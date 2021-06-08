package com.mopdet.Model.PojoModels.Test;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

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
