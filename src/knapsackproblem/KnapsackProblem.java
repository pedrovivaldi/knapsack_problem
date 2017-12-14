/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knapsackproblem;

import java.util.ArrayList;
import java.util.List;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Pedro Vivaldi <pedro.cotta.vivaldi@gmail.com>
 */
public class KnapsackProblem {

    private static XYSeries graphDataset = new XYSeries("Fitness", false);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Item item = new Item(24, 12);
        Item item2 = new Item(13, 7);
        Item item3 = new Item(23, 11);
        Item item4 = new Item(15, 8);
        Item item5 = new Item(16, 9);

        List<Item> items = new ArrayList<>();
        items.add(item);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);

        Bag.setItens(items);
        Bag.setCapacity(26.0);

        // Initialize population
        Population pop = new BagPopulation(10);
        Bag fittest = (Bag) pop.getFittest();
        System.out.println("Initial bag: " + fittest);

        // Evolve population for 100 generations
        for (int i = 0; i < 10000; i++) {
            pop.evolvePopulation(true);
            Bag bag = (Bag) pop.getFittest();
            graphDataset.add(i, bag.getValue());
        }

        // Print final results
        System.out.println("Finished");
        System.out.println("");
        System.out.println("Items:");
        System.out.println(Bag.getItems());
        System.out.println("");
        fittest = (Bag) pop.getFittest();
        System.out.println("Final bag: " + fittest);
        System.out.println("Fitness: " + fittest.getFitness());
        System.out.println("Weight: " + fittest.getWeight());
        System.out.println("Value: " + fittest.getValue());

        displayGraph();
    }

    public static void displayGraph() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(graphDataset);
        LineChart chart = new LineChart(
                "Fitness evolution",
                "Fitness evolution", dataset);

        chart.pack();
        chart.setVisible(true);
    }

}
