//Declaração do pacote
package br.com.app;

//Importação de calsse externa
import java.util.ArrayList;
import java.util.List;
import br.com.model.HttpRequestIMDB;
import br.com.model.Movie;

//Importação de classe interna
import br.com.model.ParseJsonIMDB;

//Declaração de classe
public class App {
    public static void main(String[] args) throws Exception {
        //Instanciação de objeto da classe ParseJsonIMDB passando como parâmetro o corpo da requisição HTTP, realizada via instanciamento da classe HttpRequestIMDB com aninhamento do método que fará o retorno do corpo da requisição no tipo String
        ParseJsonIMDB json = new ParseJsonIMDB(new HttpRequestIMDB().request());
        
        //Instancição de novo objeto via construtor ArrayList<>()
        List<Movie> filmes = new ArrayList<>();

        //Declaração de variáveis e atribuição dos valores de retorno dos métodos evocados.  
        List<String> title = json.parseTitulo();
        List<String> year = json.parseYear();
        List<String> rating = json.parseRating();
        List<String> image = json.parseElemento("image");

        //Loop for que fará a adição de elemento no objeto de referência atribuída à variável filmes. Cada elemento será um objeto instanciado via construtor Movie(args)
        for(int i = 0; i < title.size(); i++){
            filmes.add(new Movie(title.get(i), rating.get(i), year.get(i), image.get(i)));
        }







        //Exibição de resultado para teste
        System.out.println(filmes.get(3));
    }
}