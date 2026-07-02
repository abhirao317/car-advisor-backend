# Car Advisor

Full-stack car recommendation app for turning a confused buyer's preferences into a ranked shortlist.

## Stack

- Angular frontend in `frontend`
- Spring Boot backend in `demo`
- H2 in-memory database
- Lombok for Java boilerplate

## Backend

The backend exposes:

- `GET /api/cars`
- `GET /api/cars/filter-options`
- `POST /api/ai/recommendations`
- `GET /api/ai/recommendations`
- `GET /api/ai/recommendations/{queryId}`
- `POST /api/recommendations`
- `GET /api/recommendations`
- `GET /api/recommendations/{queryId}`
- H2 console at `/h2-console`

`GET /api/cars` returns price-sorted DTO data shaped like the frontend `CarSummary`, not raw JPA entity objects.
The backend DTO lives at `car_wale.demo.car.CarSummary` and is reused inside recommendation results.
The recommendation endpoint persists queries/results in `RecommendationService` and delegates scoring to `CarRecommendationEngine`; the starter implementation is `RuleBasedCarRecommendationEngine`.
That starter engine uses explicit filters plus query-text intent such as safety, automatic/manual transmission, family seating, mileage, and electric interest to produce ranked reasons.
Manual API examples live in `docs/api-examples.md`.
Local first-run verification steps live in `docs/local-verification-checklist.md`.
Core `Car` fields and persisted recommendation result fields are marked non-null in the JPA model; optional buyer filters can stay null.

Run from `demo`:

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk1.8.0_202'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
$env:MAVEN_USER_HOME="$PWD\.m2"
.\mvnw.cmd spring-boot:run
```

Run backend tests from `demo`:

```powershell
$env:JAVA_HOME='C:\Program Files\Java\jdk1.8.0_202'
$env:Path="$env:JAVA_HOME\bin;$env:Path"
$env:MAVEN_USER_HOME="$PWD\.m2"
.\mvnw.cmd test
```

## Frontend

Key files:

- `src/main.ts` bootstraps the standalone Angular app.
- `src/app/app.component.ts` contains the main car advisor UI logic.
- `src/app/app.component.html` contains the main car advisor template.
- `src/app/models/api-error.models.ts` mirrors structured backend error responses.
- `src/app/models/car.models.ts` contains frontend car catalog types.
- `src/app/models/recommendation.models.ts` contains frontend recommendation API types.
- `src/app/services/car-api.service.ts` loads cars from `GET /api/cars` and filter options from `GET /api/cars/filter-options`.
- `src/app/services/recommendation-api.service.ts` calls the AI recommendation endpoints.
- `src/app/utils/api-error.util.ts` extracts backend validation messages for display.
- `src/app/validators/budget-range.validator.ts` keeps the budget range rule reusable.

The UI can browse the seeded car catalog with compact specs, create a new ranked shortlist, or load a saved shortlist by query id.
The UI also lists recent saved recommendation queries after the backend has stored them.
Saved shortlists show the original query text, filter context, budget range, and creation time beside the ranked car cards.
Loading a saved shortlist also repopulates the form so the buyer can adjust the same query and request a new shortlist.
After creating a shortlist, the lookup field is updated to that saved query id.
Budget range validation exists in both the Angular form and the Spring Boot request model.
Backend validation and missing-query errors return structured `ApiErrorResponse` payloads.
All `/api/**` routes allow the Angular dev origin `http://localhost:4200` through centralized Spring MVC CORS config.

Run from `frontend`:

```powershell
npm install
npm start
```

Open `http://localhost:4200`.
