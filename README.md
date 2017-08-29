# ensembl-db-convert

The main task of this application is read data from old Ensembl database and populate the new database created within new project scope. The new database is more structured and organized than the old database. So it's used by other project components of this application. And the main usage of the new database is to populate Elastic Search instance with new JSON documents.

This is a standalone application. Hosting is not required. Users can run this application via terminal and see the data conversion.

### Main technologies used in the project.

1. Spring Boot
2. JPA
3. Maven
4. MySQL

### How to clone repository and install dependencies

You need to clone the applicaton from github. To do so, please use the following command.

```
git clone https://github.com/chanakaDe/ensembl-db-convert.git
```

Now you have the latest update of this application. Next part is installing all the dependencies.

Since this is a maven applicatoion, there are many ways to install all the dependencies. It should be quite straightforward to run your application from an IDE with some maven support (Eclipse, IntellIJ , NetBeans). These IDEs will take care about creating the correct classpath and also all the dependencies. This application is created using IntelliJ IDEA Community Version.

If you want to do it manually, please run the following command at the root folder structure of the project.

```
mvn clean install
```

This will compile your project and create the jar you defined in the pom.xml file. It runs the maven phases clean and every phase up to install (compile, test, etc).

This is a maven application and there are plenty of ways to run a maven application. Since this is a Spring Boot application, it doesn't need a seperate server to run. If you need more information, please check https://projects.spring.io/spring-boot/ and "Features" section.

(These are the same instructions of `ensembl-elastic-rest-final` project since both projects use same architecture and technologies : https://github.com/chanakaDe/ensembl-elastic-rest-final)

### Database Configuration and Introduction to codebase

This application mainly access 2 database instances same time. So apart from the standard Spring Boot database configuration, this application is custom build to accesss 2 MySQL databases at the same time.

And inside the project, there are two main sections.

1. Data Recieve 
2. Data Convert

In this URL(https://github.com/chanakaDe/ensembl-db-convert/tree/master/src/main/java/com/db) there are two files named `ConverterDbConfig.java` and `ReceiveDbConfig.java` which are related to packages named under `convert` and `receive`. The main advantage of this two sections is it's easy to understand the code base, and also easy to develop seperately and also easy to debug according to the section we are developing. 

#### How to change database credentials of two database instanses.

All the database configurations are added in the standard `application.properties` file. You can get that from here : 

https://github.com/chanakaDe/ensembl-db-convert/blob/master/src/main/resources/application.properties

##### How to change data recieving database credentials :

```
spring.datasourcetwo.url=jdbc:mysql://ensembldb.ensembl.org:3306/homo_sapiens_core_89_38
spring.datasourcetwo.username=anonymous
spring.datasourcetwo.password=
```
These are the main values you need to change : host , username and password. Since this is a open database, no specific username or password. If you need to add database with username and password, you need to fill those values.

##### How to change data saving new database credentials :

```
spring.datasourceone.url=jdbc:mysql://138.197.117.104:3306/ensembl_new
spring.datasourceone.username=root
spring.datasourceone.password=**********
```
If you need to chnage new data saving database, you need to change these values.

### How to run the application

In above section, you run the `mvn clean install` command to set up the project in your local system. If not, re run the commad again to make sure project is building without errors.

```
mvn clean install
```
Now you can see a folder named `target` in your project structure. Go to the folder and then you can see following file there.

`DBConvert-0.0.1-SNAPSHOT.jar`

You are getting this file because project is completely working and set up properly in your local system. Now you need to run. To do so, opne termianl in your `target` folder and run following command.

```
java -jar DBConvert-0.0.1-SNAPSHOT.jar
```

At the beginning of the application, you can see this Spring logo is appeared on the terminal window. 


And sometimes you may get the following warning message because we are trying to connect to a non SSL database using our Spring application. It will appear continuously and will automatically goes to next step of the application.

`Mon Aug 28 18:33:04 IST 2017 WARN: Establishing SSL connection without server's identity verification is not recommended. According to MySQL 5.5.45+, 5.6.26+ and 5.7.6+ requirements SSL connection must be established by default if explicit option isn't set. For compliance with existing applications not using SSL the verifyServerCertificate property is set to 'false'. You need either to explicitly disable SSL by setting useSSL=false, or set useSSL=true and provide truststore for server certificate verification.`

After all the database conversions done, you can see following message in the terminal.

`------------------------  end db convert ------------------------`

It mean application has converted all the available data transactions properly and now you can stop the application.

