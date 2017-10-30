package com.xwch.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by xwch on 2017/10/22.
 */
@Getter
@Setter
@ToString
public class Spitter {

    private Long id;

    @NotNull
    @Size(min = 5, max = 16, message = "{username.size}")
    private String username;

    @NotNull
    @Size(min = 5,max = 25, message = "{password.size}")
    private String password;

    @NotNull
    @Size(min = 2,max = 30, message = "{firstName.size}")
    private String firstName;

    @NotNull
    @Size(min = 2,max = 30, message = "{lastName.size}")
    private String lastName;

    @NotNull
    @Email
    private String email;


    public Spitter(){}

    public Spitter(Long id, String username, String password, String firstName, String lastName, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Spitter(String username, String password, String firstName, String lastName, String email){
        this(null,username,password,firstName,lastName,email);
    }

    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that, "firstName", "lastName", "username", "password", "email");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "firstName", "lastName", "username", "password", "email");
    }
 }
