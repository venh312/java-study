package effective.item29;

public class Main {
    static class Person {
    }
    static class Dog {
    }
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(new Person());
        stack.push(new Dog());
        System.out.println( (Person) stack.pop());
        System.out.println( (Person) stack.pop()); // ClassCastException

//        StackGeneric<Person> stackGeneric = new StackGeneric<>();
//        stackGeneric.push(new Person());
//        stackGeneric.push(new Dog());
    }
}
