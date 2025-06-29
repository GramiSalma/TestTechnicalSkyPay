# Skypay Technical Tests 

## Présentation

Ce dépôt contient deux solutions  pour les tests techniques Skypay :

1. **Service Bancaire** : gestion d’un compte (dépôt, retrait, relevé des transactions).
2. **Système de Réservation d’Hôtel** : gestion de chambres, utilisateurs et réservations, avec contrôle de cohérence et d’historique.

Les développements respectent les exigences des 
énoncés, utilisent Java 11 et JUnit 5,
appliquent une gestion rigoureuse des exceptions.



## 1. Service Bancaire

### Fonctionnalités

- Dépôt et retrait d’argent sur un compte.
- Affichage du relevé des transactions, du plus récent au plus ancien.
- Gestion des erreurs : montants négatifs, solde insuffisant.


### Structure

- `AccountService` : interface imposée.
- `Account` : implémentation de la logique métier.
- `Transaction` : enregistrement d’une opération (date, montant, solde).

### Diagramme de classe
![DiagramClasseBanking](https://github.com/user-attachments/assets/e805761e-66cd-4e79-9cb5-fc6eb5979e87)

### Resultat de printStatement() 
![image](https://github.com/user-attachments/assets/ce56a85e-0b47-4c96-8589-3266a8afb197)

### Resultat de test JUNIT 
![image](https://github.com/user-attachments/assets/8ce76ea8-ebdd-472b-a948-9cd44547c52f)




## 2. Système de Réservation d’Hôtel

### Fonctionnalités

- Création et modification de chambres et d’utilisateurs.
- Réservation de chambres sur période donnée avec contrôle de disponibilité et de solde.
- Historisation des données des réservations (snapshot).
- Affichage des chambres et réservations, du plus récent au plus ancien.
- Affichage des utilisateurs.

### Structure

- `Room`, `User`, `Booking` : entités principales.
- `Service` : gestion de l’ensemble des opérations.
- Enum `RoomType` : types de chambres.
- Contrôle strict des erreurs et des périodes.

### Diagramme de classe
![classeHotel](https://github.com/user-attachments/assets/d4d1f00c-087b-41f9-88e1-c4a231a04cf7)


### Resultat de printAll() et printAllUsers()
![image](https://github.com/user-attachments/assets/c397b5be-c81c-4ad6-971e-29b766536163)


### Resultat de test JUINIT
![image](https://github.com/user-attachments/assets/5ad6dcc4-14fa-40f2-b5ee-c82c06872bb0)



- Cas nominaux et cas d’erreur (entrées invalides, conflits de réservation, ...)

## Réponses aux Questions Bonus 

### 1. Est-il recommandé de placer toutes les fonctions dans le même service ? Expliquez.

Non, ce n’est pas recommandé. Cela brule le pricipe de SingleResponsability. 
Mettre toutes les fonctions dans 
une seule classe/service 
rend le code difficile à maintenir,
à faire évoluer et à tester.
C est mieux  de séparer les responsabilités
(par exemple : ServiceUser, ServiceRoom, 
ServiceBooking), ce qui améliore la lisibilité
, la maintenance et la réutilisation du code.



### 2. Pourquoi choisir que `setRoom` n’affecte pas les réservations passées ? Quelle autre approche possible ? Quelle recommandation ?

**Autre approche :**  
On pourrait faire en sorte que toute
modification d’une chambre (type ou prix) 
soit répercutée rétroactivement sur les 
réservations déjà créées.

**Recommandation :**  
Il est préférable de conserver u
n « snapshot » (copie) de l’état 
de la chambre dans chaque réservation a
u moment de sa création. Ainsi, une rése
rvation reflète toujours fidèlement les 
conditions acceptées lors de la réservat

ion, même si la chambre change par la suite. C
’est plus fiable et conforme aux pratiques d
es systèmes réels.
