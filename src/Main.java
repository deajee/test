import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите выражение");
        String input = scan.nextLine();

        System.out.println(calc(input));

        scan.close();
    }


    public static String calc(String input) {

        String[] tmpOps=null, operator=null, exp = null;
        int a=0, b=0;
        String op=null, tmpOp, result=null;
        //op = operator[operator.length-1];
        String [] ops = new String[2];

            exp = input.split(" ");
            //System.out.println("exp"+ exp[0] + exp[1] + exp[2]);
            //tmpOps = input.split(" "+"\\W"+" ");
            //operator = input.split("\\w");
            //tmpOp = operator[operator.length-1];
            //op = tmpOp.trim();
            op = exp[1];
            ops[0] = exp[0];
            ops[1] = exp[2];


        //System.out.println("Op="+op);


        //System.out.println("Ops0="+ops[0]);
        //System.out.println("Ops1="+ops[1]);

        if(exp.length > 3) {
                throw new RuntimeException("Выражение не подходит");

        }
        //System.out.println(operator.length);

        if(!((op.matches("-")) | (op.matches("\\+")) | (op.matches("\\*")) | (op.matches("/")))) {
                throw new RuntimeException("Неверный знак");

        }


        if((ops[0].matches("\\d+")) & (ops[1].matches("\\d+"))){
            a = Integer.parseInt(ops[0]);
            b = Integer.parseInt(ops[1]);

            if((a < 1) | (a > 10) | (b < 1) | (b > 10)) {

                    throw new RuntimeException("Выражение не подходит");

            } else result = Integer.toString(operation(a, b, op));
        } else if((ops[0].matches("[IVXivx]+")) & (ops[1].matches("[IVXivx]+"))) {
            a = RomanNum.romanToArabic(ops[0]);
            b = RomanNum.romanToArabic(ops[1]);

            if((a < 1) | (a > 10) | (b < 1) | (b > 10)) {

                    throw new RuntimeException("Выражение не подходит");

            }

            int res = operation(a, b, op);

            if(res < 1) {

                    throw new RuntimeException("Результат меньше 1");

            }

            result = RomanNum.arabicToRoman(res);

        } else {

                throw new RuntimeException("Выражение не подходит");

        }


        return result;
    }
    private static int operation(int a, int b, String op) {
        switch (op) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                return 0;
        }
    }
}