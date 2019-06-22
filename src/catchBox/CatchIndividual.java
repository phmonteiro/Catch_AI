package catchBox;

import ga.IntVectorIndividual;

import java.util.HashMap;
import java.util.LinkedList;


public class CatchIndividual extends IntVectorIndividual<CatchProblemForGA, CatchIndividual> {

    public CatchIndividual(CatchProblemForGA problem, int size) {
        super(problem, size);
    }

    public CatchIndividual(CatchIndividual original) {
        super(original);
    }

    @Override
    public double computeFitness() {
        //TODO
        LinkedList<Cell> cellsBoxes = problem.getCellsBoxes();
        HashMap<String, Integer> pairsMaped = problem.getPairsMapped();

        fitness = 0;

        // Fitness do catch até à primeira caixa
        Cell currentPosition = problem.getCellCatch();
        Cell nextPosition = cellsBoxes.get(genome[0]-1);
        fitness += pairsMaped.get(currentPosition + " / " + nextPosition);

        // Fitness entre caixas
        for (int i=0; i<genome.length-1; i++) {
            currentPosition = cellsBoxes.get(genome[i] - 1);
            nextPosition = cellsBoxes.get(genome[i+1] - 1);

            if (pairsMaped.get(currentPosition + " / " + nextPosition) != null) {
                fitness += pairsMaped.get(currentPosition + " / " + nextPosition);
            } else {
                fitness += pairsMaped.get(nextPosition + " / " + currentPosition);
            }
        }

        // Fitness da última caixa à porta
        currentPosition = cellsBoxes.get(genome[genome.length-1]-1);
        nextPosition = problem.getDoor();
        fitness += pairsMaped.get(currentPosition + " / " + nextPosition);

        return fitness;
    }

    public int[] getGenome() {
        return genome;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("fitness: ");
        sb.append(fitness);
        sb.append("\npath: ");
        for (int i = 0; i <genome.length ; i++) {
            sb.append(genome[i]).append(" ");
        }
        return sb.toString();
    }

    /**
     * @param i
     * @return 1 if this object is BETTER than i, -1 if it is WORST than I and
     * 0, otherwise.
     */
    @Override
    public int compareTo(CatchIndividual i) {
        return Double.compare(i.getFitness(), this.fitness);
    }

    @Override
    public CatchIndividual clone() {
        return new CatchIndividual(this);

    }
}
