package br.com.gympass;

public class RegexConstants {

  public static final String REGEX_ESPACO_EM_BRANCO = "\\s";
  public static final String REGEX_NUM_PILOTO = REGEX_ESPACO_EM_BRANCO + "(\\d{3})" + REGEX_ESPACO_EM_BRANCO;
  public static final String REGEX_NOME_PILOTO = REGEX_ESPACO_EM_BRANCO + "([.A-Z]+)" + REGEX_ESPACO_EM_BRANCO;
  public static final String REGEX_NUM_VOLTA = REGEX_ESPACO_EM_BRANCO + "(\\d{1,2})" + REGEX_ESPACO_EM_BRANCO;
  public static final String REGEX_HORA = "(\\d{2}\\:\\d{2}\\:\\d{2}\\.\\d{3})";
  public static final String REGEX_TEMPO_VOLTA = REGEX_ESPACO_EM_BRANCO + "(\\d{0,1}\\:?\\d{2}\\.\\d{1,3})";
  public static final String REGEX_VELOCIDADE_MEDIA_VOLTA = "(\\d{1,3}\\,\\d{0,3})";

}
