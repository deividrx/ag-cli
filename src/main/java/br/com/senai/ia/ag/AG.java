package br.com.senai.ia.ag;

import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.LogarithmicAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import br.com.senai.ia.avaliador.Avaliador;
import br.com.senai.ia.crossover.Crossover;
import br.com.senai.ia.factories.AGFactory;
import br.com.senai.ia.functions.FuncaoOtimizacao;
import br.com.senai.ia.individuos.Individuo;
import br.com.senai.ia.mutacao.Mutacao;
import lombok.AllArgsConstructor;
import picocli.CommandLine.Help.Ansi;

@AllArgsConstructor
public class AG<T extends Individuo> {

    private AGFactory<T> agFactory;
    private AGInitOpts opts;
    private FuncaoOtimizacao funcaoOtimizacao;
    private final XYSeries graph = new XYSeries("F6");

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
            // if (genIndex % 100 == 0) {
                var media = pop.calculaMedia();
                printInfoGen(genIndex, media, melhor.getFitness());
            // }

            // Parar o Algoritmo quando achar a melhor soluçaõ se achar :p
            if (melhor.getFitness() == funcaoOtimizacao.solucao()) {
                // var media = pop.calculaMedia();
                printInfoGen(genIndex, media, melhor.getFitness());
                break;
            }

            // Obtendo os pais
            var pais = new Selecao<>(pop).getPais();
            pais = cross.fazCrossover(pais, opts.getTaxaCrossover());
            pais = mutex.fazMutacao(pop, opts.getTaxaMutacao());
            var newGen = aval.avalia(pais); 

            // Aplicando o Elitismo
            var pior = newGen.getPiorIndividuo();
            if (melhor.getFitness() > pior.getFitness())
                newGen.replacePiorIndividuo(melhor);

            pop = newGen;
            genIndex++;
        }

        if (opts.getChartName() != null) {
            createGraph();
        }
    }

    public void createGraph() {
        // output graph
        var dataset = new XYSeriesCollection(graph);
        JFreeChart chart = ChartFactory.createXYLineChart(
            "", "Número da geração", "fitness", dataset);

        XYPlot plot = chart.getXYPlot();
        NumberAxis domainAxis = new NumberAxis("fitness");
        NumberAxis rangeAxis = new LogarithmicAxis("Número da geração");
        plot.setDomainAxis(rangeAxis);
        plot.setRangeAxis(domainAxis);

        try {
            ChartUtils.saveChartAsPNG(new File(opts.getChartName()), chart, 450, 400);
        } catch (Exception e) {
            System.err.println("Path inválido!");
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

        graph.add(num, ind);
        System.out.print(String.format(header, num, media, ind));
    }
}
