public class Perceptron {
    private double[] weights;
    private double learningRate = 0.1;
    private double threshold = 0.2;

    public Perceptron(int n) {
        weights = new double[n];
        for (int i = 0; i < n; i++) {
            weights[i] = Math.random();
        }
    }

    public int guess(double[] inputs) {
        double sum = 0;
        for (int i = 0; i < weights.length; i++) {
            sum += inputs[i] * weights[i];
        }
        int output = sum > threshold ? 1 : 0;
        return output;
    }

    public void train(double[] inputs, int target) {
        int guess = guess(inputs);
        double error = target - guess;
        for (int i = 0; i < weights.length; i++) {
            weights[i] += error * inputs[i] * learningRate;
        }
    }
}
