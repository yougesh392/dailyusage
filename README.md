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
