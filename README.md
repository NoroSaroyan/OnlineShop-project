# Online shop demo application

This is my first project using Spring.
This is a simple online shop application, which provides needed functional for user to shop online.
I have used **Spring Boot**, **Spring Security**, **Spring Data JPA** with **MySql Database**.
For UI part I've used **Thymeleaf** templates and CSS framework.



## Features

- User registration
  - Password encoder. You will never worry about password leak.
- User login
- User role management(user,admin,super admin)
- Product management(add,delete,edit)
- Shopping cart
  - add products to cart
  - delete products from cart
  - checkout products in cart
  - clear all products in cart
- Database schema migration

## Requirements

- JDK-17
- Maven
- MySql server


## How to use 

- Open application properties
- Change database URL,Username,Password
- `mvn install`

After deployment, you will have users with specific roles:
- User role: login `test@gmail.com`, password `test`
- Admin role: login `admin@gmail.com` , password `test`
- Super admin:  login `sadmin@gmail.com` , password `test`

And some products for testing.
