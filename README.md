## RetailApplication

### GET /customer/{id}

Get customer by customer id, return the customerDTO

e.g. /customer/7

```json
{
    "firstName": "Alex",
    "lastName": "Xu"
}
```



### POST /customer

Create new customer, return the new generate id for the new customer.

e.g. /customer

Request Body

```json
{
    "firstName":"Andrew",
    "lastName":"Wu"
}
```

Response Body

```json
10
```



### POST /customer/{id}/receipt

Through the customer id, bond the new order to the customer and return the new id of the order receipt

e.g. /customer/10/receipt

Request Body:

```json
{
    "amount":123
}
```

Response Body:

```json
9
```



### GET /customer/{id}/summary?start= && end=

Get the customer consuming summary by pathing month range of start and end. If null were passed by start or end, a recently 3 month summary will be returned. The order happened in each days would be aggregated to a monthly basis.

e.g. /customer/9/summary?start=2022-10&&end=2023-01

Response:

```json
{
    "firstName": "Hi",
    "lastName": "Hello",
    "rewardSummary": [
        {
            "monthIdentifier": "2023-01",
            "amount": "80",
            "reward": "30"
        },
        {
            "monthIdentifier": "2022-12",
            "amount": "466",
            "reward": "592"
        }
    ],
    "totalReward": 622
}
```



### GET /receipt/{id}

Get receipt by receipt id

e.g. /receipt/9

Response Body:

```json
{
    "amount": 123,
    "reward": 96,
    "createDate": "2023-02-04T08:00:00.000+00:00"
}
```
### Exception Handler
To prevent NullPointerException, when getting a non-exist customer or receipt, the system will throw an IllegalArgumentException. And the exception handler will catch that and return messages.
e.g.

```json
Cannot find the customer.
```

 

