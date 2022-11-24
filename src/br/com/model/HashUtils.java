//Declaração de pacote
package br.com.model;

//Importação de classes externas
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//Declaração de classe
public class HashUtils {
    //Declaração de método que fará o retorno de uma hash gerada com algoritmo MD5
    public static String getHashMd5(String value) throws NoSuchAlgorithmException {
        //Uso do método .getInstance(arg) para definição do tipo de algoritmo a ser usado
        MessageDigest md = MessageDigest.getInstance("MD5");
        //Declaração de variável do tipo BigInteger e instanciamento de novo objeto, com construtor recebendo os parâmetros para 
        BigInteger hash = new BigInteger(1, md.digest(value.getBytes()));
        //Retorno do valor da variável hash via método toString 
        return hash.toString(16);
    }
}
