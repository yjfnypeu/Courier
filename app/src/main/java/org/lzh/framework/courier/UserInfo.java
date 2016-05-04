package org.lzh.framework.courier;

import java.io.Serializable;

/**
 * @author Administrator
 */
public class UserInfo implements Serializable {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public UserInfo setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserInfo setPassword(String password) {
        this.password = password;
        return this;
    }
}
