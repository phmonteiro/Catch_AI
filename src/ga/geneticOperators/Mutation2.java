package ga.geneticOperators;

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
        int primeiroAlelo = random.nextInt(ind.getNumGenes());
        int segundoAlelo = 0;

        do {

            if (primeiroAlelo == ind.getNumGenes()){
                segundoAlelo = random.nextInt(ind.getNumGenes()-1);
            } else {
                segundoAlelo = random.nextInt(ind.getNumGenes());
            }

            if (primeiroAlelo > segundoAlelo) {

                int auxTrocaAlelos = primeiroAlelo;
                primeiroAlelo = segundoAlelo;
                segundoAlelo = auxTrocaAlelos;
            }
        } while (primeiroAlelo == segundoAlelo && primeiroAlelo+1 == segundoAlelo);

        int seguinteAoPrimeiro = primeiroAlelo+1;
        int auxSegundoAllello = segundoAlelo;

        for (int i=segundoAlelo; i>primeiroAlelo+1; i--) {
            ind.setGene(i, ind.getGene(i-1));
        }

        ind.setGene(seguinteAoPrimeiro, auxSegundoAllello);

//        int auxGeneSeguinteAoPrimeiroAlelo = ind.getGene(segundoAlelo);
//        ind.setGene(seguinteAoPrimeiro, ind.getGene(segundoAlelo));
//        ind.setGene(segundoAlelo, auxGeneSeguinteAoPrimeiroAlelo);
    }

    @Override
    public String toString() {
        //TODO
      throw new UnsupportedOperationException("Not Implemented Yet");    }
    }