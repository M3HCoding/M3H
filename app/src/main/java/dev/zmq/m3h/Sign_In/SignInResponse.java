package dev.zmq.m3h.Sign_In;

public class SignInResponse {
    public String message;
    public Boolean status;
    public String Token;

    public SignInResponse(String message, Boolean status, String token) {
        this.message = message;
        this.status = status;
        Token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        Token = token;
    }
}

class Token {
    public String userId;
    public String password;

    public Token() {
        this.userId = userId;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
