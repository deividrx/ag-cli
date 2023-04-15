package br.com.senai.ia.ag;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AGInitOpts {

    private int tamPopulacao;
    private int numGeracoes;
    private double taxaCrossover;
    private Double taxaMutacao;
    private String chartName;
}
