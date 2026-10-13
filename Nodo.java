public class Nodo {

    private Cancion cancion;
    private Nodo siguiente;
    private Nodo anterior;

    public Nodo(Cancion cancion) {
        this.cancion = cancion;
        this.siguiente = null;
        this.anterior = null;
    }

    public Cancion getCancion() { 
        return cancion; 
    }

    public void setCancion(Cancion cancion) { 
        this.cancion = cancion; 
    }

    public Nodo getSiguiente() { 
        return siguiente; 
    }

    public void setSiguiente(Nodo siguiente) { 
        this.siguiente = siguiente; 
    }

    public Nodo getAnterior() { 
        return anterior; 
    }

    public void setAnterior(Nodo anterior) { 
        this.anterior = anterior; 
    }
}

