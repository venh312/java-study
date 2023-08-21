package effective.item27;

import effective.item26.Stamp;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // unchecked or unsafe operations. 발생
        // javac 명령줄 인수에 -Xlint:unchecked 추가 권고
        Set<Stamp> set = new HashSet();

        // 위의 옵션을 명시하지 않고 자바 7부터 지원하는 다이아몬드 연산자 <>만으로 해결할 수 있다.
        //Set<Stamp> set = new HashSet<>();
    }
}
