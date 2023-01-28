# About
Technical interview for Aplazo

## How to use it
 * Clone the repository.
 * run docker-compose up
 *  the enpoint is `localhost:8080/credit`
 * request: 
``` javascript
   {
	"amount": 5000,
	"terms":52,
	"rate": 10
}
   ```
* response:
``` javascript
   [
	{
		"paymentNumber": 0,
		"amount": 105.76923076923077,
		"paymentDate": "2023-01-27"
	},
	{
		"paymentNumber": 1,
		"amount": 105.76923076923077,
		"paymentDate": "2023-02-03"
	}
    ]
   ```



## tests
 * ./gradlew test

## Actuator Health endpoint
http://localhost:8080/actuator/health

## author
 ``allanfrancisco.jn@gmail.com``