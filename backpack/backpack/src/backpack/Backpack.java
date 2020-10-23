package backpack;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * класс для хранения ценности за единицу и общий вес
 */
class PriceForOneWeight {

    double price;
    double allWeight;

    public PriceForOneWeight(double price, int allWeight) {
        this.price = price;
        this.allWeight = allWeight;
    }

    public double getPrice() {
        return price;
    }

    public double getAllWeight() {
        return allWeight;
    }

}

public class Backpack {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int generalWeight = input.nextInt();

        List<PriceForOneWeight> inputData = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int wi = input.nextInt();
            int weight = input.nextInt();
            double price = wi / weight; // получим удельную цену за единицу веса
            inputData.add(new PriceForOneWeight(price, weight));
        }
        //отсортируем массив по убыванию цены, так как будем брать сначала самые дорогие
        //можно было начинать проход с последнего элемента в листе
        inputData.sort(Comparator.comparingDouble(PriceForOneWeight::getPrice).reversed());
        // цикл пока есть место в рюкзаке
        double sum = 0;
        int i = 0;
        while (generalWeight > 0 && i < inputData.size()) {
            double currentWeight = inputData.get(i).getAllWeight();
            if (currentWeight <= generalWeight) {
                sum += currentWeight * inputData.get(i).getPrice();

            } else {
                sum += generalWeight * inputData.get(i).getPrice();
            }
            generalWeight -= currentWeight;
            i++;
        }
        System.out.println(String.format("%.3f", sum));
    }

}
