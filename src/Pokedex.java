import java.io.IOException;

public interface Pokedex {

    /**
     * Metodo para leer el archivo de los pokemons
     * @throws IOException valida que se eligan dentro de las opciones
     */
    public void menu() throws IOException;

    /**
     * Despliega los pokemons en el rango numerico que se entrega
     * @param a numero desde donde se buscar
     * @param b numero hasta el cual se busca
     */
    public void BuscarRango(int a, int b);

    /**
     * Despliega todos lo pokemons que se encuentran ordenados numericamente
     */
    public void MostrarTodos();

    /**
     * Despliega pokemons que coincida con el tipo de dato ingresado
     * @param tipo pokemon a buscar
     */
    public void BuscarTipo(String tipo);

    /**
     * Despliega los pokemons con primera evolucion
     */
    public void BuscarPrimeraEvolucion();

    /**
     * Despliega el pokemon que coincida con el nombre entregado por parametro
     * @param nombre pokemon a buscar
     */
    public void BuscarNombre(String nombre);

    /**
     * Despliega el pokemon que coincida con la ID entregado por parametro
     * @param ID pokemon a buscar
     */
    public void BuscarID(int ID);
}
