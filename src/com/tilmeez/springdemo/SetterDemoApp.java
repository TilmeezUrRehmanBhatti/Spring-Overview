package com.tilmeez.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SetterDemoApp {

    public static void main(String[] args) {

        // load the spring config file
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");

        // retrieve beans from spring container
        CricketCoach theCoach = context.getBean("myCricketCoach", CricketCoach.class);

        // call methods on bean
        System.out.println(theCoach.getDailyWorkout());

        System.out.println(theCoach.getDailyFortune());

        // call our new methods to get the literal values

        System.out.println(theCoach.getEmailAddress());

        System.out.println(theCoach.getTeam());

        // close context
        context.close();

    }
}
