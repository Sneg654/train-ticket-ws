package com.epam.client; /**
 * Created by Sergey_Stefoglo on 10/30/2016.
 */

import com.epam.entity.Ticket;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.util.Scanner;

public class RsClient {
    private static final String baseURL = "http://epkzkarw0338.moscow.epam.com:45054/train-ticket-rest-1/api/";
    private static final String JSON_SERVER = "TicketJSONService";
    private static final String XML_SERVER = "TicketXMLService";
    private static final String GET = "/getTicket/";
    private static final String PUT = "/payTicket";
    private static final String DELETE = "/removeTicket/";
    private static final String POST = "/bookedTicket";
    private static final String CHANGE = "change";
    public static final String CHOISE_SERVERSE = "Please choise type of web serverse(Example 1: JSON, Example 2: XML)";
    public static final String JSON = "JSON";
    public static final String XML = "XML";
    public static final String CHOISE_COMMAND = "enter command , for use serverse Exampes:\n" +
            "gt,100- for show informaion about ticket with number 100\n" +
            "rt,100- for delete  ticket with number 100\n" +
            "pt,100,5000- for payment  ticket with number 100, sum 5000\n" +
            "bt,FirstName,LastName,MiddleName,BirthDay(format:DD.MM.YYYY),ArrivaCity,DepartureCity,AriveDate(format:DD.MM.YYYY),DepartureDate(format:DD.MM.YYYY)\n";
    public static final String CHANGE_TYPE = "for change type date enter change";
    private static String serveseURL = null;
    private static String typeAnswer = null;
    private static WebResource webResource = null;
    private static ClientResponse response;

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Client client = Client.create();
            System.out.println(CHOISE_SERVERSE);
            while (sc.hasNextLine()) {
                String command = sc.nextLine();
                if (command.equalsIgnoreCase(JSON)) {

                    serveseURL = baseURL + JSON_SERVER;

                    typeAnswer = MediaType.APPLICATION_JSON;

                } else if (command.equalsIgnoreCase(XML)) {
                    serveseURL = baseURL + XML_SERVER;

                    typeAnswer = MediaType.APPLICATION_XML;
                } else {
                    System.out.println(Utils.INCORRECT_PARAM);
                    continue;
                }
                System.out.println(CHOISE_COMMAND);
                while (sc.hasNextLine()) {
                    try {
                        String[] arguments = sc.nextLine().replaceAll(Utils.SPACE, Utils.REPLACE).split(Utils.DEL);
                        if (arguments[Utils.TYPE_COMMAND].equalsIgnoreCase(Utils.GET_TICKET)) {
                            webResource = client.resource((serveseURL + GET + arguments[Utils.TICKET_ID_PARAM]));
                            response = webResource.type(typeAnswer).get(ClientResponse.class);
                           try{
                            Ticket ticket = response.getEntity(Ticket.class);
                            System.out.println(ticket);
                            System.out.println(CHANGE_TYPE);
                        }catch(UniformInterfaceException e){
                               System.out.println("ticket not found");
                               System.out.println(CHANGE_TYPE); }
                        } else if (arguments[Utils.TYPE_COMMAND].equalsIgnoreCase(Utils.REMOVE_TICKET)) {
                            webResource = client.resource((serveseURL + DELETE + arguments[Utils.TICKET_ID_PARAM]));
                            response = webResource.type(typeAnswer).delete(ClientResponse.class);
                            getMessage(response);
                        } else if (arguments[Utils.TYPE_COMMAND].equalsIgnoreCase(Utils.PAYED_TICKET)) {
                            webResource = client.resource((serveseURL + PUT));
                            Ticket ticket = new Ticket();
                            ticket.setNumberTicket(Integer.valueOf(arguments[Utils.TICKET_ID_PARAM]));
                            ticket.setCostTicket(Double.valueOf(arguments[Utils.MONEY_PARAM]));
                            response = webResource.type(typeAnswer).put(ClientResponse.class, ticket);
                            getMessage(response);
                        } else if (arguments[Utils.TYPE_COMMAND].equalsIgnoreCase(Utils.BOOKED_TICKET)) {
                            webResource = client.resource((serveseURL + POST));
                            response = webResource.type(typeAnswer).post(ClientResponse.class, fullTicket(arguments));
                            getMessage(response);
                        } else if (arguments[Utils.TYPE_COMMAND].equalsIgnoreCase(CHANGE)) {
                            System.out.println(CHOISE_SERVERSE);
                            break;

                        } else {
                            System.out.println(Utils.INCORRECT_COMMAND);
                        }

                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println(Utils.INCORRECT_COUNT_ARGUMENT);
                    } catch (ParseException e) {
                        System.out.println(Utils.INCORRECT_PARAM);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }


        } catch (Exception e) {

            e.printStackTrace();

        }


    }

    private static void getMessage(ClientResponse response) {
        String message = response.getEntity(String.class);
        System.out.println(message);
        System.out.println(CHANGE_TYPE);
    }

    private static Ticket fullTicket(String[] arguments) throws ParseException {
        Ticket ticket = new Ticket();
        ticket.setHuman(Utils.getHuman(arguments[Utils.FIRST_NAME_PARAM],
                arguments[Utils.LAST_NAME_PARAM],
                arguments[Utils.MIDDLE_NAME_PARAM],
                Utils.getDate(arguments[Utils.BIRTHDAY_PARAM])));
        ticket.setStartCity(arguments[Utils.START_CITY]);
        ticket.setFinishCity(arguments[Utils.END_CITY]);
        ticket.setArrivaDate(Utils.getDate(arguments[Utils.START_DATE]));//7
        ticket.setDepartureDate(Utils.getDate(arguments[Utils.END_DATE]));//8
        return ticket;
    }

}
