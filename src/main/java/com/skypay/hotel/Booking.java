package com.skypay.hotel;

import java.util.Date;
public class Booking {
    private final User user;
    private final Room room;
    private final Date checkIn;
    private final Date checkOut;
    private final int priceTotal;

    public Booking(User user, Room room, Date checkIn, Date checkOut, int priceTotal) {
        this.user = new User(user.getId(), user.getBalance()); // snapshot
        this.room = new Room(room.getId(), room.getType(), room.getPricePerNight()); // snapshot
        this.checkIn = (Date) checkIn.clone();
        this.checkOut = (Date) checkOut.clone();
        this.priceTotal = priceTotal;
    }

    public User getUser() { return user; }
    public Room getRoom() { return room; }
    public Date getCheckIn() { return (Date) checkIn.clone(); }
    public Date getCheckOut() { return (Date) checkOut.clone(); }
    public int getPriceTotal() { return priceTotal; }

    @Override
    public String toString() {
        return String.format("Booking{user=%s, room=%s, checkIn=%s, checkOut=%s, total=%d}",
                user, room, checkIn, checkOut, priceTotal);
    }
}