package catchBox;

import agentSearch.Action;
import agentSearch.State;


import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class CatchState extends State implements Cloneable {
    //TODO this class might require the definition of additional methods and/or attributes

    protected int[][] matrix;

    private int lineCatch;
    private int columnCatch;
    private int steps;

    public CatchState(int[][] matrix) {
        //TODO

        this.matrix = new int[matrix.length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                this.matrix[i][j] = matrix[i][j];
                if (this.matrix[i][j] == Properties.CATCH) {
                    lineCatch = i;
                    columnCatch = j;
                }
            }
        }
    }

    public void executeAction(Action action) {
        action.execute(this);
        // TODO
        fireUpdatedEnvironment();
        steps++;
        // delete after implementing
    }

    public boolean canMoveUp() {
        //TODO
        return lineCatch != 0 && matrix[lineCatch-1][columnCatch] != Properties.WALL;

    }

    public boolean canMoveRight() {
        //TODO
        return columnCatch != matrix.length-1 && matrix[lineCatch][1+columnCatch] != Properties.WALL;
    }

    public boolean canMoveDown() {
        //TODO
        return lineCatch != matrix.length-1 && matrix[1+lineCatch][columnCatch] != Properties.WALL;
    }

    public boolean canMoveLeft() {
        //TODO
        return columnCatch != 0 && matrix[lineCatch][columnCatch-1] != Properties.WALL;
    }

    public void moveUp() {
        //TODO
        matrix[lineCatch][columnCatch] = Properties.EMPTY;
        matrix[--lineCatch][columnCatch] = Properties.CATCH;

    }

    public void moveRight() {
        //TODO
        matrix[lineCatch][columnCatch] = Properties.EMPTY;
        matrix[lineCatch][++columnCatch] = Properties.CATCH;
    }

    public void moveDown() {
        //TODO
        matrix[lineCatch][columnCatch] = Properties.EMPTY;
        matrix[++lineCatch][columnCatch] = Properties.CATCH;
    }

    public void moveLeft() {
        //TODO
        matrix[lineCatch][columnCatch] = Properties.EMPTY;
        matrix[lineCatch][--columnCatch] = Properties.CATCH;
    }

    public int getNumBox() {
        //TODO
        return 0;
    }

    public void setCellCatch(int line, int column) {
        //TODO
        matrix[lineCatch][columnCatch] = Properties.EMPTY;
        lineCatch = line;
        columnCatch = column;
        matrix[line][column] = Properties.CATCH;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setGoal(int line, int column) {
        //TODO

    }

    public int getSteps() {
        //TODO
        return steps;
    }

    public int getSize() {
        return matrix.length;
    }

    public Color getCellColor(int line, int column) {
        switch (matrix[line][column]) {
            case Properties.BOX:
                return Properties.COLORBOX;
            case Properties.CATCH:
                return Properties.COLORCATCH;
            case Properties.DOOR:
                return Properties.COLORDOOR;
            case Properties.WALL:
                return Properties.COLORWALL;
            default:
                return Properties.COLOREMPTY;
        }
    }

    public int getLineCatch() {
        return lineCatch;
    }

    public int getColumnCatch() {
        return columnCatch;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof CatchState)) {
            return false;
        }

        CatchState o = (CatchState) other;
        if (matrix.length != o.matrix.length) {
            return false;
        }

        return Arrays.deepEquals(matrix, o.matrix);
    }

    @Override
    public int hashCode() {
        return 97 * 7 + Arrays.deepHashCode(this.matrix);
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append(matrix.length);
        for (int i = 0; i < matrix.length; i++) {
            buffer.append('\n');
            for (int j = 0; j < matrix.length; j++) {
                buffer.append(matrix[i][j]);
                buffer.append(' ');
            }
        }
        return buffer.toString();
    }

    @Override
    public CatchState clone() {
        //TODO
        return new CatchState(matrix.clone());
    }

    //Listeners
    private final ArrayList<EnvironmentListener> listeners = new ArrayList<>();

    public synchronized void addEnvironmentListener(EnvironmentListener l) {
        if (!listeners.contains(l)) {
            listeners.add(l);
        }
    }

    public synchronized void removeEnvironmentListener(EnvironmentListener l) {
        listeners.remove(l);
    }

    public void fireUpdatedEnvironment() {
        for (EnvironmentListener listener : listeners) {
            listener.environmentUpdated();
        }
    }

    public double computeHeuristic(Cell goalPosition) {
        return Math.abs(goalPosition.getLine() - getLineCatch())
                + Math.abs(goalPosition.getColumn() - getColumnCatch());

    }
}
