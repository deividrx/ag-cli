package br.com.senai.ia.ag;

import br.com.senai.ia.individuos.Individuo;

public class Selecao<T extends Individuo> {
	private double[] adaptabilidade;
	private Populacao<T> pais;
	private Populacao<T> atual;

	public Selecao(Populacao<T> populacao) {
		this.adaptabilidade = new double[populacao.getTamanho()];
		this.pais = new Populacao<>(populacao.getTamanho());
		this.atual = populacao;
		this.setAdaptabilidade(populacao);
		this.selecionaPais();
	}

	private void selecionaPais() {
		for (int i = 0; i < adaptabilidade.length; i++) {
			int num = (int) (0 + (Math.random() * adaptabilidade[adaptabilidade.length - 1]));
			for (int pos = 0; pos < adaptabilidade.length; pos++) {
				if (adaptabilidade[pos] >= num) {
					pais.add(atual.getIndividuo(pos));
					break;
				}
			}
		}
	}

	private void setAdaptabilidade(Populacao<T> p) {
		Individuo individuo = p.getIndividuo(0);
		this.adaptabilidade[0] = individuo.getFitness();
		for (int i = 1; i < this.adaptabilidade.length; i++) {
			individuo = p.getIndividuo(i);
			this.adaptabilidade[i] = individuo.getFitness()
					+ this.adaptabilidade[i - 1];
		}
	}

	public Populacao<T> getPais() {
		return this.pais;
	}
}
