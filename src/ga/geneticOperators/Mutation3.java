package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;


public class Mutation3<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public Mutation3(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        int numGenes = ind.getNumGenes();
        int primeiroAlelo = GeneticAlgorithm.random.nextInt(numGenes);
        int segundoAlelo;

        if (primeiroAlelo == numGenes) {
            segundoAlelo = GeneticAlgorithm.random.nextInt(numGenes-1);
        } else {
            do {
                segundoAlelo = GeneticAlgorithm.random.nextInt(numGenes);
            } while (primeiroAlelo == segundoAlelo);
        }

        int auxGene = ind.getGene(segundoAlelo);
        ind.setGene(segundoAlelo, ind.getGene(primeiroAlelo));
        ind.setGene(primeiroAlelo, auxGene);
    }

    @Override
    public String toString() {
        return "Swap mutation.\n";
    }
}