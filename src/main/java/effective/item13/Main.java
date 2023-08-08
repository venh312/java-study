package effective.item13;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        PhoneNumber phoneNumber = new PhoneNumber(111,222, 333);
        PhoneNumber copy = phoneNumber.clone();
        System.out.println(phoneNumber);
        System.out.println(copy);

        Stack stack = new Stack();
        stack.push("value1");
        stack.push("value2");

        Stack copyStack = stack.clone();
        copyStack.push("value3");

        System.out.println(stack);
        System.out.println(copyStack);

        CopyConstructor copyConstructor = new CopyConstructor(111,222, 333);
        CopyConstructor copyCopyConstructor = new CopyConstructor(copyConstructor);
        System.out.println(copyConstructor);
        System.out.println(copyCopyConstructor);
    }
}
