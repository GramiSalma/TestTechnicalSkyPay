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
- Aucune dépendance externe : stockage en mémoire.

### Structure

- `AccountService` : interface imposée.
- `Account` : implémentation de la logique métier.
- `Transaction` : enregistrement d’une opération (date, montant, solde).

### Diagramme de classe

### Resultat de Print 

### Resultat de test JUNIT 



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


### Resultat de Print

### Resultat de test



- Cas nominaux et cas d’erreur (entrées invalides, conflits de réservation, ...)

## Réponses aux Questions Bonus 

### 1. Est-il recommandé de placer toutes les fonctions dans le même service ? Expliquez.

Non, ce n’est pas recommandé. 
Mettre toutes les fonctions dans 
une seule classe/service 
rend le code difficile à maintenir,
à faire évoluer et à tester.
Il est préférable de séparer les responsabilités
(par exemple : ServiceUser, ServiceRoom, 
ServiceBooking), ce qui améliore la lisibilité
, la maintenance et la réutilisation du code.


---

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