//Execução pronta para IMDb

//Declaração do pacote
package br.com.app;

//Importação de calsse externa
import java.io.PrintWriter;

//Importação de classe interna
import br.com.model.ParseJsonIMDB;
import br.com.model.Movie;
import br.com.model.GeradorHtml;
import br.com.model.HttpRequestApi;

//Declaração de classe
public class App {
    public static void main(String[] args) throws Exception {
        //Instanciação de objeto da classe ParseJsonIMDB passando como parâmetro o corpo da requisição HTTP, realizada via instanciamento da classe HttpRequestIMDB com aninhamento do método que fará o retorno do corpo da requisição no tipo String
        ParseJsonIMDB jsonImdb = new ParseJsonIMDB(new HttpRequestApi().requestImdb("k_imdb.txt"));
        
        //Instanciação de objeto Writer via construtor PrintWriter(arquivo), que fará a escrita do conteúdo gerado via classe GeradorHtml
        PrintWriter writer = new PrintWriter("index.html");

        //Instanciação de objeto via construtor GeradorHtml(arg) onde é passado o objeto Writer de valor de referência contido na variável writer
        GeradorHtml html = new GeradorHtml(writer);

        //Evocação de método para geração do código e arquivo HTML e passando o objeto List filmes, que contém os objetos Movie e seus atributos. Evocado método para fechamento do acesso ao objeto writer
        html.gerarHtml(Movie.gerarListaFilmes(jsonImdb));      
        writer.close();

        //Exibição de mensagem que indica finalização da execução do programa
        System.out.println("Programa finalizado!");
    }
}