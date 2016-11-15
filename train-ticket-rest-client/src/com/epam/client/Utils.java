package com.epam.client;

import com.epam.entity.Human;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sergey_Stefoglo on 10/31/2016.
 */
public class Utils {

    private static DateFormat format = new SimpleDateFormat("DD.MM.YYYY");
    public final static String GET_TICKET = "gt";
    public final static String REMOVE_TICKET = "rt";
    public final static String PAYED_TICKET = "pt";
    public final static String BOOKED_TICKET = "bt";
    public final static String SPACE = " ";
    public final static String REPLACE = "";
    public final static String DEL = ",";
    public final static String INCORRECT_COMMAND = "you entered incorrect command";
    public final static String INCORRECT_PARAM = "you entered incorrect attribute";
    public final static String INCORRECT_COUNT_ARGUMENT = "You enter incorrect count args for this command";
    public final static String NUMBER_TCIKET = "number of your ticket: ";
    public static final String CHOISE_COMMAND = "enter command , for use serverse Exampes:\n" +
            "gt,100- for show informaion about ticket with number 100\n" +
            "rt,100- for delete  ticket with number 100\n" +
            "pt,100,5000- for payment  ticket with number 100, sum 5000\n" +
            "bt,FirstName,LastName,MiddleName,BirthDay(format:DD.MM.YYYY),ArrivaCity,DepartureCity,AriveDate(format:DD.MM.YYYY),DepartureDate(format:DD.MM.YYYY)\n";

    public static final int TYPE_COMMAND = 0;
    public static final int TICKET_ID_PARAM = 1;
    public static final int MONEY_PARAM = 2;

    public static final int FIRST_NAME_PARAM = 1;
    public static final int LAST_NAME_PARAM = 2;
    public static final int MIDDLE_NAME_PARAM = 3;
    public static final int BIRTHDAY_PARAM = 4;
    public static final int START_CITY = 5;
    public static final int END_CITY = 6;
    public static final int START_DATE = 7;
    public static final int END_DATE = 8;


    public static Date getDate(String date) throws ParseException {
        return format.parse(date);

    }

    public static Human getHuman(String fisrstName, String lastName, String middleName, Date birthDay) {
        Human human = new Human();
        human.setFirstName(fisrstName);
        human.setLastName(lastName);
        human.setMidlleName(middleName);
        human.setBirthday(birthDay);


        return human;
    }


}
