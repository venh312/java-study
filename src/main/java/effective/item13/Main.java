package effective.item13;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        /*
        클래스는 복제 가능한 인터페이스를 구현하여 Object.clone() 메서드에 해당 클래스의 인스턴스를 필드별로 복사하는 방법이 합법임을 나타냅니다.
        복제 가능한 인터페이스를 구현하지 않는 인스턴스에서 개체의 복제 메서드를 호출하면 CloneNotSupportedException 예외가 발생합니다.
        일반적으로 이 인터페이스를 구현하는 클래스는 공개 메서드로 (보호되는) Object.clone을 재정의해야 합니다.
        이 인터페이스는 복제 메서드를 포함하지 않으므로 이 인터페이스를 구현한다는 사실만으로 개체를 복제할 수 없습니다. 복제 메서드가 반사적으로 호출되더라도 성공한다는 보장은 없습니다.
         */
        //Cloneable c = new Cloneable() {}
        PhoneNumber phoneNumber = new PhoneNumber(111,222, 333);
        PhoneNumber copy = phoneNumber.clone();
        System.out.println(phoneNumber);
        System.out.println(copy);
        phoneNumber = new PhoneNumber(444,555, 666);
        //phoneNumber.setProfix(22);
        System.out.println(phoneNumber);
        System.out.println(copy);
    }
}
