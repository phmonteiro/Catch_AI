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
        int firstAlelo = GeneticAlgorithm.random.nextInt(numGenes);
        int secondAlelo;

        do {
            secondAlelo = GeneticAlgorithm.random.nextInt(numGenes);
        } while (firstAlelo == secondAlelo);

        if (firstAlelo > secondAlelo) {
            int aux = firstAlelo;
            firstAlelo = secondAlelo;
            secondAlelo = aux;
        }

        LinkedList<Integer> individualGenes = new LinkedList<>();
        int i;

        for (i = firstAlelo; i <= secondAlelo; i++) {
            individualGenes.add(ind.getGene(i));
        }

        int selectedGene;
        int genesRemaining = secondAlelo - firstAlelo + 1;

        for (i = firstAlelo; i <= secondAlelo; i++) {
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