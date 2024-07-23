import java.util.List;
import java.util.ArrayList;

class Solution17 {

    private static final String[] LETTERS = {
            "", // 0
            "", // 1
            "abc", // 2
            "def", // 3
            "ghi", // 4
            "jkl", // 5
            "mno", // 6
            "pqrs", // 7
            "tuv", // 8
            "wxyz" // 9
    };

    public static void getStringLetters(int n, String digits, List<String> array) {
        char digit = digits.charAt(n); // obtengo el caracter de la posicion n
        String letters = LETTERS[Character.getNumericValue(digit)]; // obtengo letras correspondientes
        for (char letter : letters.toCharArray()) { // convierto el string en arreglo de chars
            array.add(String.valueOf(letter)); // convertir el char a string en el arreglo
        }
    }

    public List<String> letterCombinations(String digits) {

        List<String> combinations = new ArrayList<>();

        if (digits.length() == 0) {
            return combinations;
        } else {
            getStringLetters(0, digits, combinations);

            for (int i = 1; i < digits.length(); i++) {
                // mismo procedimiento al anterior
                List<String> newLetters = new ArrayList<>();
                getStringLetters(i, digits, newLetters);

                // aqui pasos 1 y 2 de mi algoritmo
                List<String> tempCombinations = new ArrayList<>();
                for (String combination : combinations) {
                    for (String newLetter : newLetters) {
                        String combinedString = combination + newLetter;
                        tempCombinations.add(combinedString);
                    }
                }
                combinations.clear(); // limpio combinaciones viejas
                combinations.addAll(tempCombinations); // agrego actualización combinaciones
                newLetters.clear(); // limpio arreglo que accede a las letras nuevas
            }

            return combinations;
        }
    }
}