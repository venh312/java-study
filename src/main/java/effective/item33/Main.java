package effective.item33;

// 코드 33-1
class Favorites {
    public <T> void putFavorite(Class<T> type, T instance) {

    }

    public <T> T getFavorite(Class<T> type) {
        return null;
    }
}

public class Main {
    public static void main(String[] args) {
        Favorites f = new Favorites();
        f.putFavorite(String.class, "JAVA");
        f.putFavorite(Integer.class, 1234);
        f.putFavorite(Class.class, Favorites.class);

    }
}
