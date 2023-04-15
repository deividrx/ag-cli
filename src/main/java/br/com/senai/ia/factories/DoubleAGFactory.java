package br.com.senai.ia.factories;

import java.util.concurrent.ThreadLocalRandom;

import br.com.senai.ia.ag.Populacao;
import br.com.senai.ia.avaliador.Avaliador;
import br.com.senai.ia.avaliador.DoubleAvaliador;
import br.com.senai.ia.crossover.Crossover;
import br.com.senai.ia.crossover.DoubleCrossover;
import br.com.senai.ia.functions.FuncaoOtimizacao;
import br.com.senai.ia.individuos.DoubleIndividuo;
import br.com.senai.ia.mutacao.DoubleMutacao;
import br.com.senai.ia.mutacao.Mutacao;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DoubleAGFactory implements AGFactory<DoubleIndividuo> {

    private FuncaoOtimizacao func;

    @Override
    public Populacao<DoubleIndividuo> inciaPopulacao(Integer tamPopulacao) {
        var populacao = new Populacao<DoubleIndividuo>(tamPopulacao);
        for (int i = 0; i < tamPopulacao; i++) {
            double x = ThreadLocalRandom.current().nextDouble(func.minValue(), func.maxValue());
            double y = ThreadLocalRandom.current().nextDouble(func.minValue(), func.maxValue());
            double[] genes = {x, y};
            DoubleIndividuo ind = new DoubleIndividuo(genes);
            populacao.add(ind);   
        }
        return populacao;
    }

    @Override
    public Avaliador<DoubleIndividuo> criaAvaliador() {
        return new DoubleAvaliador(func);
    }

    @Override
    public Crossover<DoubleIndividuo> criaCrossover() {
        return new DoubleCrossover();
    }

    @Override
    public Mutacao<DoubleIndividuo> criaMutacao() {
        return new DoubleMutacao(func);
    }
}
