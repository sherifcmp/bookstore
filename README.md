<!--
*** README file for mancala game project created for bol.com
-->
[![LinkedIn][linkedin-shield]][linkedin-url]

<br />
  <h3 align="center">Wookie Book Marketplace</h3>

<br />
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#testing">Testing</a></li>
      </ul>
    </li>
    <li>
          <a href="#technical-guide">Technical guide</a>
          <ul>
            <li><a href="#storage">Storage</a></li>
            <li><a href="#deployment">Deployment</a></li>
          </ul>
    </li>
  </ol>
</details>

## About The Project

This project is a marketplace managed by Lohgarra wookie, a bookstore where she can sell books to her friends. The
application stores the books and authors of books and allow CRUD operations for these resources.

### Built With

The project was built with the following technologies/frameworks:

* [JDK v17](https://openjdk.org/projects/jdk/17/)
* [SpringBoot v2.7.4](https://spring.io/projects/spring-boot)
* [H2 v2.1.214](https://www.h2database.com/html/main.html)
* [Maven v3.6.3](https://maven.apache.org/)
* [Git v2.9.0](https://git-scm.com/)
* [Junit jupiter v5.8.2](https://junit.org/junit5/)
* [Mockito v4.5.1](https://site.mockito.org/)

## Getting started

### Prerequisites

1. Download and install [JDK v17](https://openjdk.org/projects/jdk/17/)
2. Download and install [Maven v3.6.3](https://maven.apache.org/)
3. Download and install [Git v2.9.0](https://git-scm.com/),
   then [create and add SSH key to your gitlab account](https://docs.gitlab.com/ee/gitlab-basics/create-your-ssh-keys.html)

<hr/>

### Installation

1. Using Git Bash run the following command to clone the repository

> git clone git@github.com:sherifcmp/bookstore.git

2. To start the application, using command line or from your IDE terminal make sure the project directory is the current
   directory and run the following maven command:

> mvn clean verify spring-boot:run

### Testing

1. To run the test cases, using command line or from your IDE terminal make sure the project directory is the current
   directory and run the following maven command:

> mvn test

2. You can test REST endpoints using swagger docs by visiting [this link](http://localhost:8080/swagger-ui/index.html#/)
   in chrome

## Technical notes

### Storage

The application uses h2 in memory database for storing the books and authors, for production use this can be easily
changed to connect to a database like Google [Cloud SQL](https://cloud.google.com/sql) instance. For scaling, many
considerations can be taken into account based on the defined system quality metrics.

### Data processing

The current implementations assumes the data processed by the application is small for initial load, if data set is
large, a batch processing pattern might be preferred.

### Testing

The application uses the same application database instance for testing as well, this needs to be changed to an
in-memory instance for testing only to avoid changing the state of application database.

### Monitoring, and alerting

Monitoring system health and getting alert when something unexpected happen due to program error, unexpected traffic, or
database issue for example is very important, spring actuator offer health endpoint which is currently configured by
visiting the following URL http://localhost:8080/actuator. a monitoring database system like prometheus can be very
useful in production, it can be used with 1) error alerting system like opsgenie or iris 2) a system like grafana to
show the system traffic in real time, in addition to application resource usage metrics like memory and cpu usage.

### Logging

The application currently is configured to log request and response, also logging hibernate activity. a better logging
approach can be used, which can be based on log levels and with external configuration each package log level can be
tuned. a system for managing and viewing production logs can be used like kibana.

### Deployment

The application is running on embedded tomcat server, you can package and deploy it as described
in <a href="#installation">Installation</a> section. The application can also be deployed to any cloud provider for
example GCP - App Engine (PaaS).you can also containerize the application as docker package and deploy it to GCP IaaS.

[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555

[linkedin-url]: https://www.linkedin.com/in/skhaliq/