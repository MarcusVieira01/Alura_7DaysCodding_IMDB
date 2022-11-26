//Declaração de pacote
package br.com.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @apiNote Declaração de classe concreta que define um objeto Serie 
 * @implNote Implementação de Content para generalização
 */
public class Serie implements Content{
    //Declaração de atributos
    private String title;
    private String rating;
    private String year;
    private String image;

    /**
     * Declaração de construtor
     * @param title
     * @param rating
     * @param year
     * @param image
     */
    public Serie(String title,  String rating, String year, String image){
        this.title = title;
        this.rating = rating;
        this.year = year;
        this.image = image;
    }

    //Declaração de métodos getter sobreescritos, advindos da interface Content
    @Override
    public String getTitle() {
        return this.title;
    }
    @Override
    public String getRating() {
        return this.rating;
    }
    @Override
    public String getYear() {
        return this.year;
    }
    @Override
    public String getImage() {
        return this.image;
    }
    
    /**
     * Método que retornará uma lista de objetos serie, após parseamento de um JSON via classe ParseJasonMarvel
     * @param jsonMarvel Objeto da classe ParseJsonMarvel
     * @return Lista de objetos Serie
     */
    public static List<Serie> gerarListaSerie(ParseJsonMarvel jsonMarvel){
        //Instanciação de novo objeto via construtor ArrayList<>() que armazenará cada objeto Serie
        List<Serie> listaSeries = new ArrayList<>();

        //Declaração de variáveis e atribuição dos valores de retorno dos métodos evocados, separando nas listas pertinentes cada elemento da serie.  
        List<String> title = jsonMarvel.parseTitle();
        List<String> year = jsonMarvel.parseYear();
        List<String> rating = jsonMarvel.parseRating();
        List<String> image = jsonMarvel.parseImage();

        //Loop for que fará a adição de elemento no objeto de referência atribuída à variável listaSeries. Cada elemento será um objeto instanciado via construtor Serie(args)
        for(int i = 0; i < title.size(); i++){
            listaSeries.add(new Serie(title.get(i), rating.get(i), year.get(i), image.get(i)));
        }
        
        //Retorno da lista contendo objetos
        return listaSeries;
    }


    /**
     * Sobreescrita do método to string
     * @return Cadeia de caracteres pré definidos e concatenados com os atributos do objeto
     */
    @Override
    public String toString() {
        return "Serie:\n" 
        + "title: " + title 
        + "\nrating: " + rating 
        + "\nyear: " + year
        + "\nimage: " + image;
    }
}
