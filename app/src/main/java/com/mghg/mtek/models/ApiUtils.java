package com.mghg.mtek.models;

public class ApiUtils {

    public static final String BASEURL = "https://api.collectapi.com/";
    public static EczanelerDao getEczanelerDaoInterfae(){
        return RetrofitClient.getCLient(BASEURL).create(EczanelerDao.class);
    }
}
