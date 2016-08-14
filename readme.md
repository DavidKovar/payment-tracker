# payment-tracker

The program outputs a list of all the currency and amounts to the console once per minute. The input can be typed into the command line, and optionally also be loaded from a file when starting up.

The payment tracker imports payment from this sources:

- **File**
- **Command Line**

###  Usage

Execute mvn package to build an executable fat jar.

java -jar target/payment-tracker-1.0-SNAPSHOT.one-jar.jar 

or with optionally loaded from a file when starting up

java -jar target/payment-tracker-1.0-SNAPSHOT.one-jar.jar payments.txt

