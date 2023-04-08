package br.com.senai.ia.mutacao;

import br.com.senai.ia.ag.Populacao;
import br.com.senai.ia.individuos.Individuo;

public interface Mutacao<T extends Individuo> {

    Populacao<T> fazMutacao(Populacao<T> pop, double taxaMutacao);
}
