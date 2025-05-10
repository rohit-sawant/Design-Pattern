## Table of Content
* [Single Responsibility](#single-responsibility)
  * [Desc](#desc)
  * [Adv](#adv)
  * [DisAdv](#disadv)
  * [Example](#example)
* [Open closed principle](#open-closed-principle)
  * [Desc](#desc-1)
  * [Adv](#adv-1)
  * [Disadv](#disadv-1)
  * [Example](#example-1)
* [Liskov Substitution](#liskov-substitution)
  * [Desc](#desc-2)
  * [Adv](#adv-2)
  * [Disadv](#disadv-2)
  * [Example](#example-2)
* [Interface Segregation](#interface-segregation)
  * [Desc](#desc-3)
  * [Adv](#adv-3)
  * [Disadv](#disadv-3)
  * [Example](#example-3)
* [Dependency Inversion](#dependency-inversion)
  * [Desc](#desc-4)
  * [Adv](#adv-4)
  * [disadv](#disadv-4)
  * [Wrong](#wrong)
  * [Right](#right)


### Single Responsibility
#### Desc
- Single purpose, can have many methods
- if one interface has one responsibility, it is called ISP - interface Segregation Principle

#### Adv
- Better Maintenance
- better Readability
- Easy Testing
- Scalability
- Reusable


#### DisAdv
- More classes, more boilerplate


#### Example
- Right
```java
public interface Invoice {
    public List<String> items = List.of();
    public void addItem(String item) ;
    public void removeItem(String item);
    public double calculateTotal();
}
```

- Wrong
```java
public interface Invoice {
    public void calculateTotal() ;
    public void saveToDatabase() ; // 🛑 unrelated responsibility
    public void printInvoice() ;   // 🛑 unrelated responsibility
}
```

### Open closed principle
#### Desc
- Open for extension, but closed for modification

#### Adv
- do not break existing code
- simplifies testing
- adding new feature is less risky
d
#### Disadv
- More boiler plate
  - overuse of inheritance, too many classes

#### Example
- Wrong

```java
public class PaymentProcessor {
    public void pay(String type) {
        if (type.equals("CreditCard")) {
            // Credit card logic
        } else if (type.equals("UPI")) {
            // UPI logic
        } else if (type.equals("PayPal")) {
            // PayPal logic
        }
    }
} 
```

- Right
```java
public interface PaymentMethod {
    void pay();
}

public class CreditCardPayment implements PaymentMethod {
    public void pay() {
        System.out.println("Paying with Credit Card");
    }
}

public class UpiPayment implements PaymentMethod {
    public void pay() {
        System.out.println("Paying with UPI");
    }
}

public class PaymentProcessor {
    public void processPayment(PaymentMethod method) {
        method.pay();
    }
}
```

### Liskov Substitution
#### Desc
- A child class should be replaceable by parent

#### Adv
- prevent runtime error
- Easily testable
- Strong contract

#### Disadv
- More boiler plate
- Run time instaceof Checking might be required to handle edge case
  - all payment have refund except cash

#### Example
- Wrong

```java
// 🛑 all birds cannot fly
public abstract class Bird {
    public abstract void makeSound();
    public abstract void fly();
}

public class Ostrich extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Ostrich can't fly");
    }

    @Override
    public void makeSound() { System.out.println("Boom boom"); }
}
```

- Right
```java
public abstract class Bird {
    public abstract void makeSound();
}

public abstract class FlyingBird extends Bird {
    public abstract void fly();
}

public class Sparrow extends FlyingBird {
    public void makeSound() { System.out.println("Chirp"); }
    public void fly() { System.out.println("Flying..."); }
}

public class Ostrich extends Bird {
    public void makeSound() { System.out.println("Boom boom"); }
}

```

### Interface Segregation
#### Desc
- A client should not be forced to depend on interfaces it does not use.

#### Adv
- prevent runtime error
- Easily testable
- Strong contract

#### Disadv
- More boiler plate
- Run time instaceof Checking might be required to handle edge case
    - all payment have refund except cash

#### Example
- Wrong
```java
public interface Machine {
    void print();
    void scan();
    void fax();
}
// 🛑 Basic printer can't scan and fax
public class BasicPrinter implements Machine {
    public void print() {
        System.out.println("Printing...");
    }

    public void scan() {
        throw new UnsupportedOperationException("Scan not supported");
    }

    public void fax() {
        throw new UnsupportedOperationException("Fax not supported");
    }
}

```

- Right

```java
public interface Printer {
    void print();
}

public interface Scanner {
    void scan();
}

public interface Fax {
    void fax();
}

public class BasicPrinter implements Printer {
    public void print() {
        System.out.println("Printing...");
    }
}

public class AllInOnePrinter implements Printer, Scanner, Fax {
    public void print() { System.out.println("Printing..."); }
    public void scan() { System.out.println("Scanning..."); }
    public void fax() { System.out.println("Faxing..."); }
}

```

### Dependency Inversion
#### Desc
- High level modules should not be dependent on low level concrete modules. both should be dependent on abstractions
- Don't hardcode dependencies — depend on interfaces, not concrete classes.

#### Adv
- flexible architecture
- Easy testing
- reduced coupling

#### disadv
- when abstraction is never change, then it's an overkill
- More boiler plate

#### Wrong
```java
public class EmailService {
    public void sendEmail(String message) {
        System.out.println("Sending email: " + message);
    }
}

/**
 *  🛑 `NotificationManager` is tightly coupled to `EmailService`.
 *  You can’t easily switch to SMSService, PushNotification, etc.
 */
public class NotificationManager {
    private EmailService emailService = new EmailService();  // tightly coupled

    public void notifyUser(String message) {
        emailService.sendEmail(message);
    }
}
```

#### Right
- interface
```java
public interface NotificationService {
    void send(String message);
}
```
- multiple services
```java
public class EmailService implements NotificationService {
    public void send(String message) {
        System.out.println("Sending email: " + message);
    }
}

public class SMSService implements NotificationService {
    public void send(String message) {
        System.out.println("Sending SMS: " + message);
    }
}
```

- Manager Service
```java

public class NotificationManager {
    private NotificationService service;

    public NotificationManager(NotificationService service) {
        this.service = service;
    }

    public void notifyUser(String message) {
        service.send(message);
    }
}

```





