import java.util.Comparator;

/**
 * Clase Pokemon
 */
public class Pokemon implements Comparable<Pokemon>{

    private int ID;
    private String Nombre;
    private String Etapa;
    private String EvolucionSiguiente;
    private String EvolucionPrevia;
    private String EvolucionOpcional;
    private String Tipo1;
    private String Tipo2;

    /**
     * Constructor de la clase para Pokemons
     * @param ID
     * @param nombre
     * @param etapa
     * @param EvolucionSiguiente
     * @param EvolucionPrevia
     * @param tipo1
     * @param tipo2
     */
    public Pokemon(int ID, String nombre, String etapa, String EvolucionSiguiente,String EvolucionPrevia, String tipo1, String tipo2) {
        this.ID = ID;
        this.Nombre = nombre;
        this.Etapa = etapa;
        this.EvolucionSiguiente = EvolucionSiguiente;
        this.EvolucionPrevia = EvolucionPrevia;
        this.Tipo1 = tipo1;
        this.Tipo2 = tipo2;
    }

    /**
     * Constructor de la clase para Pokemons con tres evoluciones (Eevee)
     * @param ID
     * @param nombre
     * @param etapa
     * @param EvolucionSiguiente
     * @param EvolucionPrevia
     * @param EvolucionOpcional
     * @param tipo1
     * @param tipo2
     */
    public Pokemon(int ID, String nombre, String etapa, String EvolucionSiguiente,String EvolucionPrevia, String EvolucionOpcional,String tipo1, String tipo2) {
        this.ID = ID;
        this.Nombre = nombre;
        this.Etapa = etapa;
        this.EvolucionSiguiente = EvolucionSiguiente;
        this.EvolucionPrevia = EvolucionPrevia;
        this.EvolucionOpcional = EvolucionOpcional;
        this.Tipo1 = tipo1;
        this.Tipo2 = tipo2;
    }


    /**
     * get id pokemon
     * @return Id
     */
    public int getID() {
        return ID;
    }

    /**
     * get Nombre pokemon
     * @return nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * get Etapa pokemon
     * @return Etapa
     */
    public String getEtapa() {
        return Etapa;
    }

    /**
     * get Tipo 1 pokemon
     * @return Tipo1
     */
    public String getTipo1() {
        return Tipo1;
    }

    /**
     * get Tipo 2 pokemon
     * @return Tipo2
     */
    public String getTipo2() {
        return Tipo2;
    }

    /**
     * get Evolucion Siguiente pokemon
     * @return Evolucion Siguientre
     */
    public String getEvolucionSiguiente() {
        return EvolucionSiguiente;
    }

    /**
     * get Evolucion previa pokemon
     * @return Evolucion Previa
     */
    public String getEvolucionPrevia() {
        return EvolucionPrevia;
    }

    /**
     * get Evolucion opcional
     * @return EvolucionOpcional
     */
    public String getEvolucionOpcional() {
        return EvolucionOpcional;
    }

    /**
     * compara dos pokemons segun su ID
     * @param otroPokemon the object to be compared.
     * @return alor entero positivo, negativo o cero dependiendo si la id entregada es menor, igual o mayor a la Id existente
     */
    @Override
    public int compareTo(Pokemon otroPokemon) {
        return Integer.compare(this.ID, otroPokemon.ID);
    }

    /**
     * clase que compara dos pokemons segun su nombre
     */
    public static Comparator<Pokemon> comparadorPorNombre = new Comparator<Pokemon>() {
        /**
         *
         * @param pokemon1 the first object to be compared.
         * @param pokemon2 the second object to be compared.
         * @return valor entero positivo, negativo o cero dependiendo de las dos variables entregadas
         */
        @Override
        public int compare(Pokemon pokemon1, Pokemon pokemon2) {
            return pokemon1.getNombre().compareTo(pokemon2.getNombre());
        }
    };

    /**
     * Metodo para imprimir todos los atribultos de un objeto
     * @return vacio
     */
    @Override
    public String toString() {
        System.out.println("ID: " + ID);
        System.out.println("Nombre: " + Nombre);
        System.out.println("Etapa: " + Etapa);
        System.out.println("Tipo 1: " + Tipo1);
        System.out.println("Tipo 2: " + Tipo2);
        // Es Eevee
        if (Nombre.equalsIgnoreCase("Eevee")){
            System.out.println("Primera Evolucion: " + EvolucionSiguiente);
            System.out.println("Primera Evolucion: " + EvolucionPrevia);
            System.out.println("Primera Evolucion: " + EvolucionOpcional);
            return "";
        }

        // Solo tiene Basico y 1Evolucion
        if (EvolucionPrevia.equalsIgnoreCase("Ninguna")){
            // Si es Etapa Basico
            if (Etapa.equalsIgnoreCase("Basico")){
                System.out.println("Primera Evolución: " + EvolucionSiguiente);
                return "";
            }

            // Si es Etapa 1Evolucion
            if (Etapa.equalsIgnoreCase("Primera Evolucion")){
                System.out.println("Base: " + EvolucionSiguiente);
                return "";
            }
        }

        // No tiene Evoluciones
        if (EvolucionSiguiente.equalsIgnoreCase("Ninguna") && EvolucionPrevia.equalsIgnoreCase("Ninguna")){
            return "";
        }

        // Es Basico con 2 Evoluciones
        if (Etapa.equalsIgnoreCase("Basico")){
            System.out.println("Primera Evolucion: " + EvolucionSiguiente);
            System.out.println("Segunda Evolución: " + EvolucionPrevia);
            return "";
        }

        // Es Etapa: 1Evolucion
        if (Etapa.equalsIgnoreCase("Primera Evolucion")){
            System.out.println("Base: " + EvolucionPrevia);
            System.out.println("Segunda Evolución: " + EvolucionSiguiente);
            return "";
        }

        // Es Etapa: 2Evolucion
        if (Etapa.equalsIgnoreCase("Segunda Evolucion")){
            System.out.println("Primera Evolucion: " + EvolucionSiguiente);
            System.out.println("Base: " + EvolucionPrevia);
            return "";
        }
        return "";
    }

}
