package com.epam.ticket.client;


import com.epam.ticket.ejb.Ticket;
import com.epam.ticket.ejb.TicketServer;
import com.epam.ticket.ejb.TrainTicketJaxWsServersImplService;

import java.text.ParseException;
import java.util.Scanner;

/**
 * Created by Sergey_Stefoglo on 10/31/2016.
 */
public class WSClient {
    static TrainTicketJaxWsServersImplService trainTicketJaxWsServersImplService = new TrainTicketJaxWsServersImplService();
    static TicketServer proxy = trainTicketJaxWsServersImplService.getTicketServerPort();

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(Utils.CHOISE_COMMAND);
            while (sc.hasNextLine()) {
                try {
                    String[] arguments = sc.nextLine().replaceAll(Utils.SPACE, Utils.REPLACE).split(Utils.DEL);
                    if (arguments[Utils.TYPE_COMMAND].equalsIgnoreCase(Utils.GET_TICKET)) {
                        Ticket ticket = proxy.findTicket(Integer.valueOf(arguments[Utils.TICKET_ID_PARAM]));
                        System.out.println(ticket);
                    } else if (arguments[Utils.TYPE_COMMAND].equalsIgnoreCase(Utils.REMOVE_TICKET)) {
                        String message = proxy.removeTicket(Integer.valueOf(arguments[Utils.TICKET_ID_PARAM]));
                        System.out.println(message);
                    } else if (arguments[Utils.TYPE_COMMAND].equalsIgnoreCase(Utils.PAYED_TICKET)) {
                        String message = proxy.payTicket(Integer.valueOf(arguments[Utils.TICKET_ID_PARAM]), Double.valueOf(arguments[Utils.MONEY_PARAM]));
                        System.out.println(message);
                    } else if (arguments[Utils.TYPE_COMMAND].equalsIgnoreCase(Utils.BOOKED_TICKET)) {
                        Integer number = proxy.bookedTicket(arguments[Utils.START_CITY],
                                arguments[Utils.END_CITY],
                                Utils.getDate(arguments[Utils.START_DATE]),
                                Utils.getDate(arguments[Utils.END_DATE]),
                                Utils.getHuman(arguments[Utils.FIRST_NAME_PARAM],
                                        arguments[Utils.LAST_NAME_PARAM],
                                        arguments[Utils.MIDDLE_NAME_PARAM],
                                        Utils.getDate(arguments[Utils.BIRTHDAY_PARAM])));

                        System.out.println(Utils.NUMBER_TCIKET + number);
                    } else {
                        System.out.println(Utils.INCORRECT_COMMAND);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(Utils.INCORRECT_COUNT_ARGUMENT);
                } catch (ParseException e) {
                    System.out.println(Utils.INCORRECT_PARAM);
                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
