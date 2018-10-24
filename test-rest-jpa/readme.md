# Testing Spring Boot REST Jpa Repository

## With MockMvc

### Without @SpringBootTest

See example: `/src/test/java/hello/mock_mvc/spring_runner/EmployeeControllerTest.java`

Add @ContextConfiguration for scan packages:

```
@ContextConfiguration(classes = ConfigTest.class)
```

and config file:

```
@Configuration
@ComponentScan("hello")
public class ConfigTest { }
```

### With @SpringBootTest

See example: `/src/test/java/hello/mock_mvc/spring_boot_test/EmployeeControllerTest.java`

Remove @ContextConfiguration and not need config file, only add `@SpringBootTest`