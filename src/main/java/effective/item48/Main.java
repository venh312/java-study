package effective.item48;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class Main {
    static Long pi(long n) {
        return LongStream.rangeClosed(2, n)
            .mapToObj(BigInteger::valueOf)
            .filter(i -> i.isProbablePrime(50))
            .count();
    }

    static Long piParallel(long n) {
        return LongStream.rangeClosed(2, n)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .filter(i -> i.isProbablePrime(50))
            .count();
    }

    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(10,4,2,5,6,7,1,9,8,3);
//        list.stream().forEach(number ->
//            System.out.println(number + " " + Thread.currentThread().getName())
//        );

        /*
        실행 순서를 보장할 수 없다.
        이러한 병렬 작업은 fork-join 프레임워크을 통해 이루어진다
        fork-join 프레임워크는 분할 정복 방식을 이용해 병렬 처리 속도를 높인다.
        fork: 작업을 더 작은 독립 하위작업으로 재귀적으로 분할한다.
        join: 다시 재귀적으로 모든 하위 작업의 결과가 단일 결과로 결합된다.
         */
//        list.parallelStream().forEach(number ->
//            System.out.println(number + " " + Thread.currentThread().getName())
//        );

//        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4);
//        int sumByStream = listOfNumbers.stream().reduce(2, Integer::sum);
//        int sumByStreamParallel = listOfNumbers.parallelStream().reduce(2, Integer::sum);
//        System.out.println("sumByStream = " + sumByStream);
//        System.out.println("sumByStreamParallel = " + sumByStreamParallel);

        //System.out.println("pi Stream = " + pi(10000000));
        //System.out.println("pi Parallel = " + piParallel(10000000));
    }

}
