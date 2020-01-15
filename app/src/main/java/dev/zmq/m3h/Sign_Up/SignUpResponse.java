package dev.zmq.m3h.Sign_Up;

public class SignUpResponse
{
    public Boolean status;
    public Registration data;
    public String message;

    public SignUpResponse(Boolean status, Registration data, String message)
    {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Registration getData() {
        return data;
    }

    public void setData(Registration data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
class Registration
{
    public String fullName;
    public String loginId;
    public String dateOfBirth;
    public String gender;
    public String mobileNo;
    public String password;

    public Registration(String fullName, String loginId, String dateOfBirth, String gender, String mobileNo, String password) {
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
