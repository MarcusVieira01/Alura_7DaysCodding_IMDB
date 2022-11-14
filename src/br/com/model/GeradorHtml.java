//Declaração de pacote
package br.com.model;

//Importação de classes externas
import java.io.PrintWriter;
import java.util.List;

//Declaração de classe
public class GeradorHtml {
    //Declaração de atributos
    PrintWriter escritor;

    //Declaração de construtor
    public GeradorHtml(PrintWriter escritor){
        this.escritor = escritor;
    }

    //
    public void gerarHtml(List<Movie> filmes) throws Exception{
        //
        this.escritor.write("""
            <!DOCTYPE html>
            <html lang=\"pt-br\">
            <head>
                <meta charset=\"UTF-8\">
                <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">
                <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">

                <!-- CSS only -->
                <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi\" crossorigin=\"anonymous\">  

                <title>Lista TOP 250 - IMDb API</title>
            </head>
            
                <body style=\"background-color: #1f4a4c\">
                    <nav class=\"navbar navbar-dark text-white text-center fs-3 fw-bolder pt-4 ps-3\" style=\"background-color: #081f21;\">
                        <p>Top 250 filmes do IMDb</p>
                    </nav>

                    <div class=\"row\">
                """);

        //
        for(Movie filme : filmes){
            String div = """
                                <div class=\"col-2\">
                                    <div class=\"card text-white mt-3 mb-3\"  style=\"background-color: #102d2f;\">
                                        <h4 class=\"card-header text-center fs-4\" style=\"height: 75px;\">%s</h4>
                                        <div class=\"card-body\">
                                            <img class=\"card-img\" src=\"%s\" alt=\"%s\">
                                            <p class=\"card-text text-center mt-2\">Nota: %s - Ano: %s</p>
                                        </div>
                                    </div>
                                </div>
                    """;

            //
            this.escritor.println(String.format(div, filme.getTitle(), filme.getImage(), filme.getTitle(), filme.getRating(), filme.getYear()));
        }

        //
        this.escritor.write("""
                        </div>
                    </body>
                </html>
                """);
    }
    
}
