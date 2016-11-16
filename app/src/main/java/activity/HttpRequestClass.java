package activity;

import com.baidu.mapapi.http.HttpClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/7.
 */
public class HttpRequestClass {
    private String path;

    public HttpRequestClass() {
    }

    public void getData(String path){
        this.path=path;
        OkHttpClient client = new OkHttpClient();
        //get请求
//        Request request = new Request.Builder().url(path)
//                .get().build();
        //post请求
        FormBody formBody = new FormBody.Builder()//okHttp3.X，formBody已经代替了FormEncodingBuilder
                .add("","")//请求参数
                .build();
        Request request = new Request.Builder()
                .post(formBody)
                .url(path)
                .build();
        Call call =  client.newCall(request);
        call.enqueue(new Callback() {//这里是后台线程
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //响应
//              InputStream in = response.body().byteStream();
//             //TODO
                String out = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(out);
                    //TODO 解析json文本

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
