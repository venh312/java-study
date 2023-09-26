package effective.item63;

public class Main {
    // 코드 63-1 문자열 연결을 잘못 사용한 예
    static String statement(int length) {
        String result = "";
        for (int i = 0; i < length; i++)
            result += i;
        return result;
    }

    // 코드 63-2 StringBuilder 를 사용하면 문자열 연결 성능이 크게 개선된다.
    static String statement2(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++)
            sb.append(i);
        return sb.toString();
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        statement(300000);
//        statement2(300000);
        long endTime = System.currentTimeMillis() - startTime;
        System.out.println(endTime + " ms");
    }
}
