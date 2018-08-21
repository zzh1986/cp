package com.eleven.five.entity;

/**
 * @author zhaozhihong
 */
public class ResponseBody {

    /**
     * code : success
     * data : {"agentRebates":null,"money":51.339,"loginName":null,"nickName":null,"loginTime":null,"type":null,"parentId":null,"userStatus":null,"bettingStatus":null,"freezeStatus":null,"blackStatus":null,"userDetail":null,"testAccountType":null,"userWithdrawAvail":null,"pwdEncodeType":null}
     * msg : null
     */

    private String code;
    private DataBean data;
    private Object msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }


}
