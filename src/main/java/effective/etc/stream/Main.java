package effective.etc.stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {
    static void code1() {
        int[] arr = {10,4,2,1,5};
        int[] sortArr = Arrays.stream(arr).sorted().toArray();
        System.out.println("intArr : " + Arrays.toString(arr));
        System.out.println("sortArr : " + Arrays.toString(sortArr));
    }

    static void code2() {
        int[] arr = {10,4,2,1,5};
        IntStream intStream = Arrays.stream(arr).sorted();
        System.out.println("원본 : " + Arrays.toString(arr));
        System.out.println("정렬 : " + Arrays.toString(intStream.toArray()));
        System.out.println("정렬된 arr의 합 : " + intStream.sum());
    }

    static void code3() {
        int[] arr = {10,4,2,1,5};
        long result = Arrays.stream(arr) // 소스 스트림
                .map(v -> v * 2) // 중간 연
                .filter(v -> v >= 10) // 중간 연
                .count(); // 종단 연산

        System.out.println("결과 : " + result);
    }

    public static void main(String[] args) {
        code1();
        code2();
        code3();
    }
}
