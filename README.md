# execution-time-tracker
**Execution Time tracker** logs execution time of a method annotated with **TrackExecutionTime**. Just add the dependency and rest will be taken care by **execution-time-tracker-starter**.

## Enabling the execution-time-tracker
The simplest way to enable the features is to add a dependency to the `execution-time-tracker-starter` ‘Starter’.

### To add the actuator to a Maven-based project, add the following ‘Starter’ dependency:

```xml
<dependency>       
  <groupId>com.atuldwivedi</groupId>
  <artifactId>execution-time-tracker-starter</artifactId>
  <version>1.0.0-SNAPSHOT</version>
</dependency>
```

### For Gradle, use the following declaration:

```
dependencies {
	compile("com.atuldwivedi:execution-time-tracker-starter:1.0.0-SNAPSHOT")
}
```

## Enable Features
```
execution-time-tracker:
  log:
    enabled: true
```
