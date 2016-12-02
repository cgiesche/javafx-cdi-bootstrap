[![Build Status](https://travis-ci.org/cgiesche/javafx-cdi-bootstrap.svg?branch=master)](https://travis-ci.org/cgiesche/javafx-cdi-bootstrap) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/de.perdoctus.fx/javafx-cdi-bootstrap/badge.svg)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22de.perdoctus.fx%22%20AND%20a%3A%22javafx-cdi-bootstrap%22) [![Dependency Status](https://www.versioneye.com/user/projects/57136231fcd19a00454411cd/badge.svg?style=flat)](https://www.versioneye.com/user/projects/57136231fcd19a00454411cd)

**Contexts and Dependency Injection for JavaFX**

This library allows you to use Contexts and Dependency Injection in your JavaFX application. It uses Weld as the underlying CDI implementation.

**Getting Started**

First make sure, you have added javafx-cdi-bootstrap to your classpath. If you are using maven you can achive this by adding the following dependency to your pom.xml:

```xml
<dependency>
    <groupId>de.perdoctus.fx</groupId>
    <artifactId>javafx-cdi-bootstrap</artifactId>
    <version>1.0.3</version>
</dependency>
```

Then add the following `beans.xml` file to your META-INF directory, to enable automatic bean discovery:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://xmlns.jcp.org/xml/ns/javaee" bean-discovery-mode="all">
</beans>
```

After that, simply create a class that extends `FxWeldApplication`. It is very similar to the standard `Application` class from JavaFX core and can be used equally. It also provides a main-method to start the CDI container and your JavaFX application.

```java
public class Main extends FxWeldApplication {

    @Inject
    @Bundle("bundles/Application")
    private final FXMLLoader fxmlLoader;

    public void start(Stage stage, Application.Parameters parameters) throws Exception {
        final Parent mainWindow = fxmlLoader.load(getClass().getResourceAsStream("application.fxml"));
        final Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.show();
    }

}
```

As you can see, the `FXMLLoader` is already being injected by CDI. The `@Bundle` annotation assigns the provided `ResourceBundle` to the injected `FXMLLoader`. An instance of the controller class defined in the FXML-File will automatically be created and managed within the CDI context. 

```xml
<VBox fx:controller="de.perdoctus.fx.Contoller">
   <children>
      <Label text="%label-text" />
      <Button text="%button-text" />
   </children>
</VBox>

```

From now on, you are fully CDI enabled. You can write your own producers, inject everything into controllers and even use CDI events.

```java
public class Controller {
    
    @Inject
    private XyService xyService;
    
    @Inject
    @Bundle
    private ResourceBundle rb;
    
    @Inject
    private Event<FooBar> foobarEvent;
    
    public void on(@Observes Event<OtherEvent> event) {
        ...
    }
    
    ...
}
```
 
**Cheat-Sheet**

This library includes producers for `FXMLLoader` and `ResourceBundle` classes. This is, how you can use them: 

Inject FXMLLoader:
```java
@Inject
private FXMLLoader fxmlLoader;
```

Inject FMXLLoader with "included" ResourceBundle:
```java
@Inject
@Bundle("resourceBundles/General")
private FXMLLoader fxmlLoader;
```

Inject Instance<> of FXMLLoader to be able to receive multiple fresh instances for loading different FXML files. 
```java
@Inject
private Instance<FXMLLoader> fxmlLoader;

public void loadFxmls() throws Exception {
    final FXMLLoader wizardPage1Loader = fxmlLoader.get(); // Fresh instance #1
    final Node wizardPage1View = wizardPage1Loader.load(getClass().getResourceAsStream("/fxml/WizardPage1.fxml"));
    final Object wizardPage1Controller = wizardPage1Loader.getController();

    final FXMLLoader wizardPage2Loader = fxmlLoader.get(); // Fresh instance #2
    final Node wizardPage2View = wizardPage2Loader.load(getClass().getResourceAsStream("/fxml/WizardPage2.fxml"));
    final Object wizardPage2Controller = wizardPage2Loader.getController();
}
```

Inject a ResourceBundle:
```java
@Inject
@Bundle("resourceBundles/LoginDialog")
private ResourceBundle loginDialogRb;
```


