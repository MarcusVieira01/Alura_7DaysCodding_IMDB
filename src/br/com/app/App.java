//Declaração do pacote
package br.com.app;

//Importação de classes externas
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

//Importação de classe interna
import br.com.model.KeyReader;
import br.com.model.ParseJsonIMDB;

public class App {
    public static void main(String[] args) throws Exception {
        //Instanciação de uma uri como objeto, com argumento sendo a URL do serviço da API concatenado com a chave de acesso à API devifinido pela instancia de objeto da classe Keyreader e evocação do método getter
        URI uri = new URI("https://imdb-api.com/en/API/Top250Movies/" + new KeyReader().getChave());

        //Criação de fluxo de requição e reposta via HTTP. Instanciament de client, construção da requisição e leitura do corpo da resposta
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        //Instanciação de objeto da classe ParseJsonIMDB passando o corpo da requisição HTTP
        ParseJsonIMDB json = new ParseJsonIMDB(response.body());
        //Instanciação de listas via construtores ArrayList()
        List<String> id = new ArrayList<>();
        List<String> title = new ArrayList<>();
        List<String> rank = new ArrayList<>();
        List<String> image = new ArrayList<>();

        //Atribuição ao objeto de referência contida em id, titulos, rank e image do retorno do método .parseTitulo(). Retorno este que é um ArrayList dos parâmetros contidos no JSON de respota do IMDb API
        id = json.parseElemento("id");
        title = json.parseElemento("title");
        rank = json.parseElemento("rank");
        image = json.parseElemento("image");

        //Exibição do elemento de valor da variável indice para os parâmentros desejados, como um teste de retorno
        int indice = 3;
        System.out.println("Elemento " + indice + " tem:\n" + 
        "id " + id.get(indice) + "\n" + 
        "titulo " + title.get(indice) + "\n" + 
        "rank " + rank.get(indice) + "\n" + 
        "image " + image.get(indice) + "\n");





        //USO PARA DEBUG MANUAL ____________________
        //System.out.println(response.body());
        //Uso de iteração via método forEach() para exibir cada elemento do arraylist titulo, para conferência
        // id.forEach(System.out::println);
        // title.forEach(System.out::println);
        // rank.forEach(System.out::println);
        // image.forEach(System.out::println);
        //Exibiçãod o retorno do método que trará a quantidade de elementos do arraylist titulos
        // System.out.println("Elementos: " + title.size());
        //Exibição do valor da variável statuscode, que armazena o status code da requisição HTTP
        // System.out.println("Status code: " + response.statusCode());

    }
}
