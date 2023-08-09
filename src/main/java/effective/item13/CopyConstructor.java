package effective.item13;

import lombok.ToString;

@ToString
public final class CopyConstructor {
    private int areaCode, profix, lineNumber;

    public CopyConstructor(CopyConstructor phoneNumber2) {
        this.areaCode = phoneNumber2.areaCode;
        this.profix = phoneNumber2.profix;
        this.lineNumber = phoneNumber2.lineNumber;
    }

    public CopyConstructor(int areaCode, int profix, int lineNumber) {
        this.areaCode = areaCode;
        this.profix = profix;
        this.lineNumber = lineNumber;
    }
}
