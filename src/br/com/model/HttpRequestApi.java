//Declaração de pacote
package br.com.model;

//Importação de classes externas
import java.io.File;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Classe concreta que fará uma requisição HTTP ao IMDb API e retornando o corpo da requisição na forma de string 
 * @author Marcus Vieira
 */
public class HttpRequestApi {
    /**
     * Método que fará a requisição HTTP e retorno do corpo de resposta do IMDb API
     * @param arquivoImdb Passagem de uma String contendo o caminho do arquivo que contém a chave de acesso para IMDb API
     * @return Corpo da resposta HTTP com requisição para IMDb API
     * @throws Exception
     */
    public String requestImdb(String caminhoImdb) throws Exception{
        //Instanciação de objeto da classe File que conterá o arquivo de leitura das chaves de acesso para o IMDb API
        File arquivoImdb = new File(caminhoImdb);

        //Instanciação de uma uri como objeto, com argumento sendo a URL do serviço da API concatenado com a chave de acesso à API devifinido pela instancia de objeto da classe Keyreader e evocação do método getter
        URI uriImdb = new URI("https://imdb-api.com/en/API/Top250Movies/" + new KeyReader().getChaveImdb(arquivoImdb));

        //Criação de fluxo de requição e reposta via HTTP. Instanciament de client, construção da requisição e leitura do corpo da resposta
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uriImdb).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //Retorno dp corpo da resposta da requisição HTTP
        return response.body();
    }

    /**
     * Método que fará a requisição HTTP e retorno do corpo de resposta da API Marvel
     * @param arquivoMarvel String contendo o caminho do arquivo que contém a chave de acesso para API Marvel
     * @return Corpo da resposta HTTP com requisição para API Marvel
     * @throws Exception
     */
    public String requestMarvel(String caminhoImdb) throws Exception{
        //Instanciação de objeto da classe File que conterá o arquivo de leitura das chaves de acesso para o IMDb API
        File arquivoMarvel = new File(caminhoImdb);
        //Instanciação de objeto da classe KeyReader que fará a leitura do arquivo que contém as chaves de acesso para a API Marvel
        KeyReader chave = new KeyReader();

        //Declaração de variável e atribuição do retorno do tempo decorrido em milisegundos, via método. Será componente do hash a ser passado na requisição
        String timeStamp = String.valueOf(System.currentTimeMillis());
        //Declaração de variável e atribuição do retorno do método declarado na classe HashUtils onde será passado o valor da variável timeStamp e os valores de retorno dos métodos getter para a chave de acesso à API Marvel
    	String hash = HashUtils.getHashMd5(timeStamp + chave.getChavePublicaMarvel(arquivoMarvel) + chave.getChaveApiMarvel(arquivoMarvel));
        //Declaração de variável e atribuição do retorno da formatação criada. Essa variável será a URL requicição e será passada para criação do objeto URI
		String endpoint = String.format("https://gateway.marvel.com:443/v1/public/series?ts=%s&hash=%s&apikey=%s", 
        timeStamp, hash, chave.getChaveApiMarvel(arquivoMarvel));

        //Instanciamento de objeto URI com a URL determinada pelo valor da variável endpoint
        URI uriMarvel = new URI(endpoint);

        //Criação de fluxo de requição e reposta via HTTP. Instanciament de client, construção da requisição e leitura do corpo da resposta
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uriMarvel).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //Retorno dp corpo da resposta da requisição HTTP
        return response.body();
    }
}
