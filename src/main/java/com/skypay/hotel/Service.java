package com.skypay.hotel;

import java.util.*;

public class Service {
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>();
    ArrayList<Booking> bookings = new ArrayList<>();

    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        Room room = findRoom(roomNumber);
        if (room == null) {
            rooms.add(new Room(roomNumber, roomType, roomPricePerNight));
        } else {
            room.setType(roomType);
            room.setPricePerNight(roomPricePerNight);
        }
    }

    public void setUser(int userId, int balance) {
        User user = findUser(userId);
        if (user == null) {
            users.add(new User(userId, balance));
        } else {
            user.setBalance(balance);
        }
    }

    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        User user = findUser(userId);
        Room room = findRoom(roomNumber);
        if (user == null) throw new IllegalArgumentException("Utilisateur inexistant");
        if (room == null) throw new IllegalArgumentException("Chambre inexistante");
        if (!checkIn.before(checkOut))
            throw new IllegalArgumentException("La date d'arrivée doit précéder la date de départ.");

        int nights = getDaysBetween(checkIn, checkOut);
        int totalCost = nights * room.getPricePerNight();

        if (user.getBalance() < totalCost)
            throw new IllegalArgumentException("Solde insuffisant.");

        if (!isRoomAvailable(roomNumber, checkIn, checkOut))
            throw new IllegalArgumentException("Chambre occupée sur cette période.");

        user.setBalance(user.getBalance() - totalCost);
        bookings.add(0, new Booking(user, room, checkIn, checkOut, totalCost));
    }

    public void printAll() {
        System.out.println("=== Chambres ===");
        for (int i = rooms.size() - 1; i >= 0; i--) {
            System.out.println(rooms.get(i));
        }
        System.out.println("=== Réservations ===");
        for (Booking b : bookings) {
            System.out.println(b);
        }
    }

    public void printAllUsers() {
        System.out.println("=== Utilisateurs ===");
        for (int i = users.size() - 1; i >= 0; i--) {
            System.out.println(users.get(i));
        }
    }

    // Helpers
    private Room findRoom(int id) {
        for (Room r : rooms) if (r.getId() == id) return r;
        return null;
    }
    private User findUser(int id) {
        for (User u : users) if (u.getId() == id) return u;
        return null;
    }
    private boolean isRoomAvailable(int roomId, Date checkIn, Date checkOut) {
        for (Booking b : bookings) {
            if (b.getRoom().getId() == roomId) {
                // Overlap if: start < end2 && end > start2
                if (checkIn.before(b.getCheckOut()) && checkOut.after(b.getCheckIn())) {
                    return false;
                }
            }
        }
        return true;
    }
    private int getDaysBetween(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24));
    }
}