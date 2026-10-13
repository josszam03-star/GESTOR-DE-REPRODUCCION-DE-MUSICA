import java.util.Scanner;

public class Menu {

    private ListaCircularDoblementeEnlazada lista;
    private Scanner scanner;

    public Menu() {
        this.lista = new ListaCircularDoblementeEnlazada();
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion = 0;
        do {
            mostrarOpciones();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    agregarInicio();
                    break;
                case 2:
                    agregarFinal();
                    break;
                case 3:
                    buscarCancion();
                    break;
                case 4:
                    seleccionarActual();
                    break;
                case 5:
                    eliminarPorId();
                    break;
                case 6:
                    eliminarActual();
                    break;
                case 7:
                    consultarActual();
                    break;
                case 8:
                    avanzarCancion();
                    break;
                case 9:
                    retrocederCancion();
                    break;
                case 10:
                    mostrarLista();
                    break;
                case 11:
                    consultarCantidad();
                    break;
                case 12:
                    simularReproduccion();
                    break;
                case 13:
                    System.out.println("¡Gracias por usar el gestor de reproducción! Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
            }
            System.out.println();
        } while (opcion != 13);
    }

    private void mostrarOpciones() {
        System.out.println("******************************************");
        System.out.println("       GESTOR DE LISTA DE REPRODUCCIÓN    ");
        System.out.println("******************************************");
        System.out.println("1. Agregar una canción al inicio");
        System.out.println("2. Agregar una canción al final");
        System.out.println("3. Buscar una canción por su ID");
        System.out.println("4. Seleccionar canción por su ID como actual");
        System.out.println("5. Eliminar una canción por su ID");
        System.out.println("6. Eliminar directamente la canción actual");
        System.out.println("7. Consultar la canción actual");
        System.out.println("8. Avanzar a la siguiente canción");
        System.out.println("9. Retroceder a la canción anterior");
        System.out.println("10. Mostrar la lista completa");
        System.out.println("11. Consultar la cantidad de canciones");
        System.out.println("12. Simular reproducción de k canciones");
        System.out.println("13. Salir");
        System.out.println("******************************************");
    }

    private void agregarInicio() {
        System.out.println("\n*** Agregar una Canción al Inicio ***");

        System.out.print("TITULO: ");
        String titulo = scanner.nextLine();

        System.out.print("ARTISTA: ");
        String artista = scanner.nextLine();
        int duracion = leerDuracionPositiva();

        lista.agregarAlInicio(titulo, artista, duracion);
        System.out.println("*** ¡La canción a sido agregada al inicio con éxito! ***");
    }

    private void agregarFinal() {
        System.out.println("\n*** Agregar Canción al Final ***");

        System.out.print("TITULO: ");
        String titulo = scanner.nextLine();

        System.out.print("ARTISTA: ");
        String artista = scanner.nextLine();
        int duracion = leerDuracionPositiva();

        lista.agregarAlFinal(titulo, artista, duracion);
        System.out.println("*** ¡La canción se ha agregado al final con éxito! ***");
    }

    private void buscarCancion() {
        System.out.println("\n*** Buscar Canción por su ID ***");

        int id = leerEntero("Ingrese el ID de la canción: ");
        Cancion c = lista.buscarPorId(id);

        if (c != null) {
            System.out.println("Canción encontrada: " + c);
        } else {
            System.out.println("¡Intente de nuevo. No se encontró ninguna canción con el ID " + id + "!");
        }
    }

    private void seleccionarActual() {
        System.out.println("\n*** Seleccionar Canción Actual por su ID ***");

        int id = leerEntero("Ingrese el ID de la canción: ");
        boolean exito = lista.seleccionarActualPorId(id);

        if (exito) {
            System.out.println("¡La canción actual ahora es: " + lista.getCancionActual() + "!");
        } else {
            System.out.println("No se encontró el ID " + id + ". La canción actual no cambió.");
        }
    }

    private void eliminarPorId() {
        System.out.println("\n*** Eliminar Canción por su ID ***");
        int id = leerEntero("Ingrese el ID de la canción que desea eliminar: ");
        boolean exito = lista.eliminarPorId(id);

        if (exito) {
            System.out.println("¡La canción con el ID " + id + " fue eliminada correctamente!");
        } else {
            System.out.println("No se encontró el ID " + id + ". No se elimino ninguna canción.");
        }
    }

    private void eliminarActual() {
        System.out.println("\n*** Eliminar Canción Actual ***");

        if (lista.estaVacia()) {
            System.out.println("La lista está vacía. No hay canción actual que eliminar.");
            return;
        }

        Cancion actualAntes = lista.getCancionActual();
        lista.eliminarActual();

        System.out.println("Se ha eliminado la canción actual: " + actualAntes);
        if (!lista.estaVacia()) {
            System.out.println("Nueva canción actual: " + lista.getCancionActual());
        } else {
            System.out.println("La lista ha quedado vacía.");
        }
    }

    private void consultarActual() {
        System.out.println("\n*** Canción Actual ***");

        Cancion actual = lista.getCancionActual();
        if (actual != null) {
            System.out.println("Reproduciendo actualmente la canción: " + actual);
        } else {
            System.out.println("La lista está vacía. No hay canción actual.");
        }
    }

    private void avanzarCancion() {
        if (lista.estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }
        lista.avanzar();
        System.out.println(" Avanzado a la siguiente canción.");
        System.out.println("Canción actual/Esta escuchando: " + lista.getCancionActual());
    }

    private void retrocederCancion() {
        if (lista.estaVacia()) {
            System.out.println("La lista está vacía.");
            return;
        }
        lista.retroceder();
        System.out.println(" Retrocedido a la canción anterior.");
        System.out.println("Canción actual: " + lista.getCancionActual());
    }

    private void mostrarLista() {
        System.out.println("\n--- Mostrar Lista ---");
        lista.mostrarHaciaAdelante();
        System.out.println();
        lista.mostrarHaciaAtras();
    }

    private void consultarCantidad() {
        System.out.println("\nTotal de canciones en la lista: " + lista.getTamanio());
    }

    private void simularReproduccion() {
        System.out.println("\n*** Simulación de Reproducción ***");
        int k = leerEntero("Ingrese la cantidad de canciones a simular (k > 0): ");

        if (k <= 0) {
            System.out.println("La cantidad k debe ser un entero positivo.");
            return;
        }
        lista.simularReproduccion(k);
    }

    // Métodos auxiliares de lectura segura para evitar errores con letras
    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String entrada = scanner.nextLine();
                return Integer.parseInt(entrada.trim());
            } catch (NumberFormatException e) {
                System.out.println("*** Error. Ingrese un número entero válido. ***");
            }
        }
    }

    private int leerDuracionPositiva() {
        while (true) {
            int duracion = leerEntero("Duración en segundos (mayor a 0): ");
            if (duracion > 0) {
                return duracion;
            }
            System.out.println("Error: La duración debe ser estricamente mayor a cero.");
        }
    }
}
