package entity;

/**
 * Created by Administrator on 2016/11/8.
 */
public class RegisterResult {
    /**
     * errcode : 1
     * errmsg : 注册成功！
     * tokenid : 171
     */

    private int errcode;
    private String errmsg;
    private int tokenid;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getTokenid() {
        return tokenid;
    }

    public void setTokenid(int tokenid) {
        this.tokenid = tokenid;
    }
}
