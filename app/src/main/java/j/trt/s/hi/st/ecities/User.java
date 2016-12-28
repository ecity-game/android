package j.trt.s.hi.st.ecities;


import android.util.Base64;

public class User {
    public String login;
    public String password;
    public String authCertificate;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.authCertificate = "Basic " + Base64.encodeToString((login + ":" + password).getBytes(), Base64.URL_SAFE | Base64.NO_WRAP);
    }

}
