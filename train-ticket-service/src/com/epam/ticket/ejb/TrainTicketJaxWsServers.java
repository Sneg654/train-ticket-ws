package com.epam.ticket.ejb;

import com.epam.ticket.entity.Human;
import com.epam.ticket.entity.Ticket;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;

/**
 * Created by Sergey_Stefoglo on 10/30/2016.
 */

//Задача 1. Используя JAX-WS создать веб-сервис, предоставляющий следующие операции над билетом на поезд,
// описанные во введении: забронировать билет на поезд, получить билет по номеру, оплатить билет на поезд,
// вернуть билет на поезд. Также, создать клиент, демонстрирующий использование веб-сервиса.


@WebService(name = "TicketServer")


public interface TrainTicketJaxWsServers {

    @WebMethod
    Integer bookedTicket(@WebParam(name = "startCity") String startCity,
                         @WebParam(name = "endCity") String endCity,
                         @WebParam(name = "startDate") Date startDate,
                         @WebParam(name = "endDate") Date endDate,
                         @WebParam(name = "human",targetNamespace = "http://ejb.ticket.epam.com/") Human human);

    @WebMethod
    Ticket findTicket(@WebParam(name = "numberTicket") Integer numberTicket);

    @WebMethod
    String payTicket(@WebParam(name = "numberTicket") Integer numberTicket, @WebParam(name = "money") Double money);

    @WebMethod
    String removeTicket(@WebParam(name = "numberTicket") Integer numberTicket);

}
