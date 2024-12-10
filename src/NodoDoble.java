/**
 * Clase Nodo Doble
 *
 */
public class NodoDoble {

    private Pokemon pokemon;
    private NodoDoble anterior;

    private NodoDoble siguiente;

    /**
     * Contructor de la clase
     * @param pokemon elemento a agregar
     */
    public NodoDoble(Pokemon pokemon) {
        this.pokemon = pokemon;
        this.anterior = null;
        this.siguiente = null;
    }

    /**
     * get siguiente
     * @return siguiente.
     */
    public NodoDoble getSiguiente() {
        return this.siguiente;
    }

    /**
     * get pokemon
     * @return pokemon
     */
    public Pokemon getPokemon() {
        return pokemon;
    }

    /**
     * get anterior
     * @return Anterior.
     */
    public NodoDoble getAnterior() {
        return this.anterior;
    }

    /**
     * sete siguiente
     * @param siguiente
     */
    public void setSiguiente(NodoDoble siguiente) {
        this.siguiente = siguiente;
    }

    /**
     * set anterior
     * @param anterior
     */
    public void setAnterior(NodoDoble anterior) {
        this.anterior = anterior;
    }


}


