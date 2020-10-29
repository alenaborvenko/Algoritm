/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DecodeHaffman;

import BinaryTree.TreeHaffman;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import BinaryTree.TreeHaffmanNode;

/**
 * Кодирование текста по Хаффману Идея заключается в том, чтобы часто
 * встречающемся символам присвоить короткий доступ к примеру, если в тексте
 * симвал а встречается в 50%, то закодируем а символом 0 если б встречается в
 * 25% то присвоем б 10 если с встречается оставшиеся 25%, то присвоем с 11 таки
 * образом часто встречающиеся символы кодируютя меньшим количеством бит и нет
 * неоднозначности (как в азбуке морзе, когда мы не можем различить, что нам
 * передали и или 2 и), так как в узлах нет символов, все символы ТОЛЬКО в
 * листьях двоичного дерева поиска
 *
 * @author alenk
 */
public class Haffman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // считаем строку c консоли
        Scanner input = new Scanner(System.in);
        String str = input.nextLine().strip();
        // для строк не нулевой длины
        if (str.length() != 0) {

            int sizeStr = str.length();
            // проанализируем строку (сколько раз встречается в строке символ)
            final Map<Character, Integer> dictionary = HaffmanParseString(sizeStr, str);
            // если у нас не один повторяющийся символ
            // если символ один, то просто кодируем его 0
            if (dictionary.size() != 1) {
                //создадим все узлы нашего дерева и добавим их в список
                List<TreeHaffmanNode> listNode = new ArrayList<>();
                dictionary.keySet().stream().map(key -> new TreeHaffmanNode(key, dictionary.get(key))).forEachOrdered(newNode -> {
                    listNode.add(newNode);
                });
                // построим дерево и нам интересен только корень, и с корня начнем считывать как кодировать символ 
                TreeHaffmanNode root = TreeHaffman.buildTreeDecode(listNode);
                // результат обхода дерева (должны быть все символы в строке)
                List<Integer> res = new ArrayList<>();
                Map<Character, String> result = new HashMap<>();
                result = TreeHaffman.TableHaffman(root, res, result);
                printCodeHaffman(sizeStr, result, str, dictionary.size());

            } // если у нас только 1 буква тогда и считать ничего не надо
            else if (dictionary.size() == 1) {
                printCodeHaffman(sizeStr, str, dictionary.size());
            }
        } else {
            System.out.println("Empty string!!");
        }
    }

    // метод, анализирующий строку посимвольно и сохраняющий результат частоты вхождения символа
    public static Map<Character, Integer> HaffmanParseString(int sizeStr, String str) {
        // Создадим словарь, где будут наши символы как ключи и значение число (процентное соотношение)
        Map<Character, Integer> dictionary = new HashMap<>();
        for (int i = 0; i < sizeStr; i++) {
            // если в словаре у нас есть уже этот символ
            // пересичтаем ему процент
            if (dictionary.containsKey(str.charAt(i))) {
                // добавим к текущему значению единицу и разделим на общее количество букв в строке
                dictionary.put(str.charAt(i), (1 + (dictionary.get(str.charAt(i)))));
            } else {
                // если элемента нет, то просто положим наш символ
                // процент у нас дробное число
                dictionary.put(str.charAt(i), 1);
            }
        }
        return dictionary;
    }
    // получим теперь строковое представление кодированное хаффманном
    // стринг билдер работает через стек, и не состается каждый раз новый объект типа строка, 
    // конкатенация была бы через пул строк, у нас хранилось бы много кусочков строки в памяти

    public static void printCodeHaffman(int sizeStr, Map<Character, String> result, String str, int dictionarySize) {
        if (dictionarySize != 1) {
            StringBuilder strRes = new StringBuilder("");
            for (int i = 0; i < sizeStr; i++) {
                strRes.append(result.get(str.charAt(i)));
            }
            // распечатаем результат
            String resultStr = strRes.toString();
            System.out.println("" + result.keySet().size() + " " + resultStr.length());
            for (Character key : result.keySet()) {
                System.out.println("" + key + ": " + result.get(key));
            }
            System.out.println(resultStr);
        }
    }

    public static void printCodeHaffman(int sizeStr, String str, int dictionarySize) {
        if (dictionarySize == 1) {

            StringBuilder strRengRes = new StringBuilder("");
            for (int i = 0; i < str.length(); i++) {
                strRengRes.append("0");

            }
            String res = strRengRes.toString();
            System.out.println("1 " + res.length());
            System.out.println("" + str.charAt(0) + ": 0");
            System.out.println(res);
        }

    }
}
