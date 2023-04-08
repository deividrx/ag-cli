package br.com.senai.ia.factories;

import br.com.senai.ia.ag.Populacao;
import br.com.senai.ia.avaliador.Avaliador;
import br.com.senai.ia.crossover.Crossover;
import br.com.senai.ia.individuos.Individuo;
import br.com.senai.ia.mutacao.Mutacao;

public interface AGFactory<T extends Individuo> {

    Populacao<T> inciaPopulacao(Integer tamPopulacao);
    Avaliador<T> criaAvaliador();
    Crossover<T> criaCrossover();
    Mutacao<T> criaMutacao();
}
