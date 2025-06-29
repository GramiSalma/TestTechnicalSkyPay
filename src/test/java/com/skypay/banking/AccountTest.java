package com.skypay.banking;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testDepositAndWithdrawAndPrintStatement() {
        Account account = new Account();
        account.deposit(1000, LocalDate.of(2012, 1, 10));
        account.deposit(2000, LocalDate.of(2012, 1, 13));
        account.withdraw(500, LocalDate.of(2012, 1, 14));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        account.printStatement();
        System.setOut(System.out);

        String expected =
                "Date       | Montant | Solde\n" +
                        "14/01/2012 | -500 | 2500\n" +
                        "13/01/2012 | 2000 | 3000\n" +
                        "10/01/2012 | 1000 | 1000\n";
        // Ignore whitespaces
        assertEquals(
                expected.replaceAll("\\s", ""),
                out.toString().replaceAll("\\s", "")
        );
    }

    @Test
    void testNegativeDeposit() {
        Account account = new Account();
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
    }

    @Test
    void testNegativeWithdraw() {
        Account account = new Account();
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-500));
    }

    @Test
    void testOverdraft() {
        Account account = new Account();
        account.deposit(100);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(200));
    }
}