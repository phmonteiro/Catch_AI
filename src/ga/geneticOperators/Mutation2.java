package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

import java.util.Random;

import static ga.GeneticAlgorithm.random;


public class Mutation2<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public Mutation2(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        int numGenes = ind.getNumGenes();
        int primeiroAlello = GeneticAlgorithm.random.nextInt(numGenes);
        int segundoAlello;

        if (primeiroAlello == numGenes) {
            segundoAlello = GeneticAlgorithm.random.nextInt(numGenes - 1);
        } else {
            do {
                segundoAlello = GeneticAlgorithm.random.nextInt(numGenes);
            } while (primeiroAlello == segundoAlello);
        }

        if (primeiroAlello > segundoAlello) {
            int aux = primeiroAlello;
            primeiroAlello = segundoAlello;
            segundoAlello = aux;
        }

        int j = segundoAlello - 1;
        int auxGene;

        for (int i = primeiroAlello + 1; i < j ; i++, j--) {
            auxGene = ind.getGene(i);
            ind.setGene(i, ind.getGene(j));
            ind.setGene(j, auxGene);
        }
    }

    @Override
    public String toString() {
        return "Inversion mutation.\n";
    }
}