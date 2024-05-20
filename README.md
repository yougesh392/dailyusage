# Daily Usage Service

This is a Spring Boot application that manages and retrieves daily usage data for a user.

## Technologies Used

- Java
- Spring Boot
- Maven

## Service Description

The `DailyUsage` service is responsible for managing and retrieving daily usage data for a user. It provides endpoints to create usage history and get usage history for the current billing cycle.

## API Endpoints

### POST /api/dailyusage
Creates a daily usage record.

**Request Body:**
```json
{
    "userId": "string",
    "phoneNumber": "string",
    "cycleId": "string"
}
```
### POST /api/dailyusage/currentbillingcycle

Retrieves the usage history for the current billing cycle.  

**Request Body:**
```json
{
    "userId": "string",
    "phoneNumber": "string",
    "cycleId": "string"
}
```
**Response Body:**

```json
{
    "usageHistory": [
        {
            "date": "string",
            "dataUsed": "number"
        }
    ]
}
```
## Scheduler

The `DailyUsageService` class contains a method `updateUsageAndDate()` that is annotated with `@Scheduled`. This annotation is part of Spring's task scheduling support and indicates that the method should be invoked at regular intervals.

The `updateUsageAndDate()` method is scheduled to run every 15 minutes. This is specified by the `fixedRate` parameter in the `@Scheduled` annotation, which is set to `15 * 60 * 1000` milliseconds (equivalent to 15 minutes).

Here's what the `updateUsageAndDate()` method does:

1. It retrieves all `DailyUsage` objects from the repository.
2. For each `DailyUsage` object, it checks if there's a `UsageHistory` entry for the current day.
3. If there's an entry for the current day, it increments the `usedInMb` field by 15.
4. If there's no entry for the current day, it creates a new `UsageHistory` entry with `usedInMb` set to 0 and the date set to the current date.
5. Finally, it saves the updated `DailyUsage` object back to the repository.

This method is used to simulate the accumulation of data usage over time. Every 15 minutes, it either updates the data usage for the current day or creates a new usage record for a new day.

## Error Handling
for the error handling, I used the `@ControllerAdvice` annotation to define a global exception handler. This class contains methods annotated with `@ExceptionHandler` that handle specific exceptions and return an appropriate response.
```json
{
   "status": 404,
   "message": "User not found"
}
```

## Database Schema
The service uses MongoDB as its database. The main collection used by this service is `DailyUsageDAO`.

**Collection: DailyUsage**
- `_id`: The unique identifier for the document. It is a string.
- `userId`: The ID of the user. It is a string.
- `cycleId`: The ID of the billing cycle. It is a string.
- `usageHistory`: An array of usage history documents. Each document contains:
  - `date`: The date of usage. It is a string.
  - `dataUsed`: The amount of data used. It is a number.

Please note that MongoDB is a NoSQL database, so it doesn't have a traditional schema like SQL databases. The fields mentioned above are based on the usage in the code and may not cover all fields in the actual documents.

## How to Run
To run this project, you need to have Java and Maven installed on your machine. Then, you can clone this repository and run the following command in the project directory:

## Class Diagram

![Service Design Diagram](images/Dailyusage.png)
