package knapsackproblem;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pedro Vivaldi <pedro.cotta.vivaldi@gmail.com>
 */
public class BagPopulation extends Population {

    private static final int TOURNAMENT_SIZE = 5;
    private static final double MUTATION_RATE = 0.015;

    public BagPopulation(int size) {
        population = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Bag newBag = new Bag();
            population.add(newBag);
        }
        //System.out.println(population);
    }

    @Override
    public void evolvePopulation(boolean elitism) {
        System.out.println(population);
        System.out.println(this.getFittest());

        int elitismOffset = 0;
        List<Bag> newPopulation = new ArrayList<>(population.size());

        if (elitism) {
            newPopulation.add((Bag) this.getFittest());
            elitismOffset = 1;
        }

        // Crossover population
        // Loop over the new population's size and create individuals from Current population
        for (int i = 1; i < population.size(); i++) {
            // Select parents
            Bag parent1 = tournamentSelection();
            Bag parent2 = tournamentSelection();
            // Crossover parents
            Bag child = new Bag((Bag) parent1.crossover(parent2));
            // Add child to new population
            newPopulation.add(child);
        }

        // Mutate the new population a bit to add some new genetic material
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            if (Math.random() < MUTATION_RATE) {
                newPopulation.get(i).mutate();
            }
        }

        population = new ArrayList<>(newPopulation);
    }

    // Selects candidate tour for crossover
    private Bag tournamentSelection() {
        // Create a tournament population
        Population tournament = new BagPopulation(TOURNAMENT_SIZE);
        // For each place in the tournament get a random candidate tour and
        // add it
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (Math.random() * this.populationSize());
            tournament.population.set(i, this.getBag(randomId));
        }
        // Get the fittest tour
        Bag fittest = new Bag((Bag) tournament.getFittest());
        return fittest;
    }

    // Gets a bag from population
    public Bag getBag(int index) {
        return (Bag) population.get(index);
    }
}
