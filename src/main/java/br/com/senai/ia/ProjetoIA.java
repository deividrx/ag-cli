package br.com.senai.ia;

import br.com.senai.ia.ag.AG;
import br.com.senai.ia.ag.AGInitOpts;
import br.com.senai.ia.factories.BinaryAGFactory;
import br.com.senai.ia.factories.DoubleAGFactory;
import br.com.senai.ia.individuos.BinaryIndividuo;
import br.com.senai.ia.individuos.DoubleIndividuo;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help.Ansi;
import picocli.CommandLine.Option;

@Command(name = "ag-cli", description = "Implementação do Algoritmo Evolucionário", mixinStandardHelpOptions = true)
public class ProjetoIA implements Runnable {

    @Option(names = { "-g", "--geracoes" }, description = "Número de gerações")
    private int numGeracoes = 0;

    @Option(names = { "-p", "--populacao" }, description = "Tamanho da população")
    private int tamPopulacao = 100;

    @Option(names = { "-c", "--crossover" }, description = "Taxa de crossover")
    private double taxaCrossover = 0.65;

    @Option(names = { "-m", "--mutacao" }, description = "Taxa de mutação")
    private double taxaMutacao = 0.10;

    @Option(names = { "-C", "--codec" }, description = "Codificação a ser usada")
    private TipoAlgoritmo tipoCodec = TipoAlgoritmo.DOUBLE;

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ProjetoIA()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        // Confirmar os valores
        var confirm = confirmDialog();
        if (!confirm) System.exit(0);

        var opts = AGInitOpts.builder()
            .tamPopulacao(tamPopulacao)
            .numGeracoes(numGeracoes)
            .taxaMutacao(taxaMutacao)
            .taxaCrossover(taxaCrossover).build();

        // Rodando o Algoritmo
        switch (tipoCodec) {
            case BINARY -> {
                var agFactory = new BinaryAGFactory();
                new AG<BinaryIndividuo>(agFactory, opts).run();
            }
            case DOUBLE -> {
                var agFactory = new DoubleAGFactory();
                new AG<DoubleIndividuo>(agFactory, opts).run();
            }
        }
    }

    private boolean confirmDialog() {
        String header = Ansi.AUTO.string("""

                 @|bold,green ->|@ Taxa de Mutação: @|bold %s|@
                 @|bold,green ->|@ Taxa de Crossover: @|bold %s|@
                 @|bold,green ->|@ Número de Gerações: @|bold %s|@
                 @|bold,green ->|@ Tamanho da população: @|bold %s|@
                 @|bold,green ->|@ Tipo Codec: @|bold %s|@

                """);

        System.out.print(
                String.format(header, taxaMutacao, taxaCrossover, numGeracoes, tamPopulacao, tipoCodec));

        String question = "Correto? (s)sim/(n)não ";
        String value = System.console().readLine(question).toLowerCase();
        return value.equals("s");
    }
}
