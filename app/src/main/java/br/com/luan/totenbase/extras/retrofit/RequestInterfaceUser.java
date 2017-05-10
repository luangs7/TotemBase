package br.com.luan.totenbase.extras.retrofit;


import java.util.Map;

import br.com.luan.totenbase.extras.model.Auth;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

/**
 * Created by Luan on 29/07/2016.
 */
public interface RequestInterfaceUser {

//  <---------------  POST requests --------------------->


    @POST("auth")
    Call<Auth> requestAuth(@Body RequestBody json);

    @POST("surveys")
    Call<ResponseBody> requestSend(@HeaderMap Map<String, String> headers, @Body RequestBody json);


}
