//Declaração de pacote
package br.com.model;

//Importação de classes externas
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.NoSuchAlgorithmException;
import javax.management.InvalidAttributeValueException;

/**
 *@apiNote Classe concreta que fará uma requisição HTTP ao IMDb API e retornando o corpo da requisição na forma de string 
 *
 */
public class HttpRequestApi {
    //Declaração de atributos
    String corpo = "";
    String endpoint;

    /**
     * AUXILIAR MARVEL
     * @param caminhoChaveMarvel
     */
    private void endpointGenerator(String caminhoChaveMarvel){
        try{
            //Instanciação de objeto da classe File que conterá o arquivo de leitura das chaves de acesso para a API Marvel
            File arquivoMarvel = new File(caminhoChaveMarvel);
    
            //Instanciação de objeto da classe KeyReader que fará a leitura do arquivo que contém as chaves de acesso para a API Marvel
            KeyReader chave = new KeyReader();
        
            //Declaração de variável e atribuição do retorno do tempo decorrido em milisegundos, via método. Será componente do hash a ser passado na requisição
            String timeStamp = String.valueOf(System.currentTimeMillis());
    
            //Declaração de variável e atribuição do retorno do método declarado na classe HashUtils onde será passado o valor da variável timeStamp e os valores de retorno dos métodos getter para a chave de acesso à API Marvel
            String hash = HashUtils.getHashMd5(timeStamp + chave.getChavePublicaMarvel(arquivoMarvel) + chave.getChaveApiMarvel(arquivoMarvel));
    
            //Declaração de variável e atribuição do retorno da formatação criada. Essa variável será a URL requicição e será passada para criação do objeto URI
            this.endpoint = String.format("https://gateway.marvel.com:443/v1/public/series?ts=%s&hash=%s&apikey=%s", 
            timeStamp, hash, chave.getChaveApiMarvel(arquivoMarvel));

        }catch(NullPointerException enull){
            System.out.println("Falha de leitura de arquivo: " + enull.getMessage());
        } catch (NoSuchAlgorithmException ealg) {
            System.out.println("Falha no algorito: " + ealg.getMessage());
        }

    }

    /**IMDB
     * Método que fará a requisição HTTP e retorno do corpo de resposta do IMDb API
     * @param arquivoImdb Passagem de uma String contendo o caminho do arquivo que contém a chave de acesso para IMDb API
     * @return Corpo da resposta HTTP com requisição para IMDb API
     */
    public String requestImdb(String caminhoChaveImdb){
        //Instanciação de objeto da classe File que conterá o arquivo de leitura das chaves de acesso para o IMDb API
        File arquivoImdb = new File(caminhoChaveImdb);

        //Estrutura try/catch que fará a execução do código da cláusula try e em caso de exceção, elas serão tratadas
        try{
            //Instanciação de uma uri como objeto, com argumento sendo a URL do serviço da API concatenado com a chave de acesso à API devifinido pela instancia de objeto da classe Keyreader e evocação do método getter
            URI uriImdb = new URI("https://imdb-api.com/en/API/Top250Movies/" + new KeyReader().getChaveImdb(arquivoImdb));

            //Criação de fluxo de requição e reposta via HTTP. Instanciament de client, construção da requisição e leitura do corpo da resposta
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(uriImdb).GET().build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            //Atribuição do retorno do corpo
            this.corpo = response.body();
            
            //Condicional que irá lançar uma excessão de atributo inválido caso o corpo da resposta à requisição HTTP contenha a string que define um erro de chave. Caso false, será exibida mensagem pertinente
            if(this.corpo.contains("Invalid API Key")){
                //Lançamento de excessão dedicada
                throw new InvalidAttributeValueException("Chave inválida!");
            }else{
                //Exibição de mensagem pertinente
                System.out.println("Requisição para IMDb API realizada com sucesso!");
            }

        //Declaração de clausulas catch para tratamento das exceções
        }catch(NullPointerException enull){
            System.out.println("Requisição falhou: " + enull.getMessage());
        }catch(URISyntaxException esyn){
            System.out.println("Requisição falhou: " +esyn.getMessage());
        }catch(UncheckedIOException eunch){
            System.out.println("Requisição falhou: " +eunch.getMessage());
        } catch (IOException eioex) {
            System.out.println("Requisição falhou: " +eioex.getMessage());
        } catch (InterruptedException einterr) {
            System.out.println("Requisição falhou: " +einterr.getMessage());
        } catch (InvalidAttributeValueException einvatr) {
            System.out.println("Requisição falhou: " +einvatr.getMessage());
        }

        //Retorno do valor da varuável corpo
        return this.corpo;
    }

    /**
     * MARVEL
     * Método que fará a requisição HTTP e retorno do corpo de resposta da API Marvel
     * @param arquivoMarvel String contendo o caminho do arquivo que contém a chave de acesso para API Marvel
     * @return Corpo da resposta HTTP com requisição para API Marvel
     * @throws Exception
     */
    public String requestMarvel(String caminhoChaveMarvel) throws Exception{
        try{
            //Evocação de método intero que fará a geração do endpoint, sendo comporto pela URL da API, um timestamp, uma hash contendo o timestamp e as chaves, assim como a chave privada
            endpointGenerator(caminhoChaveMarvel);
            
            //Instanciamento de objeto URI com a URL determinada pelo valor da variável endpoint
            URI uriMarvel = new URI(endpoint);
    
            //Criação de fluxo de requição e reposta via HTTP. Instanciament de client, construção da requisição e leitura do corpo da resposta
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().uri(uriMarvel).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            this.corpo = response.body();

            //Condicional que irá lançar uma excessão de atributo inválido caso o corpo da resposta à requisição HTTP contenha a string que define um erro de chave. Caso false, será exibida mensagem pertinente
            if(this.corpo.contains("Invalid API Key")){
                //Lançamento de excessão dedicada
                throw new InvalidAttributeValueException("Chave inválida!");
            }else{
                //Exibição de mensagem pertinente
                System.out.println("Requisição para Marvel API realizada com sucesso!");
            }
        
        //Declaração de clausulas catch para tratamento das exceções
        }catch(NullPointerException enull){
            System.out.println("Requisição falhou: " + enull.getMessage());
        }catch(URISyntaxException esyn){
            System.out.println("Requisição falhou: " +esyn.getMessage());
        }catch(UncheckedIOException eunch){
            System.out.println("Requisição falhou: " +eunch.getMessage());
        } catch (IOException eioex) {
            System.out.println("Requisição falhou: " +eioex.getMessage());
        } catch (InterruptedException einterr) {
            System.out.println("Requisição falhou: " +einterr.getMessage());
        } catch (InvalidAttributeValueException einvatr) {
            System.out.println("Requisição falhou: " +einvatr.getMessage());
        }

        //Retorno dp corpo da resposta da requisição HTTP
        return this.corpo;
    }
}
