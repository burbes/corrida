package br.com.gympass;

import static java.lang.Math.abs;
import static java.lang.String.format;
import static java.time.Duration.ofSeconds;
import static java.util.Comparator.comparing;
import static java.util.Objects.isNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class ProcessadorEstatistica {

  private static final BigDecimal FATOR_METROS_POR_SEGUNDO = new BigDecimal(3.6f);
  private Map<Long, Estatistica> estatisticas = new HashMap<>();
  private List<Volta> voltas;

  private Duration melhorVoltaDaCorrida = ofSeconds(0);

  private AtomicInteger posicaoChegada = new AtomicInteger(1);
  private Duration tempoVencedor = ofSeconds(0, 0);

  public ProcessadorEstatistica(List<Volta> voltas) {
    this.voltas = voltas;
  }

  public void extrairResultados() {

    voltas
        .stream()
        .forEach(volta ->
        {
          if (estatisticas.get(volta.getCodCorredor()) == null) {
            estatisticas.put(volta.getCodCorredor(), new Estatistica());
          }

          Estatistica estatistica = estatisticas.get(volta.getCodCorredor());

          estatistica.setQtdeVoltas(volta.getNumVolta());

          if (isNull(estatistica.getNomePiloto())) {
            estatistica.setNomePiloto(volta.getNomeCorredor());
          }

          if (isNull(estatistica.getCodPiloto())) {
            estatistica.setCodPiloto(volta.getCodCorredor());
          }
          estatistica.setTempoTotalProva(estatistica.getTempoTotalProva().plus(volta.getTempoVolta()));

          //melhor volta de cada piloto
          if (estatistica.getMelhorVolta().compareTo(volta.getTempoVolta()) < 0) {
            estatistica.setMelhorVolta(volta.getTempoVolta());
          }

          //melhor volta da corrida
          if (melhorVoltaDaCorrida.compareTo(estatistica.getMelhorVolta()) < 0) {
            melhorVoltaDaCorrida = estatistica.getMelhorVolta();
          }

          //velocidade media total
          BigDecimal distanciaVoltaEmMetros = (new BigDecimal(volta.getTempoVolta().getSeconds()).add(new BigDecimal(volta.getTempoVolta().getNano() * 1e-9f)) ).multiply((new BigDecimal(volta.getVelocidadeMedia()).divide(FATOR_METROS_POR_SEGUNDO, RoundingMode.HALF_UP)));
          BigDecimal distanciaTotalEmMetros = distanciaVoltaEmMetros.multiply(new BigDecimal(estatistica.getQtdeVoltas()));
          BigDecimal velocidadeMediaTotalEmMetros = distanciaTotalEmMetros.divide(new BigDecimal(estatistica.getTempoTotalProva().getSeconds()).add(new BigDecimal(estatistica.getTempoTotalProva().getNano() * 1e-9f)), RoundingMode.HALF_UP);
          BigDecimal velocidadeMediaProvaKmph = velocidadeMediaTotalEmMetros.multiply(FATOR_METROS_POR_SEGUNDO);

          estatistica.setVelocidadeMediaProva(velocidadeMediaProvaKmph.doubleValue());
        });

    //posicionando corredores e definindo dif de tempo
    estatisticas
        .values()
        .stream()
        .sorted(comparing(Estatistica::getTempoTotalProva))
        .forEach(estatistica -> {
          int posChegada = posicaoChegada.getAndIncrement();
          estatistica.setPosChegada(posChegada);

          if (posChegada == 1) {
            tempoVencedor = estatistica.getTempoTotalProva();
            estatistica.setDifTempoVencedor(ofSeconds(0, 0));
          } else {
            Duration tempoVencedor = this.tempoVencedor;
            estatistica.setDifTempoVencedor(estatistica.getTempoTotalProva().minus(tempoVencedor));
          }
        });

    //IMPRIMINDO RESULTADOS
    System.out.println("\n Cod.Piloto    Nom.Piloto  Pos.Cheg  Qtde.Voltas  Temp.Total   Melhor Volta  Vel.Med.  Diferença Tempo Vencedor");

    estatisticas
        .values()
        .stream()
        .sorted(comparing(Estatistica::getPosChegada))
        .forEach(estatistica ->
            System.out.println(
                format(" %10s %13s %7d %9d %13s %16s %10.4f %14s",
                    estatistica.getCodPiloto(),
                    estatistica.getNomePiloto(),
                    estatistica.getPosChegada(),
                    estatistica.getQtdeVoltas(),
                    formatDuration(estatistica.getTempoTotalProva()),
                    formatDuration(estatistica.getMelhorVolta()),
                    estatistica.getVelocidadeMediaProva(),
                    formatDuration(estatistica.getDifTempoVencedor())
                )
            )
        );


  }

  public static String formatDuration(Duration duration) {
    long seconds = duration.getSeconds();
    long nanos = duration.getNano();
    long absSeconds = abs(seconds);
    return format(
        "%d:%02d:%02d:%03d",
        absSeconds / 3600,
        (absSeconds % 3600) / 60,
        absSeconds % 60,
        nanos);
  }

}
