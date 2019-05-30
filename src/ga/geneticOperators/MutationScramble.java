package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

import java.util.LinkedList;


public class MutationScramble<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationScramble(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        int numGenes = ind.getNumGenes();
        int firstAlello = GeneticAlgorithm.random.nextInt(numGenes);
        int secondAlello;

        do {
            secondAlello = GeneticAlgorithm.random.nextInt(numGenes);
        } while (firstAlello == secondAlello);

        if (firstAlello > secondAlello) {
            int aux = firstAlello;
            firstAlello = secondAlello;
            secondAlello = aux;
        }

        LinkedList<Integer> individualGenes = new LinkedList<>();
        int i;

        for (i = firstAlello; i <= secondAlello; i++) {
            individualGenes.add(ind.getGene(i));
        }

        int selectedGene;
        int genesRemaining = secondAlello - firstAlello + 1;

        for (i = firstAlello; i <= secondAlello; i++) {
            selectedGene =  individualGenes.get(GeneticAlgorithm.random.nextInt(genesRemaining));
            ind.setGene(i, selectedGene);
            individualGenes.remove(individualGenes.indexOf(selectedGene));
            genesRemaining--;
        }
    }

    @Override
    public String toString() {
        return "Scramble";
    }
}