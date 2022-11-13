//Declaração do pacote
package br.com.app;

//Importação de calsse externa
import java.util.List;

import br.com.model.HttpRequestIMDB;
//Importação de classe interna
import br.com.model.ParseJsonIMDB;


public class App {
    public static void main(String[] args) throws Exception {
        //Instanciação de objeto da classe ParseJsonIMDB passando como parâmetro o corpo da requisição HTTP, realizada via instanciamento da classe HttpRequestIMDB com aninhamento do método que fará o retorno do corpo da requisição no tipo String
        ParseJsonIMDB json = new ParseJsonIMDB(new HttpRequestIMDB().request());

        //Declaração de variáveis e atribuição dos valores de retorno dos métodos evocados.  
        List<String> id = json.parseID();
        List<String> title = json.parseTitulo();
        List<String> rank = json.parseRank();
        List<String> image = json.parseElemento("image");

      





        //USO PARA DEBUG MANUAL ____________________

        //Exibição do elemento de valor da variável indice para os parâmentros desejados, como um teste de retorno
        int indice = 3;
        System.out.println("Elemento " + indice + " tem:\n" + 
        "id " + id.get(indice) + "\n" + 
        "titulo " + title.get(indice) + "\n" + 
        "rank " + rank.get(indice) + "\n" + 
        "image " + image.get(indice) + "\n");

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
