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
        int primeiroAlelo = GeneticAlgorithm.random.nextInt(numGenes);
        int seguinteAoPrimeiro, segundoAlelo, auxTroca;

        do {
            if (primeiroAlelo == numGenes) {
                segundoAlelo = GeneticAlgorithm.random.nextInt(numGenes - 1);
            } else {
                segundoAlelo = GeneticAlgorithm.random.nextInt(numGenes);
            }

            if (primeiroAlelo > segundoAlelo) {
                auxTroca = primeiroAlelo;
                primeiroAlelo = segundoAlelo;
                segundoAlelo = auxTroca;
            }
        } while (primeiroAlelo == segundoAlelo && primeiroAlelo + 1 == segundoAlelo);

        seguinteAoPrimeiro = primeiroAlelo + 1;
        auxTroca = ind.getGene(segundoAlelo);

        for (int i = segundoAlelo; i > seguinteAoPrimeiro; i--) {
            ind.setGene(i, ind.getGene(i - 1));
        }

        ind.setGene(seguinteAoPrimeiro, auxTroca);

//        int auxGeneSeguinteAoPrimeiroAlelo = ind.getGene(segundoAlelo);
//        ind.setGene(seguinteAoPrimeiro, ind.getGene(segundoAlelo));
//        ind.setGene(segundoAlelo, auxGeneSeguinteAoPrimeiroAlelo);
    }

    @Override
    public String toString() {
        return "Insert mutation.\n";
    }
}