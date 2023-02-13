# IWEXWORK

Ensure all binaries are updated.
  sudo apt update

Use apt to install Java JDK 17.
  sudo apt install oracle-java17-installer --install-recommends
  
Install the NGINX server.
  sudo apt-get install nginx

cd /etc/nginx/sites-available/
sudo nano default
https://github.com/SanzharAkparaliev/IWEXWORK.git

configuration changes

location ~ \.do$ {
  proxy_pass        http://localhost:8080;
  proxy_set_header  X-Real-IP $remote_addr;
  proxy_set_header  X-Forwarded-For $proxy_add_x_forwarded_for;
  proxy_set_header  Host $http_host;
}

intall maven 
sudo apt update
sudo apt install maven

cloning project
git clone https://github.com/SanzharAkparaliev/IWEXWORK.git
cd IWEXWORK
mvn install
mvn spring-boot:run

Navigate to http://localhost:8080 on your browser.

  
