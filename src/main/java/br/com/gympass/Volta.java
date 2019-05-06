package br.com.gympass;

import static br.com.gympass.ConversorLinha.calculaTempoVoltaEmSegundos;
import static java.time.LocalTime.parse;
import static java.time.format.DateTimeFormatter.ofPattern;

import java.time.Duration;
import java.time.LocalTime;

public class Volta {

  private LocalTime hora;
  private Long codCorredor;
  private String nomeCorredor;
  private Integer numVolta;
  private Duration tempoVolta;
  private Double velocidadeMedia;

  public Volta(String horaLap, String numVolta, String tempoLap, String velMedia, String nomePiloto, String codCorredor) {
    this.hora = parse(horaLap, ofPattern("HH:mm:ss.SSS"));
    this.numVolta = Integer.valueOf(numVolta);
    this.tempoVolta = calculaTempoVoltaEmSegundos(tempoLap);
    this.velocidadeMedia = Double.valueOf(velMedia.replace(",", "."));
    this.nomeCorredor = nomePiloto;
    this.codCorredor = Long.valueOf(codCorredor);
  }


  public LocalTime getHora() {
    return hora;
  }

  public void setHora(LocalTime hora) {
    this.hora = hora;
  }

  public Integer getNumVolta() {
    return numVolta;
  }

  public void setNumVolta(Integer numVolta) {
    this.numVolta = numVolta;
  }

  public Duration getTempoVolta() {
    return tempoVolta;
  }

  public void setTempoVolta(Duration tempoVolta) {
    this.tempoVolta = tempoVolta;
  }

  public Double getVelocidadeMedia() {
    return velocidadeMedia;
  }

  public void setVelocidadeMedia(Double velocidadeMedia) {
    this.velocidadeMedia = velocidadeMedia;
  }

  public Long getCodCorredor() {
    return codCorredor;
  }

  public void setCodCorredor(Long codCorredor) {
    this.codCorredor = codCorredor;
  }

  public String getNomeCorredor() {
    return nomeCorredor;
  }

  public void setNomeCorredor(String nomeCorredor) {
    this.nomeCorredor = nomeCorredor;
  }
}
