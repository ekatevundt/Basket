import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int[] newProducts = new int[]{100, 200, 300, 50};
        String[] newNameOfProducts = new String[]{"Pillow", "Mouse", "T-shirt", "Milk"};
        Basket basket = new Basket(newProducts, newNameOfProducts);
        basket.addToCart(3, 10);
        basket.addToCart(1, 2);
        basket.printCart();
        basket.saveBin(new File("basket.bin"), basket);
        basket.addToCart(2, 5);
        basket.printCart();
        Basket basket2 = Basket.loadFromBin(new File("basket.bin"));
        basket2.printCart();


    }
}