package register;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
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
import entity.User;
import network.RetrofitClient;
import entity.RegisterResult;
import retrofit2.Callback;

/**
 * Created by Administrator on 2016/11/9.
 */
public class RegisterActivity extends Activity implements RegisterView {

    private ProgressDialog progressDialog;
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
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        btnRegister.setVisibility(View.VISIBLE);
        tvLoginForgetPwd.setVisibility(View.GONE);
    }

    @OnClick( R.id.btn_register)
    public void onClick(View view) {
         showProgress();
        /**
         * 网络请求：业务
         *  1.显示进度条
         *  2.氢气结束后，隐藏进度条
         *  3。显示提示信息，弹土司
         *  4.导航到主界面
         */
                 User user = new User(etLoginCount.getText().toString(),etLoginPwd.getText().toString());
                 new RegisterPresenter(this).register(user);
        }
    @Override
    public void navigateToHome() {
        startActivity(new Intent(this,RegisterSuccessPage.class));
    }

    @Override
    public void showProgress() {
          progressDialog = ProgressDialog.show(this,"正在注册","客官请稍等");
    }
    @Override
    public void hideProgress() {
        if (progressDialog!=null){
            progressDialog.cancel();
        }
    }
    @Override
    public void showMessage(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    }

