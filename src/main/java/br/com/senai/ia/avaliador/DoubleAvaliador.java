package br.com.senai.ia.avaliador;

import br.com.senai.ia.ag.Populacao;
import br.com.senai.ia.individuos.DoubleIndividuo;

public class DoubleAvaliador implements Avaliador<DoubleIndividuo> {

    @Override
    public Populacao<DoubleIndividuo> avalia(Populacao<DoubleIndividuo> pop) {
        for (DoubleIndividuo doubleIndividuo : pop) {
            double x = doubleIndividuo.getGene(0);
            double y = doubleIndividuo.getGene(1);

            double fitness = 0.5 - (((Math.pow(
                    Math.sin(Math.sqrt(x * x + y * y)), 2)) - 0.5) / (Math.pow(
                            1.0 + 0.001 * (x * x + y * y), 2)));
            doubleIndividuo.setFitness(fitness);
        }
        return pop;
    }

}
