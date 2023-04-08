package br.com.senai.ia.factories;

import br.com.senai.ia.ag.Populacao;
import br.com.senai.ia.avaliador.Avaliador;
import br.com.senai.ia.avaliador.AvaliadorBinary;
import br.com.senai.ia.crossover.BinaryCrossover;
import br.com.senai.ia.crossover.Crossover;
import br.com.senai.ia.individuos.BinaryIndividuo;
import br.com.senai.ia.mutacao.BinaryMutacao;
import br.com.senai.ia.mutacao.Mutacao;

public class BinaryAGFactory implements AGFactory<BinaryIndividuo> {

    @Override
    public Populacao<BinaryIndividuo> inciaPopulacao(Integer tamPopulacao) {
		Populacao<BinaryIndividuo> pop = new Populacao<>(tamPopulacao);
		for (int i = 0; i < tamPopulacao; i++) {
			BinaryIndividuo ind = new BinaryIndividuo();
			for (int j = 0; j < 44; j++) {
				double num = Math.random();
				if (num < 0.5) {
					ind.setGene(j, 1);
				} else {
					ind.setGene(j, 0);
				}
			}
			pop.add(ind);
		}
		return pop;
    }

    @Override
    public Avaliador<BinaryIndividuo> criaAvaliador() {
        return new AvaliadorBinary();
    }

    @Override
    public Crossover<BinaryIndividuo> criaCrossover() {
        return new BinaryCrossover();
    }

    @Override
    public Mutacao<BinaryIndividuo> criaMutacao() {
        return new BinaryMutacao();
    }

}
