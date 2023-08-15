package effective.item14;

class Person implements Comparable<Person> {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static int sgn(int number) {
        if (number > 0) {
            return 1; // 양수의 경우
        } else if (number < 0) {
            return -1; // 음수의 경우
        } else {
            return 0; // 0인 경우
        }
    }

//    @Override
//    public int compareTo(Person otherPerson) {
//        return age - otherPerson.getAge(); // 오름차순
//        //return otherPerson.getAge() - age; // 내림차순
//    }

    @Override
    public int compareTo(Person otherPerson) {
        return Integer.compare(this.age, otherPerson.age);
    }

    @Override
    public String toString() {
        return name + " (" + age + " years)";
    }

    public static void main(String[] args) {
        Person x = new Person("Alice", 30);
        Person y = new Person("Bob", 25);

        // 대칭성
        // sgn(x.compareTo(y)) == -sgn(y.compareTo(x))
        System.out.println(sgn(x.compareTo(new Person("Bob", 25))));
        System.out.println(-sgn(y.compareTo(new Person("Alice", 30))));
    }
}
