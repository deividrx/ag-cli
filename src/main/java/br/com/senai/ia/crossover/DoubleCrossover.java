package br.com.senai.ia.crossover;

import br.com.senai.ia.ag.Populacao;
import br.com.senai.ia.individuos.DoubleIndividuo;

public class DoubleCrossover implements Crossover<DoubleIndividuo> {

    @Override
    public Populacao<DoubleIndividuo> fazCrossover(Populacao<DoubleIndividuo> pais, double taxaCross) {
        var newGen = new Populacao<DoubleIndividuo>(pais.getTamanho());
        
		for (int i = 0; i < pais.getTamanho(); i += 2) {
			double beta = Math.random();
            
			DoubleIndividuo filho1 = new DoubleIndividuo();
			DoubleIndividuo filho2 = new DoubleIndividuo();
			var p1 = pais.getIndividuo(i);
            var p2 = pais.getIndividuo(i+1);

            if (beta < taxaCross) {
                // Cruzamento Aritmético
                for (int j = 0; j < 2; j++) {
                    // Filho 1
                    double gene1 = beta * p1.getGene(j) + (1 - beta) * p2.getGene(j);
                    filho1.setGene(j, gene1);

                    // Filho 2
                    double gene2 = (1 - beta) * p1.getGene(j) + beta * p2.getGene(j);
                    filho2.setGene(j, gene2);
                }

                newGen.add(filho1);
                newGen.add(filho2);
            } else {
				newGen.add(p1);
				newGen.add(p2);
            }
		}
        return newGen;
    }

}
