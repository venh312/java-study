package effective.item65;

import java.lang.reflect.*;

public class ReflectionExample {
    public static void main(String[] args) {
        // 리플렉션을 사용하여 클래스 정보를 얻어옵니다.
        Class<?> myClass = MySampleClass.class;

        // 클래스 이름 출력
        System.out.println("Class Name: " + myClass.getName());
        System.out.println("=============");
        // 클래스의 메서드 목록 출력
        Method[] methods = myClass.getDeclaredMethods();
        System.out.println("=> Methods:");
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        System.out.println("=============");
        // 클래스의 필드 목록 출력
        Field[] fields = myClass.getDeclaredFields();
        System.out.println("=> Fields:");
        for (Field field : fields) {
            System.out.println(field.getName());
        }
    }
}

class MySampleClass {
    private int myField;
    
    public void myMethod1() {
        // 메서드 내용
    }
    
    public void myMethod2() {
        // 메서드 내용
    }
}
