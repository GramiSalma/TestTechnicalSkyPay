package com.skypay.banking;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Account implements AccountService {
    private final List<Transaction> transactions = new ArrayList<>();
    private int balance = 0;

    @Override
    public void deposit(int amount) {
        deposit(amount, LocalDate.now());
    }

    // Overload for testing with custom date
    public void deposit(int amount, LocalDate date) {
        if (amount <= 0) throw new IllegalArgumentException("Le dépôt doit être positif.");
        balance += amount;
        transactions.add(new Transaction(date, amount, balance));
    }

    @Override
    public void withdraw(int amount) {
        withdraw(amount, LocalDate.now());
    }

    // Overload for testing with custom date
    public void withdraw(int amount, LocalDate date) {
        if (amount <= 0) throw new IllegalArgumentException("Le retrait doit être positif.");
        if (amount > balance) throw new IllegalArgumentException("Solde insuffisant.");
        balance -= amount;
        transactions.add(new Transaction(date, -amount, balance));
    }

    @Override
    public void printStatement() {
        System.out.println("Date       | Montant | Solde");
        List<Transaction> reversed = new ArrayList<>(transactions);
        Collections.reverse(reversed);
        for (Transaction t : reversed) {
            System.out.printf("%02d/%02d/%d | %d | %d%n",
                    t.getDate().getDayOfMonth(),
                    t.getDate().getMonthValue(),
                    t.getDate().getYear(),
                    t.getAmount(),
                    t.getBalance());
        }
    }

    // Pour les tests (accès aux transactions)
    List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }
}