package activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.feucui.seektreasure.R;
import inter.CallBackUi;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/7.
 */
public class TestHttp extends Activity {
    @BindView(R.id.btn)
    Button btn;
    private String TAG = "ZJ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_http);
        ButterKnife.bind(this);
    }
//
//    @OnClick(R.id.btn)
//    public void onClick() {
//        NetClient.getInstance().getData("https://api.github.com/users/gqq").enqueue(new CallBackUi() {
//            @Override
//            public void onFailureInUI(Call call, IOException exception) {
//                Toast.makeText(TestHttp.this,"failure",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onResponseInUI(Call call, Response response) {
//                Toast.makeText(TestHttp.this,"success",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
  @OnClick(R.id.btn)
    public void onClick() {
         NetClient.getInstance().getData("https://api.github.com/users/gqq").enqueue(new Callback() {
             @Override
             public void onFailure(Call call, IOException e) {
                 Log.e(TAG, "onFailure: " );
             }
             @Override
             public void onResponse(Call call, Response response) throws IOException {
                 Log.e(TAG, "onResponse: " );
             }
         });
    }

}
