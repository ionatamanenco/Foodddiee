# FoodForYou

Food4you is a generic Web/Mobile application that will help the customers of any restaurant to place their orders without the waiter approaching them.

## Development

After starting the application it is accessible under `localhost:8080`.

## Build

The application can be built using the following command:

```
mvnw clean package
```

The application can then be started with the following command - here with the profile `production`:

```
java -Dspring.profiles.active=production -jar ./target/food-for-you-0.0.1-SNAPSHOT.jar
```