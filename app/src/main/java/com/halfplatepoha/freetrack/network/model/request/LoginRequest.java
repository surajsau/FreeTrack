package com.halfplatepoha.freetrack.network.model.request;

/**
 * Created by surajkumarsau on 17/07/16.
 */
public class LoginRequest {

    private String handle;
    private String loginType;

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

}
