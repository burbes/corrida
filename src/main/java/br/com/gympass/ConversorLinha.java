package br.com.gympass;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;
import static java.util.Collections.emptyList;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ConversorLinha {

  private static final String REGEX_ESPACO_EM_BRANCO = "\\s";
  private static final String REGEX_NUM_PILOTO = REGEX_ESPACO_EM_BRANCO + "(\\d{3})" + REGEX_ESPACO_EM_BRANCO;
  private static final String REGEX_NOME_PILOTO = REGEX_ESPACO_EM_BRANCO + "([.A-Z]+)" + REGEX_ESPACO_EM_BRANCO;
  private static final String REGEX_NUM_VOLTA = REGEX_ESPACO_EM_BRANCO + "(\\d{1,2})" + REGEX_ESPACO_EM_BRANCO;
  private static final String REGEX_HORA = "(\\d{2}\\:\\d{2}\\:\\d{2}\\.\\d{3})";
  private static final String REGEX_TEMPO_VOLTA = REGEX_ESPACO_EM_BRANCO + "(\\d{0,1}\\:?\\d{2}\\.\\d{1,3})";
  private static final String REGEX_VELOCIDADE_MEDIA_VOLTA = "(\\d{1,3}\\,\\d{0,3})";

  private List<String> lines;

  public ConversorLinha(String fileName) {
    this.lines = this.readFileInList(fileName);
  }

  public List<Volta> getVoltas() {
    return lines
        .stream()
        .skip(1)
        .map(linha -> new Volta(
            getValueByRegex(linha, REGEX_HORA),
            getValueByRegex(linha, REGEX_NUM_VOLTA),
            getValueByRegex(linha, REGEX_TEMPO_VOLTA),
            getValueByRegex(linha, REGEX_VELOCIDADE_MEDIA_VOLTA),
            getValueByRegex(linha, REGEX_NOME_PILOTO),
            getValueByRegex(linha, REGEX_NUM_PILOTO)
        ))
        .collect(Collectors.toList());
  }

  public static Duration calculaTempoVoltaEmSegundos(String tempoVolta) {

    Integer segundosMinuto = Integer.valueOf(getValueByRegex(tempoVolta, "(\\d{0,1})\\:", 1)) * 60;
    Integer segundosSegundos = Integer.valueOf(getValueByRegex(tempoVolta, "(\\d{2})\\.", 1));
    Integer segundosNano = Integer.valueOf(getValueByRegex(tempoVolta, "\\.(\\d{2,3})", 1));

    return Duration.ofSeconds(segundosMinuto + segundosSegundos, segundosNano);
  }

  public static String getValueByRegex(String linha, String regex) {
    return getValueByRegex(linha, regex, 0);
  }

  public static String getValueByRegex(String linha, String regex, Integer group) {
    Pattern compile = Pattern.compile(regex);
    Matcher matcher = compile.matcher(linha);

    String result = "";

    if (matcher.find()) {
      result = matcher.group(group).trim();
    }

    return result;
  }


  private List<String> readFileInList(String fileName) {

    List<String> lines = emptyList();

    try {
      lines = readAllLines(get(fileName), UTF_8);
    } catch (IOException e) {
      System.err.println("Não foi possivel converter em linhas");
    }

    return lines;
  }

}
