# **Banking System Web Application**
## Project Overview
This is a **Banking Sytem Web Application** built using **Java, 
JSP(Java Server Pages)**, and **Servlets**. The application allows users to perform essential banking operations like 
**viewing account balance, depositing and withdrawing money**, and **viewing transaction history**.
The Project follows a structured approach to ensure proper user management and financial transactions. and runs on a local Apache Tomcat 
server with PostgreSQL as the database.

## **Features**
- **User Authentication:** Users can log in with their credentials to access their banking dashboard.
- **View Account Balance:** Users can view the current balance in their account.
- **Deposit Money:** Users can deposit a specified amount into their account.
- **Withdraw Money:** Users can withdraw a specified amount from their account.
- **Transaction History:** Users can view a detailed history of all their transactions, including the transaction type, amount, and date.
## **Technologies Used**
- **Backend:**
  - Java Servlets and JSP
  - PostgreSQL(Database)
  - SQL for database operations
- **Frontend:**
    - HTML
    - CSS(for styling)
- **Server:**
  - Apache Tomcat(running on localhost)
## **Project Structure**
### **JSP Files:**
  - **input.jsp:** Handles the user login functionality.
  - **welcome.jsp:** Displays options for viewing balance, depositing, withdrawing
, and viewing transaction history after successful login.
  - **balance.jsp:** Displays the current acount balance of the user.
  - **deposit.jsp:** Allows users to deposit money to** their account.
  - **withdraw.jsp:** Allows users to withdraw money from their account.
  - **transactionHistory.jsp:** Displays the user's transaction history in a table format.
## Servlets:
- **BalanceServlet:** Retrieves and displays the user's current balance from the database.
- **DepositServlet:** Handles the deposit transactions and updates the database.
- **WithdrawServlet:** Handles the withdrawal transactions and updates the database.
- **TransactionHistoryServlet:** Retrieves the user's transaction history from the database and displays it.

## Database:
The application uses **PostgreSQL** as the database management system. The following tables are used in the database:
- **users:** Stores user information such as account number, name, and password.
- **transaction_history:** Stores the details of each transaction made by the users, including transaction type, amount, and timestamp.
