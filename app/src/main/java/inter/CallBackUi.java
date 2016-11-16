package inter;

import android.os.Handler;
import android.os.Looper;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 子线程通知主线程刷新UI
 * Created by ZJ on 2016/11/8.
 */
public abstract class CallBackUi implements Callback {
    Handler mHandler = new Handler(Looper.getMainLooper());

    @Override
    public void onFailure(final Call call, final IOException e) {
    mHandler.post(new Runnable() {//主线程
        @Override
        public void run() {
            onFailureInUI(call,e);
        }
    });
    }
    @Override
    public void onResponse(final Call call, final Response response) throws IOException {
    mHandler.post(new Runnable() {//主线程
        @Override
        public void run() {
       onResponseInUI(call,response);
        }
    });
    }
    public abstract void onFailureInUI(Call call,IOException exception);
    public abstract void onResponseInUI(Call call,Response response);
}
