import java.io.File;
import java.util.Scanner;

public class Main {
    private static Perceptron perceptron;
    public static void main(String[] args) {
        
    }
    public static void readFromFile(String path) {
        File file = new File(path);
        try {
            Scanner s = new java.util.Scanner(file);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] split = line.split(",");
                double v1 = Double.parseDouble(split[0]);
                double v2 = Double.parseDouble(split[1]);
                double v3 = Double.parseDouble(split[2]);
                double v4 = Double.parseDouble(split[3]);
                String name = split[4];
                if (path.equals("Train-set.txt")) {
                    perceptron.train(name, v1, v2, v3, v4);
                }
                if (path.equals("Test-set.txt")) {
                    perceptron.guess(name, v1, v2, v3, v4);
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
