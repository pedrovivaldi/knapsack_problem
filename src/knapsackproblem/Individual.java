package knapsackproblem;

/**
 *
 * @author PedroHenrique
 */
public abstract class Individual {

    protected double fitness = 0;

    public abstract Individual crossover(Individual parent);

    public abstract void mutate();

    public abstract double getFitness();
}
