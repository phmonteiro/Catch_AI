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

        Cell currentPosition = problem.getCellCatch();
        Cell nextPosition;
        Cell firstCell, secondCell;
        int i,j;

        fitness=0;

        // calcular da primeira posição do agente até à primeira caixa
        for (Pair pair : pairs) {
            firstCell = pair.getCell1();
            secondCell = pair.getCell2();

            if ((currentPosition == firstCell && cellsBoxes.get(genome[0]-1) == secondCell) || (currentPosition == secondCell && cellsBoxes.get(genome[0]-1) == firstCell)) {
                fitness += pair.getValue();
                break;
            }
        }

        // distancias somente entre caixas
        for (i=0; i<genome.length-1; i++) {
            currentPosition = cellsBoxes.get(genome[i]-1);
            nextPosition = cellsBoxes.get(genome[i+1]-1);

            for (Pair pair : pairs) {
                firstCell = pair.getCell1();
                secondCell = pair.getCell2();

                if ((currentPosition == firstCell && nextPosition == secondCell) || (currentPosition == secondCell && nextPosition == firstCell)) {
                    fitness += pair.getValue();
                    break;
                }
            }
        }

        // distancia da última caixa à porta
        currentPosition = cellsBoxes.get(genome[genome.length-1]-1);
        nextPosition = problem.getDoor();

        for (Pair pair : pairs) {
            firstCell = pair.getCell1();
            secondCell = pair.getCell2();

            if ((currentPosition == firstCell && nextPosition == secondCell) || (currentPosition == secondCell && nextPosition == firstCell)) {
                fitness += pairs.get(i).getValue();
                break;
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
