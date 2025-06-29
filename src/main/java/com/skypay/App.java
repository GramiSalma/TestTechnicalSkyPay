package com.skypay;


import com.skypay.hotel.RoomType;
import com.skypay.hotel.Service;

import java.text.SimpleDateFormat;

/**
 *
 *
 */
public class App 
{
    public static void main(String[] args) throws Exception {
        Service service = new Service();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Rooms
        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(3, RoomType.SUITE, 3000);

        // Users
        service.setUser(1, 5000);
        service.setUser(2, 10000);

        // bookRoomsService
        try { service.bookRoom(1, 2, sdf.parse("30/06/2026"), sdf.parse("07/07/2026")); } catch (Exception e) { System.out.println(e.getMessage()); }
        try { service.bookRoom(1, 2, sdf.parse("07/07/2026"), sdf.parse("30/06/2026")); } catch (Exception e) { System.out.println(e.getMessage()); }
        service.bookRoom(1, 1, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));
        service.bookRoom(2, 1, sdf.parse("08/07/2026"), sdf.parse("10/07/2026"));
        service.bookRoom(2, 3, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));

        // ModificationWithSetRoom
        service.setRoom(1, RoomType.SUITE, 10000);

        // PrintAll and PrintAllUsers
        System.out.println();
        service.printAll();
        System.out.println();
        service.printAllUsers();
    }

}