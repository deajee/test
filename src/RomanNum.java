import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

enum RomanNum {
    I(1), IV(4), V(5), IX(9), X(10),
    XL(40), L(50), XC(90), C(100);

    private int value;

    RomanNum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static List<RomanNum> getReverseSortedValues() {
        return Arrays.stream(values())
                .sorted(Comparator.comparing((RomanNum e) -> e.value).reversed())
                .collect(Collectors.toList());
    }

    public static int romanToArabic(String input) {

        String roman = input.toUpperCase();
        int result = 0;

        List<RomanNum> romans = RomanNum.getReverseSortedValues();

        int i = 0;

        while ((roman.length() > 0) && (i < romans.size())) {
            RomanNum symbol = romans.get(i);
            if (roman.startsWith(symbol.name())) {
                result += symbol.getValue();
                roman = roman.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        String checking = arabicToRoman(result);

        if (!checking.equals(input)) {
            throw new IllegalArgumentException(input + " не римское число");
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        if ((number <= 0) || (number > 4000)) {
            throw new IllegalArgumentException();
        }

        List<RomanNum> romans = RomanNum.getReverseSortedValues();

        int i = 0;

        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romans.size())) {
            RomanNum currentSymbol = romans.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}

