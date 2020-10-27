/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package конечныеавтоматы;

import java.util.Scanner;

/**
 * Разбор текста на слова. Если мы встретили символ, то состояние конечного
 * автомата не меняется.
 *
 * @author alenk
 */
// перечисление состояний автомата
// либо в слове либо не в слове
// всего 2 состояния
enum StateOfAutomat {
    OutOfWord,
    InWord
};

public class КонечныеАвтоматы {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // считывание строки из консоли
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();

        // pos - текущая позиция конечного автомата, позиция может быть только до конца строки
        // у автомата только 2 состояния: в слове и не в слове
        // когда мы в слове или в не слова состояние не меняется, оно меняется только при переходе из
        // символа буквы в не букву и наоборот
        // state - отвечает за состояние автомата, начальное состояние не в слове
        StateOfAutomat state = StateOfAutomat.OutOfWord;
        // переменная для запоминания длины текущего слова
        int word_len = 0;
        for (int pos = 0; pos < str.length(); pos++) {
            // определим, какое текущее состояние
            switch (state) {
// если состояние не в слове
                case OutOfWord -> {
                    // если у нас сейчас буква, то мы переходим из состояния не слово в состояние слово
                    if (isAlpha(str.charAt(pos))) {
                        word_len = 1;
                        state = StateOfAutomat.InWord;
                    }
                }
                //если в состояние слово
                case InWord -> {
                    // если мы все еще встречаем букву
                    if (isAlpha(str.charAt(pos))) {
                        // прибавляем к длине слова еще один символ      
                        word_len += 1;
                    } else {
                        // если мы в слове, но встретили не букву, то распечатаем количество символов в слове и 
                        // само слово и меняем состояние автоматаФ
                        System.out.print("Word length: " + word_len);
                        System.out.println(" word: " + str.substring(pos - word_len, pos));
                        state = StateOfAutomat.OutOfWord;
                    }
                }
            }

        }

    }

    // функция проверки, буквенный ли символ текущий символ из строки
    static boolean isAlpha(char symbol) {

        return (symbol >= 'A' && symbol <= 'Z')
                || (symbol >= 'a' && symbol <= 'z');
    }

}
