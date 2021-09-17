# The Pet Shop
This application is made for an online store for selling dogs,
so this includes the following tasks:  
1. Performing CRUD operations for the Dog
2. Creating order for a dog (quantity of 1 per order)
***
## Installation
Just pull the app from github and run the main file  
**PetShopApplication.java**  
And the apis are available on the following link:  
[DogsAPI](http://localhost:8080/dogs)
***
## Usage
####*__*All requests are in JSON format__*
###DOG CRUD
1. To create a new dog:  
   [Create Dog](http://localhost:8080/dogs)  
    **Request Example:**  
   {"breed" : "labrador" , "sex" : "m"}  
    **Request Type: _POST_**  
   **Response Example:**  
   {"id" : 1 , "breed" : "labrador" , "sex" : "m"}  
   **Response Code: _201_**  
   **Invalid Response Code: _400_**
2. To get all dogs :  
      [Get All Dogs](http://localhost:8080/dogs)  
      **Request Example:**  
      {}  
      **Request Type: _GET_**  
      **Response Example:**    
      [{"id" : 1 , "breed" : "labrador" , "sex" : "m"},  {"id" : 1 , "breed" : "labrador" , "sex" : "m"}]  
      **Response Code: _200_**  
      **Invalid Response Code: _404_**
3. To get a dog with certain ID:  
      [Get Dog With ID: 1](http://localhost:8080/dogs/1)  
      **Request Body Example:**  
      {}  
      **Path Variable Example:**  
      http://localhost:8080/dogs/1
      **Request Type: _GET_**  
      **Response Example:**  
      {"id" : 1 , "breed" : "labrador" , "sex" : "m"}  
      **Response Code: _200_**  
      **Invalid Response Code: _404_**
4. To get a dog with a specific breed:  
      [Get Dog with Breed (labrador)](http://localhost:8080/dogs/breeds/labrador)  
      **Request Body Example:**  
      {}  
      **Path Variable Example:**  
      http://localhost:8080/dogs/breeds/labrador  
      **Request Type: _GET_**  
      **Response Example:**  
      [{"id" : 1 , "breed" : "labrador" , "sex" : "m"},  {"id" : 1 , "breed" : "labrador" , "sex" : "f"}]  
      **Response Code: _200_**  
      **Invalid Response Code: _404_**
5. To update dog partially:  
      [Update Dog with ID:1 Partially](http://localhost:8080/dogs/1)  
      **Request Body Example:**  
      {"sex" : "f"}  
      **Path Variable Example:**  
      http://localhost:8080/dogs/1
      **Request Type: _PATCH_**  
      **Response Example:**  
      {"id" : 1 , "breed" : "labrador" , "sex" : "f"}  
      **Response Code: _200_**  
      **Invalid Response Code: _404_ if wrong ID , _405_ if wrong parameters**
6. To update whole dog object:  
      [Update Dog with ID:1](http://localhost:8080/dogs/1)  
      **Request Body Example:**  
      {"breed" : "wolf" , "sex" : "f"}  
      **Path Variable Example:**  
      http://localhost:8080/dogs/1
      **Request Type: _PUT_**  
      **Response Example:**  
      {"id" : 1 , "breed" : "wolf" , "sex" : "f"}  
      **Response Code: _200_**  
      **Invalid Response Code: _404_ if wrong ID , _405_ if wrong parameters**
7. To delete a dog object:  
   [Delete Dog with ID:1](http://localhost:8080/dogs/1)  
   **Request Body Example:**  
   {}  
   **Path Variable Example:**  
   http://localhost:8080/dogs/1
   **Request Type: _DELETE_**  
   **Response Example:**  
   {}  
   **Response Code: _200_**  
   **Invalid Response Code: _404_ if wrong ID , _405_ if wrong parameters**
###Create Order

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## History

TODO: Write history

## Credits

TODO: Write credits

## License

TODO: Write license