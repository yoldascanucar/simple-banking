# SIMPLE BANKING

## OVERVIEW

This Java project is a cognitive training tool designed to improve short-term working memory, inspired by the Digit Span
subtest of the WAIS-IV (Wechsler Adult Intelligence Scale, Fourth Edition). The project includes exercises for Forward,
Backward, and Sequencing digit spans, allowing users to practice various aspects of working memory.

## FEATURES

### Account Management

- **Account Overview**: Users can view their account balances and associated IBANs.
- **Multiple Accounts**: Users can manage multiple accounts under a single profile.
- **Account Selection**: Users can select specific accounts to perform transactions.

### Transaction Features

- **Deposit Money**: Users can deposit money into their selected accounts.
- **Withdraw Money**: Users can withdraw money from their accounts, provided they have sufficient balance.
- **EFT Transactions**: Users can transfer money to other accounts using IBAN numbers.
- **Transaction History**: Users can view the results of their transactions, including updated balances.

### User Interface


- **Interactive UI**: The application provides a clean and intuitive user interface built with JavaFX.
- **Animations**: Smooth animations are used to display success messages and transitions between pages.
- **Responsive Design**: The UI is designed to be responsive and user-friendly across different screen sizes.
- **Error Handling**: Clear error messages are displayed for invalid inputs or insufficient funds.

## TECHNOLOGIES

 - **Java**
 - **JavaFX, JavaFX Animation, JavaFX CSS**
 - **Hibernate ORM**
 - **JDBC**
 - **MySQL Database**


## INSTALLATION

### Prerequisites

- **Java JDK 11 or higher**
- **MySQL Server**
- **Maven**

### Steps

#### Database Setup

      Create a database named banking_app in MySQL and execute the SQL scripts from the sql folder  
      Update the hibernate.cfg.xml file with your MySQL credentials (username and password).

#### Clone the Repository

      git clone https://github.com/yoldascanucar/simple-banking.git
      cd banking-application

#### Build the Project:

      mvn clean install

#### Run the Application



## USAGE

### Login

- Enter your password on the main page to log in.

### Select an Operation

- After logging in, choose between deposit, withdraw, or EFT operations.

### Perform Transactions

- Follow the on-screen instructions to complete your transactions.

### Logout

- Click the logout button to exit the application.



## PROJECT STRUCTURE

- **Controllers**: Contains the JavaFX controllers for handling UI events.

- **Entity**: Contains the Hibernate entity classes (ClientInformation, ClientAccount).

- **DAO**: Contains the data access objects for database operations.

- **Util**: Contains utility classes for animations, validations, and session management.

- **FXML**: Contains the FXML files for the UI layouts.

- **Resources**: Contains images and CSS files for styling.


## LICENSE

- This project is licensed under the MIT License. 



