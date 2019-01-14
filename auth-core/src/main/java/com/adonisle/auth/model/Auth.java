package com.adonisle.auth.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.beans.ConstructorProperties;
import java.io.IOException;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "user" )


public class Auth {
    @Id
    @GeneratedValue( strategy = GenerationType.AUTO )
    private long id;
    private String email;
    private String password;

    public static Auth fromString(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return (Auth)mapper.readValue(json, Auth.class);
    }

    public static String toString(Auth Auth) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(Auth);
    }

    @ConstructorProperties({"id", "email", "password"})
    public Auth(long id, String email,  String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public long getId() {
        return this.id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Auth)) {
            return false;
        } else {
            Auth other = (Auth)o;
            if (!other.canEqual(this)) {
                return false;
            } else if (this.getId() != other.getId()) {
                return false;
            } else {
                Object this$email = this.getEmail();
                Object other$email = other.getEmail();
                if (this$email == null) {
                    if (other$email != null) {
                        return false;
                    }
                } else if (!this$email.equals(other$email)) {
                    return false;
                }

                Object this$password = this.getPassword();
                Object other$password = other.getPassword();
                if (this$password == null) {
                    if (other$password != null) {
                        return false;
                    }
                } else if (!this$password.equals(other$password)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Auth;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        long $id = this.getId();
        result = result * 59 + (int)($id >>> 32 ^ $id);
        Object $email = this.getEmail();
        result = result * 59 + ($email == null ? 43 : $email.hashCode());
        Object $password = this.getPassword();
        result = result * 59 + ($password == null ? 43 : $password.hashCode());
        return result;
    }

    public String toString() {
        return "Auth(id=" + this.getId() + ", email=" + this.getEmail() + ", password=" + this.getPassword() + ")";
    }

    public Auth() {
    }
}
