/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryTree;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Класс для построения дерева Хаффмана, у которого узлы TreeHaffmanNode в
 * статический метод (обращаться к этому методу можно без создания экземпляра
 * класса, нам нужен только корень этого дерева, который имеет тип узла) можно
 * было добавить в класс TreeHaffmanNode, но по смыслу этот класс является узлом
 * дерева
 *
 * @author alenk
 */
public class TreeHaffman {

    /**
     * построитель дерева для кодирования строки ни в каком корне не может быть
     * символа!!!! символы только в листах
     */
    public static TreeHaffmanNode buildTreeDecode(List<TreeHaffmanNode> listNode) {
        // пока в коллекции больше одного корневого узла (root)
        // цикл является конечным, перерастание в бесконечный цикл НЕВОЗМОЖНО, так как коллекция имеет конечный размер
        // на каждой итерации удаляется 2 элемента, а добавляется только 1 (результат объединения)
        while (listNode.size() > 1) {
            // отсортируем коллекцию по ВОЗРАСТАНИЮ корня
            // таким образом, самые редко встречающиеся символы будут вначале списка
            // их мы будем объединять в новый узел, а корень станет суммой их процентов вхождения в текст
            listNode.sort(Comparator.comparing(TreeHaffmanNode::getProcent));
            // возьмем левый узел
            TreeHaffmanNode left = listNode.get(0);
            // удалим этот узел из списка, он нам уже не потребуется, так как объединится с правым узлом
            listNode.remove(0);
            // запомним правый узел
            TreeHaffmanNode right = listNode.get(0);
            listNode.remove(0);
            // создадим новый узел, у которого ссылка левого узла - наш left, а правый - right
            // корень узла - это сумма процентов левого и правого узла
            TreeHaffmanNode unionNode = new TreeHaffmanNode(left, right);
            // добавим в наше дерево
            // таким образом получим двоичное дерево, где приоритет добавление - минимум процентного соотношения вхождения символов
            listNode.add(unionNode);
        }
        // вернем КОРЕНЬ построенного дерева
        return listNode.get(0);
    }

    /**
     * метод для обхода дерева и записываем в мапу символ и его строковое
     * представление в виде 0 и 1 в зависимости из какого поддерева этот символ
     * (левый - 0, правый - 1) ни в каком корне не может быть символ!!!!!
     * символы только в листах
     */
    @SuppressWarnings("unchecked")
    public static Map<Character, String> TableHaffman(TreeHaffmanNode root, List res, Map map) {
        // если у нас есть в левом поддереве что-то идем в левое поддерево
        // по левым поддеревьям у нас 1
        if (root.getLeft() != null) {
            res.add(0);
            TableHaffman(root.getLeft(), res, map);
        }
        // идем по правому поддереву, здесь у нас 0, те получаем последовательность 0 и 1
        if (root.getRight() != null) {
            res.add(1);
            TableHaffman(root.getRight(), res, map);
        }
        // если нам встречается символ, то добавляем в мапу символ и ее числовое представление в виде строки
        if (root.getSymbol() != '\u0000') {
            StringBuilder str = new StringBuilder("");
            for (int i = 0; i < res.size(); i++) {
                str.append(res.get(i));
            }
            map.put(root.getSymbol(), str.toString());

        }
        // вернуться надо откуда пришел
        if (res.size() >= 1) {
            res.remove(res.size() - 1);
        }
        return map;
    }

    /**
     * построитель дерева для декодирования строки входные данные : мапа с
     * ключами символ - и как закодирован символ root - корень дерева
     */
    public static TreeHaffmanNode buildTreeEncode(Map<Character, String> encode) {
        // создадим корень дерева, который будем возвращать
        TreeHaffmanNode root = new TreeHaffmanNode();
        // обход по мапе
        for (Character symbol : encode.keySet()) {
            // получим строковое представление
            String encodeStringForSymbol = encode.get(symbol);
            // построим узел по этому
            NodeForNewChar(encodeStringForSymbol, root, 0, symbol);

        }
        return root;
    }

    /**
     * Реккурсивная функция для построения дерева для КОНКРЕТНОГО символа
     *
     * @param encodeStringForSymbol - строковое представление символа
     * @param root - самый корень дерева
     * @param i - текущая позиция
     * @param symbol - символ, для которого строиться дерево
     */
    private static void NodeForNewChar(String encodeStringForSymbol, TreeHaffmanNode root, int i, Character symbol) {
        // если мы прошли все символы закодированной строки
        if (i == encodeStringForSymbol.length()) {
            // установим символ
            root.setSymbol(symbol);
            // выход из рекурсии
            return;
        }
        char symbolEncode = encodeStringForSymbol.charAt(i);
        //если этот символ 0 - то левое поддерево
        // если у root нет еще левого подерева, то надо его создать
        if (symbolEncode == '0') {
            if (root.getLeft() == null) {
                // создаем новый узел
                TreeHaffmanNode newNode = new TreeHaffmanNode();
                // присвоим узлу предка левый узел
                root.setLeft(newNode);
                //перейдем в левую ветку и продолжим анализ строки
                root = root.getLeft();
            } else { // если левый узел есть, то просто в него перейдем
                root = root.getLeft();
            }
        } else {
            // если нам встретился символ 1 и нам нельзя идти вправо, создадим узел
            if (root.getRight() == null) {
                TreeHaffmanNode newNode = new TreeHaffmanNode();
                // установим правый узел
                root.setRight(newNode);
                // перейдем в правый узел
                root = root.getRight();
            } else { // если есть правый узел, то просто перейдем в него
                root = root.getRight();
            }
        }
        NodeForNewChar(encodeStringForSymbol, root, i + 1, symbol);
    }

    /**
     * Обход дерева
     *
     * @param root - корень дерева
     * @param decode - декодированная строка
     * @param leghtString - длина строки
     */
    public static void traversalTree(TreeHaffmanNode root, String decode, int leghtString) {
        
        int i = 0;
        while (i < leghtString) {
            i = recursiveTraversalTree(root, root, decode, i, leghtString);
        }
    }

    /**
     * Рекурсивный метод обхода дерева
     *
     * @param root - начальный корень
     * @param rootforStep - текущий корень
     * @param decode - декодированная строка
     * @param i - текущий символ в закодированной строке
     * @param leghtString - длина строки
     * @return возвращем на каком числе мы остановились
     */
    public static int recursiveTraversalTree(TreeHaffmanNode root, TreeHaffmanNode rootforStep, String decode, int i, int leghtString) {
        // крайний случай рекурсии, когда встретили первый символ печатаем его и выходим из рекурсии
        if(rootforStep.getSymbol() != '\u0000'){
           System.out.print(rootforStep.getSymbol()); 
        }
        else{
            // получим символ закодированной строки
            char leftOrRight = decode.charAt(i);
            // если встретился 0 идем в левую ветку, иначе в правую ветку, пока не дойдем до символа
            if (leftOrRight == '0') {
                rootforStep = rootforStep.getLeft();

            } else {
                rootforStep = rootforStep.getRight();

            }
            // идем в рекурсию, и увеличиваем i (нам в рекурсии нужен следующий символ, пока не дойдем до листа)
           i =  recursiveTraversalTree(root, rootforStep, decode, ++i, leghtString);

        }
        return i;
    }
}
