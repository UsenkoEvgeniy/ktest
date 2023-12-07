# Quote REST API
REST api which allow:
### 1 Creation of a user account. User properties are name, email, password and date of creation

POST http://localhost:8080/user
```json
{
    "name": "JD",
    "email": "sor@email.com",
    "password": "password"
}
```
### 2 Add quote

Only registered user can add quote. UserId must be in request headers as "user-id"

POST http://localhost:8080/quote
```json
{
  "content": "First quote"
}
```
### 3 View quote by id

GET http://localhost:8080/quote/1

### 4 View quotes by page

GET http://localhost:8080/quote?page=?&qty=?

where "page" is a page number, "qty" - quotes per page

### 5 View random quote

GET http://localhost:8080/quote/rand

### 6 Modify quote

Only quote author can modify quote

PATCH http://localhost:8080/quote/1

### 7 Get top 10 quotes

GET http://localhost:8080/quote/top

### 8 Get worst 10 quotes

GET http://localhost:8080/quote/worst


### 9 Delete quote by id

Only author can delete quote.

DELETE http://localhost:8080/quote/1

### 10 Add upvote or downvote

POST http://localhost:8080/quote/1/vote?vote=?

vote=true - like
vote=false - dislike

### 11 Get graph of the evolution of the votes over time.

GET http://localhost:8080/quote/1/graph

Return json with rolling sum of date and rating for that day.