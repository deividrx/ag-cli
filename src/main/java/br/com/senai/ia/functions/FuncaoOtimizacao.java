package br.com.senai.ia.functions;

public interface FuncaoOtimizacao {
    double funcao(double x, double y);
    double maxValue();
    double minValue();
    double solucao();
}
