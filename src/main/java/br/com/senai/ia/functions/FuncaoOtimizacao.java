package br.com.senai.ia.functions;

public interface FuncaoOtimizacao {
    String getName();
    double funcao(double x, double y);
    double maxValue();
    double minValue();
    double solucao();
}
