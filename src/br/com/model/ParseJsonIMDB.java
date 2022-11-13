//Declaração de pacote
package br.com.model;

//Importação de classes externas
import java.util.ArrayList;
import java.util.List;

//Declaração de classe
public class ParseJsonIMDB {
    //Atributos privados
    private String json;

    //Declaração de constutor
    public ParseJsonIMDB(String json) {
        this.json = json;
    }

    //Método getter para os atributos
    public String getJson(){
        return this.json;
    }

    //Declaração de método auxiliar que fará a remoção de caracteres indesejados, fará a divisão do JSON e retornará um array com os elementos do atributo json
    private String[] extrairJson() {
        //Realizada a substituição de caracteres indesejados por uma string vazia
        String jsonSemCaracteres = this.json.replace("{\"items\":[{", "")
        .replace("\"}],\"errorMessage\":\"\"}", "")
        .replace("},{", ",");

        //Uso de método da classe String para separação do JSON em elementos individuais. Caractere separador usado ,
        String[] jsonSeparado = jsonSemCaracteres.split("\",");
        
        //Retorno do array com os elementos do atributo json já separados e sem caracteres indesejados
        return jsonSeparado;
    }
    
    //Declaração de método que fará atribuição de cada elemento de ID na lista declarada
    public List<String> parseID(){
        //Instanciação de novo objeto List com construtor ArrayList
        List<String> listId = new ArrayList<>();
        
        //Looping forEach (template) que fará a iteração de cada elemento do array jsonSemVirgulas. Em cada iteração a condição acessará o retorno do método contains(arg) em cada caso e se  true fará a adição do valor de elemento à ArrayList listaTitulos 
        for (String elemento : extrairJson()) {
            if(elemento.contains("\"id")){
                listId.add(elemento.replaceAll("id\":\"", "").replaceFirst("\"", ""));
            }
        }
        
        //Retorno o objeto ArrayList populado com os títulos
        return listId;
    }
    
    //Declaração de método que fará atribuição de cada elemento de Rank na lista declarada
    public List<String> parseRank(){
        //Instanciação de novo objeto List com construtor ArrayList
        List<String> listRank = new ArrayList<>();
        
        //Looping forEach (template) que fará a iteração de cada elemento do array jsonSemVirgulas. Em cada iteração a condição acessará o retorno do método contains(arg) em cada caso e se  true fará a adição do valor de elemento à ArrayList listaTitulos 
        for (String elemento : extrairJson()) {
            if(elemento.contains("\"rank")){
                listRank.add(elemento.replaceAll("rank\":\"", "").replaceFirst("\"", ""));
            }
        }
        
        //Retorno o objeto ArrayList populado com os títulos
        return listRank;
    }

    //Declaração de método que fará atribuição de cada elemento de título na lista declarada
    public List<String> parseTitulo(){
        //Instanciação de novo objeto List com construtor ArrayList
        List<String> listaTitulos = new ArrayList<>();
        
        //Looping forEach (template) que fará a iteração de cada elemento do array jsonSemVirgulas. Em cada iteração a condição acessará o retorno do método contains(arg) em cada caso e se  true fará a adição do valor de elemneto à ArrayList listaTitulos 
        for (String elemento : extrairJson()) {
            if(elemento.contains("\"title")){
                listaTitulos.add(elemento.replaceAll("title\":\"", "").replaceFirst("\"", ""));
            }
        }
        
        //Retorno o objeto ArrayList populado com os títulos
        return listaTitulos;
    }
    
    
    
    //Declaração de método que fará a separação do JSON em elementos individuais e retornará um List com apenas os elementos do parâmetro desejado
    public List<String> parseElemento(String parametro){
        //Instanciação de novo objeto List com construtor ArrayList
        List<String> lista = new ArrayList<>();
        
        //Declaração de variável que conterá o valor do parâmentro a ser acessado concatenado com um valor string constante para que seja realizada a segregação pertinente
        String regexConcatenada = parametro + "\":\"";
        
        //Looping forEach (template) que fará a iteração de cada elemento do array jsonSemVirgulas. Em cada iteração a condição acessará o retorno do método contains(arg) em cada caso e se  true fará a adição do valor de elemneto à ArrayList listaTitulos 
        for (String elemento : extrairJson()) {
            if(elemento.contains("\"" + parametro)){
                lista.add(elemento.replaceAll(regexConcatenada, "")
                .replaceFirst("\"", ""));
            }
        }
        
        //Retorno do objeto de referência contida em listaTitulos
        return lista;
    }
}
