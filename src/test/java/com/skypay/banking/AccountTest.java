package com.skypay.banking;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

    @Test
    void testDepositAndWithdrawAndPrintStatement() {
        System.out.println("===== TEST 1 : SERVICE BANCAIRE =====");

        // Création du compte
        Account account = new Account();

        // Dépôt de 1000 le 10 janvier 2012
        System.out.println("Dépôt de 1000 le 10/01/2012");
        account.deposit(1000, LocalDate.of(2012, 1, 10));

        // Dépôt de 2000 le 13 janvier 2012
        System.out.println("Dépôt de 2000 le 13/01/2012");
        account.deposit(2000, LocalDate.of(2012, 1, 13));

        // Retrait de 500 le 14 janvier 2012
        System.out.println(" Retrait de 500 le 14/01/2012");
        account.withdraw(500, LocalDate.of(2012, 1, 14));


        account.printStatement();

        // Capture de la sortie console de printStatement()
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        account.printStatement();
        System.setOut(System.out);

        // Résultat attendu
        String expected =
                "Date       | Montant | Solde\n" +
                        "14/01/2012 | -500    | 2500\n" +
                        "13/01/2012 | 2000    | 3000\n" +
                        "10/01/2012 | 1000    | 1000\n";

        // Vérification que le relevé est correct (en ignorant les espaces)

        assertEquals(
                expected.replaceAll("\\s", ""),
                out.toString().replaceAll("\\s", "")
        );

        System.out.println("Test complet réussi !");
    }

    @Test
    void testNegativeDeposit() {
        System.out.println("Test d’un dépôt négatif...");
        Account account = new Account();
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
    }

    @Test
    void testNegativeWithdraw() {
        System.out.println(" Test d’un retrait négatif...");
        Account account = new Account();
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-500));
    }

    @Test
    void testOverdraft() {
        System.out.println("Test d’un retrait supérieur au solde...");
        Account account = new Account();
        account.deposit(100);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(200));
    }
}
