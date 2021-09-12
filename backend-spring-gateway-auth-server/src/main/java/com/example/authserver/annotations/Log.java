package com.example.authserver.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
// 表示可以修饰的范围, packages、types, etc
/**
 * JDK(Java Development Kit) contains JRE(Java runtime environment), JRE contains JVM
 * JDK has compiler(javac)
 * JVM can execute .class file
 * .class file is created by .java file
 * .java file is for human to read
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    String value()default "<blank>";
}
