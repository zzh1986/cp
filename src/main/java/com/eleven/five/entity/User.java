package com.eleven.five.entity;

/**
 * @author zhaozhihong
 */
public class User {

    /**
     * isdefaultLogin : true
     * loginName : zzh1986
     * loginPwd : 5b731135d2da81baa1d10855a100ce6e
     * validCode :
     * validateDate : 1534152074680
     */

    private boolean isdefaultLogin;
    private String loginName;
    private String loginPwd;
    private String validCode;
    private Long validateDate;

    public boolean isIsdefaultLogin() {
        return isdefaultLogin;
    }

    public void setIsdefaultLogin(boolean isdefaultLogin) {
        this.isdefaultLogin = isdefaultLogin;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public Long getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(Long validateDate) {
        this.validateDate = validateDate;
    }
}
