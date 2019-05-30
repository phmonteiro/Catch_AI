package ga.geneticOperators;

import ga.IntVectorIndividual;
import ga.Problem;

import java.util.Arrays;
import java.util.LinkedList;


public class RecombinationCycle<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    //TODO this class might require the definition of additional methods and/or attributes

    public RecombinationCycle(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        int numGenes = ind1.getNumGenes();

        int origin = 0;
        int current = origin;
        LinkedList<Integer> visitedPositions = new LinkedList<>();

        do {
            visitedPositions.add(current);
            current = ind1.getIndexof(ind2.getGene(current));
        } while (origin != current);

        int aux;
        for (int i = 0; i < numGenes; i++) {
            if (!visitedPositions.contains(i)) {
                aux = ind1.getGene(i);
                ind1.setGene(i, ind2.getGene(i));
                ind2.setGene(i, aux);
            }
        }
    }

    @Override
    public String toString(){
        return "Cycle";
    }
}