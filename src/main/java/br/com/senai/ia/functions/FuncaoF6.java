package br.com.senai.ia.functions;

public class FuncaoF6 implements FuncaoOtimizacao {

    private final int MIN = -100;
    private final int MAX = 100;
    private final double SOLUCAO = 1;

    @Override
    public double funcao(double x, double y) {
        var dividendo = Math.pow(Math.sin(Math.sqrt(x * x + y * y)), 2) - 0.5;
        var divisor = Math.pow(1D + 0.001 * (x * x + y * y), 2);
        return 0.5 - ( dividendo / divisor);
    }

    @Override
    public double maxValue() {
        return MAX;
    }

    @Override
    public double minValue() {
        return MIN;
    }

    @Override
    public double solucao() {
        return SOLUCAO;
    }
}
