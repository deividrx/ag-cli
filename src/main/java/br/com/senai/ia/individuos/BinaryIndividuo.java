package br.com.senai.ia.individuos;

public class BinaryIndividuo implements Individuo {

	private int[] genes;
	private double fitness;

	public BinaryIndividuo() {
		this.genes = new int[44];
		this.fitness = 0;
	}

	public BinaryIndividuo(int[] genes) {
		this.genes = genes;
		this.fitness = 0;
	}

	public int[] getGenes() {
		return this.genes;
	}

	public int getGene(int pos) {
		return this.genes[pos];
	}

	public double getFitness() {
		return this.fitness;
	}

	public void setGene(int pos, int val) {
		this.genes[pos] = val;
	}

    @Override
	public void setFitness(double val) {
		this.fitness = val;
	}

	public void setIndividuo(int[] genes) {
		this.genes = genes;
	}

	public void swapGene(int pos) {
		this.genes[pos] = 1 - this.genes[pos];
	}

    @Override
    public Individuo clone() {
        try {
            return (BinaryIndividuo) super.clone();
        } catch (Exception e) {
            var aux = new BinaryIndividuo(genes);
            aux.setFitness(fitness);
            return aux;
        }
    }
}
