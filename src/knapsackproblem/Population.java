package knapsackproblem;

import java.util.List;

/**
 *
 * @author Pedro Vivaldi <pedro.cotta.vivaldi@gmail.com>
 */
public abstract class Population {

    protected List<Individual> population;

    public abstract void evolvePopulation(boolean elitism);

    public Individual getFittest() {

        Individual fittest = null;
        if (!population.isEmpty()) {
            fittest = population.get(0);

            for (int i = 1; i < population.size(); i++) {
                if (fittest.getFitness() <= population.get(i).getFitness()) {
                    fittest = population.get(i);
                }
            }
        }
        return fittest;
    }

    public int populationSize() {
        return population.size();
    }
}
