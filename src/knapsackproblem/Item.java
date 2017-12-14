package knapsackproblem;

/**
 *
 * @author Pedro Vivaldi <pedro.cotta.vivaldi@gmail.com>
 */
public class Item {

    private double weight;
    private double value;

    public Item(double value, double weight) {
        this.weight = weight;
        this.value = value;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + "w=" + weight + ", p=" + value + '}';
    }
}
