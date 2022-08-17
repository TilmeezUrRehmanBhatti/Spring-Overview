package com.tilmeez.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanScopeDemoApp {
    public static void main(String[] args) {

        // load the spring configuration file
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("beanScope-applicationContext.xml");

        // retrieve beans from spring container
        Coach theCoach = context.getBean("myCoach", Coach.class);


        Coach alphaCoach = context.getBean("myCoach", Coach.class);

        // check if they are same
        boolean result = (theCoach == alphaCoach);

        // print out result
        System.out.println("pointing to the same object: " + result);

        System.out.println("Memory location fot theCoach: " + theCoach);

        System.out.println("Memory location fot alphaCoach: " + alphaCoach);

        // close the context
        context.close();
    }
}
