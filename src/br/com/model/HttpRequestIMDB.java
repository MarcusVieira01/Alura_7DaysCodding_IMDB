//Declaração de pacote
package br.com.model;

//Importação de classes externas
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//Declaração de classe
public class HttpRequestIMDB {
    //Declaração de método funcional, onde será executada a requisiçã HTTP para a API IMDbAPI de chave obtida da classe KeyReader, capturada resposta e retornado o body como String
    public String request() throws Exception{
        //Instanciação de uma uri como objeto, com argumento sendo a URL do serviço da API concatenado com a chave de acesso à API devifinido pela instancia de objeto da classe Keyreader e evocação do método getter
        URI uri = new URI("https://imdb-api.com/en/API/Top250Movies/" + new KeyReader().getChave());

        //Criação de fluxo de requição e reposta via HTTP. Instanciament de client, construção da requisição e leitura do corpo da resposta
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //Retorno dp corpo da resposta da requisição HTTP
        return response.body();
    }
}
