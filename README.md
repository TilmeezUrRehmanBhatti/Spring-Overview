# Spring-Overview


## Why Spring?

**Spring in a Nutshell**
+ Very popular framework for building Java applications
+ Initially a simpler and lightweight alternative to J2EE
+ Provides a large number of helper classes ... make things easier

**What About J2EE???**  
<img src="inkdrop://file:3su59w4iK" width = 600 />

<img src="inkdrop://file:wjSsYm1n-" width = 600 />

+ **JMS**- Java Message Service
+ **RMI** - Remote Method Invocation
+ **CMP** - Container Managed Persistence
+ **JCA** - Java Connector Architecture
+ **JPA** - Java Persistence API
+ **JSF** - Java Server Faces
+ **JAXB** - Java API for XML Binding
+ **JAX-WS** - Java Web Services (SOAP)
+ **JAX-RS** - Java Web Services (RES)
+ **CDI** - Context Dependency Injection (IoC)
+ **JMS** - Java Message Service

**EJB v1 and v2 - Complexity**
+ Early version of EJB (v1 and v2) were extremely complex!!!
+ Multiple deployment descriptors
+ Multiple interfaces
+ Poor Performance of Entity Beans



   <img src="inkdrop://file:KUCPndZuf" width = 100/>

**J2EE Development without EJB**
+ Rod Johnson
    + Founder of Spring
+ Book: J2EE Development without EJB, Wrox Press
+ Book: Java Development with Spring Framework, Wrox Press

