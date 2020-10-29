/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EncodeHaffman;

import BinaryTree.TreeHaffman;
import BinaryTree.TreeHaffmanNode;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Класс, для декодирования На вход подается строки и кодировки символов, надо
 * восстановить исходную строку  k и l через пробел — количество различных букв,
 * встречающихся в строке, и размер получившейся закодированной строки
 * Sample Input 2:
 *
 * 4 14 
 * a: 0
 * b: 10 
 * c: 110 
 * d: 111 
 * 01001100100111 
 * Sample Output 2:
 *
 * abacabad
 *
 * @author alenk
 */
public class HaffmanEncode {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // считаем первую строку
        StringTokenizer token = new StringTokenizer(input.nextLine());
        // количество различных букв
        int k = Integer.parseInt(token.nextToken());
        // длина строки
        int l = Integer.parseInt(token.nextToken());
        // мапа для хранения пар символ - кодировка
        Map<Character,String> encode = new HashMap<>();
        // получим представление символов
        encode = BuildMapEncode(k,input);
        // получим корень дерева Хаффмана
        TreeHaffmanNode root = TreeHaffman.buildTreeEncode(encode);
        
        //сичтаем строку закодированную
        String decodeString = input.nextLine();
        // обход дерева
        TreeHaffman.traversalTree(root, decodeString, l);
        System.out.println("");
        
    }
// метод, выполняющий анализ строки и сохранения результата анализа в мапу
    // где ключ - это символ, значение - строковое представление закодированного символа по Хаффману
    private static Map<Character, String> BuildMapEncode(int k, Scanner input) {
        Map<Character,String> resultMap = new HashMap<>();
        //считываем строки () представление числа, строки вида Character: String из 0 и 1
        // всего их к штук
        for(int i = 0; i < k; i++){
            StringTokenizer string = new StringTokenizer(input.nextLine());
            // получим символ (это именно первый элемент в строке)
            Character key = string.nextToken().charAt(0);
            // получим значение и удалим все лишние пробелы
            String value = string.nextToken().strip();
            resultMap.put(key, value);
        }
        return resultMap;
    }

}
