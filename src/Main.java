import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] newProducts = new int[]{100, 200, 300, 50};
        String[] newNameOfProducts = new String[]{"Pillow", "Mouse", "T-shirt", "Milk"};
        Basket basket = new Basket(newProducts, newNameOfProducts);
        basket.addToCart(3, 10);
        basket.addToCart(1, 2);
        basket.printCart();
        File file = new File("basket.txt");
        basket.saveTxt(new File("basket.txt"));
        basket.addToCart(2, 5);
        basket.saveTxt(new File("basket.txt"));
        Basket basket1 = Basket.loadFromTxtFile(file);
        basket1.printCart();


    }
}