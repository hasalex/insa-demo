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
    gcloud compute firewall-rules update wf-allow-http --allow tcp:80


Prepare the image
------------

*TODO*


Prepare the instance
------------


* Create the instance and log in


    gcloud compute instances create insa-demo --image ubuntu-14-10-java8 --network wf
    gcloud compute ssh insa-demo


* Install the software


    sudo apt install apache2
    sudo apt install openjdk-8-jdk
    sudo apt install unzip
    curl http://download.jboss.org/wildfly/8.2.0.Final/wildfly-8.2.0.Final.tar.gz | tar xfz -

RUN
------------

* Start WildFly


    bin/standalone.sh -b 0.0.0.0

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
