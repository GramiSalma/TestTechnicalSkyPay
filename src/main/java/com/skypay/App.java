package com.skypay;

import com.skypay.banking.Account;
import com.skypay.banking.AccountService;
import com.skypay.hotel.RoomType;
import com.skypay.hotel.Service;

import java.text.SimpleDateFormat;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("===== TEST 1 : SERVICE BANCAIRE =====");
        AccountService account = new Account();

        System.out.println("\n--- Dépôts ---");
        System.out.println("Dépôt de 1000€");
        account.deposit(1000);
        System.out.println("Dépôt de 500€");
        account.deposit(500);

        System.out.println("\n--- Retraits valides ---");
        System.out.println("Retrait de 300€");
        account.withdraw(300);

        System.out.println("\n--- Cas d'erreurs : opérations invalides ---");
        try {
            System.out.println("Tentative de dépôt de -100€");
            account.deposit(-100);
        } catch (Exception e) {
            System.out.println("Erreur attendue : " + e.getMessage());
        }
        try {
            System.out.println("Tentative de retrait de -50€");
            account.withdraw(-50);
        } catch (Exception e) {
            System.out.println("Erreur attendue : " + e.getMessage());
        }
        try {
            System.out.println("Tentative de retrait de 2000€ (solde insuffisant)");
            account.withdraw(2000);
        } catch (Exception e) {
            System.out.println("Erreur attendue : " + e.getMessage());
        }

        System.out.println("\n--- Relevé des opérations bancaires ---");
        account.printStatement();

        System.out.println("\n\n====================================================");
        System.out.println("===== TEST 2 : SERVICE DE RESERVATION D’HOTEL =====");
        System.out.println("====================================================\n");

        Service service = new Service();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("--- Création des chambres ---");
        service.setRoom(1, RoomType.STANDARD, 1000);
        service.setRoom(2, RoomType.JUNIOR, 2000);
        service.setRoom(3, RoomType.SUITE, 3000);

        System.out.println("Chambres créées : 1(STANDARD), 2(JUNIOR), 3(SUITE)");

        System.out.println("\n--- Création des utilisateurs ---");
        service.setUser(1, 5000);
        service.setUser(2, 10000);
        System.out.println("Utilisateurs créés : 1 (5000€), 2 (10000€)");

        System.out.println("\n--- Tentatives de réservations ---");
        try {
            System.out.println("Utilisateur 1 tente de réserver chambre 2 du 30/06/2026 au 07/07/2026");
            service.bookRoom(1, 2, sdf.parse("30/06/2026"), sdf.parse("07/07/2026"));
        } catch (Exception e) {
            System.out.println("Erreur attendue (dates invalides ou solde) : " + e.getMessage());
        }
        try {
            System.out.println("Utilisateur 1 tente de réserver chambre 2 du 07/07/2026 au 30/06/2026");
            service.bookRoom(1, 2, sdf.parse("07/07/2026"), sdf.parse("30/06/2026"));
        } catch (Exception e) {
            System.out.println("Erreur attendue (dates invalides) : " + e.getMessage());
        }

        System.out.println("Utilisateur 1 réserve chambre 1 du 07/07/2026 au 08/07/2026");
        service.bookRoom(1, 1, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));

        System.out.println("Utilisateur 2 réserve chambre 1 du 08/07/2026 au 10/07/2026");
        service.bookRoom(2, 1, sdf.parse("08/07/2026"), sdf.parse("10/07/2026"));

        System.out.println("Utilisateur 2 réserve chambre 3 du 07/07/2026 au 08/07/2026");
        service.bookRoom(2, 3, sdf.parse("07/07/2026"), sdf.parse("08/07/2026"));

        System.out.println("\n--- Modification de la chambre 1 (devient SUITE à 10000€) ---");
        service.setRoom(1, RoomType.SUITE, 10000);

        System.out.println("\n--- Affichage de toutes les chambres et réservations ---");
        service.printAll();

        System.out.println("\n--- Affichage de tous les utilisateurs ---");
        service.printAllUsers();
    }
}
