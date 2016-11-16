package register;

import entity.User;
import entity.RegisterResult;
import network.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 处理业务逻辑
 * Created by Administrator on 2016/11/9.
 */
public class RegisterPresenter {
    private RegisterView mRegisterView;

    public RegisterPresenter(RegisterView mRegisterView){
        this.mRegisterView = mRegisterView;
    }
    // 加载数据
    public void register(User user){
        mRegisterView.showProgress();
        RetrofitClient.getInatance().register(user).enqueue(new Callback<RegisterResult>() {
            @Override
            public void onResponse(Call<RegisterResult> call, Response<RegisterResult> response) {
                RegisterResult result = response.body();
                if (response!=null&response.isSuccessful()){
                    mRegisterView.showMessage("注册中");


                }if (result.getErrcode()==1){
                    mRegisterView.showMessage(result.getErrmsg());
                    mRegisterView.hideProgress();
                    mRegisterView.navigateToHome();
                 
                }
                mRegisterView.showMessage(result.getErrmsg());
            }

            @Override
            public void onFailure(Call<RegisterResult> call, Throwable t) {
                mRegisterView.showMessage("我的心碎了一地");
                mRegisterView.hideProgress();
            }
        });
    }
}
