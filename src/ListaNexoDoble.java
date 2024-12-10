import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Clase lista nodo Doble
  */
public class ListaNexoDoble {

    LinkedList<String> ListaPorTipo = new LinkedList<>();
    LinkedList<String> ListaPrimeraEvolucion = new LinkedList<>();

    private NodoDoble cabeza;

    /**
     * Constructor de la clase
     */
    public ListaNexoDoble() {
        this.cabeza = null;
    }

    /**
     * Metodo para agregar un nodo
     *
     * @param pokemon elemento a agregar
     * @return retorna true si se agrega
     */
    public boolean agregarFinal(Pokemon pokemon) {
        // Nodo con Pokemon a insertar al Inicio
        NodoDoble nodo = new NodoDoble(pokemon);

        // Si no hay elementos
        if (this.cabeza == null) {
            this.cabeza = nodo;
            this.cabeza.setSiguiente(null);
            this.cabeza.setAnterior(null);
            return true;
        }

        // Si hay elementos
        NodoDoble aux = this.cabeza;
        while (aux.getSiguiente() != null){
            aux = aux.getSiguiente();
        }
        aux.setSiguiente(nodo);
        nodo.setAnterior(aux);
        return true;
    }

    /**
     * Metodo que busca los pokemons que tengan un tipo especifico
     *
     * @param tipo pokemon a buscar
     * @return null si no se encuentra
     */
    public Pokemon obtenerPokemonsPorTipo(String tipo) {
        // Si no hay ningun elemento
        if (this.cabeza == null) {
            System.out.println("[] No hay Pokemons.");
            return null;
        }

        if (this.cabeza != null){
            boolean vacia = true;

            for (NodoDoble aux = this.cabeza; aux != null ; aux = aux.getSiguiente()) {
                // Si el Tipo1 del Pokemon actual es igual al tipo ingresado retornar ese Pokemon
                if (aux.getPokemon().getTipo1().equalsIgnoreCase(tipo)) {
                    ListaPorTipo.add(aux.getPokemon().getNombre());
                    vacia = false;
                }
                // Si el Tipo2 del Pokemon actual es igual al tipo ingresado retornar ese Pokemon
                else if (aux.getPokemon().getTipo2().equalsIgnoreCase(tipo)) {
                    ListaPorTipo.add(aux.getPokemon().getNombre());
                    vacia = false;
                }
            }
            if (vacia == false) {
                System.out.println("-----------------");
                System.out.println("[] Lista de Pokemons del tipo: " + tipo);
                for (String pokemon : ListaPorTipo) {
                    System.out.println("- " + pokemon);
                }
                System.out.println("-----------------");
                System.out.println("");
                ListaPorTipo.clear();
                return null;
            }

            System.out.println("-----------------");
            System.out.println("[] No hay Pokemons del tipo: "+tipo);
            System.out.println("-----------------");
            System.out.println("");
            return null;
        }
        System.out.println("[] No hay ningun Pokemon con ese tipo");
        return null;
    }

    /**
     * Metodo para obetener los pokemons que poseen primera evoluvion
     *
     * @return null si no encuentra
     */
    public Pokemon ObtenerPrimeraEvolucion() {
        // si no hay elemento
        if (this.cabeza == null) {
            System.out.println("[] No hay ningun Pokemon registrado");
            return null;
        }

        NodoDoble aux1 = this.cabeza;
        while (aux1 != null) {
            // Si la etapa es igual a primera evolucion retorna ese pokemon
            if (aux1.getPokemon().getEtapa().equalsIgnoreCase("Primera Evolucion")) {
                ListaPrimeraEvolucion.addFirst(aux1.getPokemon().getNombre());
            }
            // avanza al siguiente nodo
            aux1 = aux1.getSiguiente();
        }
        System.out.println("------------");
        System.out.println("[] Lista de Pokemons de Primera Evolucion: ");
        for (String pokemonEvo : ListaPrimeraEvolucion) {
            System.out.println("- "+pokemonEvo);
        }
        System.out.println("------------");
        ListaPrimeraEvolucion.clear();

        return null;
    }

    /**
     * Metodo para vaciar una lista
     */
    public void Vaciar() {
        this.cabeza = null;
    }

}
