package br.com.senai.ia.mutacao;

import java.util.concurrent.ThreadLocalRandom;

import br.com.senai.ia.ag.Populacao;
import br.com.senai.ia.functions.FuncaoOtimizacao;
import br.com.senai.ia.individuos.DoubleIndividuo;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DoubleMutacao implements Mutacao<DoubleIndividuo> {

    private FuncaoOtimizacao func;

    @Override
    public Populacao<DoubleIndividuo> fazMutacao(Populacao<DoubleIndividuo> pop, double taxaMutacao) {
        for (DoubleIndividuo doubleIndividuo : pop) {
            double num = Math.random();
            if (num < taxaMutacao) {
                double gene;
				double rd = Math.random();
                if (rd < 0.5) {
                    gene = ThreadLocalRandom.current().nextDouble(
                        func.minValue(),
                        func.maxValue()
                    );
                } else {
                    gene = ThreadLocalRandom.current().nextInt(
                        (int) func.minValue(),
                        (int) func.maxValue()
                    );
                }
                int randomInt = ThreadLocalRandom.current().nextInt(0, 2);
                doubleIndividuo.setGene(randomInt, gene);
            }
        }
        return pop;
    }
}
