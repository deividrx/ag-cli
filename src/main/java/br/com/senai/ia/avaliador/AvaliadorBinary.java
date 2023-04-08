package br.com.senai.ia.avaliador;

import br.com.senai.ia.ag.Populacao;
import br.com.senai.ia.individuos.BinaryIndividuo;

public class AvaliadorBinary implements Avaliador<BinaryIndividuo> {

    @Override
    public Populacao<BinaryIndividuo> avalia(Populacao<BinaryIndividuo> populacao) {
        for (int i = 0; i < populacao.getTamanho(); i++) {
            BinaryIndividuo ind = populacao.getIndividuo(i);
            double x = doubleValue(getX(ind));
            double y = doubleValue(getY(ind));
            double fitness = 0.5 - (((Math.pow(
                    Math.sin(Math.sqrt(x * x + y * y)), 2)) - 0.5) / (Math.pow(
                            1.0 + 0.001 * (x * x + y * y), 2)));
            ind.setFitness(fitness);
        }
        return populacao;
    }

	private static int[] getX(BinaryIndividuo ind) {
		int[] binx = new int[22];
		for (int j = 0; j < 22; j++) {
			binx[j] = ind.getGene(j);
		}
		return binx;
	}

	private static int[] getY(BinaryIndividuo ind) {
		int[] biny = new int[22];
		for (int j = 22; j < 44; j++) {
			biny[j - 22] = ind.getGene(j);
		}
		return biny;
	}

	private static double doubleValue(int[] genes) {
		double dec = binaryToDecimal(genes);
		dec = dec * 200 / (Math.pow(2, 22) - 1);
		dec = dec - 100;
		return dec;
	}

	private static int binaryToDecimal(int[] vet) {
		int sum = 0;
		for (int i = vet.length - 1; i >= 0; i--) {
			sum += vet[i] * Math.pow(2, vet.length - i - 1);
		}
		return sum;
	}
}
