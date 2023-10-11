## Environment

---

<u>**JDK**</u> : Java Development Kit

- Provides the tools to debug, compile and execute Java programs.
- JDK contains the JRE with Java compiler.

<u>**JVM**</u> : Java Virtual Machine

- Responsible for converting the byte code to machine code when executing a Java program.
- Provides Memory management, Garbage Collection, Security, etc.
- Provides platform independence through the VM.

<u>**JRE**</u> : Java Runtime Environment

- Holds the implementation of the JVM
- Only contains tools to execute (and not compile) a Java program (Jar/War).

---

### Annotations

Annotations can be parsed by a tool or the compiler and are available at runtime.

- Built-in annotations :
  - `@Override` for inheritance
  - `@Deprecated`
  - `@SuppressWarnings`
  - `@FunctionalInterface`
  - `@SafeVarargs`


- Custom annotation :
  - Interface annotated with `@Interface`.
  - No parameters.
  - Can have meta annotations attached.


### Class Loader

- Loads the compiled .class files into the JVM memory.
- Built-in classloaders :
  - Bootstrap : loads JDK internal classes.
  - Extension : loads from /extensions directory.
  - System : loads from the current classpath.
- Access : `MyClass.class.getClassLoader();`
- ClassLoaders delegate to their parent loader when loading a class, if not found in the range of the parent,
the loader attempts to load the class itself.


### Unchecked Exceptions

- No need to be declared in a throw clause.
- `RunTimeException` & subclasses :
  - `ClassCastException`
  - `NullPointerException`


### Inheritance

- Hierarchy b/w classes : transitive.
- Private members are hidden and need getters & setters.

```
Cat c = new Cat();  
Animal a = c        // Fine.  
Cat c1 = (Cat) a    // Explicit casting works because c is of type Animal.

Animal a = new Animal();
Cat c1 = (Cat) a    // ClassCastException.
```

### Interfaces

- Abstract Class.
- Methods are abstract and public by default, and do not have a body, except for default methods.
- Attributes are by default public static final.


### Functional Interface

- Interface of only 1 method, annotated `@FunctionalInterface`.
- Purpose :
  - Removes boilerplate code.
  - Can use lambda to instantiate them.
  - ex : `java.util.Function`.
- FI can have non-abstract Object methods, such as `equals()`.


### Pass-by-value

- Java is *pass-by-value*, not *pass-by-reference*.
- *Pass-by-value* : the method parameters are copied to another variable. The method uses the copy.
- *Pass-by-reference* : a reference to the Object is passed to the method. The method uses the actual parameter.


### Increment operators

- <u>Post-increment Operator</u>

`a++` is short for
```
t = a;
a = a + 1;
return t;
```
a is incremented, old value is returned.

- <u>Pre-increment Operator</u>

`++a` is short for
```
a = a + 1;
return a;
```
a is incremented, new value is returned.


### StringBuilder vs StringBuffer

| StringBuffer | StringBuilder    |
|--------------|------------------|
| Thread-safe  | Not Thread-safe  |
| Synchronized | Not synchronized |
| Slower       | Faster           |


### String manipulation

String manipulation is important for several reasons :
- Data cleaning (remove spaces, special chars...).
- Parsing small parts of large datasets.
- Formatting for communication b/w systems or user-friendly outputs.
- Pattern matching for search.


### Immutability

- Enhances performances by reusing existing Objects.
- Can lead to a drop in performances for large Objects as a new Object is created with each manipulation.
- Contributes to safer code, Objects can be used as keys without fear of unexpected change.
- Thread-safe.

### Miscellaneous

- String class is final and immutable.
- <u>All</u> primitive wrappers extend the `java.lang.Number` class.
- `compareTo()` returns 0 is equal, < 0 if before the parameter in lexical order, and > 0 if after.
- `s1.concat(s2)` return a new String and does not modify the existing String.
- The + operator has precedence over == : 
`"result : " + string1 == string2` will transform to `"result : string1" == "string2"` and return `false`.
