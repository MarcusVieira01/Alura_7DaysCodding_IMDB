//Declaração do pacote
package br.com.model;

//Importação de classes externas
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Classe concreta com método de leitura de um arquivo contendo a chave 
 * de acesso ao IMDb API e atribui ao atributo chave. Atributo acessível via método getter
 * @author Marcus Vieira
 */
public class KeyReader {
    //Atributos privados
    String chaveImdb;
    String chaveApiMarvel;
    String chavePublicaMarvel;

    /**
     * @apiNote IMDB
     * Método getter de recuperação do valor da chave de acessoao IMDb API
     * @return String contendo a chave de acesso ao IMDb API
     * @throws Exception
     */
    public String getChaveImdb(File arquivo) throws Exception {
        //Evocação de método que define uma chave advinda do arquivo k_imdb.txt
        leChave(arquivo);
        //Retorna o valor do atributo chave
        return chaveImdb;
    }

    /**
     * @apiNote MARVEL
     * @returno valor da chave de acesso à Marvel API
     * @throws Exception
     */
    public String getChaveApiMarvel(File arquivo) throws Exception {
        //Evocação de método que define uma chave advinda do arquivo k_imdb.txt
        leChave(arquivo);
        //Retorna o valor do atributo chave
        return chaveApiMarvel;
    }

    /**
     * @apiNote MARVEL
     * @return o valor da chave privada de acesso à Marvel API
     * @throws Exception
     */
    public String getChavePublicaMarvel(File arquivo) throws Exception {
        //Evocação de método que define uma chave advinda do arquivo k_imdb.txt
        leChave(arquivo);
        //Retorna o valor do atributo chave
        return chavePublicaMarvel;
    }

    /**
     * @apiNote AUXILIAR
     * @param arquivo Necessária passagem de um objeto instanciado pela classe File 
     * @throws Exception
     */
    private void leChave(File arquivo) throws Exception{
        //Instanciamento de objetos para realizar o fluxo de leitura dos dados do arquivo específicado via valor da variável arquivo, tranformação em uma sequencia de chars e posterior buffer dessa cadeia 
        InputStream fileInputStream = new FileInputStream(arquivo);
        Reader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        //Declaração de variável e atribuição do valor de retorno do método de leitura de linha da classe BufferedReader
        String lido = bufferedReader.readLine();

        //Condicional que, conforme a comparação, será realizado o tratamento da chave para a API Marvel ou da chave para o IMDb API
        if(lido.contains(",")){
            //Declaração de variável, uma rray de strings, que receberá o valor de retorno do método aninhado de leitura de linha do arquivo e sua devida separação no caractere ",". 
            String[] listaChaves = lido.split(",");

            //Atribuição dos valores dos elementos do array com a devida remoção de cabeçalho
            this.chaveApiMarvel = listaChaves[0].replace("apikey=","");
            this.chavePublicaMarvel = listaChaves[1].replace("privatekey=","");

            //Fechamento do acesso ao objeto bufferedReader
            bufferedReader.close();
        }else{
            //Atribuição do valor de retorno do método .readLine() do objeto de referência atribuída à variável bufferedReader ao atributo chaveImdb
            this.chaveImdb = lido;

            //Fechamento do acesso ao objeto bufferedReader
            bufferedReader.close();
        }
    }
}
