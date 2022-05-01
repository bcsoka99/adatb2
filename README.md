#UTAZASI IRODA
This is a dumbed down application meant to represent a travel agency and it's functions.

## HOW TO USE 

- Download oracle db v21.
- Log in with username: sys, password: (the one you used during the oracle installation), to orcl ssid.
- Create a new user: C##UI with password: asd123 and give this user all the possible grants.
- In the project, you can find a directory called database, run all the scripts, starting with 'databaseInitialization.sql', and run all the other scripts based on the number in the beginning of their name.
- When you are done, run maven goal: mvn vaadin:build-frontend
- You can start up the application from src/main/java/classes/Application.java