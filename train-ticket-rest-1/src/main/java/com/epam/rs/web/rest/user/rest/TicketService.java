package com.epam.rs.web.rest.user.rest;

import com.epam.rs.web.rest.user.entity.Ticket;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sergey_Stefoglo on 10/31/2016.
 */
public abstract class TicketService {
    public static int currentNumber = 100;
    protected static final String BOOKED = "BOOKED";
    protected static final String PAID = "PAID";
    protected static final String TICKET_NOT_FOUND = "ticket not found";
    protected static final String TICKET_REMOVED =  "ticket was removed";
    protected static final String TICKET_INSUFICCIENT =  "ticket was removed";
    protected static final String TICKET =  "ticked #" ;
    protected static final String DELIVERY =  " paid, delivery " ;
    protected static Map<Integer, Ticket> tickets = new HashMap<>();

    static {
    tickets= StartCollection.getCollection(tickets);
    }

    public Ticket getTicket(Integer id) {
        return tickets.get(id);
    }


    public Response bookedTicket(Ticket innerTicket) {
        Ticket ticket = new Ticket();
        ticket.setStartCity(innerTicket.getStartCity());
        ticket.setFinishCity(innerTicket.getFinishCity());
        ticket.setCostTicket(5700d);
        ticket.setArrivaDate(innerTicket.getArrivaDate());
        ticket.setDepartureDate(innerTicket.getDepartureDate());
        ticket.setStatusTicket(BOOKED);
        ticket.setHuman(innerTicket.getHuman());
        ticket.setNumberTicket(currentNumber++);
        tickets.put(ticket.getNumberTicket(), ticket);
        return Response.ok(ticket.getNumberTicket().toString()).build();
    }

    public Response payTicket(Ticket innerTicket) {
        Double summ = 0d;
        String returnMessage;
        Ticket ticket = tickets.get(innerTicket.getNumberTicket());
        if (ticket != null) {
            summ = innerTicket.getCostTicket() - ticket.getCostTicket();
            if (summ >= 0) {
                ticket.setStatusTicket(PAID);
                tickets.put(innerTicket.getNumberTicket(), ticket);
                returnMessage = TICKET + innerTicket.getNumberTicket() + DELIVERY + summ;
            } else {
                returnMessage = TICKET_INSUFICCIENT;
            }
        } else {
            returnMessage =TICKET_NOT_FOUND;
        }
        return Response.ok(returnMessage).build();
    }


    public Response removeTicket(Integer numberTicket) {
        String returnMessage;
        Ticket ticket = tickets.remove(numberTicket);
        if (ticket != null) {
            returnMessage =TICKET_REMOVED;
        } else {
            returnMessage = TICKET_NOT_FOUND;
        }

        return Response.ok(returnMessage).build();
    }


}
