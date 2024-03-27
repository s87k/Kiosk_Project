package kiosk_prj.login;

public class LoginVO {
    private String id;
    private String pw;

    public LoginVO(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }
}

