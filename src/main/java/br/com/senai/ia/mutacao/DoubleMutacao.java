package br.com.senai.ia.mutacao;

import java.util.concurrent.ThreadLocalRandom;

import br.com.senai.ia.ag.Populacao;
import br.com.senai.ia.individuos.DoubleIndividuo;

public class DoubleMutacao implements Mutacao<DoubleIndividuo> {

    @Override
    public Populacao<DoubleIndividuo> fazMutacao(Populacao<DoubleIndividuo> pop, double taxaMutacao) {
        for (DoubleIndividuo doubleIndividuo : pop) {
            double num = Math.random();
            if (num < taxaMutacao) {
                double gene;
				double rd = Math.random();
                if (rd < 0.5) {
                    gene = Math.random();
                } else {
                    gene = ThreadLocalRandom.current().nextInt(0, 101);
                }
                int randomInt = ThreadLocalRandom.current().nextInt(0, 2);
                doubleIndividuo.setGene(randomInt, gene);
            }
        }
        return pop;
    }
}
