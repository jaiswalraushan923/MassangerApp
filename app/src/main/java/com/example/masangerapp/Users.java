package com.example.masangerapp;

public class Users {
    public Users() {
        // Empty constructor required for Firebase deserialization
    }
    String profilepic,mail,userName,password,userId,lastMassage,status;

    public Users(String id, String name, String emaill, String password, String profilepic, String status) {
        this.userId=id;
        this.userName=name;
        this.mail=emaill;
        this.password=password;
        this.profilepic=profilepic;
        this.status=status;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return userName;
    }

    public void setUsername(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMassage() {
        return lastMassage;
    }

    public void setLastMassage(String lastMassage) {
        this.lastMassage = lastMassage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
