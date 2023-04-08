package br.com.senai.ia.crossover;

import br.com.senai.ia.ag.Populacao;
import br.com.senai.ia.individuos.Individuo;

public interface Crossover<T extends Individuo> {

    Populacao<T> fazCrossover(Populacao<T> pais, double taxaCross);
}
