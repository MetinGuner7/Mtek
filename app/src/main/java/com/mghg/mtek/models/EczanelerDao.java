package com.mghg.mtek.models;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface EczanelerDao {
    @Headers({"authorization: apikey 5FwjwfMVmZRiyYHFsMtxUW:3RztPBnK48dk4n6l8gVYog",
            "content-type: application/json"})
    @GET("health/dutyPharmacy?ilce=%C3%87ankaya&il=Ankara")
    Call<Eczaneler> tumEczaneler();

    @Headers({"authorization: apikey 5FwjwfMVmZRiyYHFsMtxUW:3RztPBnK48dk4n6l8gVYog",
            "content-type: application/json"})
    @POST("health/dutyPharmacy")
    @FormUrlEncoded
    Call<Eczaneler> ilArama(@Field("kisi_ad") String il_adi);
}
