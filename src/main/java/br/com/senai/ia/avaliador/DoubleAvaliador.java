package br.com.senai.ia.avaliador;

import br.com.senai.ia.ag.Populacao;
import br.com.senai.ia.functions.FuncaoOtimizacao;
import br.com.senai.ia.individuos.DoubleIndividuo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DoubleAvaliador implements Avaliador<DoubleIndividuo> {

    private FuncaoOtimizacao funcaoOtimizacao;

    @Override
    public Populacao<DoubleIndividuo> avalia(Populacao<DoubleIndividuo> pop) {
        for (DoubleIndividuo doubleIndividuo : pop) {
            double x = doubleIndividuo.getGene(0);
            double y = doubleIndividuo.getGene(1);
            double fitness = funcaoOtimizacao.funcao(x, y);
            fitness = fitness / funcaoOtimizacao.solucao();
            if (fitness < 0) {
                fitness = 0;
            }
            doubleIndividuo.setFitness(Math.abs(fitness));
        }
        return pop;
    }

}
