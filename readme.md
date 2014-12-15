Run the example on Google Cloud Engine
=============

Setup gcloud
------------

* Download and install it

* Set it up, and plug it on the project (sewacloud in my case)


    gcloud auth login
    gcloud config set project sewacloud
    gcloud config set compute/zone europe-west1-c


Prepare the project
------------


* Prepare the network


    gcloud network create wf
    gcloud compute firewall-rules create wf-allow-http  --network wf --description "Incoming http allowed." --allow tcp:80 tcp:8080 tcp:9990
    gcloud compute firewall-rules create wf-allow-ssh  --network wf --description "Incoming ssh allowed." --allow tcp:22
    gcloud compute firewall-rules update wf-allow-http --allow tcp:80 tcp:8080 tcp:9990


Prepare the image
------------

*TODO*


Prepare the instance
------------


* Create the instance and log in


    gcloud compute instances create insa-demo --image ubuntu-14-10-java8 --machine-type f1-micro --network wf
    gcloud compute ssh insa-demo


* Install the software


    sudo apt install apache2
    sudo apt install openjdk-8-jdk
    curl http://download.jboss.org/wildfly/8.2.0.Final/wildfly-8.2.0.Final.tar.gz | tar xfz -


RUN
------------

* Configure and start WildFly


    cd wildfly-8.2.0.Final/
    bin/add-user.sh --silent alexis hassler
    bin/standalone.sh -b 0.0.0.0 -bmanagement 0.0.0.0


* Deploy the app


    cp ~/Projets/insa/insa-demo/target/insa-cloud-1.0.war ~/tmp/ROOT.war
    <my-local-wildfly>/bin/jboss-cli.sh --controller=23.251.128.77:9990

... in the cli terminal :

    deploy ~/tmp/ROOT.war


Configure Apache
===============

* From the client


    gcloud compute copy-files .google-cloud/proxy_http.conf insa-demo:~


* On the server


    sudo ln -s /etc/apache2/mods-available/proxy.load /etc/apache2/mods-enabled/
    sudo ln -s /etc/apache2/mods-available/proxy_http.load /etc/apache2/mods-enabled/
    sudo mv ~/proxy_http.conf /etc/apache2/mods-available/
    sudo ln -s /etc/apache2/mods-available/proxy_http.conf /etc/apache2/mods-enabled/


Connect to the DB
===============

* Install MySQL


    sudo apt install mysql-server


* Connect as root


    mysql -u root -p


* Create the DB and the user


    CREATE DATABASE insademo;
    GRANT ALL ON insademo.* TO insademo@localhost IDENTIFIED BY 'insademo';


* Download the driver


    wget http://central.maven.org/maven2/mysql/mysql-connector-java/5.1.34/mysql-connector-java-5.1.34.jar


* Create the DataSource (from jboss-cli, on the server)


    module add --name=com.mysql.jdbc --resources=mysql-connector-java-5.1.34.jar  --dependencies=javax.api,javax.transaction.api
    /subsystem=datasources/jdbc-driver=mysql:add(driver-module-name=com.mysql.jdbc,driver-name=mysql)
    data-source add --name=MySQLDS --jndi-name=java:jboss/datasources/MySQLDS --connection-url=jdbc:mysql://localhost:3306/insademo --user-name=insademo --password=insademo --driver-name=mysql


Clean
------------

Remove the instance, at the end of the demo


     gcloud compute instances delete insa-demo --delete-disks all


Run the example on Open Shift
=============

Setup rhc
------------


    sudo gem install rhc
    rhc setup


Prepare the domain
------------


    rhc domain create swt


Prepare the instance
------------


    rhc create-app insademo jboss-wildfly-8
    # rem : no - in the name


Prepare the DataBase
------------

    rhc cartridge add mysql-5.5 -a insademo

Uncomment the javax.sql.DataSource in WEB-INF/web.xml


Push & run the app
------------


    git remote add openshift ssh://`rhc show-app insademo --gears ssh`/~/git/insademo.git/
    git push -f openshift master


Clean
----------


    git remote remove openshift
    rhc delete-app insademo
