package catchBox;

import ga.IntVectorIndividual;

import java.util.LinkedList;


public class CatchIndividual extends IntVectorIndividual<CatchProblemForGA, CatchIndividual> {
    private double value;

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
        LinkedList<Pair> pairs = problem.getPairs();

        fitness = 0;

        // Fitness do catch até à primeira caixa
        Cell currentPosition = problem.getCellCatch();
        Cell nextPosition = cellsBoxes.get(genome[0]-1);
        fitness += fitnessBetweenTwoPositions(pairs, currentPosition, nextPosition);

        // Fitness entre caixas
        for (int i=0; i<genome.length-1; i++) {
            currentPosition = cellsBoxes.get(genome[i] - 1);
            nextPosition = cellsBoxes.get(genome[i+1] - 1);

            fitness += fitnessBetweenTwoPositions(pairs, currentPosition, nextPosition);
        }

        // Fitness da última caixa à porta
        currentPosition = cellsBoxes.get(genome[genome.length-1]-1);
        nextPosition = problem.getDoor();
        fitness += fitnessBetweenTwoPositions(pairs, currentPosition, nextPosition);

        return fitness;
    }

    private double fitnessBetweenTwoPositions (LinkedList<Pair> pairs, Cell currentPosition, Cell nextPosition) {
        Cell firstCell, secondCell;

        for (Pair p : pairs) {
            firstCell = p.getCell1();
            secondCell = p.getCell2();

            if ((currentPosition.equals(firstCell) && nextPosition.equals(secondCell)) ||
                    (currentPosition.equals(secondCell) && nextPosition.equals(firstCell))) {
                return p.getValue();
            }
        }

        return 0;
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
