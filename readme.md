# Payment Tracker

The program outputs a list of all the currency and amounts to the console once per minute. The input can be typed into the command line, and optionally also be loaded from a file when starting up.

The payment tracker imports payment from this sources:

- **File**
- **Command Line**

### Requirements:

- **JRE 1.8**
- **Maven**

###  Compiling & Testing

Program can be compiled and tested using Maven.

for testing use:

```
mvn test
```

for packaging use:

```
mvn package
```

###  Running 

for running use:
```
java -jar target/payment-tracker-1.0-SNAPSHOT.jar 
```
or with optionally loaded from a file when starting up
```
java -jar target/payment-tracker-1.0-SNAPSHOT.jar payments.txt
```
