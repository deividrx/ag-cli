package br.com.senai.ia.individuos;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DoubleIndividuo implements Individuo {

    private double fitness;
    private double[] genes;

    public DoubleIndividuo() {
        this.genes = new double[2];
    }

    public DoubleIndividuo(double[] genes) {
        this.genes = genes;
    }

    public double getGene(int pos) {
        return genes[pos];
    }

    public void setGene(int pos, double gene) {
        genes[pos] = gene;
    }

    @Override
    public void setFitness(double val) {
        this.fitness = val;
    }

    @Override
    public double getFitness() {
        return fitness;
    }

    @Override
    public Individuo clone() {
        try {
            return (DoubleIndividuo) super.clone();
        } catch (Exception e) {
            return new DoubleIndividuo(fitness, genes);
        }
    }

}
