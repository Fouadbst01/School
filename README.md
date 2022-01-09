# School
Manage professor and departement using java and java fx

# Database Script (MySQL)
```SQL
CREATE DATABASE Ecole;

CREATE TABLE Deparetement(
    id_dept int AUTO_INCREMENT PRIMARY KEY,
    nom varchar(50)
);

CREATE TABLE Professeur(
	id_prof int AUTO_INCREMENT PRIMARY KEY,
    nom varchar(50),
    prenom varchar(50),
    cin varchar(50),
    adresse varchar(255),
    telephone varchar(50),
    email varchar(50),
    date_recrutement DATE,
    id_dept int,
    FOREIGN KEY (id_dept) REFERENCES Deparetement(id_dept)
);
```

# Add test data to DataBase
> - Call AddFakeData Function in the main function and execute it
> 
> - AddFakerData Function generate FakeData and push it in ur DB
> - you must call it one time otherwise each call will add data to ud DB
```java
public static void main(String[] args) {
        AddFakeData();
    }
```

# Don't forget to load maven dependencies
> - For Intellij IDEA
> 
![Capture d’écran 2022-01-09 114700](https://user-images.githubusercontent.com/70094556/148682554-fae658cb-0e47-4aad-96a1-e885b33a54c3.png)

# Interfaces
> - Professor
> 
![Capture d’écran 2022-01-09 123304](https://user-images.githubusercontent.com/70094556/148682523-67de371a-84f5-47ee-9942-55369d270411.png)
> - Department
> 
![Capture d’écran 2022-01-09 123324](https://user-images.githubusercontent.com/70094556/148682312-6668cdf7-581a-46a1-8b85-028cfb7eaf34.png)