![clipboard.png](inkdrop://file:1DqHEG0QF)

**Spring 5**
+ Update minimum requirements for Java 8 or higher
+ Deprecated legacy integration for: Tiles, Velocity, Portlet, Guava etc
+ Upgraded Spring MVC to use new version of Servlet API 4.0
+ Added new reactive programming framework: Spring WebFlux

# Spring Core Framework

[Spring Website](www.spring.io)

**Goals of Spring**
+ Lightweight development with Java POJOs(Plain-Old_Java-Objects)
+ Dependency injection to promote losse coupling
+ Declarative programming with Aspect-Oriented-Programming (AOP)
+ Minimize boilerplate Java code

![clipboard.png](inkdrop://file:Xoj6Xo4XV)


**What are Spring "Projects"**
+ Additional Spring **modules** built-on top of the core Spring Framework
+ Only use what you need...
    + Spring Cloud, Spring Data
    + Spring Batch, Spring Security
    + Spring for Android, Spring Web Flow
    + Spring Web Services, Spring LDAP

# Spring Inversion of Control -XML Configuration

## Inversion of Control (IoC)
The approach of outsourcing the construction and management of objects.

**Coding Scenario**

<img src="inkdrop://file:Nl5bBUoSr" width = 500 />    

+ App should be configurable
+ Easily change the coach for another sport
    + Hockey, Cricket, Tennis, Gymnastics etc...

**Code Demo**
+ **MyApp.java:** main method
+ **BaseballCoach.java**
+ **Coach.java:** interface after refactoring
+ **TrackCoach.java**


**Spring Inversion of control**

_Spring Container_

<img src="inkdrop://file:1f03z31Le" width = 600 />

+ Spring provides an object factory, so application talk to spring.
+ Based on a configuration file or annotation, Spring will give the appropriate implementation.

<img src="inkdrop://file:CyO21iKuv" width = 200 />

+ Primary functions
    + Create and manage objects (Inversion of Control)
    + Inject object's dependencies (Dependency Injection)


**Configuring Spring Container**
+ XML configuration file (legacy, but most legacy apps still use this)
+ Java Annotation (modern)
+ Java source code (modern)

**Spring Development Process**
1. Configure your Spring Beans
2. Create a Spring Container
3. Retrieve Beans from Spring Container

*Step 1: Configure Spring Beans*

File:applicationContext.xml
```XML
<beans ...>
  <bean id="myCoach"
        class="com.tilmeez.springdemo.BaseballCoach">
  </bean>
</beans>
```
+ The `id=` is like alias
+ `class=` is fully qualified class name of implementation class

*Step 2: Create a Spring Container*
+ Spring container is generally known as **ApplicationContext**
+ Specialized implementation
    + ClassPathXmlApplicationContext
    + AnnotationConfigApplicationContext
    + GenericWebApplicationContext
    + other ...

```Java
ClassPathXmlApplicationContext context =
  new ClassPathXmlApplicationContext("applicationContext.xml");
```
+ `"applicationContext.xml"` is name of config file from step 1

*Step 3: Retrieve Beans from Container*

+ Application gonna talk to Spring Container give me a coach object
    + Based on application configuration file it will give implementation of that given interface.

```JAVA
// create a spring container
ClassPathXmlApplicationContext context =
  new ClassPathXmlApplicationContext("applicationContext.xml");

// retrieve beans from spring container
Coach theCoach = context.getBean("myCoach", Coach.class);
```
<img src="inkdrop://file:H7M-rfUKw" width = 300 />

**What is a Spring Bean?**

A "Spring Bean" is simply a Java object.

When Java objects are created by the Spring Container, then Spring refers to them as "Spring Beans".

Spring Beans are created from normal Java classes .... just like Java objects.

Here's a blurb from the Spring Reference Manual
> In Spring, the objects that form the backbone of your application and that are managed by the Spring IoC container are called beans. A bean is an object that is instantiated, assembled, and managed by a Spring IoC container. Otherwise, a bean is simply one of many objects in your application. Beans, and the dependencies among them, are reflected in the configuration metadata used by a container.

Source: https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#beans-introduction

In the early days, there was a term called "Java Beans". Spring Beans have a similar concept but Spring Beans do not follow all of the rigorous requirements of Java Beans.

**Question**

Why do we specify the Coach interface in getBean()?

For example:

```JAVA

Coach theCoach = context.getBean("myCoach", Coach.class);

```



**Answer**


When we pass the interface to the method, behind the scenes Spring will cast the object for you.

```JAVA
context.getBean("myCoach", Coach.class)
  ```


However, there are some slight differences than normal casting.

From the Spring docs:

*Behaves the same as getBean(String), but provides a measure of type safety by throwing a BeanNotOfRequiredTypeException if the bean is not of the required type. This means that ClassCastException can't be thrown on casting the result correctly, as can happen with getBean(String).*

Source:  <http://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/BeanFactory.html#getBean-java.lang.String-java.lang.Class->

# Spring Dependency Injection - XML Configuration

## Spring Dependency Injection - Overview

                          The dependency inversion principle. 

     The client delegates to calls to another object the responsibility of providing its dependencies .


**Car factory**
.    
<img src="inkdrop://file:FS2gMoq8J" width= 400/>

_Spring Container_


 <img src="inkdrop://file:7xrUJup2a" width = 600 />


+ Primary functions
    + Create and manage objects (Inversion of Control)
    + Inject object's dependencies (Dependency Injection)

**Demo Example**

+ our **Coach** already provides daily workouts
+ Now will also provide daily fortunes
    + New helper:**FortuneService**
    + This is a **dependency**

**Injection Types**
+ There are many types of injection with spring
+ The most common
    + Constructor Injection
    + Setter Injection

### Constructor Injection

**Deployment Process - Constructor Injection**
1. Define the dependency interface and class
2. Create a constructor in your class for injection
3. Configure the dependency injection in Spring config file

*Step 1: Define the dependency interface and class*

File:FortuneService.java
```JAVA
public interface FortuneService {
  
  public String getFortune();

}
```

File:HappyFortuneService.java
```JAVA
public class HappyFortuneService implements FortuneService {
  
  public String getFortune() {
    return "Today is your lucky day!";
  }
}
```


*Step 2:Create a constructor in your class for injection*

File:BaseballCoach.java
```JAVA
public class BaseballCoach implements Coach {
  
  private FortuneService fortuneService;
  
  public BaseballCoach(FortuneService theFortuneService) {
    fortuneService = theFortuneService;
  }
  ...
}
```

<img src="inkdrop://file:nTbbVOerl" width=300 />


*Step 3:Configure the dependency injection in Spring config file*

File:applicationContext.XML
```XML
<bean id="myFortuneService"
      class="com.tilmeez.springdemo.HappyFortuneService">
</bean>

<bean id="myCoach"
      class="com.tilmeez.springdemo.BaseballCoach">
  <constructor-arg ref="myFortuneService" />
</bean>
```

<img src="inkdrop://file:811Sc0rKG" width=500 />


**How Spring Processes your Config File**

![clipboard.png](inkdrop://file:XtKFb30Hn)

### Setter Injection

              Inject dependencies by calling setter method(s) on your class

**Development Process - Setter Injection**
1. Create setter method(s) in your class injections
2. Configure the dependency injection in Spring config file

*Step 1:Create setter method(s) n your class for injection*    
File:CricketCoach.java   
<img src="inkdrop://file:K9BQKRk-E" width=500 />

*Step 2: Configure the dependency injection in Spring config file*

File:applicationContext.xml   
<img src="inkdrop://file:2sbVJOu8x" width=500 />


**Call setter method on Java class**

<img src="inkdrop://file:mTP9PHNBc" width=500 />

**How Spring Processes your Config File**

<img src="inkdrop://file:LHA2q8wfS" width=500 />


## Injecting Literal Values

<img src="inkdrop://file:Yp_T4ttcG" width=300 />

**Development Process**
1. Create setter method(s) in your class for injections
2. Configure the injection in Spring config file

_Step 1: Create setter method(s) in your class for injections_

File:CricketCoach.java
```JAVA
public class CricketCoach implements Coach {
  
  private String emailAddress;
  private String team;
  
  public void setEmailAddress(String emailAddress) ...
  
  public void setTeam(String team) ...
    ...
}
```

_Step 2:Configure the injection in Spring config file_

File:applicationContext.xml
```XML
<bean id="myCricketCoach"
      class="com.tilmeez.springdemo.CricketCoach">
  <property name="fortuneService" ref="myFortuneService"/>
  <property name="emailAddress" value="thebestcoach@gmail.com" />
  <property name="team" value="Sunrsisers Hyderbad" />
</bean>
```

**Question:**

For the CricketCoach example with Setter Injection, why do we use the CricketCoach class instead of the Coach interface?

**Answer:**


The getTeam() method is only defined in the CricketCoach class. It is not part of the Coach interface.

As a result, you would need the following code:

```JAVA
CricketCoach theCricketCoach = 
             context.getBean("myCricketCoach", CricketCoach.class);
```

\---

The Coach interface has two methods: getDailyWorkout and getDailyFortune

The CricketCoach class has four methods: getDailyWorkout, getDailyFortune, getTeam and setTeam

\---

When you retrieve a bean from the Spring container using the Coach interface:

```JAVA
Coach theCricketCoach = context.getBean("myCricketCoach", Coach.class)
```

You only have access to the methods defined in the Coach interface: getDailyWorkout and getDailyFortune. Even though the actual implementation has additional methods, you only have visibility to methods that are defined at the Coach interface level.

\---

When you retrieve a bean from the Spring container using the CricketCoach class:

```JAVA
CricketCoach theCricketCoach = 
  context.getBean("myCricketCoach",CricketCoach.class);
``` 

You have access to the methods defined in the Coach interface: getDailyWorkout and getDailyFortune.

ALSO, you have access to the additional methods defined in the CricketCoach class: getTeam, setTeam.

\---

The bottom line is it depends on how you retrieve the object and assign it ... that determines the visibility you have to the methods.


## Injecting Values from a Properties File

<img src="inkdrop://file:hg3V_SEIY" width=300 />

**Development Process**
1. Create Properties File
2. Load Properties File in Spring config file
3. Reference values from Properties File

_Step 1:Create Properties File_   
File:sport.properties
```properties
 (name)       (value)
   |             |
foo.email=myeasycoach@gmail.com
foo.team=Royal Challengers Bangalore
```

_Step 2:Load Properties file in Spring config file_    
File:applicationContext.xml
```XML
<context:property-placeholder location="classpath:sport.properties"/>
```

_Step 3: Reference Values from Properties File_    
File:applicationContext.xml     
<img src="inkdrop://file:BRjgi7TPm" width=300 />

# Spring Bean Scopes and Lifecycle

## Bean Scopes - Overview

+ Scope refers to the life-cycle of a bean
+ It tells
    + How does(long) the bean lives?
    + How many instances are created?
    + How is bean shared

**Default Scope:Singleton**
```XML
<beans ...>
  <bean id="myCoach"
        class="com.tilmeez.springdemo.TrackCoach">
    ...
  </bean>
</beans>
```

*_What is Singleton?_*
+ Spring Container creates only one instance of the bean, by default
+ It is cached memory
+ All requests for the bean
    + Will return a SHARED reference to the SAME bean

<img src="inkdrop://file:ebLihTjsi" width=400 />

**Explicitly Specify Bean Scope**
```XML
<beans ...>
  <bean id="myCoach"
        class="com.tilmeez.springdemo.TrackCoach"
        
        scope="singleton"> <!-- Set-up bean scope -->
    ...
  </bean>
</beans>
```

**Additional Spring Bean Scopes**

| Scope              | Description                                                  |
| ------------------ | ------------------------------------------------------------ |
| **singleton**      | Create a single shared instance of the bean. Default scope.  |
| **prototype**      | Create a new bean instance for each container request.       |
| **request**        | Scoped to an HTTP web request. Only used for web apps.       |
| **session**        | Scoped to an HTTP web session. Only used for web apps.       |
| **global-session** | Scoped to a global HTTP web session. Only used for web apps. |


**Prototype Scope Example**
> Prototype scope: new object for each request

```XML
<beans ...>
  <bean id="myCoach"
        class="com.tilmeez.springdemo.TrackCoach"
        
        scope="prototype"> <!-- Set-up bean scope -->
    ...
  </bean>
</beans>
```
<img src="inkdrop://file:hzvYegvXW" width=400 />

+ Prototype is good for keeping track of stateful data.
+ Prototype is gonna create a new bean for each request for that component or that object.

## Bean Lifecycle

<img src="inkdrop://file:y3XnpOfBg" width=400 />

**Bean Lifecycle Method/Hooks**
+ You can add custom code during **bean initialization**
    + Calling custom business logic methods
    + Setting up handles to resources (db,socket,file etc)
+ You can add custom code during **bean destruction**
    + Calling custom business logic method
    + Clean up handles to resource (db,socket,file etc)

**Init : method configuration**
```XML
<beans ...>
  <bean id="myCoach"
        class="com.tilmeez.springdemo.TrackCoach"
        init-method="doMyStartupStff"> <!-- Set-up bean initialization, Any Method name  -->
    ...
  </bean>
</beans>
```
**Destroy: Method configuration**
```XML
<beans ...>
  <bean id="myCoach"
        class="com.tilmeez.springdemo.TrackCoach"
        init-method="doMyStartupStff"
        destroy-method="doMyCleanupStuff"> <!-- Set-up bean destroy method , Any Method name  -->
    
    ...
  </bean>
</beans>
```

**Development Process**
1. Define your methods for init and destroy
2. Configure the method names in Spring config file

> **Special Note about init and destroy Method Signatures**
> >**Access modifier** The method can have any access modifier (public, protected, private)
> >
> >**Return type** The method can have any return type. However, "void' is most commonly used. If you give a return type just note that you will not be able to capture the return value. As a result, "void" is commonly used.
> >
> >**Method name** The method can have any method name.
> >
> >**Arguments** The method can not accept any arguments. The method should be no-arg.



> *Special Note about Destroy Lifecycle and Prototype Scope*
> > There is a subtle point you need to be aware of with "prototype" scoped beans.
>>>For "prototype" scoped beans, Spring does not call the **destroy method**
>>>
>>> **In contrast to the other scopes, Spring does not manage the complete lifecycle of a prototype bean** *: the container instantiates, configures, and otherwise assembles a prototype object, and hands it to the client, with no further record of that prototype instance.*
>>>
>>> *Thus, although initialization lifecycle callback methods are called on all objects regardless of scope, **in the case of prototypes, configured destruction lifecycle callbacks are not called**. The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding.*
>>>
>>> *---*
>>> This also applies to both XML configuration and Annotation-based configuration.


