package effective.item13;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public final class PhoneNumber implements Cloneable
{
    private int areaCode, profix, lineNumber;

    public PhoneNumber(int areaCode, int profix, int lineNumber) {
        this.areaCode = areaCode;
        this.profix = profix;
        this.lineNumber = lineNumber;
    }

    @Override
    protected PhoneNumber clone() throws CloneNotSupportedException {
        return (PhoneNumber) super.clone();
    }
}
