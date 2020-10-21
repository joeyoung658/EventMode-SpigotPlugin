package Origin.EventMode.Data;

import java.util.InputMismatchException;

public class StringToInt {
    public StringToInt() {
    }

    public static int stringToInt(String a) {
        try {
            int result = Integer.parseInt(a);
            return result;
        } catch (InputMismatchException var2) {
            return 0;
        }
    }
}
