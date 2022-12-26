# learn_Jwt


this is a spring-boot app in this parte of the app i create token and role authentificattion

# first 

you have to create database with the name 'jwt-learn'



## Run the app 

mvn spring-boot:run

# accec the app

after runing this app it will automaticly create 2 user 'Admin' and 'User'

to connect with the admin you have 
"uerName" = 'admin123'
"userPassword" = 'admin@pass'

to connect with the user you have 
"uerName" = 'user123'
"userPassword" = 'user@pass'

you can also register new user by sending a request to the "/regestNewUser" endpoint as the flowing format

    {
        "userName":"",
        "userPassword":"",
        "usrFirstName":"",
        "userLastName":""
    }

    you don't need to add role because by default it's user role