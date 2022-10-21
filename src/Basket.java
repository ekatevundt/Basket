import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Basket {
    private static int[] prices;
    private static String[] products;
    private static int[] totalBasket;
    private int sum;
    private static boolean[] isFilled;


    public Basket(int[] prices, String[] products) {
        Basket.prices = prices;
        Basket.products = products;
        totalBasket = new int[products.length];
        isFilled = new boolean[products.length];
    }

    public Basket(int[] prices, String[] products, int[] totalBasket, boolean[] isFilled) {
        Basket.prices = prices;
        Basket.products = products;
        Basket.totalBasket = totalBasket;
        Basket.isFilled = isFilled;
    }

    public void addToCart(int productNum, int amount) {
        totalBasket[productNum] += amount;

        isFilled[productNum] = true;
    }

    public void printCart() {
        for (int i = 0; i < products.length; i++) {
            if (isFilled[i]) {
                System.out.println("You chose " + products[i] + ", " + totalBasket[i] + " items;" +
                        "price = " + prices[i] * totalBasket[i]);
                sum += prices[i] * totalBasket[i];
            }
        }
        System.out.println("Total price = " + sum);
    }

    public void saveTxt(File textFile) throws IOException {
        textFile = new File(textFile.toURI());
        try (PrintWriter out = new PrintWriter(textFile)) {
            for (int i = 0; i < totalBasket.length; i++) {

                out.print(totalBasket[i] + " ");
            }
            out.println();
            for (int j = 0; j < totalBasket.length; j++) {
                out.print(products[j] + " ");
            }
            out.println();
            for (int t = 0; t < prices.length; t++) {
                out.print(prices[t] + " ");
            }
            out.println();
            for (int t = 0; t < isFilled.length; t++) {
                out.print(isFilled[t] + " ");
            }
        }
    }

    public static Basket loadFromTxtFile(File textFile) throws IOException {

        String[] productsInBasket;
        String[] productNames;
        String[] pricesInBasket;
        String[] isProduct;
        try (BufferedReader reader = new BufferedReader(new FileReader(textFile))) {

            if (textFile.exists()) {
                productsInBasket = reader.readLine().split(" ");
                for (int i = 0; i < productsInBasket.length; i++) {
                    totalBasket[i] = Integer.parseInt(productsInBasket[i]);
                }
                productNames = reader.readLine().split(" ");
                for (int i = 0; i < products.length; i++) {
                    products[i] = productNames[i];
                }
                pricesInBasket = reader.readLine().split(" ");
                for (int i = 0; i < pricesInBasket.length; i++) {
                    prices[i] = Integer.parseInt(pricesInBasket[i]);
                }
                isProduct = reader.readLine().split(" ");
                for (int i = 0; i < isProduct.length; i++) {
                    isFilled[i] = Boolean.parseBoolean(isProduct[i]);
                }
                return new Basket(prices, products, totalBasket, isFilled);
            } else {
                return new Basket(prices, products, totalBasket, isFilled);
            }
        }
    }
}




