import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static PerceptronSetosa perceptron;
    public static double setosa = 0;
    public static int versicolor = 0;
    public static boolean isTrained = false;
    private static int testaccuracy = 0;
    private static int n = 0;
    public static boolean firstloop = true;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        perceptron = new PerceptronSetosa(n);
        while (!isTrained) {
            readFromFile("Train-set.txt");
        }
        readFromFile("Test-set.txt");
        System.out.println("Setosa: " + setosa);
        System.out.println("Versicolor: " + versicolor);
        System.out.println("Accuracy : " +  setosa / 15 * 100 + "%");
        ////////////////////////////////////////////////////////////////////////////////////////////
        while (true) {
            System.out.println("Podaj vectory separowane przecinkiem np: v1,v2,v3,v4");
            System.out.println("Aby zakończyć wpisz exit");
            ArrayList<Double> vec = new ArrayList<>();
            String vektorek = scanner.next();
            if (vektorek.equals("exit")) {
                break;
            }
            String[] split = vektorek.split(",");
            for (String d : split) {
                double v = Double.parseDouble(d);
                vec.add(v);
            }
            int answer = perceptron.guess(vec);
            if (answer == 1) {
                System.out.println("Iris-setosa");
            } else {
                System.out.println("Iris-versicolor");
            }
        }
    }
    public static void readFromFile(String path) {
        File file = new File(path);
        testaccuracy = 0;
        try {
            Scanner s = new java.util.Scanner(file);
            ArrayList<Double> args = new ArrayList<>();
            while (s.hasNextLine()) {
                String line = s.nextLine();
                String[] split = line.split(",");
                for (int i =0;i<split.length-1;i++){
                    double v = Double.parseDouble(split[i]);
                    args.add(v);
                }
                String result = split[split.length-1];
                if (firstloop) {
                    n = split.length-1;
                    perceptron.changeArgs(n);
                    firstloop = false;
                }
                ////////////////////////////////////////////////////////////////////////
                if (path.equals("Train-set.txt")) {
                   int setos = perceptron.guess(args);
                   if (result.equals("Iris-setosa")&& setos ==1) {
                       testaccuracy++;
                   }
                    if (result.equals("Iris-setosa")&& setos ==0) {
                        testaccuracy--;
                    }
                   if (result.equals("Iris-versicolor")&& setos == 0) {
                       testaccuracy++;
                   }
                    if (result.equals("Iris-versicolor")&& setos == 1) {
                        testaccuracy--;
                    }
                    if (testaccuracy == 70) {
                        isTrained = true;
                    }
                    if (result.equals("Iris-setosa")) {
                        if (setos == 1) {
                            args.clear();
                            continue;
                        }
                        perceptron.train(args, 1);
                    } else {
                        if (setos == 0) {
                            args.clear();
                            continue;
                        }
                        perceptron.train(args, 0);
                    }
                    args.clear();
                }
                ////////////////////////////////////////////////////////////////////////////////////////////
                if (path.equals("Test-set.txt")) {
                    int resultGuess = perceptron.guess(args);
                    if (resultGuess == 1) {
                        System.out.println("Iris-setosa");
                        setosa++;
                    } else {
                        System.out.println("Iris-versicolor");
                        versicolor++;
                    }
                    args.clear();
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
