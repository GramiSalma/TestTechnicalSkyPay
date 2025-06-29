package com.skypay.hotel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;


import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {
    Service service;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    @BeforeEach
    void setUp() {
        service = new Service();
        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(3, RoomType.SUITE, 3000);
        service.setUser(1, 5000);
        service.setUser(2, 10000);
    }

    @Test
    void testBookingAndExceptions() throws Exception {
        // User 1 books room 2 for 7 nights (cost: 14000, should fail)
        assertThrows(IllegalArgumentException.class, () ->
                service.bookRoom(1, 2, sdf.parse("30/06/2026"), sdf.parse("07/07/2026"))
        );
        // User 1 books room 2 with invalid dates (checkIn after checkOut)
        assertThrows(IllegalArgumentException.class, () ->
                service.bookRoom(1, 2, sdf.parse("07/07/2026"), sdf.parse("30/06/2026"))
        );
        // User 1 books room 1 for 1 night (success)
        service.bookRoom(1, 1, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));
        // User 2 books room 1 for 2 nights (success)
        service.bookRoom(2, 1, sdf.parse("08/07/2026"), sdf.parse("10/07/2026"));
        // User 2 books room 3 for 1 night (success)
        service.bookRoom(2, 3, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));
        // Modify room 1 (should not affect previous bookings)
        service.setRoom(1, RoomType.SUITE, 10000);
    }
}