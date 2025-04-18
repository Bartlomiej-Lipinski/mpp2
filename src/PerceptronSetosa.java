import java.util.ArrayList;

public class PerceptronSetosa {
    private ArrayList<Double> weights;
    private double learningRate = 0.5;
    private double threshold = 0.2;

    public PerceptronSetosa(int n) {
        weights = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            weights.add(Math.random());
        }
    }

    public int guess(ArrayList<Double> inputs) {
        return calculateNet(inputs) > threshold ? 1 : 0;
    }
    private double calculateNet(ArrayList<Double> inputs) {
        double sum = 0;
        for (int i = 0; i < weights.size(); i++) {
            sum += inputs.get(i) * weights.get(i);
        }
        return sum;
    }

    public void train(ArrayList<Double> inputs, int target) {
        int guess = guess(inputs);
        double error = target - guess;
        inputs.add(-1.0);
        weights.add(threshold);
        for (int i =0;i<weights.size();i++) {
            double WeightsPrim = weights.get(i)+error*inputs.get(i)*learningRate;
            weights.set(i, WeightsPrim);
        }
        threshold = weights.getLast();
        weights.removeLast();
    }
    public void changeArgs(int n) {
        weights.clear();
        for (int i = 0; i < n; i++) {
            weights.add(Math.random());
        }
    }
}
