//Declaração de pacote
package br.com.model;

//Importação de classes externas
import java.util.ArrayList;
import java.util.List;

/**
 *@apiNote Declaração de classe que fará o parseamento do JSON de retorno da API Marvel
 */
public class ParseJsonMarvel {
    //Atributos privados
    private String json;

    /**
     * Declaração de constutor
     * @param json
     */
    public ParseJsonMarvel(String json) {
        this.json = json;
    }

    /**
     * Método getter para os atributos
     * @return atributo JSON do objeto
     */
    public String getJson(){
        return this.json;
    }

    /**
     * Método que fará a seccção do valor do atributo json em elementos individuais para posterior parseamento
     * @return Array de strings com os elementos seccionados do JSON de resposta da API Marvel
     */
    private String[] extrairJson(){
        //Declraação de arrays e atribuição dos retornos dos métodos .split(), onde é separado cada elemento do JSON
        String[] primeiroSplit = json.split(",\"results\":");
        String[] segundoSplit = primeiroSplit[1].split("]}}");
        String[] jsonSeparado = segundoSplit[0].split(",\"");
        //Retorno do array com a lista de elementos
        return jsonSeparado;
    }

    /**
     * Método de parseamento do elemento title do atributo JSON
     * @return Lista de String com os elementos parseados
     */
    public List<String> parseTitle(){
        //Declaração de variável lista via construtor, onde serão armazenados os elementos parseados
        List<String> listaTitle = new ArrayList<>();

        //Template foreach que fará a iteração do array retornado pelo método interno extrairJson()
        for (String elemento : extrairJson()) {
            //Condicional que incluirá o elemento do array caso contenha title, após remoção de caracteres indesejados 
            if(elemento.contains("title\":\" ")){
                listaTitle.add(elemento.replaceAll("title\":\" ", "").replaceAll("\"", ""));
            }else if(elemento.contains("title\":\"")){
                listaTitle.add(elemento.replaceAll("title\":\"", "").replaceAll("\"", ""));
            }
        }

        //Retorno do objeto lista
        return listaTitle;
    }

    /**
     * Método de parseamento do elemento rating do atributo JSON
     * @return Lista de String com os elementos parseados
     */
    public List<String> parseRating(){
        //Declaração de variável lista via construtor, onde serão armazenados os elementos parseados
        List<String> listaRating = new ArrayList<>();

        //Template foreach que fará a iteração do array retornado pelo método interno extrairJson()
        for (String elemento : extrairJson()) {
            //Condicional que incluirá o elemento do array caso contenha rating, após remoção de caracteres indesejados 
            if(elemento.contains("rating\":")){
                listaRating.add(elemento.replaceAll("rating\":", ""));
            }
        }

        //Retorno do objeto lista
        return listaRating;
    }

    /**
     * Método de parseamento do elemento year do atributo JSON
     * @return Lista de String com os elementos parseados
     */
    public List<String> parseYear(){
        //Declaração de variável lista via construtor, onde serão armazenados os elementos parseados
        List<String> listaYear = new ArrayList<>();

        //Template foreach que fará a iteração do array retornado pelo método interno extrairJson()
        for (String elemento : extrairJson()) {
            //Condicional que incluirá o elemento do array caso contenha rating, após remoção de caracteres indesejados 
            if(elemento.contains("startYear\":")){
                listaYear.add(elemento.replaceAll("startYear\":", ""));
            }
        }

        //Retorno do objeto lista
        return listaYear;
    }

    /**
     * Método de parseamento do elemento image do atributo JSON
     * @return Lista de String com os elementos parseados
     */
    public List<String> parseImage(){
        //Declaração de variável lista via construtor, onde serão armazenados os elementos parseados
        List<String> listaImage = new ArrayList<>();

        //Template foreach que fará a iteração do array retornado pelo método interno extrairJson()
        for (String elemento : extrairJson()) {
            //Condicional que incluirá o elemento do array caso contenha rating, após remoção de caracteres indesejados 
            if(elemento.contains("thumbnail\":")){
                listaImage.add(elemento.replaceAll("thumbnail\":\\{\"path\":\"", "").replaceFirst("\"", "") + ".jpg");
            }
        }

        //Retorno do objeto lista
        return listaImage;
    }

}
