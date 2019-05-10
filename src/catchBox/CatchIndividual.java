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
        int pairsLength = pairs.size();
        Pair currentPair;

        Cell catchCurrent = problem.getCellCatch();
        Cell door = problem.getDoor();
        fitness=0;
        int i,j;

        // calcular da primeira posição até à primeira posição do genoma
        for (i=0; i<pairsLength; i++) {
            currentPair = pairs.get(i);
            if ((catchCurrent == currentPair.getCell1() && cellsBoxes.get(genome[0]) == currentPair.getCell2())
                || (catchCurrent == currentPair.getCell2() && cellsBoxes.get(genome[0]) == currentPair.getCell1())) {

                fitness += currentPair.getValue();
            }
        }

        // distancias entre as caixas
        for (i=0; i<genome.length-1; i++) {
            for (j=0; j<pairsLength; i++) {
                currentPair = pairs.get(j);

                if ((cellsBoxes.get(genome[i]) == currentPair.getCell1() && cellsBoxes.get(genome[i+1]) == currentPair.getCell2())
                   || (cellsBoxes.get(genome[i]) == currentPair.getCell2() && cellsBoxes.get(genome[i+1]) == currentPair.getCell1())) {
                    fitness += currentPair.getValue();
                }
            }
        }

        catchCurrent = cellsBoxes.get(genome.length);

        // distancia da última caixa à porta
        for (i=0; i<pairsLength; i++) {
            currentPair = pairs.get(i);
            if ((catchCurrent == currentPair.getCell1() && door == currentPair.getCell2())
                || (catchCurrent == currentPair.getCell2() && door == currentPair.getCell1())) {

                fitness += currentPair.getValue();
            }
        }

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
        return (this.fitness == i.getFitness()) ? 0 : (this.fitness < i.getFitness()) ? 1 : -1;
    }

    @Override
    public CatchIndividual clone() {
        return new CatchIndividual(this);

    }
}
