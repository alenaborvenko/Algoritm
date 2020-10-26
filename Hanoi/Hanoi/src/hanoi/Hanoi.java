/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hanoi;

/**
 * Solution of Hanoi (first version 3 disk, 3 pin)
 * @author alenk
 */
public class Hanoi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // call recursive methods
        // первый параметр высота n (количество дисков)
        // второй параметр столбец с которого переложить
        // третий параметр столбец на который переложить
        hanoi(3,1,2);
    }
    // рекурсивный метод
    // n - количество дисков
    // i -  столбец с которого переложить
    // k - столбец на который переложить
    private static void hanoi(int n, int i, int k) {
        // крайний случай, на котором рекурсия завершиться
        if(n == 1) {
            System.out.println("Move disk " +n + " from pin " + i + " to " + k);
        } else{
            // расчет свободного столбца, на который сейчас можно переложить
            // общее количество перестановок - занятые ячейки ( 6 = 1 + 2 + 3)
            int tmp = 6 - i - k;
            // уменьшаем количество дисков
            // перекладываем с итого столбца на столбец tmp
            hanoi(n - 1, i, tmp);
            System.out.println("Move disk " + n + " from pin " + i + " to " + k);
            // перекладываем с tmp на k
            hanoi(n - 1, tmp, k);
        }
    }
    
}
