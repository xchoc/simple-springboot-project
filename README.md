# Simple SpringBoot Project

Spring Boot + PostgreSQL + Auth0


## Used code and links
* [Auth0 Integration Samples for Java Spring Security MVC](https://auth0.com/docs/quickstart/webapp/java-spring-security-mvc/01-login)
* [Testing the Web Layer using MockMvc](https://spring.io/guides/gs/testing-web/)
* [Testing Method Security @WithMockUser](https://docs.spring.io/spring-security/site/docs/current/reference/html/test-method.html)
* [SpringBoot-Auth0-ThymeLeaf](https://github.com/qtzar/SpringBoot-Auth0-ThymeLeaf)
* [Spring Boot - from beginning to production (rus)](https://habrahabr.ru/post/257223/)
* [HikariCP](https://github.com/brettwooldridge/HikariCP/wiki/Down-the-Rabbit-Hole)
* [Bootstrap Alerts - toastr](https://mdbootstrap.com/javascript/alerts/)

## Load database from dump file
```
psql -h localhost -p 7301 -U postgres -f app_data.backup postgres
```

## Auth0 setup
1. Create [Auth0](https://auth0.com/) account.
2. Add domain, client id and secret to *application.properties*:
```
# Auth0 settings
com.auth0.domain=[FIX_ME]
com.auth0.clientId=[FIX_ME]
com.auth0.clientSecret=[FIX_ME]
```
3. *(Optional)* Auth0 enables also to use Custom Database. Please find more information: [Authenticate Users using a Custom Database](https://auth0.com/docs/connections/database/custom-db).
4. JSON that will be returned from Auth0. "Sub" is unique id of user:
```
{
    "sub": "auth0|5a4bc949ebc33d03b8b2639d",
    "nickname": "test",
    "name": "test@gmail.com",
    "picture": "https://s.gravatar.com/avatar/662e5c603f4fa638abed0eed9650bd60?s=480&r=pg&d=https%3A%2F%2Fcdn.auth0.com%2Favatars%2Fda.png",
    "updated_at": "2018-01-09T23:02:49.937Z",
    "email": "test@gmail.com",
    "email_verified": true
}
```
