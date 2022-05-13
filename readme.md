## Document
This is a command tool that supports the exchange of the currency.
### Start
Use the maven command to compile and package the project.

```
mvn install
```
### Usage
Use the following command to get the conversion result of two currencies.

#### CommandLine

```
java -jar currency-0.0.1-SNAPSHOT.jar cdata='{"fromCurrency":"eur","toCurrency":"gbp","fromAmount":30.222}'
java -jar currency-0.0.1-SNAPSHOT.jar hcdata='{"fromCurrency":"eur","toCurrency":"gbp","fromAmount":30.222,"date":"2022-05-10"}'
```
### JSON File
```
java -jar currency-0.0.1-SNAPSHOT.jar cdata=convertCurrency.json
java -jar currency-0.0.1-SNAPSHOT.jar hcdata=historicalConvertCurrency.json
```

### YAML File
```
java -jar currency-0.0.1-SNAPSHOT.jar cdata=convertCurrency.yml
java -jar currency-0.0.1-SNAPSHOT.jar hcdata=historicalConvertCurrency.yml
```
**convertCurrency.json,convertCurrency.json,historicalConvertCurrency.json,historicalConvertCurrency.yml is located in the resource directory.
When we test the command tool, we need to put them in the same directory as the jar file.**

