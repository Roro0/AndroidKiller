package register;

/**
 * 注册接口
 * Created by Administrator on 2016/11/9.
 */
public interface RegisterView {
    // 完善视图接口
    void navigateToHome();//导航到HOME页面

    void showProgress();//显示注册中进度视图

    void hideProgress();//隐藏注册中进度视图

    void showMessage(String msg);//显示提示信息
}
