# OOP and Design Pattern Note:

## SOLID
- Single Responsibility: Each class having a single role --> no more than 1 reason for it to change.
- Open - Closed: A module should be closed for modification but open for extension --> should be able to change what the module do, without changing the source code of the modules
- Liskov Substitution: Subtypes must be substitutable for their base types. --> Sub should expect no more and provide no less.
- Interface Segregation: Many specific interfaces are better than one general purpose interface --> Classes should not depend on interfaces they do not use.
- Dependency Injection: High level modules should not depend upon low level modules --> The dependency should be via abstraction.
<The core of framework design !!!>

## Design Pattern
### Factory Pattern:
- Used when you don't know exactly what kind of classes that you will instantiate until run time.
- When you want to localize the creation logic to instantiate object & not exposing them to the client.
--> Support lazy initialization
### Composition over Inheritance:
- Classes should achieve polymorphic behaviors and code reuse by their composition of instances of other classes that implement the desired functionality (composed of many 'HAS-A' rela instead of being a 'IS-A' of many type).
- Benefits: 
  + More flexibility - we can change implementation of class at run-time by selectively choose the included object. With inheritance cannot simply becuz u cannot change behavior of base class at run time.
  + Multiple inheritance can leads to the diamond problem !!
  + Decouples a class from a complex system
  + Easier for testing
- Drawbacks:
  + Inheritance maybe more useful when used in a 'IS-A' relationship. 
  + A design based on composition might include more objects
### Facade:
- When everything's too complex: Create an object that acts as a platform to talk ...?
### Observer:
- When u need some 'observers' to receive an *UPDATE* when a 'subject' change.
- First- a set of 'observers' might choose to register / unregister to a 'subject'. The subject will store the reference to the observer, so that whenever there's a change to it, the subject will notify the observers of the change.
### Builder:
- When u want to build a complex/composite object using other objects, and follow by a step-by-step approach. 
(Seperate the construction of a complex object from its representation).
- 2 steps:
  1. Encapsulate creating and assembling the parts of a complex object in a seperate 'Builder' object. (E.g: building the parts!!!)
  2. A class delegates object creation to the 'Builder' object.
- We might have a "director" class holding reference to the "builder" class. Through the director, we set the parts to be constructed by the builder. Then the builder returns the object.  

