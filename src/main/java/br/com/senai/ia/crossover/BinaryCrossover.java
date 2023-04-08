package br.com.senai.ia.crossover;

import br.com.senai.ia.ag.Populacao;
import br.com.senai.ia.individuos.BinaryIndividuo;

public class BinaryCrossover implements Crossover<BinaryIndividuo> {

    @Override
    public Populacao<BinaryIndividuo> fazCrossover(Populacao<BinaryIndividuo> pais, double taxaCross) {
        Populacao<BinaryIndividuo> novaGeracao = new Populacao<>(pais.getTamanho());
		for (int i = 0; i < pais.getTamanho(); i += 2) {
			double num = Math.random();
			BinaryIndividuo filho1 = new BinaryIndividuo();
			BinaryIndividuo filho2 = new BinaryIndividuo();
			
			if (num < taxaCross) {
				int pos = (int) (0 + (Math.random() * 43));

				var p1 = pais.getIndividuo(i);
                var p2 = pais.getIndividuo(i+1);

				for (int j = 0; j < pos; j++) {
					filho1.setGene(j, p1.getGene(j));
					filho2.setGene(j, p2.getGene(j));
				}
				
				for (int j = pos; j < 44; j++) {
					filho1.setGene(j, p1.getGene(j));
					filho2.setGene(j, p2.getGene(j));
				}
				
				novaGeracao.add(filho1);
				novaGeracao.add(filho2);
				
			} else {
				novaGeracao.add(pais.getIndividuo(i));
				novaGeracao.add(pais.getIndividuo(i+1));
			}
		}
		return novaGeracao;
	}

}
