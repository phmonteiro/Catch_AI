package catchBox;

import ga.Problem;
import java.util.LinkedList;

public class CatchProblemForGA implements Problem<CatchIndividual> {
    //TODO this class might require the definition of additional methods and/or attributes

    private LinkedList<Cell> cellsBoxes;
    private LinkedList<Pair> pairs;
    private Cell cellCatch;
    private Cell door;


    public CatchProblemForGA(LinkedList<Cell> cellsBoxes, LinkedList<Pair> pairs, Cell cellCatch, Cell door) {
        //TODO

        this.cellsBoxes = cellsBoxes;
        this.pairs = pairs;
        this.cellCatch = cellCatch;
        this.door = door;
    }

    public LinkedList<Pair> getPairs() {
        return new LinkedList<>(pairs);
    }

    public LinkedList<Cell> getCellsBoxes() {
        return cellsBoxes;
    }

    public Cell getDoor() {
        return door;
    }

    public Cell getCellCatch() {
        return cellCatch;
    }

    @Override
    public CatchIndividual getNewIndividual() {
        //TODO
        return new CatchIndividual(this, cellsBoxes.size());
    }

    @Override
    public String toString() {
        //TODO

        StringBuilder sb = new StringBuilder();
        sb.append("# of boxes");
        sb.append(cellsBoxes.size());
        sb.append("\nPairs:");
        sb.append(pairs.toString());
        sb.append("\n");

        return sb.toString();
    }
}
