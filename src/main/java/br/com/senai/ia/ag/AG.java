package br.com.senai.ia.ag;

import br.com.senai.ia.avaliador.Avaliador;
import br.com.senai.ia.crossover.Crossover;
import br.com.senai.ia.factories.AGFactory;
import br.com.senai.ia.individuos.Individuo;
import br.com.senai.ia.mutacao.Mutacao;
import lombok.AllArgsConstructor;
import picocli.CommandLine.Help.Ansi;

@AllArgsConstructor
public class AG<T extends Individuo> {
    
    private AGFactory<T> agFactory;
    private AGInitOpts opts;

    public void run() {
        // Operações
        Avaliador<T> aval = agFactory.criaAvaliador();
        Crossover<T> cross = agFactory.criaCrossover();
        Mutacao<T> mutex = agFactory.criaMutacao();

        // Criando a populacao incial
        int genIndex = 1;
        boolean infinity = opts.getNumGeracoes() == 0;
        var pop = aval.avalia(agFactory.inciaPopulacao(opts.getTamPopulacao()));

        while (true) {
            if (!infinity && !(genIndex < opts.getNumGeracoes()))
                break;

            // Obtendo o melhor da atual geração
            // Sem Deep Copy o melhor é subistiruido aleatoriamente
            T melhor = deepCopy(pop.getMelhorIndividuo());

            // Mostrando info
            var media = pop.calculaMedia();
            printInfoGen(genIndex, media, melhor.getFitness());

            // Parar o Algoritmo quando achar a melhor soluçaõ se achar :p
            if (melhor.getFitness() == 1) break;

            // Obtendo os pais
            Populacao<T> pais = new Selecao<>(pop).getPais();

            // Criando a nova geração
            pop = aval.avalia(
                mutex.fazMutacao(
                    cross.fazCrossover(pais, opts.getTaxaCrossover()), opts.getTaxaMutacao()));

            // Aplicando o Elitismo
            var pior = pop.getPiorIndividuo();
            if (melhor.getFitness() > pior.getFitness())
                pop.replacePiorIndividuo(melhor);

            genIndex++;
        }
    }

    @SuppressWarnings("unchecked")
    private T deepCopy(T individuo) {
        return (T) individuo.clone();
    }

    private void printInfoGen(int num, double media, double ind) {
        String header = Ansi.AUTO.string("""
                @|bold,red ->|@ Num Geração: @|bold %s|@
                    Média da geração: @|bold %s|@
                    Fitness do melhor: @|bold %s|@
                """);

        System.out.print(String.format(header, num, media, ind));
    }
}
