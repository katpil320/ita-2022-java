# ita-2022-java

## About

This project is an educational example of basic REST api, implemented as an e-shop backend for book store.
Our ITA API provides ways to store,update and retrieve products, their authors and genres as well.
Can be used as a backend for frontend application.

## Getting started

Before you can start this app, make sure you've set proper configuration and have running instance of PostgresSQL database.
By default, the app is configured to run along with docker-compose file in root directory. If you want to use your own database,
navigate to src/main/resources/application.properties and change following:
- `spring.datasource.url=(example: dbc:postgresql://localhost:5421/the_database_name)`
- `spring.datasource.username=(db_username)`
- `spring.datasource.password=(db_user_password)`

This app uses AWS S3 buckets as storage for storing previews of products by default.
You just have to provide some details in `app.aws.s3.*`. If you leave it blank, don't expect it will work :)

## Cloud
This app can be run as a microservice too. All you have to do is to enable `spring.cloud.discovery.enabled=` to **true**.
After changing this, the app should be ready to get registered to your discovery servers. 

If you have a configuration server, you can enable it too by changing `spring.cloud.config.enabled=` to **true**.

## Author
Martin Lipták - Developer
