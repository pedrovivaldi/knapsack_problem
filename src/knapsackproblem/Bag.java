package knapsackproblem;

import java.util.BitSet;
import java.util.List;

/**
 *
 * @author Pedro Vivaldi <pedro.cotta.vivaldi@gmail.com>
 */
public class Bag extends Individual {

    private static List<Item> allItens;
    private static double capacity;
    private BitSet itensInBag;

    public Bag() {
        this.itensInBag = new BitSet(allItens.size());
        for (int i = 0; i < allItens.size(); i++) {
            if (Math.random() < 0.5) {
                itensInBag.set(i);
            }
        }

        fitness = 0;
    }

    public Bag(Bag bag) {
        this.itensInBag = new BitSet(allItens.size());
        for (int i = 0; i < allItens.size(); i++) {
            if (bag.itensInBag.get(i)) {
                itensInBag.set(i);
            } else {
                itensInBag.clear(i);
            }
        }

        fitness = 0;
    }

    @Override
    public Individual crossover(Individual parent) {
        Bag child = new Bag();
        if (parent != null) {
            Bag parentTour = (Bag) parent;
            child = new Bag();

            // Get start and end sub tour positions for parent1's tour
            int startPos = (int) (Math.random() * allItens.size());
            int endPos = (int) (Math.random() * allItens.size());

            if (startPos > endPos) {
                int aux = endPos;
                endPos = startPos;
                startPos = aux;
            }

            // Loop and add the sub tour from parent1 to our child
            for (int i = 0; i < allItens.size(); i++) {
                // If our start position is less than the end position
                if (i >= startPos && i <= endPos) {
                    child.setBitByValue(i, itensInBag.get(i));
                } else {
                    child.setBitByValue(i, parentTour.itensInBag.get(i));
                }
            }
        }
        return child;
    }

    @Override
    public void mutate() {
        int pos = (int) (allItens.size() * Math.random());

        if (itensInBag.get(pos)) {
            itensInBag.clear(pos);
        } else {
            itensInBag.set(pos);
        }

        fitness = 0;
    }

    @Override
    public double getFitness() {
        if (fitness == 0) {
            double weight = getWeight();
            fitness = weight + getValue();
            if (weight > capacity) {
                fitness = -fitness;
            }
        }
        return fitness;
    }

    public double getWeight() {
        double sum = 0.0;
        for (int i = 0; i < allItens.size(); i++) {
            if (itensInBag.get(i)) {
                sum += allItens.get(i).getWeight();
            }
        }
        return sum;
    }

    public double getValue() {
        double sum = 0.0;
        for (int i = 0; i < allItens.size(); i++) {
            if (itensInBag.get(i)) {
                sum += allItens.get(i).getValue();
            }
        }
        return sum;
    }

    public static void setItens(List<Item> itens) {
        allItens = itens;
    }

    public static void setCapacity(double cap) {
        capacity = cap;
    }

    public void setBitByValue(int pos, boolean value) {
        if (value) {
            itensInBag.set(pos);
        } else {
            itensInBag.clear(pos);
        }

        fitness = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Bag: ");
        for (int i = 0; i < allItens.size(); i++) {
            sb.append(itensInBag.get(i) + " ");
        }
        return sb.toString();
    }

    public static List<Item> getItems() {
        return allItens;
    }

}
