/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BinaryTree;

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
// созданим структуру (класс) нашего дерева, у которого есть лист символ
// есть корень (это процент вхождения этого символа в текст)
// есть указатели на левое и правое поддерево (указывают на точно такие же деревья)
// если это листь, то левые и правые указатели указывают на NULL (в никуда), признак листа
public class TreeHaffmanNode {

    // опишем поля класса, где символ - это кодируемый символ
    // процент - это процент вхождения этого символа в текст
    // левые и правые поддеревья
    private char symbol;
    private Integer procent;
    // считчик листов, по условию задачи надо быстро вывести количество листов
    private TreeHaffmanNode left;
    private TreeHaffmanNode right;
    // сделаем конструкторы
    // конструктор без параметров - инициализации пустого дерева (некая абстракция)
    // конструктор с параметрами - конкретный узел дерева

    public TreeHaffmanNode() {
        left = null;
        right = null;
        // процент НЕ может быть отрицательным
        // нулевым в непрерывной математике также быть не может, ведь это означает, что этого символа просто нет
        // но в дискретной математики может быть, так как процент может быть настолько маленьким, что непредставим в виде КОНЕЧНОГО числа
        // строго говоря, в дискретной математики нет вообще целых чисел, есть числа МАКСИМАЛЬНО приближенные к целым, с какой-либо погрешностью
        procent = 0;
        // какой символ нам нужен в инициализации структуры?
        // никакой, сначала указывает на мусор, что достался нам от прошлых программ.        
    }

    // создаем узел этого дерева уже с конкретными параметрами
    // вызываем конструктор без параметров (т.е. создаем новый узел, куда помещаем наши значения)
    // сделаем его приватным, таки мобразом. сначала у нас будет пустое дерево (создадим по конструктору без параметров
    // а добавлять будем с помощью метода add
    public TreeHaffmanNode(Character symbol, Integer procent) {
        this();
        this.symbol = symbol;
        this.procent = procent;
    }

    // получение значения корня дерева
    public Integer getProcent() {
        return procent;
    }

    public char getSymbol() {
        return symbol;
    }
// получение левого и правого поддерева

    public TreeHaffmanNode getLeft() {
        return left;
    }

    public TreeHaffmanNode getRight() {
        return right;
    }
    // установим левый или правый лист

    public void setLeft(TreeHaffmanNode left) {
        this.left = left;
    }

    public void setRight(TreeHaffmanNode right) {
        this.right = right;
    }
    // посчитаем процент корень текущего узла

    public void setProcent(Integer procent) {
        this.procent = procent;
    }

    public TreeHaffmanNode(TreeHaffmanNode left, TreeHaffmanNode right) {
        this.left = left;
        this.right = right;
        procent = left.getProcent() + right.getProcent();
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }
}

