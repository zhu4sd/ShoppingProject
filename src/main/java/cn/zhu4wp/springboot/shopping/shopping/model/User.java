package cn.zhu4wp.springboot.shopping.shopping.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "id",nullable = false)
    private long id;
    @Column(name = "username",nullable = false)
    @NotBlank(message = "username不可以为空！")
    private String username;
    @Column(name = "password",nullable = false)
    @NotBlank(message = "password不可以为空")
    @Size(min = 4,max = 6,message = "password长度应为4-6！")
    private String password;

    private String repassword;

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public User() {
    }

    public User(String username, String password, long id) {
        this.username = username;
        this.password = password;
        this.id = id;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", id=" + id +
                '}';
    }

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

    public void setId(long id) {
        this.id = id;
    }
}
