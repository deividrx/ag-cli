package br.com.senai.ia.functions;

public class FuncaoShubert implements FuncaoOtimizacao {

    private final double MIN = -5.12;
    private final double MAX = 5.12;
    private final double SOLUCAO = -186.7309;

    @Override
    public double funcao(double x, double y) {
        var s1 = 1D;
        for (int i = 0; i <= 5; i++) {
            s1 += i * Math.cos((i + 1) * x + 1);
        }

        var s2 = 1D;
        for (int i = 0; i <= 5; i++) {
            s1 += i * Math.cos((i + 1) * y + 1);
        }

        return s2 - s1;
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
