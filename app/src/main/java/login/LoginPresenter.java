package login;

import android.util.Log;

import entity.LoginResult;
import entity.User;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 处理业务逻辑
 * Created by Administrator on 2016/11/9.
 */
public class LoginPresenter {
    public String TAG="ZJ";
    /**
     * 主要做业务逻辑处理，对外提供一个方法
     *    从网络，本地数据库请求数据等耗时操作
     *怎么调到视图的方法呢？
     * 1. Activity，传个上下文：生命周期的限制，所以不采用
     * 2. 接口回调的方式：我们要使用的方式
     */
    private LoginView mLoginView;
    public LoginPresenter(LoginView mLoginView){
        this.mLoginView=mLoginView;
    }
    public void login(User user){
        mLoginView.showProgress();//加载数据时，显示进度框
        RetrofitClient.getInatance().login(user).enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {

                LoginResult loginResult = response.body();
                Log.e(TAG, "onResponse:进1     "+"errcode="+loginResult.getErrcode() );
                  if (response!=null&response.isSuccessful()){
                      Log.e(TAG, "onResponse: 进2" );
                      mLoginView.showMessage("登录中");
                  }if (loginResult.getErrcode()==1){
                    Log.e(TAG, "onResponse:进3 " );
//                        mLoginView.showMessage("登录成功");
                           mLoginView.showMessage(loginResult.getErrmsg());
                        //夹在成功后，隐藏进度框
                        mLoginView.hideProgress();
                        mLoginView.navigateToHome();
                }

            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.e(TAG, "onFailure:进4 " );
            mLoginView.showMessage("我想哭，呜呜呜~~~~");
                mLoginView.hideProgress();
            }
        });
    }
}
