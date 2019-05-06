package br.com.gympass;

import java.time.Duration;

public class Estatistica {

  private Integer posChegada;
  private Long codPiloto;
  private String nomePiloto;
  private Integer qtdeVoltas;
  private Duration tempoTotalProva = Duration.ofSeconds(0, 0);
  private Duration melhorVolta = Duration.ofSeconds(0, 0);
  private Double velocidadeMediaProva;
  private Duration difTempoVencedor = Duration.ofSeconds(0, 0);

  public Estatistica() {
  }

  public Integer getPosChegada() {
    return posChegada;
  }

  public void setPosChegada(Integer posChegada) {
    this.posChegada = posChegada;
  }

  public Long getCodPiloto() {
    return codPiloto;
  }

  public void setCodPiloto(Long codPiloto) {
    this.codPiloto = codPiloto;
  }

  public String getNomePiloto() {
    return nomePiloto;
  }

  public void setNomePiloto(String nomePiloto) {
    this.nomePiloto = nomePiloto;
  }

  public Integer getQtdeVoltas() {
    return qtdeVoltas;
  }

  public void setQtdeVoltas(Integer qtdeVoltas) {
    this.qtdeVoltas = qtdeVoltas;
  }

  public Duration getTempoTotalProva() {
    return tempoTotalProva;
  }

  public void setTempoTotalProva(Duration tempoTotalProva) {
    this.tempoTotalProva = tempoTotalProva;
  }

  public Duration getMelhorVolta() {
    return melhorVolta;
  }

  public void setMelhorVolta(Duration melhorVolta) {
    this.melhorVolta = melhorVolta;
  }

  public Double getVelocidadeMediaProva() {
    return velocidadeMediaProva;
  }

  public void setVelocidadeMediaProva(Double velocidadeMediaProva) {
    this.velocidadeMediaProva = velocidadeMediaProva;
  }

  public Duration getDifTempoVencedor() {
    return difTempoVencedor;
  }

  public void setDifTempoVencedor(Duration difTempoVencedor) {
    this.difTempoVencedor = difTempoVencedor;
  }
}
