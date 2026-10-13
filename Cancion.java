public class Cancion {

    //Atributos
    private int id;
    private String titulo;
    private String artista;
    private int duracionSegundos;

    public Cancion(int id, String titulo, String artista, int duracionSegundos) {

        if (duracionSegundos <= 0) {
            throw new IllegalArgumentException("La duración debe ser mayor a cero.");
        }

        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.duracionSegundos = duracionSegundos;
    }

    public int getId() { 
        return id;
    }

    public String getTitulo() { 
        return titulo; 
    }

    public String getArtista() { 
        return artista; 
    }

    public int getDuracionSegundos() { 
        return duracionSegundos; 
    }

    @Override
    public String toString() {
        return "[ID: " + id + "] " + titulo + " - " + artista + " (" + duracionSegundos + "s)";
    }
}