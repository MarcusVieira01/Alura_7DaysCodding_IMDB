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

public class App {
    public static void main(String[] args) throws Exception {
        //Instanciação de uma uri como objeto, com argumento sendo a URL do serviço da API concatenado com a chave de acesso à API devifinido pela instancia de objeto da classe Keyreader e evocação do método getter
        URI uri = new URI("https://imdb-api.com/en/API/Top250Movies/" + new KeyReader().getChave());

        //Criação de fluxo de requição e reposta via HTTP. Instanciament de client, construção da requisição e leitura do corpo da resposta
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //Armazenamento do corpo da resposta. No caso um JSON.
        String jsonTotal = response.body();
        
        //Uso de método da classe String para separação do JSON em elementos individuais. Caractere separador usado ,
        String[] jsonSemVirgulas = jsonTotal.split("\",\"");

        //Instanciação de nova ArrayList para armazenar os títulos
        List<String> titulos = new ArrayList<>();
        //Looping forEach (template) que fará a iteração de cada elemento do array semVirgulas. Em cada iteração a condição acessará o retorno do método contains(arg) que caso true fará a adição do valor de elemneto à ArrayList titulos 
        for (String elemento : jsonSemVirgulas) {
            if(elemento.contains("title")){
                titulos.add(elemento.replaceAll("title\":\"", ""));
            }
        }



        
        //Uso de iteração via método forEach() para exibir cada elemento do arraylist titulo, para conferência
        titulos.forEach(System.out::println);
        //Exibiçãod o retorno do método que trará a quantidade de elementos do arraylist titulos
        System.out.println("Elementos: " + titulos.size());
        //Exibição do valor da variável statuscode, que armazena o status code da requisição HTTP
        System.out.println("Status code: " + response.statusCode());

    }
}
