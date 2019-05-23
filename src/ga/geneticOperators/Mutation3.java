package ga.geneticOperators;

import ga.GeneticAlgorithm;
import ga.IntVectorIndividual;
import ga.Problem;

import java.util.LinkedList;


public class Mutation3<I extends IntVectorIndividual, P extends Problem<I>> extends Mutation<I, P> {

    public Mutation3(double probability) {
        super(probability);
    }

    @Override
    public void mutate(I ind) {
        int numGenes = ind.getNumGenes();
        int primeiroAlelo = GeneticAlgorithm.random.nextInt(numGenes);
        int segundoAlelo;

        do {
            segundoAlelo = GeneticAlgorithm.random.nextInt(numGenes);
        } while (primeiroAlelo == segundoAlelo);


        if (primeiroAlelo > segundoAlelo) {
            int aux = primeiroAlelo;
            primeiroAlelo = segundoAlelo;
            segundoAlelo = aux;
        }

        LinkedList<Integer> individualGenes = new LinkedList<>();
        LinkedList<Integer> scrambledGenes = new LinkedList<>();

        int i;

        for (i = 0; i < ind.getNumGenes(); i++) {
            individualGenes.add(individualGenes.get(i));
        }

        Integer geneEscolhido;
        for (Integer gene : individualGenes) {
            geneEscolhido =  GeneticAlgorithm.random.nextInt(individualGenes.size()-1);
            scrambledGenes.add(geneEscolhido);
            individualGenes.remove(geneEscolhido);
        }

        int j;
        for (i=primeiroAlelo, j=0; i<=segundoAlelo; i++, j++) {
            ind.setGene(i, scrambledGenes.get(j));
        }

    }

    @Override
    public String toString() {
        return "Scramble mutation.\n";
    }
}