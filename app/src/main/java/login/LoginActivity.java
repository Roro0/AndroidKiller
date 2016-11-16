package login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import activity.RegisterSuccessPage;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.feucui.seektreasure.R;
import entity.LoginResult;
import entity.User;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/11/9.
 */
public class LoginActivity extends Activity  implements LoginView{
    private String TAG="ZJ";
    /**
     * 当前Activity主要做了什么：本来应该做的视图，业务操作，这样写的话，Activity是不是太过于臃肿了？
     * 怎么去给Activity减压呢？
     * 1. 业务是不是可以抽离出去呢？
     *
     */
    @BindView(R.id.tv_login_count)
    TextView tvLoginCount;
    @BindView(R.id.et_login_count)
    EditText etLoginCount;
    @BindView(R.id.tv_login_pwd)
    TextView tvLoginPwd;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.tv_login_forget_pwd)
    TextView tvLoginForgetPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

        private void initView() {
        btnLogin.setVisibility(View.VISIBLE);

    }

    @OnClick(R.id.btn_login)
    public void onClick() {
        User user = new User(etLoginCount.getText().toString(),etLoginPwd.getText().toString());
        new LoginPresenter(this).login(user);

    }

private ProgressDialog mProgress;
    @Override
    public void navigateToHome() {
         startActivity(new Intent(this, RegisterSuccessPage.class));
    }
    @Override
    public void showProgress() {
        Log.e(TAG, "showProgress:进5 " );
      mProgress=ProgressDialog.show(this,"正在登录","客官请稍候");
    }
    @Override
    public void hideProgress() {
       if (mProgress!=null){
           Log.e(TAG, "hideProgress:进6 " );
        mProgress.dismiss();
}
    }
    @Override
    public void showMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
