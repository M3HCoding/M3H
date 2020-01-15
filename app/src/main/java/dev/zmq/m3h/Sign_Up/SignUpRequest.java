package dev.zmq.m3h.Sign_Up;

public class SignUpRequest {
    private String fullName;
    private String loginId;
    private String dateOfBirth;
    private String gender;
    private String mobileNo;
    private String password;

    public SignUpRequest(String fullName, String loginId, String dateOfBirth, String gender, String mobileNo, String password) {
        this.fullName = fullName;
        this.loginId = loginId;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.mobileNo = mobileNo;
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
