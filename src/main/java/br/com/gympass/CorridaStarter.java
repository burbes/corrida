package br.com.gympass;

import java.nio.file.Paths;
import java.util.List;

public class CorridaStarter {

  public static void main(String[] args) throws Exception {

    if (args == null || args.length == 0 || args[0].trim().isEmpty()) {
      throw new Exception("File not found");
    }

    String caminhoArquivo = Paths.get(args[0]).toString();

    ConversorLinha conversorLinha = new ConversorLinha(caminhoArquivo);

    List<Volta> voltas = conversorLinha.getVoltas();

    ProcessadorEstatistica processadorEstatistica = new ProcessadorEstatistica(voltas);
    processadorEstatistica.extrairResultados();

  }

}
