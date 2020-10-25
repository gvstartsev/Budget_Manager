package budget;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public interface PurchaseFileManager {

    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String filePath = "G:\\purchases.txt";

    default void write(Object object) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(gson.toJson(object));
            System.out.println("Purchases were saved!");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    default BudgetManager read() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            System.out.println("Purchases were loaded!");
            return gson.fromJson(reader, BudgetManager.class);
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

}