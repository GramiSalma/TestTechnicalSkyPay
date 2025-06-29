
# Skypay Technical Tests

## Présentation

Ce dépôt contient deux solutions aux tests techniques Skypay :

1. **Service Bancaire** : gestion d’un compte (dépôt, retrait, relevé des transactions).
2. **Système de Réservation d’Hôtel** : gestion des chambres, des utilisateurs et des réservations, avec contrôle de cohérence et historisation.

Les développements respectent les exigences des énoncés, utilisent **Java 11** et **JUnit 5**, et appliquent une gestion rigoureuse des exceptions.



##  Service Bancaire

###  Fonctionnalités

* Dépôt et retrait d’argent sur un compte.
* Affichage du relevé des transactions du plus récent au plus ancien.
* Gestion des erreurs : montants invalides, solde insuffisant, date nulle.

###  Structure

* `AccountService` : interface imposée.
* `Account` : implémentation de la logique métier.
* `Transaction` : enregistrement d’une opération (date, montant, solde).

###  Diagramme de classe

![DiagramClasseBanking](https://github.com/user-attachments/assets/e805761e-66cd-4e79-9cb5-fc6eb5979e87)

###  Résultat de `printStatement()`

![image](https://github.com/user-attachments/assets/ce56a85e-0b47-4c96-8589-3266a8afb197)

###  Résultats des tests JUnit

![image](https://github.com/user-attachments/assets/8ce76ea8-ebdd-472b-a948-9cd44547c52f)

---

##  2. Système de Réservation d’Hôtel

### Fonctionnalités

* Création et modification de chambres et d’utilisateurs.
* Réservation de chambres sur une période donnée avec contrôle de disponibilité et de solde.
* Historisation des données des réservations .
* Affichage des chambres et réservations du plus récent au plus ancien.
* Affichage des utilisateurs existants.

### Structure

* `Room`, `User`, `Booking` : entités principales.
* `Service` : gestion centralisée des opérations.
* `RoomType` : énumération des types de chambres.
* Contrôle strict des erreurs, des soldes et des conflits de réservation.

###  Diagramme de classe

![classeHotel](https://github.com/user-attachments/assets/d4d1f00c-087b-41f9-88e1-c4a231a04cf7)

###  Résultat de `printAll()` et `printAllUsers()`

![image](https://github.com/user-attachments/assets/c397b5be-c81c-4ad6-971e-29b766536163)

### Résultats des tests JUnit

![image](https://github.com/user-attachments/assets/5ad6dcc4-14fa-40f2-b5ee-c82c06872bb0)



##  Réponses aux questions bonus

### 1. Est-il recommandé de placer toutes les fonctions dans un seul service ? Pourquoi ?

Non, ce n’est **pas recommandé**, car cela viole le principe de **responsabilité unique** (*Single Responsibility Principle*).

Mettre toutes les fonctionnalités dans une seule classe rend le code :difficile à maintenir,moins lisible et moins testable.

C'est mieux de séparer les responsabilités (ex : `ServiceUser`, `ServiceRoom`, `ServiceBooking`).



### 2. Pourquoi choisir que `setRoom` n’affecte pas les réservations passées ? Quelle autre approche ? Quelle recommandation ?

Parce que quand quelqu’un réserve une chambre, il l’a fait avec un prix et un type à ce moment-là. Si plus tard on change la chambre ( le prix ou le type ), c’est pas normal que ça change les réservations déjà faites.
Sinon c’est comme dire au client : "Ah t’as réservé à 1000 dh, mais maintenant c’est 5000 dh, paye plus !"

**Recommandation :**
On peut conserver l’état de la chambre au moment de la réservation (type, prix, etc.). Ainsi, la réservation reflète **les conditions acceptées à l’instant t**, même si la chambre est modifiée par la suite.
