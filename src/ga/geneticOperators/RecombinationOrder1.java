package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class RecombinationOrder1<I extends IntVectorIndividual, P extends Problem<I>> extends Recombination<I, P> {

    //TODO this class might require the definition of additional methods and/or attributes

    private int cut1, cut2;
    private LinkedList<Integer> child1Genes;
    private LinkedList<Integer> child2Genes;

    public RecombinationOrder1(double probability) {
        super(probability);
    }

    @Override
    public void recombine(I ind1, I ind2) {
        int numGenes = ind1.getNumGenes();
        int[] child1 = new int[numGenes];
        int[] child2 = new int[numGenes];
        cut1 = GeneticAlgorithm.random.nextInt(numGenes);
        int i;

        do {
            cut2 = GeneticAlgorithm.random.nextInt(numGenes);
        } while (cut1 == cut2);

        if (cut1 == 0 && cut2 == numGenes-1) {
            return;
        }

        if (cut1 > cut2) {
            int aux = cut1;
            cut1 = cut2;
            cut2 = aux;
        }

        child1Genes = new LinkedList<>();
        child2Genes = new LinkedList<>();

        for (i = cut1; i <= cut2; i++) {
            child1[i] = ind1.getGene(i);
            child2[i] = ind2.getGene(i);
            child1Genes.add(child1[i]);
            child2Genes.add(child2[i]);
        }

        fillOffspring(child1, child1Genes, ind2);
        fillOffspring(child2, child2Genes, ind1);

        for (i = 0; i < numGenes; i++) {
            ind1.setGene(i, child1[i]);
            ind2.setGene(i, child2[i]);
        }
    }


    private void fillOffspring (int[] offspring, LinkedList<Integer> offspringGenes, I ind) {
        int numGenes = ind.getNumGenes();
        int writePosition;

        if (cut2 == numGenes - 1) {
            writePosition = 0;
        } else {
            writePosition = cut2 + 1;
        }

        for (int i = cut2 + 1; i < ind.getNumGenes(); i++) {
            if (!offspringGenes.contains(ind.getGene(i))) {
                offspring[writePosition] = ind.getGene(i);
                offspringGenes.add(offspring[writePosition]);

                if (offspringGenes.size() == offspring.length) {
                    return;
                }

                writePosition++;
                if (writePosition == numGenes) {
                    if (offspring[0] != 0) {
                        return;
                    }
                    writePosition = 0;
                }
            }
        }

        for (int i = 0; i <= cut2; i++) {
            if (!offspringGenes.contains(ind.getGene(i))) {
                offspring[writePosition] = ind.getGene(i);
                offspringGenes.add(offspring[writePosition]);

                if (offspringGenes.size() == offspring.length) {
                    break;
                }

                writePosition++;
                if (writePosition == numGenes) {
                    if (offspring[0] != 0) {
                        break;
                    }
                    writePosition = 0;
                }
            }
        }
    }


    @Override
    public String toString(){
        return "Order 1";
    }
}