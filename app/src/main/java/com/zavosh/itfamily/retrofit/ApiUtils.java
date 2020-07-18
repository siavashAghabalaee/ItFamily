package com.zavosh.itfamily.retrofit;

public class ApiUtils {
    private ApiUtils() {}

    public static final String BASE_URL = "https://itfamili.zavoshsoftware.com/";

    public static APIService getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}
