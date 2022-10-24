import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Basket implements Serializable {
    private int[] prices;
    private String[] products;
    private int[] totalBasket;
    private int sum;
    private boolean[] isFilled;


    public Basket(int[] prices, String[] products) {
        this.prices = prices;
        this.products = products;
        this.totalBasket = new int[products.length];
        this.isFilled = new boolean[products.length];
    }

    public Basket(int[] prices, String[] products, int[] totalBasket, boolean[] isFilled) {
        this.prices = prices;
        this.products = products;
        this.totalBasket = totalBasket;
        this.isFilled = isFilled;
    }

    public void addToCart(int productNum, int amount) {
        totalBasket[productNum] += amount;

        isFilled[productNum] = true;
    }

    public void printCart() {
        sum = 0;
        for (int i = 0; i < products.length; i++) {
            if (isFilled[i]) {

                System.out.println("You chose " + products[i] + ", " + totalBasket[i] + " items;" +
                        "price = " + prices[i] * totalBasket[i]);
                sum += prices[i] * totalBasket[i];
            }
        }
        System.out.println("Total price = " + sum);
    }


    public void saveBin(File binFile, Basket basket) throws IOException {
        binFile = new File(binFile.toURI());
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(binFile))) {
            out.writeObject(basket);
        }
    }


    public static Basket loadFromBin(File binFile) throws IOException, ClassNotFoundException {

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(binFile))) {
            Basket obj = (Basket) in.readObject();
            return obj;
        }
    }
}





