package entity;

/**
 * Created by Administrator on 2016/11/9.
 */
public class LoginResult {
    /**
     *   "errcode": 1,                  //状态值
     "errmsg": "登录成功！",        //返回信息
     "headpic": "add.jpg",          //头像地址
     "tokenid": 171

     */
    private int errcode;
    private String errmsg;
    private String headpic;
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

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic;
    }

    public int getTokenid() {
        return tokenid;
    }

    public void setTokenid(int tokenid) {
        this.tokenid = tokenid;
    }
}
