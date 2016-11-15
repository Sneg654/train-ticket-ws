package com.epam.ticket.ejb;

import com.epam.ticket.entity.Human;
import com.epam.ticket.entity.Ticket;
import com.epam.ticket.start.StartCollection;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey_Stefoglo on 10/30/2016.
 */
@WebService(name = "TicketServer", endpointInterface = "com.epam.ticket.ejb.TrainTicketJaxWsServers")

@Stateless
public class TrainTicketJaxWsServersImpl implements TrainTicketJaxWsServers {
    public static int currentNumber = 100;
    public static final String BOOKED = "BOOKED";
    public static final String PAID = "PAID";
    private static final String TICKET_NOT_FOUND = "ticket not found";
    private static final String TICKET_REMOVED =  "ticket was removed";
    private static final String TICKET_INSUFICCIENT =  "ticket was removed";
    private static final String TICKET =  "ticked #" ;
    private static final String DELIVERY =  " paid, delivery " ;
    private static Map<Integer, Ticket> tickets = new HashMap<Integer, Ticket>();
    static {
     tickets= StartCollection.getCollection(tickets);
    }
    @WebMethod
    public Integer bookedTicket(@WebParam(name ="startCity")String startCity,
                                @WebParam(name ="endCity") String endCity,
                                @WebParam(name ="startDate")Date startDate,
                                @WebParam(name ="endDate") Date endDate,
                                @WebParam(name ="human", targetNamespace = "http://ejb.ticket.epam.com/")Human human) {
        Ticket ticket = new Ticket();
        ticket.setStartCity(startCity);
        ticket.setFinishCity(endCity);
        ticket.setCostTicket(5700d);
        ticket.setArrivaDate(endDate);
        ticket.setDepartureDate(startDate);
        ticket.setStatusTicket(BOOKED);
        ticket.setHuman(human);
        ticket.setNumberTicket(currentNumber++);
        tickets.put(ticket.getNumberTicket(), ticket);
        return ticket.getNumberTicket();
    }
    @WebMethod
    public Ticket findTicket(@WebParam(name = "numberTicket")Integer numberTicket) {
        return tickets.get(numberTicket);
    }

    @WebMethod
    public String payTicket(@WebParam(name = "numberTicket") Integer numberTicket, @WebParam(name = "money") Double money) {
        Double summ = 0d;
        String returnMessage;
        Ticket ticket = tickets.get(numberTicket);
        if (ticket != null) {
            summ = money - ticket.getCostTicket();
            if (summ >= 0) {
                ticket.setStatusTicket(PAID);
                returnMessage = TICKET + numberTicket + DELIVERY + summ;
            } else {
                returnMessage = TICKET_INSUFICCIENT;
            }
        } else {
            returnMessage = TICKET_NOT_FOUND;
        }
        return returnMessage;
    }



    @WebMethod
    public String removeTicket(@WebParam(name = "numberTicket") Integer numberTicket) {
        String returnMessage;
        Ticket ticket = tickets.remove(numberTicket);
        if (ticket != null) {
            returnMessage = TICKET_REMOVED;
        } else {
            returnMessage = TICKET_NOT_FOUND;
        }

        return returnMessage;
    }


}
