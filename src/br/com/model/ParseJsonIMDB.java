//Declaração de pacote
package br.com.model;

import java.util.ArrayList;
import java.util.List;

//Declaração de classe
public class ParseJsonIMDB {
    //Atributos privados
    String json;

    //Declaração de constutor
    public ParseJsonIMDB(String json) {
        this.json = json;
    }

    //Declaração de método que fará a separação do JSON em elementos individuais e retornará um List apenas com os títulos dos filmes
    public List<String> parseTitulo(){
        //Instanciação de novo objeto List com construtor ArrayList
        List<String> listaTitulos = new ArrayList<>();
        //Uso de método da classe String para separação do JSON em elementos individuais. Caractere separador usado ,
        String[] jsonSemVirgulas = this.json.split("\",\"");
        //Looping forEach (template) que fará a iteração de cada elemento do array semVirgulas. Em cada iteração a condição acessará o retorno do método contains(arg) que caso true fará a adição do valor de elemneto à ArrayList titulos 
        for (String elemento : jsonSemVirgulas) {
            if(elemento.contains("title")){
                listaTitulos.add(elemento.replaceAll("title\":\"", ""));
            }
        }
        //Retorno do objeto de referência contida em listaTitulos
        return listaTitulos;
    }
}
