package retrofit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.feucui.seektreasure.R;
import entity.User;
import entity.RegisterResult;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/11/8.
 */
public class RetrofitActivity extends Activity {
    private  String TAG="ZJ";
    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_http);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn)
    public void onClick() {
//        RetrofitClient.getInatance().getData().enqueue(new Callback<Result>() {
//            @Override
//            public void onResponse(Call<Result> call, Response<Result> response){//主线程
//                String name = Thread.currentThread().getName();
//                Log.e(TAG, "onResponse: " +name+"----------re="+response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<Result> call, Throwable t) {//主线程
//                Log.e(TAG, "onFailure: " );
//            }
//        });
        User user = new User("qjd","654321");
RetrofitClient.getInatance().register(user).enqueue(new Callback<RegisterResult>() {
    @Override
    public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
        Log.e(TAG, "onResponse: "+response.body().toString() );
    }
    @Override
    public void onFailure(Call<RegisterResult> call, Throwable t) {
        Log.e(TAG, "onFailure: " );
    }
});
    }
}
