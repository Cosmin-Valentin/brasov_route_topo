# Endpointuri Implementate

## /error

- **Request Type**: GET
- **Description**: Gestionați tratarea erorilor personalizate pentru aplicație. Afișează mesaje de eroare bazate pe codul de status HTTP.
- **Parameters**: None
- **Response**: Răspunde cu o pagină de eroare afișând mesajul corespunzător statusului HTTP (de ex. 404 sau 500).

## /populate-db

- **Request Type**: GET
- **Description**: Populează baza de date cu zone, sectoare și rute predefinite dintr-un fișier JSON.
- **Parameters**: None
- **Response**: Returnează coduri de stare HTTP: 200 OK pentru o populație reușită sau 500 Internal Server Error în cazul unor erori de citire fișier sau operațiuni de bază de date.

## / (Home Page)

- **Request Type**: GET
- **Description**: Redirectează la pagina principală a aplicației.
- **Parameters**: None
- **Response**: Răspunde cu layout-ul principal și cu pagina "page-home".

## /login

- **Request Type**: GET
- **Description**: Afișează pagina de autentificare.
- **Parameters**: None
- **Response**: Răspunde cu layout-ul principal și pagina "page-login".

## /logout

- **Request Type**: GET
- **Description**: Gestionează deconectarea utilizatorului.
- **Parameters**: None
- **Response**: Redirectează utilizatorul la pagina principală după deconectare.

## /routes

- **Request Type**: GET
- **Description**: Recuperează rutele cele mai recente, sortate după dată.
- **Parameters**: None
- **Response**: Răspunde cu lista de rute și cu layout-ul principal utilizând pagina "page-latest-routes".

## /areas/{zoneId}/sectors/{sectorId}

- **Request Type**: GET
- **Description**: Afișează rutele dintr-un sector specific al unei zone.
- **Parameters**:
  - **zoneId**: ID-ul zonei.
  - **sectorId**: ID-ul sectorului.
- **Response**: Răspunde cu layout-ul principal și cu pagina "page-routes", afișând sectorul și rutele asociate.

## /areas

Request Type: GET
Description: Recuperează toate zonele.
Parameters: None
Response: Răspunde cu layout-ul principal și pagina "page-areas", afișând toate zonele.

## /areas/{zoneId}

- **Request Type**: GET
- **Description**: Recuperează sectoarele dintr-o zonă specifică.
- **Parameters**:
  - **zoneId**: ID-ul zonei.
- **Response**: Răspunde cu layout-ul principal și pagina "page-sectors", afișând zona și sectoarele sale.

## /add-route

- **Request Type**: GET și POST
- **Description**: Afișează un formular pentru adăugarea unei rute noi sau procesează formularul pentru crearea unei rute.
- **Parameters**:
  - **Form parameters (POST)**: name, difficulty, length, quickDraws, rating, sectorImage.
- **Response**:
  - **GET**: Răspunde cu layout-ul principal și pagina "page-route-form" cu formularul de adăugare.
  - **POST**: Redirectează la /add-route în cazul adăugării reușite a rutei sau returnează formularul cu erori de validare.

## /remove-route

- **Request Type**: POST
- **Description**: Șterge o rută după ID-ul său.
- **Parameters**: id (ID-ul rutei care trebuie șters).
- **Response**: Răspunde cu 200 OK dacă ștergerea a fost reușită sau 500 Internal Server Error în caz de eșec.
