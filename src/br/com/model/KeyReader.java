//Declaração do pacote
package br.com.model;

//Importação de classes externas
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

/**
 * Classe concreta com método de leitura de um arquivo contendo a chave de acesso ao IMDb API e atribui ao atributo chave. Atributo acessível via método getter
 * @author Marcus Vieira
 */
public class KeyReader {
    //Atributos privados
    String chaveImdb;
    String chaveApiMarvel;
    String chavePublicaMarvel;

    /**
     * Método getter de recuperação do valor da chave de acessoao IMDb API
     * @return String contendo a chave de acesso ao IMDb API
     * @throws Exception
     */
    public String getChaveImdb() throws Exception {
        //Evocação de método que define uma chave advinda do arquivo k_imdb.txt
        leChaveImdb();
        //Retorna o valor do atributo chave
        return chaveImdb;
    }

    public String getChavePrivadaMarvel() throws Exception {
        //Evocação de método que define uma chave advinda do arquivo k_imdb.txt
        leChaveMarvel();
        //Retorna o valor do atributo chave
        return chavePublicaMarvel;
    }
    public String getChaveApiMarvel() throws Exception {
        //Evocação de método que define uma chave advinda do arquivo k_imdb.txt
        leChaveMarvel();
        //Retorna o valor do atributo chave
        return chaveApiMarvel;
    }

    /**
     * Método que realiza o Stream do arquivo de texto k-imdb.txt e atribui ao atributo chaveImdb o valor lido
     * @throws Exception
     */
    private void leChaveImdb() throws Exception {
        //Declaração de variável que armazeno o nome do arquivo consultado
        String arquivo = "k_imdb.txt";

        //Instanciamento de objetos para realizar o fluxo de leitura dos dados do arquivo específicado via valor da variável arquivo, tranformação em uma sequencia de chars e posterior buffer dessa cadeia 
        InputStream fileInputStream = new FileInputStream(arquivo);
        Reader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        //Atribuição do valor de retorno do método .readLine() do objeto de referência atribuída à variável bufferedReader ao atributo chave
        this.chaveImdb = bufferedReader.readLine();
        //Fechamento do acesso ao objeto bufferedReader
        bufferedReader.close();
    }

    /**
     * Método que realiza o Stream do arquivo de texto k-marvel.txt e atribui aos atributos chaveApiMarvel e chavePrivadaMarvel
     * @throws Exception
     */
    private void leChaveMarvel() throws Exception {
        //Declaração de variável que armazeno o nome do arquivo consultado
        String arquivo = "k_marvel.txt";

        //Instanciamento de objetos para realizar o fluxo de leitura dos dados do arquivo específicado via valor da variável arquivo, tranformação em uma sequencia de chars e posterior buffer dessa cadeia 
        InputStream fileInputStream = new FileInputStream(arquivo);
        Reader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        
        //Declaração de variável, uma rray de strings, que receberá o valor de retorno do método aninhado de leitura de linha do arquivo e sua devida separação no caractere ",". 
        String[] listaChaves = bufferedReader.readLine().split(",");

        //Atribuição dos valores dos elementos do array com a devida remoção de cabeçalho
        this.chaveApiMarvel = listaChaves[0].replace("apikey=","");
        this.chavePublicaMarvel = listaChaves[1].replace("privatekey=","");

        //Fechamento do acesso ao objeto bufferedReader
        bufferedReader.close();
    }
}
