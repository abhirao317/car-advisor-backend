# API Examples

Use these after starting the Spring Boot app from `demo`.

## Car Catalog

```powershell
curl http://localhost:8080/api/cars
```

Returns seeded car catalog rows as `CarSummary` DTOs.

## Car Filter Options

```powershell
curl http://localhost:8080/api/cars/filter-options
```

Returns distinct `bodyTypes` and `fuelTypes` from the seeded catalog.

## Create Recommendation

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

The same endpoint is also available at:

```powershell
curl -Method POST http://localhost:8080/api/recommendations `
  -ContentType "application/json" `
  -Body '{
    "queryText": "I want a fuel efficient petrol hatchback.",
    "budgetMin": 6,
    "budgetMax": 11,
    "bodyType": "Hatchback",
    "fuelType": "Petrol"
  }'
```

## Load Saved Recommendation

Replace `1` with a `queryId` returned by the create recommendation response.

```powershell
curl http://localhost:8080/api/ai/recommendations/1
```

The same lookup is also available at:

```powershell
curl http://localhost:8080/api/recommendations/1
```

Missing saved recommendations return the same structured error shape as validation failures:

```powershell
curl http://localhost:8080/api/ai/recommendations/999999
```

Expected response status: `404 Not Found`.

## List Recent Recommendation Queries

```powershell
curl http://localhost:8080/api/ai/recommendations
```

Returns up to 10 recent saved query summaries. The same list is also available at:

```powershell
curl http://localhost:8080/api/recommendations
```

## Validation Error Example

```powershell
curl -Method POST http://localhost:8080/api/ai/recommendations `
  -ContentType "application/json" `
  -Body '{
    "queryText": "I need a recommendation.",
    "budgetMin": 20,
    "budgetMax": 10
  }'
```

Expected response status: `400 Bad Request`.
The same structured response is used for short buying-situation text, overlong text, and other request validation failures.
Malformed JSON request bodies also return the same `ApiErrorResponse` shape.

If the catalog has no cars, recommendation requests also return `400 Bad Request` with a message asking you to add cars first.
If the recommendation engine cannot produce any ranked candidates, the endpoint returns `400 Bad Request` without saving an empty shortlist.
