package examen_practico;

public class ListaCircularDoblementeEnlazada {
    private Nodo inicio;
    private Nodo finalNodo; 
    private Nodo actual;
    private int tamaño;
    private int contadorId; 
    public ListaCircularDoblementeEnlazada() {
        this.inicio = null;
        this.finalNodo = null;
        this.actual = null;
        this.tamaño = 0;
        this.contadorId = 1; // Empieza en 1
    }

    public boolean estaVacia() {
        return tamaño == 0;
    }

    public int getTamaño() {
        return tamaño;
    }

    public Cancion getCancionActual() {
        if (estaVacia()) return null;
        return actual.getCancion();
    }

    // 1. Agregar al inicio O(1)
    public void agregarAlInicio(String titulo, String artista, int duracion) {
        Cancion nuevaCancion = new Cancion(contadorId++, titulo, artista, duracion);
        Nodo nuevo = new Nodo(nuevaCancion);

        if (estaVacia()) {
            inicio = nuevo;
            finalNodo = nuevo;
            inicio.setSiguiente(inicio);
            inicio.setAnterior(inicio);
            actual = nuevo; // Regla: la primera canción se convierte en la actual
        } else {
            nuevo.setSiguiente(inicio);
            nuevo.setAnterior(finalNodo);
            finalNodo.setSiguiente(nuevo);
            inicio.setAnterior(nuevo);
            inicio = nuevo;
            // Al agregar a una lista no vacía, la canción actual NO cambia
        }
        tamaño++;
    }

    // 2. Agregar al final O(1)
    public void agregarAlFinal(String titulo, String artista, int duracion) {
        Cancion nuevaCancion = new Cancion(contadorId++, titulo, artista, duracion);
        Nodo nuevo = new Nodo(nuevaCancion);

        if (estaVacia()) {
            inicio = nuevo;
            finalNodo = nuevo;
            inicio.setSiguiente(inicio);
            inicio.setAnterior(inicio);
            actual = nuevo;
        } else {
            nuevo.setAnterior(finalNodo);
            nuevo.setSiguiente(inicio);
            finalNodo.setSiguiente(nuevo);
            inicio.setAnterior(nuevo);
            finalNodo = nuevo;
            // La canción actual se mantiene igual
        }
        tamaño++;
    }

    // 3. Buscar por identificador O(n)
    public Cancion buscarPorId(int id) {
        if (estaVacia()) return null;

        Nodo aux = inicio;
        for (int i = 0; i < tamaño; i++) {
            if (aux.getCancion().getId() == id) {
                return aux.getCancion();
            }
            aux = aux.getSiguiente();
        }
        return null;
    }

    // 4. Seleccionar por identificador para convertirla en actual O(n)
    public boolean seleccionarActualPorId(int id) {
        if (estaVacia()) return false;

        Nodo aux = inicio;
        for (int i = 0; i < tamaño; i++) {
            if (aux.getCancion().getId() == id) {
                actual = aux;
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    // 5. Eliminar por identificador O(n)
    public boolean eliminarPorId(int id) {
        if (estaVacia()) return false;

        Nodo aux = inicio;
        for (int i = 0; i < tamaño; i++) {
            if (aux.getCancion().getId() == id) {
                eliminarNodo(aux);
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    // 6. Eliminar directamente la canción actual O(1)
    public boolean eliminarActual() {
        if (estaVacia()) return false;
        eliminarNodo(actual);
        return true;
    }

    // Método auxiliar para desacoplar un nodo de la lista circular
    private void eliminarNodo(Nodo nodoAEliminar) {
        if (tamaño == 1) {
            inicio = null;
            finalNodo = null;
            actual = null;
        } else {
            Nodo anteriorNodo = nodoAEliminar.getAnterior();
            Nodo siguienteNodo = nodoAEliminar.getSiguiente();

            anteriorNodo.setSiguiente(siguienteNodo);
            siguienteNodo.setAnterior(anteriorNodo);

            // Si eliminamos el inicio o el final, actualizamos esas referencias
            if (nodoAEliminar == inicio) {
                inicio = siguienteNodo;
            }
            if (nodoAEliminar == finalNodo) {
                finalNodo = anteriorNodo;
            }

            // Si eliminamos la canción actual, la nueva actual será la que le seguía
            if (nodoAEliminar == actual) {
                actual = siguienteNodo;
            }
        }
        tamaño--;
    }

    // 8. Avanzar O(1)
    public void avanzar() {
        if (!estaVacia()) {
            actual = actual.getSiguiente();
        }
    }

    // 9. Retroceder O(1)
    public void retroceder() {
        if (!estaVacia()) {
            actual = actual.getAnterior();
        }
    }

    // 10. Mostrar la lista completa en ambos sentidos O(n)
    public void mostrarHaciaAdelante() {
        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo aux = inicio;
        System.out.println("--- Lista hacia adelante ---");
        for (int i = 0; i < tamaño; i++) {
            String marca = (aux == actual) ? " (ACTUAL)" : "";
            System.out.println(aux.getCancion() + marca);
            aux = aux.getSiguiente();
        }
    }

    public void mostrarHaciaAtras() {
        if (estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo aux = finalNodo;
        System.out.println("--- Lista hacia atrás ---");
        for (int i = 0; i < tamaño; i++) {
            String marca = (aux == actual) ? " (ACTUAL)" : "";
            System.out.println(aux.getCancion() + marca);
            aux = aux.getAnterior();
        }
    }

    // 12. Simular reproducción de k canciones O(k)
    public void simularReproduccion(int k) {
        if (estaVacia()) {
            System.out.println("No hay canciones para reproducir.");
            return;
        }
        System.out.println("--- Iniciando reproducción simulada de " + k + " canciones ---");
        for (int i = 1; i <= k; i++) {
            System.out.println("Reproduciendo [" + i + "/" + k + "]: " + actual.getCancion());
            avanzar();
        }
        System.out.println("--- Fin de la reproducción simulada ---");
    }
}
