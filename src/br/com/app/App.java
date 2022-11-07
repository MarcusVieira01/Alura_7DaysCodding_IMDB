//Declaração do pacote
package br.com.app;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//Importação de classe interna
import br.com.model.KeyReader;

public class App {
    public static void main(String[] args) throws Exception {
        //Declaração de variável e atribuição do valor da chave contida no arquivo k_imdb. Usada a classe KeyReader para obtenção do valor da chave de acesso do IMDb API
        String chave = new KeyReader().getChave();

        //Declaração de variáveis. A variável uri recebe a referência de um objeto instanciado via classe URI. O valor passado no construtor é a concatenação da url de acesso da API ao serviço dos top 250 filmes e o valor da chave de acesso
        String url = "https://imdb-api.com/en/API/Top250Movies/" + chave;
        URI uri = new URI(url);

        //Criação de fluxo de requição e reposta via HTTP. Instanciament de client, construção da requisição e leitura do corpo da resposta
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //Armazenamento do código de status da resposta, assim como o corpo da resposta. No caso um JSON.
        int statusCode = response.statusCode();
        String conteudo = response.body();

        //Exibição dos valores contidos nas variáveis para conferência
        System.out.println(conteudo);
        System.out.println("Status code: " + statusCode);
    }
}
