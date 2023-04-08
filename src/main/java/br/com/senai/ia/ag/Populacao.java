package br.com.senai.ia.ag;

import java.util.ArrayList;
import java.util.Iterator;

import br.com.senai.ia.individuos.Individuo;

public class Populacao<T extends Individuo> implements Iterable<T> {

    private final ArrayList<T> populacao;

    public Populacao(int tamanho) {
        this.populacao = new ArrayList<>(tamanho);
    }

    public int getTamanho() {
        return populacao.size();
    }

    public T getMelhorIndividuo() {
        T melhor = populacao.get(0);
        for (T t : populacao) {
            if (t.getFitness() > melhor.getFitness()) {
                melhor = t;
            }
        }        
        return melhor;
    }

    public T getPiorIndividuo() {
        T pior = populacao.get(0);
        for (T t : populacao) {
            if (pior.getFitness() > t.getFitness())
                pior = t;
        }
        return pior;
    }

    public void replacePiorIndividuo(T individuo) {
        var pior = getPiorIndividuo();
        populacao.set(populacao.indexOf(pior), individuo);
    }

    public T getIndividuo(int pos) {
        return populacao.get(pos);
    }

    public void add(T individuo) {
        populacao.add(individuo);
    }

    public void setIndividuo(int pos, T individuo) {
        populacao.set(pos, individuo);
    }

    public double calculaMedia() {
        var media = 0D;
        for (T t : populacao) {
            media += t.getFitness();
        }
        return media / populacao.size();
    }

    @Override
    public Iterator<T> iterator() {
        return populacao.iterator();
    }
}
