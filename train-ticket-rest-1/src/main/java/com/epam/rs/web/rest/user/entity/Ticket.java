package com.epam.rs.web.rest.user.entity;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sergey_Stefoglo on 10/27/2016.
 */

@XmlRootElement
//@JsonIgnoreProperties(ignoreUnknown = true)
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "numberTicket",
        "startCity",
        "finishCity",
        "departureDate",
        "arrivaDate",
        "costTicket",
        "statusTicket",
        "human"})
public class Ticket implements Serializable {

    private Integer numberTicket;

    private String startCity;

    private String finishCity;

    private Date departureDate;

    private Date arrivaDate;

    private Double costTicket;

    private String statusTicket;

    private Human human;


    public Integer getNumberTicket() {
        return numberTicket;
    }

    public void setNumberTicket(Integer numberTicket) {
        this.numberTicket = numberTicket;
    }

//    @XmlElement
    public String getStartCity() {
        return startCity;
    }

    public void setStartCity(String startCity) {
        this.startCity = startCity;
    }

//    @XmlElement
    public String getFinishCity() {
        return finishCity;
    }

    public void setFinishCity(String finishCity) {
        this.finishCity = finishCity;
    }

//    @XmlElement
    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }


    public Date getArrivaDate() {
        return arrivaDate;
    }

    public void setArrivaDate(Date arrivaDate) {
        this.arrivaDate = arrivaDate;
    }

    public Double getCostTicket() {
        return costTicket;
    }

    public void setCostTicket(Double costTicket) {
        this.costTicket = costTicket;
    }

    public String getStatusTicket() {
        return statusTicket;
    }

    public void setStatusTicket(String statusTicket) {
        this.statusTicket = statusTicket;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "numberTicket=" + numberTicket +
                ", startCity='" + startCity + '\'' +
                ", finishCity='" + finishCity + '\'' +
                ", departureDate=" + departureDate +
                ", arrivaDate=" + arrivaDate +
                ", costTicket=" + costTicket +
                ", statusTicket='" + statusTicket + '\'' +
                ", human=" + human +
                '}';
    }
}
