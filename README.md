# Objectives

  * Write input and output specifications in terms of *requires* and *ensures* statements
  * Derive test inputs using input partitioning
  * Derive test input using boundary value analysis
  * Derive test input using decision tables
  * Write basic black-box test reports

# Class Standards for Black-box testing

  * The specification should make clear the requires and ensures for correct output
  * The ensures classes must be checked in any test
  * Invalid input that violates any requires clause should be tested and checked that it throws a `RuntimeException`
  * Ensures only includes something about exceptions if there is a throws-clause with a non-`RuntimeException`. * Pick a [naming convention](https://dzone.com/articles/7-popular-unit-test-naming) for the tests so that they are self-documenting and be consistent with that convention.
 
 
# Notes and Advice

  * Test design should be driven by:

    1. **Traceability**: from where did this defect originate from?
    2. **Readability** are the tests self-documentating and understandable by others in a reasonbale amount of time?

  * Write test code so that it can evolve together with the program it is testing.
  * Imagine you have to assert
     
     ```java
      assert(a && b && c);
      ```
     
     In this case, the following is preferable because the failure of an individual assertion is identifiable:
     
     ```java
      assert(a);
      assert(b);
      assert(c);
      ```
      
  * Group assertions in meaningful ways. For example, imagine that the goal is to prove that two queues have the same values with the exception of the last value. A solution can factor the two assertion into a single method as follows:
      
      ```java
      @Test
      public void myAssertion(Queue a, Queue b){
        assertEquals(a.size,b.size);
        a.dequeue();
        b.dequeue();
        assertEquals(a,b);
      }
      ```
      
      The above test *assumes* that dequeue is bug free and **Queue** implements equals.

  * [JUnit 5](https://junit.org/junit5/docs/current/user-guide/) allows test-methods to fail with an exception, if it is explicitly is stated that this is expected to happen. So, if a method should throw a null-pointer exception if it gets the argument null as input, this can be tested with the test case:
  
    ```java
    @Test
    void testExpectedException() {
      Assertions.assertThrows(NullPointerException.class, () -> {
        m(null);
      });
    }
    ```
  
# Problems

1. **(30 points)** Consider the **Queue** class that implements a queue with a *rotating array*. The success of testing depends on what is expected and that is defined by the specification. The specification should be *model based* and state properties relative to a *model class* which in this case is a queue; alternatively, a specification can be more formal stating how the internal state of the class changes on each method and ultimately represents the model state.

    For example, in a queue, the *size()* method returns the size of the queue. The specification does not say *how that size is computed* nor does is say *how the size is represented by the implementation*. It only says that *size()* returns the length of the queue and does not change the state of the queue. This specification approach is model based stating properties based on the more abstract model of a queue leaving out specific details to how the queue is implemented. Alternatively, any formal specification used to prove properties of the queue would need to map the actual implementation details to the model state and reason directly about implementation details. For black-box testing, model-based specification is typically the correct level of specification.

    **Part A:** Write a model-based specification for the **Queue** class (the requires and ensures clauses as shown in class).

    **Part B:** Identify invariants on the internal state of the **Queue** that must hold in order for the rotating-queue implementation to behave according to the queue model it is implementing. An important role of the specification is to make clear a sufficient set of requirements at each method to ensure that any invariants needed for correct model behavior hold. 

2. **(20 points)** Consider the method *ff* in the class **Misc** with its specification.

    **Part A:** the precondition in the `@requires` clause can be weakened, meaning that it can be less restrictive in certain situations. Write that weaker clause.

    **Part B:** the postcondition in the `@ensures` clause can be strengthened, meaning that it can be more restrictive in certain situations, Write that stronger clause.

    **Part C:** Apply [black-box testing](https://en.wikipedia.org/wiki/Black-box_testing) to create and implement [JUnit 5](https://junit.org/junit5/) tests in the **FTests** class. Clearly document each test in the class. These tests should be based on the specification.

3. **(50 points)** Your task is to derive test cases for a number of methods using their specifications. Analyze the specifications in order to divide the set of possible inputs into different situations. Then write a set of test cases for each method, which covers the different cases well.

    The assignment is about a class called **WorkSchedule**. The purpose of the class is to manage the schedule for the employees of a company. The time is divided in units of 1 hour and the hours are simply identified by integers (Note that this is an unrealistic simplification). For each hour, the schedule stores the number employees which is needed at that time. It could for instance be that during week-days the need is that 5 employees work at the same time, but less during nights and weekend. For each hour the schedule also stores the names of the employees who have been assigned to work at that hour.
    
    **Your task**: for each specification below
    
      * Specify (the domain and) the input space for each argument to the method (including the object of that method).
      * Divide this input space into partitions, based on the specification.
      * Implement at least one test case for each partition, and possibly additional border cases. The **WorkSchedule** class is in a jar in the project. Use [JUnit 5](https://junit.org/junit5/) for the tests and put them in the **WorkScheduleTests** class.
      * Implement tests based on boundary-value analysis for the input partitions. 
      
    For each test case, give a comment or method name which describes what situation it tests. Additionally, you must explain and motivate why you divided the test cases in the way you did. You must show how you defined the input space, and how you partitioned it. 

```java
/**
 * creates a schedule which contains the hours 0,1,2,( ...,size - 1 where for
 * each hour the number of needed employees is set to zero and there are no
 * employees assigned to it
 */
 public WorkSchedule(int size) {}
 
/**
 * gives back an object of the class Hour, which has two fields: requiredNumber
 * of type int is the required number of employees working at hour time.
 * workingEmployees of type String[] is the names of the employees who have so
 * far been assigned to work at hour time.
 */
 public Hour readSchedule(int time) {}

 /**
  * sets the number of required working employees to nemployee for all hours in
  * the interval starttime to endtime.
  */
  public void setRequiredNumber(int nemployee, int starttime, int endtime) {}

 /**
  * schedules employee to work during the hours from starttime to endtime.
  */
  public boolean addWorkingPeriod(String employee, int starttime, int endtime) {}

/**
 * returns a list of all employees working at some point during the interval
 * starttime to endtime.
 */
 public String[] workingEmployees(int starttime, int endtime) {}

/**
 * returns the closest time starting from currenttime for which the required
 * amount of employees has not yet been scheduled.
 */
 public int nextIncomplete(int currenttime) {}
```

# Grading Rubric

| Problem | Point Value | Your Score |
| ------- | ----------- | ---------- |
| `Queue` specification | 30 | |
| `ff` specification and tests | 20 | |
| `WorkSchedule` tests | 50 | |
| General adherence the class standards for style, names, etc. | 10 | |

# Acknowledgements

Adapted from [TDA567/DIT082, LP2, HT2015](http://www.cse.chalmers.se/edu/year/2016/course/course/TDA567_Testing_debugging_and_verification/schedule.html) by Atze van de Ploeg formerly at the University of Gothenburg, Chalmers, Deparment of Computer Science and Engineering.
