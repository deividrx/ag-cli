package br.com.senai.ia.mutacao;

import br.com.senai.ia.ag.Populacao;
import br.com.senai.ia.individuos.BinaryIndividuo;

public class BinaryMutacao implements Mutacao<BinaryIndividuo> {

    @Override
    public Populacao<BinaryIndividuo> fazMutacao(Populacao<BinaryIndividuo> pop, double taxaMutacao) {
        for (int i = 0; i < pop.getTamanho(); i++) {
            BinaryIndividuo ind = pop.getIndividuo(i);
            for (int j = 0; j < 44; j++) {
                double num = Math.random();
                if (num < taxaMutacao) {
                    ind.swapGene(j);
                }
            }
        }
        return pop;
    }

}
