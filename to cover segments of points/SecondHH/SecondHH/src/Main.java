
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Pair {

    int left;
    int right;

    public Pair(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }

}

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt(); // количество прямых
        List<Pair> straight = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int left = input.nextInt();
            int right = input.nextInt();
            straight.add(new Pair(left, right)); // create new straight with point
        }
        // sort for right position
        straight.sort(Comparator.comparingInt(Pair::getRight).thenComparing((o1, o2) -> {
            int v = o1.getLeft() - o2.getLeft();
            return (v > 0) ? v : o2.getLeft() - o1.getLeft(); //To change body of generated lambdas, choose Tools | Templates.
        }));

        int currentpoint; // текущая left граница
        int lastHasPoint = straight.get(0).getRight();
        List<Integer> resultArray = new ArrayList<>();//result
        for (Pair pair : straight) {

            currentpoint = pair.getLeft();
            if (lastHasPoint < currentpoint) {
                resultArray.add(lastHasPoint);
                lastHasPoint = pair.getRight();
            }

        }
        resultArray.add(lastHasPoint);
        //out stream api
        System.out.println(resultArray.size());
        resultArray.stream().map(result -> {
            System.out.print(result);
            return result;
        }).forEachOrdered(_item -> {
            System.out.print(" ");
        });
    }
}
