package com.skypay.hotel;

public class User {
    private final int id;
    private int balance;

    public User(int id, int balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() { return id; }
    public int getBalance() { return balance; }
    public void setBalance(int balance) { this.balance = balance; }

    @Override
    public String toString() {
        return String.format("User{id=%d, balance=%d}", id, balance);
    }
}