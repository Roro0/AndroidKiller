package activity;

import com.google.gson.Gson;

import entity.User;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 *
 * Created by Administrator on 2016/11/7.
 */
public class NetClient implements UserApi{
    private   OkHttpClient okHttpClient;
    private static NetClient client;
    private NetClient(){
        okHttpClient = new OkHttpClient();
        /**
         * okHttp添加了拦截器功能，可以将请求和响应拦截下来显示出来。
         * 1.添加依赖
         * 2.使用：给OkHttp使用上
         */
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }
    public static synchronized NetClient getInstance(){
        if (client==null){
            client = new NetClient();
        }
          return client;
    }

    /**
     * get请求
     * @param path
     * @return
     */
    @Override
    public Call getData(String path) {
        //构建请求
        Request request=new Request.Builder()
                .get()
                .url(path)
                .build();
        return  okHttpClient.newCall(request);
    }

    /**
     * post请求
     * @param user
     * @return
     */
    @Override
    public Call register(User user) {
        Gson gson = new Gson();
        RequestBody body = RequestBody.create(null,gson.toJson(user));
        Request request =  new Request.Builder()
                .post(body)
                .url(UserApi.URL_REGISTER)
                .build();
        return okHttpClient.newCall(request);
    }
}
 interface UserApi{
     
     String URL_REGISTER = "http://admin.syfeicuiedu.com/Handler/UserHandler.ashx?action=register";
     Call getData(String path);
     Call register(User user);
}