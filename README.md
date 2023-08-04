 Ensure all binaries are updated.
  sudo apt update
--------------------------------------------------------------------------------------------------------------------------------------
  Use apt to install Java JDK 17.
  sudo apt install oracle-java17-installer --install-recommends
 --------------------------------------------------------------------------------------------------------------------------------------
Install the NGINX server.
  sudo apt-get install nginx

cd /etc/nginx/sites-available/

-sudo nano exam

server {
    server_name example.com;
    index index.html index.htm;
    access_log /var/log/nginx/example.log;
    error_log  /var/log/nginx/example-error.log error;

    location / {
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header Host $http_host;
        proxy_pass http://127.0.0.1:8080;
        proxy_redirect off;
    }
}

save and exit

ln -s /etc/nginx/sites-available/exam /etc/nginx/sites-enabled/
--------------------------------------------------------------------------------------------------------------------------------------

after you configure ngnix with name "exam"


sudo apt update
sudo apt install maven (if you have error on start or run the project "it means wrong version you install")

*****************************************************
you can clone your project with git

git clone https://github.com/github_name/project_name.git

If your reposity is private, you need to write your account information when terminal asks.

******
Create database 
******

-sudo mysql -u root -p

-CREATE DATABASE calendar CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-with "utf8mb4_unicode_ci" we can also insert russian characters into mysql (we also use latin characters)

-exit

--------------------------------------------------------------------------------------------------------------------------------------


After you create database, you need to configure your "application.properties" in "\src\main\resources"

Like: 
*******************************
spring.datasource.url=jdbc:mysql://localhost:3306/database_name
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.generate-ddl=true
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
server.port=8080
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.main.allow-circular-references:true


server.error.path=/error
*******************************
after that you are ready for your project to live


cd project_name

sudo mvn spring-boot:run  (when you close terminal, your project also down"for test you can use)
sudo mvn spring-boot:start (even closing your terminal, project will stay alive)
sudo mvn spring-boot:stop (you can stop your project whenever you want)

********
After you make your project live

you can get free ssl certificate for your server domain "example.com"

sudo apt install certbot python3-certbot-nginx

sudo certbot --nginx -d example.com -d www.example.com

IMPORTANT NOTES:
 - Congratulations! Your certificate and chain have been saved at:
   /etc/letsencrypt/live/example.com/fullchain.pem
   Your key file has been saved at:
   /etc/letsencrypt/live/example.com/privkey.pem
   Your cert will expire on 2020-08-18. To obtain a new or tweaked
   version of this certificate in the future, simply run certbot again
   with the "certonly" option. To non-interactively renew *all* of
   your certificates, run "certbot renew"
 - If you like Certbot, please consider supporting our work by:

   Donating to ISRG / Let's Encrypt:   https://letsencrypt.org/donate
   Donating to EFF:                    https://eff.org/donate-le



Finally you can see your project at "example.com" with free ssl certificate.
