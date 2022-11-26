//Declaração de pacote
package br.com.model;

import java.util.ArrayList;
import java.util.List;

//Importação de classes externas


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
    public String[] extrairJson(){
        //Declraação de arrays e atribuição dos retornos dos métodos .split(), onde é separado cada elemento do JSON
        String[] primeiroSplit = json.split(",\"results\":");
        String[] segundoSplit = primeiroSplit[1].split("]}}");
        String[] jsonSeparado = segundoSplit[0].split(",\"");
        //Retorno do array com a lista de elementos
        return jsonSeparado;
    }

    /**
     * 
     * @return
     */
    public List<String> parseTitle(){
        //
        List<String> listaTitle = new ArrayList<>();
        //
        for (String elemento : extrairJson()) {
            //
            if(elemento.contains("title\":\" ")){
                listaTitle.add(elemento.replaceAll("title\":\" ", "").replaceAll("\"", ""));
            }else if(elemento.contains("title\":\"")){
                listaTitle.add(elemento.replaceAll("title\":\"", "").replaceAll("\"", ""));
            }
        }

        return listaTitle;
    }
}
