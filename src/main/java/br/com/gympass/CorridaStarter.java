package br.com.gympass;

import java.nio.file.Paths;
import java.util.List;

public class CorridaStarter {

  public static void main(String[] args) {

    if (args == null || args.length == 0 || args[0].trim().isEmpty()) {
      System.err.println("Não foi possivel encontrar corrida");
      return;
    }

    String caminhoArquivo = Paths.get(args[0]).toString();

    ConversorLinha conversorLinha = new ConversorLinha(caminhoArquivo);

    List<Volta> voltas = conversorLinha.getVoltas();

    ProcessadorEstatistica processadorEstatistica = new ProcessadorEstatistica(voltas);
    processadorEstatistica.extrairResultados();

  }

}
