package br.com.gympass;

import static br.com.gympass.RegexConstants.REGEX_HORA;
import static br.com.gympass.RegexConstants.REGEX_NOME_PILOTO;
import static br.com.gympass.RegexConstants.REGEX_NUM_PILOTO;
import static br.com.gympass.RegexConstants.REGEX_NUM_VOLTA;
import static br.com.gympass.RegexConstants.REGEX_TEMPO_VOLTA;
import static br.com.gympass.RegexConstants.REGEX_VELOCIDADE_MEDIA_VOLTA;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ConversorLinha {

  private List<String> lines;

  public ConversorLinha(String fileName) throws IOException {
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

  public static Duration calculaTempoVolta(String tempoVolta) {

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


  private List<String> readFileInList(String fileName) throws IOException {

    List<String> lines;

    try {
      lines = readAllLines(get(fileName), UTF_8);
    } catch (IOException e) {
      throw new IOException("Could not be possible to parse the file");
    }

    return lines;
  }

}
