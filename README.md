# Learning Hibernate

A simple Java project demonstrating Hibernate ORM framework integration with a basic User entity.

## Project Structure

- `src/main/java/com/snapgramfx/User.java`: Entity class with JPA annotations
- `src/main/java/com/snapgramfx/HibernateUtil.java`: Utility class for managing Hibernate SessionFactory
- `src/main/java/com/snapgramfx/Main.java`: Demo application showing basic Hibernate operations
- `src/main/resources/hibernate.cfg.xml`: Hibernate configuration file

## Configuration

The project is configured to use MySQL database. You may need to modify the following properties in `hibernate.cfg.xml`:

```xml
<property name="hibernate.connection.url">jdbc:mysql://localhost:3306/hibernate_db?createDatabaseIfNotExist=true</property>
<property name="hibernate.connection.username">root</property>
<property name="hibernate.connection.password">password</property>
```

## How to Run

1. Make sure you have MySQL installed and running
2. Update database credentials in `hibernate.cfg.xml` if needed
3. Run the project using Maven:

```bash
mvn clean compile exec:java -Dexec.mainClass="com.snapgramfx.Main"
```

## Dependencies

- Hibernate Core 5.6.15.Final
- MySQL Connector 8.0.33
- H2 Database 2.2.224 (for testing)

## Features

- Basic entity mapping with JPA annotations
- Hibernate SessionFactory configuration
- Simple CRUD operations example