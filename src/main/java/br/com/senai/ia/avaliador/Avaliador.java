package br.com.senai.ia.avaliador;

import br.com.senai.ia.ag.Populacao;
import br.com.senai.ia.individuos.Individuo;

public interface Avaliador<T extends Individuo> {

    Populacao<T> avalia(Populacao<T> pop);
}
