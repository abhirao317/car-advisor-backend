# Local Verification Checklist

Use this checklist when you are ready to build and run the project locally.

## 1. Backend

From `demo`:

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk1.8.0_202'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
$env:MAVEN_USER_HOME="$PWD\.m2"
.\mvnw.cmd test
.\mvnw.cmd spring-boot:run
```

Expected:

- Tests pass.
- Spring Boot starts on `http://localhost:8080`.
- H2 console opens at `http://localhost:8080/h2-console`.
- H2 JDBC URL is `jdbc:h2:mem:car_advisor`, user is `sa`, and password is blank.

## 2. API Smoke Checks

With the backend running:

```powershell
curl http://localhost:8080/api/cars
curl http://localhost:8080/api/cars/filter-options
```

Then create a recommendation:

```powershell
curl -Method POST http://localhost:8080/api/ai/recommendations `
  -ContentType "application/json" `
  -Body '{
    "queryText": "I want a safe automatic SUV for city and highway use.",
    "budgetMin": 8,
    "budgetMax": 18,
    "bodyType": "SUV",
    "fuelType": "Petrol"
  }'
```

Expected:

- Response includes `queryId`.
- `results` contains ranked cars.
- Each result includes a `reason`.

## 3. Frontend

From `frontend`:

```powershell
npm install
npm start
```

Expected:

- Angular starts on `http://localhost:4200`.
- The catalog panel loads seeded cars.
- Submitting the form shows a ranked shortlist.
- Recent saved queries appear after a recommendation is created.
- Loading a saved query id returns the same shortlist context.

