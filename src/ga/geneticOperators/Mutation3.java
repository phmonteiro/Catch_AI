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



/*        int[] availableGenes = new int[segundoAlelo-primeiroAlelo+1];*/

       /* for (int i = 0; i < segundoAlelo ; i++) {
            individualGenes[i] = ind.getGene(i);
        }

        int geneEscolhido;



        for (int i = 0; i < individualGenes.length; i++) {

            scrambledGenes[i] =  geneEscolhido;
        }

        for (int i = primeiroAlelo; i < segundoAlelo ; i++) {
            ind.setGene(i, );
        }*/

    }


/*    public int[] escolherGene(int[] individualGenes, int[] scrambledGenes){
        int geneEscolhido = GeneticAlgorithm.random.nextInt(individualGenes.length-1);

        for (int i = 0; i < individualGenes.length; i++) {
            if (individualGenes[i] == geneEscolhido){
                int[] newGenome = new int[individualGenes.length-1];

                for (int j = 0; j < individualGenes.length - 1; j++) {
                    if (individualGenes[j] != geneEscolhido){
                        newGenome[i]
                    }

                }
                scrambledGenes[i] = geneEscolhido;
            }
        }

    }*/



/*        int numGenes = ind.getNumGenes();
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
    }*/
}