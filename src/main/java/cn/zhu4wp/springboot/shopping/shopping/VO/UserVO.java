package cn.zhu4wp.springboot.shopping.shopping.VO;

import java.io.Serializable;

public class UserVO implements Serializable{

    private static final long serivalVersionUID = 1L;

    private String username;
    private String password;
    private Integer id;
    private String dbflag;
    private String repassword;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDbflag() {
        return dbflag;
    }

    public void setDbflag(String dbflag) {
        this.dbflag = dbflag;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public UserVO() {
    }
}
