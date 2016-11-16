package network;

import entity.LoginResult;
import entity.User;
import entity.RegisterResult;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;


/**
 * 封装网络请求
 * Created by Administrator on 2016/11/8.
 */
public class RetrofitClient implements UserRetrofitApi {


   private static RetrofitClient retrofitClient;
    private static final String BASE_URL = "http://admin.syfeicuiedu.com";
    private final UserRetrofitApi userRetrofitApi;


    private RetrofitClient(){
   Retrofit retrofit =  new Retrofit.Builder()
            .baseUrl(BASE_URL)
           //添加Gson转换器---需要添加依赖:com.squareup.retrofit2:converter-gson
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        //生成UserRetrofitApi接口，它使用Gson库来做转换
        userRetrofitApi = retrofit.create(UserRetrofitApi.class);
    }

    public static synchronized RetrofitClient getInatance(){
    if (retrofitClient==null){
        retrofitClient = new RetrofitClient();
    }
        return retrofitClient;
    }
    @Override
    public Call<RegisterResult> register(@Body User user) {
        return userRetrofitApi.register(user);
    }

    @Override
    public Call<LoginResult> login(@Body User user) {
        return userRetrofitApi.login(user);
    }

}
