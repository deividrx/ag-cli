package br.com.senai.ia.individuos;

public interface Individuo {

    double getFitness();
    void setFitness(double val);
    Individuo clone();
}
