# Project Marketplace Service

Project Marketplace service supplies integration api's to Create, Bid and Choose the winning bid for a given project.

## Getting Started

```
navigate to [my project] 
https://github.com/vdurshenapelli/project-marketplace
git checkout/download/clone 
mvn clean install

mvn spring-boot:run
```
### Prerequisites.
```
must have java 8, maven plugins installed.
In-memory database is used.
```

### Swagger

Swagger url - http://localhost:8080/swagger-ui.html

### Assumptions
```
A buyer cannot submit bids after deadline is reached for a project.
Winning bid for a project is decided only after the deadline to submit a project is completed.
A buyer can bid for price grater than the maximum price set by the seller.
Seller should provide the deadline while submiiting a new project , else return error messsage
Buyer should provide mandatory buyername,estimated amount while submitting bid request, else return error message
```
### Step by step guide

A step by step sequence with sample request and response 

```
Give the example
```
1.Submit a new project
url : http://localhost:8080/project
method : POST

date time format for deadline 
 ```
 "dd-MM-yyyy hh:mm:ss"

 For verifying validations on submitting bids only before deadline and finding the winning bid after deadline is reached,
 Please provide deadline as current local time + 5/10 min. This allows to verify all endpoints at once.
 On the backend, time is persisted in UTC format

 For example if current time is 15:20:20, provide deadline as 15:30:20.
 You can submit bids before 15:30:20 only and you can get the winning bid after 15:30:20.
 ```
 sample request:

```
{
	"description":"Ecommerce website",
	"maximumBudget":1000,
	"deadLine":"08-04-2018 19:27:22"
}


```

sample response :
```
{
    "projectId": 1
}
```
2. Submit bid for any project

endpoint : http://localhost:8080/bid
method : POST
sample request
```
{
	"estimatedAmt":800,
	"buyerName":"Looser",
	"project":{
            "id":1
	}
}
```
sample response :
```
{
    "bidId": 1
}
```
sample response when deadline for submitting bids is completed

```
{
    "code": "bid.closed",
    "description": "Deadline is completed and you cannot bid anymore."
}
```
sample response when project id is invalid

```
{
    "code": "project.id.invalid",
    "description": "Invalid projectId."
} 
```
sample response when estimatedAmount/buyerName is null

```
{
    "code": "field.notNull",
    "description": "Field value cannot be null"
}
```

3.show bids submitted for a project
endpoint : http://localhost:8080/project/{projectId}
method  : GET

sample request
```
http://localhost:8080/project/1
```

sample response
```
{
    "id": 1,
    "description": "Ecommerce website",
    "maximumBudget": 1000,
    "deadLine": "08-04-2018 07:50:22",
    "biddings": [
        {
            "id": 1,
            "estimatedAmt": 2000,
            "buyerName": "Looser"
        },
        {
            "id": 2,
            "estimatedAmt": 100,
            "buyerName": "winner"
        }
    ]
}
```

sample response when project id is invalid

```
{
    "code": "project.id.invalid",
    "description": "Invalid projectId."
} 
```

4. Show winning bid for a project after deadline is passed
endpoint : http://localhost:8080/{projectId}/bid/winner
method : GET
sample request : http://localhost:8080/1/bid/winner

sample response :

```
{
    "id": 2,
    "estimatedAmt": 100,
    "buyerName": "winner"
}

```
sample response when deadline to submit bids is not reached
```
{
    "code": "bid.still.open",
    "description": "Winning bid is displayed only after deadline is completed."
}

```
5. list all projects:

endpoint : http://localhost:8080/project/all
method : GET
sample request : http://localhost:8080/project/all


End with an example of getting some data out of the system or using it for a little demo


About Coding Challenge

```
The time the exercise took                   - 6 hours
Exercise Difficulty                          - Medium
How did you feel about the exercise itself?  - 10
How did you feel about the exercise itself?  - 10
```

## Deployment

mvn spring-boot:run

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Vikas Durshenapelli** - [github](https://github.com/vdurshenapelli)

