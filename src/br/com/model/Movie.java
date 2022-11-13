//Declaração de pacote
package br.com.model;
//Declaração de classe
public class Movie {
    //Declaração de atributos
    private String title;
    private int year;
    private String image;
    private float rating;

    //Declaração de construtor
    public Movie(String title,  String rating, String year, String image){
        this.title = title;
        this.rating = Float.valueOf(rating);
        this.year = Integer.parseInt(year);
        this.image = image;
    }

    //Declaração de métodos getter
    public String getTitle() {
        return this.title;
    }
    public float getRating() {
        return this.rating;
    }
    public int getYear() {
        return this.year;
    }
    public String getImage() {
        return this.image;
    }
    
    //Sobreescrita do método to string
    @Override
    public String toString() {
        return "Movie:\n" 
        + "title: " + title 
        + "\nrating: " + String.valueOf(rating) 
        + "\nyear: " + String.valueOf(year) 
        + "\nimage: " + image;
    }
}
