package effective.item33;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// 코드 33-1
class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();

    public <T> void putFavorite(Class<T> type, T instance){
        // favorites 가 비한정적 와일드카드 타입이라 이 맵 안에 아무것도 넣을수 없다고 생각되지만,
        // 와일드카드 타입이 중첩 되었다는 점을 깨달아야 한다.
        // 맵이 아니라 키가 와일드카드 타입인것이다.
        // 이는 모든 키가 서로 다른 매개변수화 타입일 수 있다는 뜻이다.
        favorites.put(Objects.requireNonNull(type), instance);
    } // 클래스의 리터럴 타입은 Class가 아니라 Class<T>이다.

    public <T> T getFavorite(Class<T> type){
        return type.cast(favorites.get(type));
        // Object 타입의 객체(favorites.get(type)를 꺼내 T로 바꿔 반환해야 한다.
        // cast메서드로 이 객체 참조를 Class 객체가 가리키는 타입으로 동적 형변환 한다.
    }
}

public class Main {
    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorite(String.class, "JAVA");
        f.putFavorite(Integer.class, 0xcafebabe);
        f.putFavorite(Class.class, Favorites.class);

        String favoriteString = f.getFavorite(String.class);
        int favoriteInteger = f.getFavorite(Integer.class);
        Class<?> favoriteClass = f.getFavorite(Class.class);

        System.out.printf("%s %x %s%n", favoriteString, favoriteInteger, favoriteClass.getName());
    }
}
