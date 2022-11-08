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

    //Declaração de método que fará a separação do JSON em elementos individuais e retornará um List com apenas os elementos do parâmetro desejado
    public List<String> parseElemento(String parametro){
        //Instanciação de novo objeto List com construtor ArrayList
        List<String> lista = new ArrayList<>();
        //Declaração de variável que conterá o valor do parâmentro a ser acessado concatenado com um valor string constante para que seja realizada a segregação pertinente
        String regexConcatenada = parametro + "\":\"";

        //Realizada a substituição de caracteres indesejados por uma string vazia
        String jsonLimpo = this.json.replace("{\"items\":[{", "")
        .replace("\"}],\"errorMessage\":\"\"}", "")
        .replace("},{", ",");
        
        //Uso de método da classe String para separação do JSON em elementos individuais. Caractere separador usado ,
        String[] jsonSemVirgulas = jsonLimpo.split("\",");

        //Looping forEach (template) que fará a iteração de cada elemento do array jsonSemVirgulas. Em cada iteração a condição acessará o retorno do método contains(arg) em cada caso e se  true fará a adição do valor de elemneto à ArrayList listaTitulos 
        for (String elemento : jsonSemVirgulas) {
            if(elemento.contains("\"" + parametro)){
                lista.add(elemento.replaceAll(regexConcatenada, "")
                .replaceFirst("\"", ""));
            }
        }
        
        //Retorno do objeto de referência contida em listaTitulos
        return lista;
    }
}
