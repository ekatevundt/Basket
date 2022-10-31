import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
        int[] newProducts = new int[]{100, 200, 300, 50};
        String[] newNameOfProducts = new String[]{"Pillow", "Mouse", "T-shirt", "Milk"};
        Basket basket = new Basket(newProducts, newNameOfProducts);
        basket.addToCart(3, 10);
        basket.addToCart(1, 2);
        basket.printCart();
        String obj = String.valueOf(new JSONObject());
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        obj = gson.toJson(basket);
        try (FileWriter file = new FileWriter("basket.json")) {
            file.write(obj.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();}
            File file = new File("basket.json");
            //basket.saveTxt(new File("basket.txt"));
            //basket.addToCart(2, 5);
            // basket.saveTxt(new File("basket.txt"));
            Basket basket1 = Basket.loadFromTxtFile(file);
            basket1.addToCart(3,2);
            basket1.printCart();
            ClientLog allLog = new ClientLog();
            allLog.log(4, 5);
            allLog.log(3, 6);
            allLog.log(4, 3);
            allLog.exportAsCSV(new File("log.csv"));


        }
    }
