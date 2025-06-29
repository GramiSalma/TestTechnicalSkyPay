package com.skypay.hotel;
public class Room {
    private final int id;
    private RoomType type;
    private int pricePerNight;

    public Room(int id, RoomType type, int pricePerNight) {
        this.id = id;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }

    public int getId() { return id; }
    public RoomType getType() { return type; }
    public int getPricePerNight() { return pricePerNight; }

    public void setType(RoomType type) { this.type = type; }
    public void setPricePerNight(int pricePerNight) { this.pricePerNight = pricePerNight; }

    @Override
    public String toString() {
        return String.format("Room{id=%d, type=%s, pricePerNight=%d}", id, type, pricePerNight);
    }
}