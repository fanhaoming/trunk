package com.trunk.support.permission;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fanhaoming
 * @ClassName LoginWebAuthenticationDetails
 * @Description TODO
 * @Date 2019/8/15 11:49
 * @Version
 **/
public class LoginWebAuthenticationDetails extends WebAuthenticationDetails {

    private static final long serialVersionUID = 336993541165304236L;

    private String serverRandomId;
    private String encryptedClientRandom;
    private String encryptedInputValue;
    private String username;
    private String password;

    public LoginWebAuthenticationDetails(HttpServletRequest request) {
        super(request);
        serverRandomId = request.getParameter("serverRandomId");
        encryptedClientRandom = request.getParameter("encryptedClientRandom");
        encryptedInputValue = request.getParameter("encryptedInputValue");
        username = request.getParameter("username");
        password = request.getParameter("password");
    }

    public String getServerRandomId() {
        return serverRandomId;
    }

    public String getEncryptedClientRandom() {
        return encryptedClientRandom;
    }

    public String getEncryptedInputValue() {
        return encryptedInputValue;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}

