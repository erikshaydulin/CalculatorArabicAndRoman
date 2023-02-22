import java.util.Scanner;
import java.lang.System;


class Main {
    private static boolean numberIsRoman = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Введите выражение : ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Выход из программы.");
                break;
            }

            try {
                String result = calc(input);
                if (numberIsRoman == true) {
                    String resultRoman = arabicToRoman(result);
                    System.out.println("Результат: " + resultRoman);
                } else {
                    System.out.println("Результат: " + result);
                }
            } catch (Exception e){
                System.out.println("Ошибка: " + e.getMessage());
            }
        }
    }



    static String calc(String input) throws IllegalArgumentException {
        String[] tokens = input.split(" ");
        if (tokens.length < 3) {
            throw new IllegalArgumentException("строка не является математической операцией");
        } else if (tokens.length != 3) {
            throw new IllegalArgumentException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }
        String num1;
        String num2;
        num1 = tokens[0];
        num2 = tokens[2];
        try{
            if (isRomanNumeral(num1) == true && isRomanNumeral(num2) == true){
                numberIsRoman = true;
            } else if (isRomanNumeral(num1) == false && isRomanNumeral(num2) == true){
                throw new IllegalArgumentException("Используются одновременно разные системы счисления");
            } else if (isRomanNumeral(num1) == true && isRomanNumeral(num2) == false) {
                throw new IllegalArgumentException("Используются одновременно разные системы счисления");
            } else {
                numberIsRoman = false;
            }
        } catch (Exception e){
            System.err.println("Произошла ошибка: " + e.getMessage());
            System.exit(1);
        }

        int leftOperand = 0;
        int rightOperand = 0;

        if (numberIsRoman == true){
            leftOperand = convertRomanToArabic(num1);
            rightOperand = convertRomanToArabic(num2);
        } else{
            leftOperand = Integer.parseInt(num1);
            rightOperand = Integer.parseInt(num2);
        }
        try{
            if (leftOperand < 1 || leftOperand > 10 || rightOperand < 1 || rightOperand > 10){
                throw new IllegalArgumentException("На вход принимаются только числа от 1 до 10");
            }
        } catch (Exception e){
            System.err.println("Произошла ошибка: " + e.getMessage());
            System.exit(1);
        }
        String operator = tokens[1];
        int result;

        switch(operator){
            case"+":
                result = leftOperand + rightOperand;
                break;
            case"-":
                result = leftOperand - rightOperand;
                break;
            case"*":
                result = leftOperand * rightOperand;
                break;
            case"/":
 //               if (rightOperand == 0){
 //                   throw new IllegalArgumentException("На 0 делить нельзя.");
 //               }
                result = leftOperand / rightOperand;
                break;
            default:
                System.err.println("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                System.exit(1);
                throw new IllegalArgumentException();
        }

        return Integer.toString(result);
    }

    public static boolean isRomanNumeral(String s) {
        String[] romanNumerals = new String[]{"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        for (String romanNumeral : romanNumerals) {
            if (romanNumeral.equals(s)) {
                return true;
            }
        }
        return false;
    }

    public static int convertRomanToArabic(String romanNumeral) {
        String[] romanDecimal = new String[]{"I", "II", "III","IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int[] arabicDecimal = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int i = 0; i < romanDecimal.length; i++) {
            if (romanDecimal[i].equals(romanNumeral)) {
                return arabicDecimal[i];
            }
        }
        return 0;
    }

    public static String arabicToRoman(String numberString) {
        int number = Integer.parseInt(numberString);
        if (number < 1 || number > 100) {
            throw new IllegalArgumentException("в римской системе нет отрицательных чисел");
        }
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };
        return roman[number];
    }
}