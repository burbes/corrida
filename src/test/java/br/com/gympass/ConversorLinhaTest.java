package br.com.gympass;

import static br.com.gympass.ConversorLinha.getValueByRegex;
import static br.com.gympass.RegexConstants.REGEX_NOME_PILOTO;
import static br.com.gympass.RegexConstants.REGEX_NUM_PILOTO;
import static br.com.gympass.RegexConstants.REGEX_NUM_VOLTA;
import static br.com.gympass.RegexConstants.REGEX_TEMPO_VOLTA;
import static br.com.gympass.RegexConstants.REGEX_VELOCIDADE_MEDIA_VOLTA;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class ConversorLinhaTest {

  @Test
  public void testGetValueByRegex() throws IOException {

    //scenario
    String linha = "23:49:08.277      038 – F.MASSA                           1\t\t1:02.852                        44,275";

    //action
    String hora = getValueByRegex(linha, RegexConstants.REGEX_HORA);
    String numVolta = getValueByRegex(linha, REGEX_NUM_VOLTA);
    String tempoVolta = getValueByRegex(linha, REGEX_TEMPO_VOLTA);
    String velMedia = getValueByRegex(linha, REGEX_VELOCIDADE_MEDIA_VOLTA);
    String nomePiloto = getValueByRegex(linha, REGEX_NOME_PILOTO);
    String numPiloto = getValueByRegex(linha, REGEX_NUM_PILOTO);

    //validation
    Assert.assertEquals(hora, "23:49:08.277");
    Assert.assertEquals(numVolta, "1");
    Assert.assertEquals(tempoVolta, "1:02.852");
    Assert.assertEquals(velMedia, "44,275");
    Assert.assertEquals(nomePiloto, "F.MASSA");
    Assert.assertEquals(numPiloto, "038");

  }

  @Test
  public void testCalculaTempoVolta() {

    //scenario
    String tempoVolta = "1:02.852";

    //action
    Duration duration = ConversorLinha.calculaTempoVolta(tempoVolta);

    //validation
    Assert.assertEquals(duration.getSeconds(), 62L);
    Assert.assertEquals(duration.getNano(), 852L);

  }

  @Test
  public void testGetVolta() throws IOException {

    //scenario
    ConversorLinha conversorLinha = new ConversorLinha(System.getProperty("user.dir") + "/target/classes/corrida.txt");

    //action
    List<Volta> voltas = conversorLinha.getVoltas();

    //validation
    Assert.assertNotNull(voltas);
    Assert.assertEquals(voltas.size(), 23);

    Volta volta = voltas.get(0);
    Assert.assertEquals(volta.getNomeCorredor(), "F.MASSA");
    Assert.assertTrue(volta.getCodCorredor().equals(38l));
    Assert.assertTrue(volta.getNumVolta().equals(1));
    Assert.assertTrue(volta.getTempoVolta().equals(Duration.ofSeconds(62, 852)));
    Assert.assertTrue(volta.getVelocidadeMedia().equals(44.275d));

  }


}
