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
# Interface
![javapor](https://user-images.githubusercontent.com/70094556/148646248-0c73e884-68df-4178-8206-4e0f4ea2ffc1.png)
