# The Pet Shop
This application is made for an online store for selling dogs,
so this includes the following tasks:  
1. Performing CRUD operations for the Dog
2. Creating order for a dog (quantity of 1 per order)
***
## Pre-requisites
* JDK8 +
* Maven
* Git (Preferable)
* Postman or any HTTPClient (Optional)
* IDE

**PetShopApplication.java**  
And the apis are available on the following link:  
[DogsAPI](http://localhost:8080/dogs)  
**_For Order process_**
1. Create inventory request
2. Create order request
***
## Installation
1. Clone the project from github using the following command on the terminal  
```git clone https://github.com/elzomor13/PetShop.git```
2. Navigate to the project folder
```cd .\PetShop```
3. Run the project with the following command on the terminal
```.\mvnw spring-boot:run```

***
## Usage
####*__*All requests are in JSON format__*
###DOG CRUD
1. To create a new dog:  
   **API:**
   http://localhost:8080/dogs  
    **Request Example:**  
   ```{"breed" : "labrador" , "sex" : "m"}```  
    **Request Type: _POST_**  
   **Response Example:**  
   ```{"id" : 1 , "breed" : "labrador" , "sex" : "m"}```  
   **Response Code: _201_**  
   **Invalid Response Code: _400_**
2. To get all dogs :  
      **API:** http://localhost:8080/dogs  
      **Request Example:**  
      ```{}```  
      **Request Type: _GET_**  
      **Response Example:**    
      ```[{"id" : 1 , "breed" : "labrador" , "sex" : "m"},{"id" : 2 , "breed" : "labrador" , "sex" : "f"}]```  
      **Response Code: _200_**  
      **Invalid Response Code: _404_**
3. To get a dog with certain ID:  
      **API:** http://localhost:8080/dogs/1  
      **Request Body Example:**  
      ```{}```  
      **Path Variable Example:**  
      http://localhost:8080/dogs/1  
      **Request Type: _GET_**  
      **Response Example:**  
      ```{"id" : 1 , "breed" : "labrador" , "sex" : "m"}```  
      **Response Code: _200_**  
      **Invalid Response Code: _404_**
4. To get a dog with a specific breed:  
      **API: ** http://localhost:8080/dogs/breeds/labrador   
      **Request Body Example:**  
      ```{}```  
      **Path Variable Example:**  
      http://localhost:8080/dogs/breeds/labrador  
      **Request Type: _GET_**  
      **Response Example:**  
      ```[{"id" : 1 , "breed" : "labrador" , "sex" : "m"},  {"id" : 1 , "breed" : "labrador" , "sex" : "f"}]```  
      **Response Code: _200_**  
      **Invalid Response Code: _404_**
5. To update dog partially:  
      **API: * http://localhost:8080/dogs/1  
      **Request Body Example:**  
      ```{"sex" : "f"}```    
      **Path Variable Example:**  
      http://localhost:8080/dogs/1
      **Request Type: _PATCH_**  
      **Response Example:**  
      ```{"id" : 1 , "breed" : "labrador" , "sex" : "f"}```    
      **Response Code: _200_**  
      **Invalid Response Code: _404_ if wrong ID , _405_ if wrong parameters**
6. To update whole dog object:  
      **API:** ```http://localhost:8080/dogs/1```  
      **Request Body Example:**  
      ```{"breed" : "wolf" , "sex" : "f"}```  
      **Path Variable Example:**  
      http://localhost:8080/dogs/1  
      **Request Type: _PUT_**  
      **Response Example:**  
      ```{"id" : 1 , "breed" : "wolf" , "sex" : "f"}```  
      **Response Code: _200_**  
      **Invalid Response Code: _404_ if wrong ID , _405_ if wrong parameters**
7. To delete a dog object:  
   **API:** http://localhost:8080/dogs/1  
   **Request Body Example:**  
   ```{}```  
   **Path Variable Example:**  
   http://localhost:8080/dogs/1
   **Request Type: _DELETE_**  
   **Response Example:**  
   ```{}```  
   **Response Code: _200_**  
   **Invalid Response Code: _404_ if wrong ID , _405_ if wrong parameters**
###Create Inventory
To create a new inventory:  
**API:** http://localhost:8080/inventory)  
**Request Example:**  
```{"dog" {"breed" : "labrador" , "sex" : "m"}, "quantity" : 10 }```  
**Request Type: _POST_**  
**Response Example:**  
```{"id" : 1 , "dog" {"breed" : "labrador" , "sex" : "m"}, "quantity" : 10 }```  
**Response Code: _201_**  
**Invalid Response Code: _400_**
###Create Order
To create a new inventory:  
**API:** http://localhost:8080/inventory  
**Request Example:**  
```{"customer" : {"name" : "mohamed" , "email" : "m@123.com"} , "dog" : {"breed" : "labrador" , "sex" : "m"}, "price" : 10 , "currency" : "EGP" }```  
**Request Type: _POST_**  
**Response Example:**  
```{"id": 4,"customer": {"id": 3,"name": "mohamed","email": "123@d.com"},"createdAt": "2021-09-17T18:32:28.015032600Z"}```  
**Response Code: _201_**  
**Invalid Response Code: _400_**
***
## Testing
To test, you can run the following command in terminal using mvn:  
```java .\mvnw clean test```
***
## Coverage Report
You can find it in the following path  
```src/main/java/resources/Coverage Report```