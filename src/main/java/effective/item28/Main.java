package effective.item28;

public class Main {
    static class Animal { }
    static class Dog extends Animal { }
    static class Cat extends Animal { }
    public static void main(String[] args) {
        Animal[] animals = new Dog[5]; // 배열 공변성

        animals[0] = new Dog();
        animals[1] = new Cat(); // 이 부분에서 컴파일 오류는 발생하지 않지만, 런타임에 ArrayStoreException이 발생

        for (Animal animal : animals) {
            System.out.println(animal);
        }
    }
}
