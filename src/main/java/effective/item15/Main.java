package effective.item15;



public class Main {
    public static void print(int[] arr) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println("AGE : " + Age.AGE);

//        print(Age.AGE_ARR);
//        Age.AGE_ARR[0] = 99;
//        Age.AGE_ARR[1] = 99;
//        Age.AGE_ARR[2] = 99;
//        print(Age.AGE_ARR);

        int[] ageClone = Age.values();
        print(ageClone);
    }
}
