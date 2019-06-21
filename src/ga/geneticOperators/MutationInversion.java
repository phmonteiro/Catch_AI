package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

import java.util.Random;

import static ga.GeneticAlgorithm.random;


public class MutationInversion<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public MutationInversion(double probability) {
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

        int j = secondAlelo - 1;
        int auxGene;

        for (int i = firstAlelo + 1; i < j ; i++, j--) {
            auxGene = ind.getGene(i);
            ind.setGene(i, ind.getGene(j));
            ind.setGene(j, auxGene);
        }
    }

    @Override
    public String toString() {
        return "Inversion";
    }
}