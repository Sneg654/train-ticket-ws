package com.epam.rs.web.rest.user.entity;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sergey_Stefoglo on 10/27/2016.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "lastName",
        "firstName",
        "midlleName",
        "birthday"  })
public class Human implements Serializable {

    private String lastName;

    private String firstName;

    private String midlleName;

    private Date birthday;

//    @XmlElement
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

//    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

//    @XmlElement
    public String getMidlleName() {
        return midlleName;
    }

    public void setMidlleName(String midlleName) {
        this.midlleName = midlleName;
    }

//    @XmlElement
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Human{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", midlleName='" + midlleName + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
