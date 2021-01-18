# Read Me First
The following command used to run the project:

* mvn spring-boot:run

prerequisites: Java,Maven,Mysql

there are two controllers (UsersController,DiscountController)
* first you can create a user by calling post request on 'http://localhost:8080/retail-store-discount/users/'

with json body '{
"name":"name",
"userType":"AFFILIATE"
}'

note: user type can be "EMPLOYEE" or "AFFILIATE" or "CUSTOMER"

* second you can generate a discount bill by sending a post request to 'http://localhost:8080/retail-store-discount/discount/'

with json body 
ex: '{
"userId": 19,
"items": [
{
"quantity": 5,
"price": 210.50,
"type": "meat"
},
{ "quantity": 5,
"price": 50.25,
"type": "groceries"},
{ "quantity": 4,
"price": 400,
"type": "bread"}
]
}'

* Please note that 'groceries' is a keyword used to determine the groceries type while calculating the discount and use the user you just created

### You can use "mvn clean install" to Build the project from the command-line


### You can use "mvn clean test" to run unit test in Maven
