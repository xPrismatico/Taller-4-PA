import ucn.ArchivoEntrada;
import ucn.Registro;
import java.io.IOException;
import java.sql.Array;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Clase que implementa a Pokedex
 */
public class PokedexImpl implements Pokedex {

    private final ListaNexoDoble listaNexoPokemons;
    private final ArrayList<Pokemon> listaPokemons;
    private final ArrayList<Pokemon> listaPokemonsABC;

    /**
     * Constructor de la clase
     *
     * @throws IOException
     */
    public PokedexImpl() throws IOException {
        this.listaPokemons = new ArrayList<>(); // lista de pokemons ordenados por ID
        this.listaNexoPokemons = new ListaNexoDoble(); // lista de pokemons ordenados por ID
        this.listaPokemonsABC = new ArrayList<>(); //lista de pokemons ordenados alfabeticamente
        menu();
    }

    /**
     * Metodo para generar un menu para acceder a los metodos
     *
     * @throws IOException
     */
    @Override
    public void menu() throws IOException {
        LecturaArchivo();

        boolean salir = false;
        while (salir == false) {
            Scanner in = new Scanner(System.in);
            System.out.println("--------------------------------------------------------");
            System.out.println("| Escoge una opción para interactuar con la Pokedex ! |");
            System.out.println("-------[ MENÚ ]-----------------------------------------");
            System.out.println("1. Buscar pokemons segun un rango");
            System.out.println("2. Desplegar todos los pokemons");
            System.out.println("3. Buscar pokemons segun un tipo");
            System.out.println("4. Mostrar pokemons de Primera Evolucion");
            System.out.println("5. Busqueda de un pokemon especifico");
            System.out.println("6. Cerrar");

            System.out.print("[] Ingrese una opción: ");
            String opcion = in.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("[] Ingrese el numero inicial desde donde quiere buscar: ");
                    //valdacion letras
                    while (!in.hasNextInt()) {
                        System.out.print("[] Por favor, ingrese un número: ");
                        in.next();
                    }
                    int inicial = in.nextInt();

                    System.out.print("[] Ingrese el numero final hasta donde quiere buscar: ");
                    //validacion letras
                    while (!in.hasNextInt()) {
                        System.out.print("[] Por favor, ingrese un número: ");
                        in.next();
                    }
                    int Final = in.nextInt();

                    BuscarRango(inicial, Final);
                    break;

                case "2":
                    MostrarTodos();
                    break;

                case "3":
                    System.out.print("[] Ingrese el tipo de Pokemon que desea buscar: ");
                    String tipo = in.nextLine();
                    BuscarTipo(tipo);
                    break;

                case "4":
                    BuscarPrimeraEvolucion();
                    break;

                case "5":
                    System.out.println("--------[BUSCADOR]---------");
                    System.out.println("[] ¿Como quieres buscar tu Pokemon?:");
                    System.out.println("1. Buscar por ID");
                    System.out.println("2. Buscar por Nombre");
                    System.out.print("[] Elige una opcion: ");
                    String respuesta = in.nextLine();

                    // Buscar por ID
                    if (respuesta.equalsIgnoreCase("1")) {
                        System.out.print("[] Ingrese la ID del Pokemon que quiere buscar: ");
                        while (!in.hasNextInt()) {
                            System.out.print("[] Por favor, ingrese un número: ");
                            in.next();
                        }
                        int id = in.nextInt();
                        BuscarID(id);
                    }

                    // Buscar por Nombre
                    else if (respuesta.equalsIgnoreCase("2")) {
                        System.out.print("[] Ingrese el nombre del Pokemon que quiere buscar: ");

                        String nombre = in.nextLine();
                        BuscarNombre(nombre);
                    }

                    // Validacion
                    else {
                        System.out.println("[] Escoge una opcion valida");
                    }
                    break;

                case "6":
                    System.out.println("[] Has cerrado la Pokedex.");
                    salir = true;
                    return;

                default:
                    System.out.println("[] Opción no válida, escoja 1, 2, 3, 4, 5 o 6: ");
                    break;
            }
        }
    }

    /**
     * Despliega los pokemos en el rango numerico que se entrega
     *
     * @param A numero desde donde se buscar
     * @param B numero hasta el cual se busca
     */
    @Override
    public void BuscarRango(int A, int B) {
        if (A < 1 || B > this.listaPokemons.size() || A > B) {
            System.out.println("------------");
            System.out.println("[] Rango no valido");
            System.out.println("------------");
            return;
        }
        System.out.println("------------");
        System.out.println("[] Lista de Pokemons desde el ID " + A + " hasta el ID " + B + ":");
        for (int i = A - 1; i < B; i++) {
            Pokemon pokemon = this.listaPokemons.get(i);
            System.out.println("- "+pokemon.getNombre());
        }
        System.out.println("------------");
    }

    /**
     * Despliega todos lo pokemons que se encuentran ordenados numericamente
     */
    @Override
    public void MostrarTodos() {
        Collections.sort(listaPokemonsABC, Pokemon.comparadorPorNombre);

        System.out.println("-----------------");
        System.out.println("[] Lista de Pokemons:");
        for (Pokemon pokemon : listaPokemonsABC) {
            System.out.println("- " + pokemon.getNombre());
        }
        System.out.println("-----------------");
    }

    /**
     * Despliega pokemons que coincidan con el tipo de dato ingresado
     *
     * @param tipo de pokemon
     */
    @Override
    public void BuscarTipo(String tipo) {
        this.listaNexoPokemons.obtenerPokemonsPorTipo(tipo);
    }

    /**
     * Despliega los pokemons con primera evolucion
     */
    @Override
    public void BuscarPrimeraEvolucion() {
        this.listaNexoPokemons.ObtenerPrimeraEvolucion();
    }

    /**
     * Despliega el pokemon que coincida con el nombre entregado por parametro
     *
     * @param nombre pokemon a buscar
     */
    @Override
    public void BuscarNombre(String nombre) {
        Scanner in = new Scanner(System.in);
        for (Pokemon pokemon : this.listaPokemons) {
            if (pokemon.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("--------[ ENCONTRADO! ]---------");
                System.out.println(pokemon.toString());
                // No tiene evoluciones
                if (pokemon.getEvolucionPrevia().equalsIgnoreCase("Ninguna") && pokemon.getEvolucionSiguiente().equalsIgnoreCase("Ninguna") ) {
                    return;
                }
                boolean salir = false;
                while (salir == false) {
                    // Tiene evoluciones
                    int IDPokemon = pokemon.getID();
                    System.out.println("----------------------------------");
                    System.out.println("[] ¿Quieres desplegar informacion sobre sus Evoluciones?");
                    System.out.println("1. Si");
                    System.out.println("2. Salir");
                    System.out.print("[] Ingrese una opcion: ");
                    String opcion = in.nextLine();
                    System.out.println("----------------------------------");
                    if (opcion.equals("1")) { // DESPLEGAR INFO EVOLUCIONES
                        // No tiene Segunda Evolucion
                        if (pokemon.getEvolucionPrevia().equalsIgnoreCase("Ninguna")) {
                            System.out.println("-----------[EVOLUCIONES]-----------------------");
                            //Es Basico
                            if (pokemon.getEtapa().equalsIgnoreCase("Basico")) {
                                System.out.println("[] Primera Evolución de " + pokemon.getNombre() + ": ");
                                System.out.println(this.listaPokemons.get(IDPokemon).toString());
                                System.out.println("----------------------------------");
                            }
                            //Es Primera Evolucion
                            else if (pokemon.getEtapa().equalsIgnoreCase("Primera Evolucion")) {
                                System.out.println("[] Etapa Base de " + pokemon.getNombre() + ": ");
                                if (pokemon.getNombre().equalsIgnoreCase("Jolteon")){
                                    System.out.println(this.listaPokemons.get(IDPokemon-3).toString());
                                }
                                else if (pokemon.getNombre().equalsIgnoreCase("Flareon")){
                                    System.out.println(this.listaPokemons.get(IDPokemon-4).toString());
                                }
                                else{
                                    System.out.println(this.listaPokemons.get(IDPokemon-2).toString());
                                }
                                System.out.println("----------------------------------");
                            }
                        }
                        // Es Eevee
                        else if (pokemon.getNombre().equalsIgnoreCase("Eevee")){
                            System.out.println("-----------[EVOLUCIONES]-----------------------");
                            System.out.println("[] Primera Evolucion de " + pokemon.getNombre() + ": ");
                            System.out.println(this.listaPokemons.get(IDPokemon).toString());
                            System.out.println("[] Primera Evolucion de " + pokemon.getNombre() + ": ");
                            System.out.println(this.listaPokemons.get(IDPokemon + 1).toString());
                            System.out.println("[] Primera Evolucion de " + pokemon.getNombre() + ": ");
                            System.out.println(this.listaPokemons.get(IDPokemon + 2).toString());

                            System.out.println("----------------------------------");
                        }
                        // Es Etapa Basica
                        else if (pokemon.getEtapa().equalsIgnoreCase("Basico")) {
                            System.out.println("-----------[EVOLUCIONES]-----------------------");
                            System.out.println("[] Primera Evolucion de " + pokemon.getNombre() + ": ");
                            System.out.println(this.listaPokemons.get(IDPokemon).toString());
                            System.out.println("[] Segunda Evolucion de " + pokemon.getNombre() + ": ");
                            System.out.println(this.listaPokemons.get(IDPokemon + 1).toString());
                            System.out.println("----------------------------------");
                        }
                        // Es Primera Evolucion
                        else if (pokemon.getEtapa().equalsIgnoreCase("Primera Evolucion")) {
                            System.out.println("-----------[EVOLUCIONES]-----------------------");
                            System.out.println("[] Etapa Base de " + pokemon.getNombre() + ": ");
                            System.out.println(this.listaPokemons.get(IDPokemon - 2).toString());
                            System.out.println("[] Segunda Evolucion de " + pokemon.getNombre() + ": ");
                            System.out.println(this.listaPokemons.get(IDPokemon).toString());
                            System.out.println("----------------------------------");
                        }
                        // Es Segunda Evolucion
                        else if (pokemon.getEtapa().equalsIgnoreCase("Segunda Evolucion")) {
                            System.out.println("-----------[EVOLUCIONES]-----------------------");
                            System.out.println("[] Etapa Base de " + pokemon.getNombre() + ": ");
                            System.out.println(this.listaPokemons.get(IDPokemon - 3).toString());
                            System.out.println("[] Primera Evolucion de " + pokemon.getNombre() + ": ");
                            System.out.println(this.listaPokemons.get(IDPokemon - 2).toString());
                            System.out.println("----------------------------------");
                        }

                    }

                    else if (opcion.equals("2")) { // SALIR
                        salir = true;
                        return;
                    }

                    else { // VALIDACION
                        System.out.println("[] Opción inválida");
                    }
                }
                return;
            }
        }
        System.out.println("--------[ NO ENCONTRADO! ]---------");
        System.out.println("[] El Pokemon "+nombre+" no está en la Pokedex.");
    }


        /**
         * Despliega el pokemon que coincida con la ID entregado por parametro
         *
         * @param ID pokemon a buscar
         */
        @Override
        public void BuscarID ( int ID){
            Scanner in = new Scanner(System.in);

            for (Pokemon pokemon : this.listaPokemons) {
                if (pokemon.getID() == ID) {
                    System.out.println("--------[ ENCONTRADO! ]---------");
                    System.out.println(pokemon.toString());
                    // No tiene evoluciones
                    if (pokemon.getEvolucionPrevia().equalsIgnoreCase("Ninguna") && pokemon.getEvolucionSiguiente().equalsIgnoreCase("Ninguna") ) {
                        return;
                    }
                    boolean salir = false;
                    while (salir == false) {
                        // Tiene evoluciones
                        int IDPokemon = pokemon.getID();
                        System.out.println("----------------------------------");
                        System.out.println("[] ¿Quieres desplegar informacion sobre sus Evoluciones?");
                        System.out.println("1. Si");
                        System.out.println("2. Salir");
                        System.out.print("[] Ingrese una opcion: ");
                        String opcion = in.nextLine();
                        System.out.println("----------------------------------");
                        if (opcion.equals("1")) { // DESPLEGAR INFO EVOLUCIONES
                            // No tiene Segunda Evolucion
                            if (pokemon.getEvolucionPrevia().equalsIgnoreCase("Ninguna")) {
                                System.out.println("-----------[EVOLUCIONES]-----------------------");
                                //Es Basico
                                if (pokemon.getEtapa().equalsIgnoreCase("Basico")) {
                                    System.out.println("[] Primera Evolución de " + pokemon.getNombre() + ": ");
                                    System.out.println(this.listaPokemons.get(IDPokemon).toString());
                                    System.out.println("----------------------------------");
                                }
                                //Es Primera Evolucion
                                else if (pokemon.getEtapa().equalsIgnoreCase("Primera Evolucion")) {
                                    System.out.println("[] Etapa Base de " + pokemon.getNombre() + ": ");
                                    if (pokemon.getNombre().equalsIgnoreCase("Jolteon")){
                                        System.out.println(this.listaPokemons.get(IDPokemon-3).toString());
                                    }
                                    else if (pokemon.getNombre().equalsIgnoreCase("Flareon")){
                                        System.out.println(this.listaPokemons.get(IDPokemon-4).toString());
                                    }
                                    else{
                                        System.out.println(this.listaPokemons.get(IDPokemon-2).toString());
                                    }
                                    System.out.println("----------------------------------");
                                }
                            }
                            // Es Eevee
                            else if (pokemon.getNombre().equalsIgnoreCase("Eevee")){
                                System.out.println("-----------[EVOLUCIONES]-----------------------");
                                System.out.println("[] Primera Evolucion de " + pokemon.getNombre() + ": ");
                                System.out.println(this.listaPokemons.get(IDPokemon).toString());
                                System.out.println("[] Primera Evolucion de " + pokemon.getNombre() + ": ");
                                System.out.println(this.listaPokemons.get(IDPokemon + 1).toString());
                                System.out.println("[] Primera Evolucion de " + pokemon.getNombre() + ": ");
                                System.out.println(this.listaPokemons.get(IDPokemon + 2).toString());

                                System.out.println("----------------------------------");
                            }
                            // Es Etapa Basica
                            else if (pokemon.getEtapa().equalsIgnoreCase("Basico")) {
                                System.out.println("-----------[EVOLUCIONES]-----------------------");
                                System.out.println("[] Primera Evolucion de " + pokemon.getNombre() + ": ");
                                System.out.println(this.listaPokemons.get(IDPokemon).toString());
                                System.out.println("[] Segunda Evolucion de " + pokemon.getNombre() + ": ");
                                System.out.println(this.listaPokemons.get(IDPokemon + 1).toString());
                                System.out.println("----------------------------------");
                            }
                            // Es Primera Evolucion
                            else if (pokemon.getEtapa().equalsIgnoreCase("Primera Evolucion")) {
                                System.out.println("-----------[EVOLUCIONES]-----------------------");
                                System.out.println("[] Etapa Base de " + pokemon.getNombre() + ": ");
                                System.out.println(this.listaPokemons.get(IDPokemon - 2).toString());
                                System.out.println("[] Segunda Evolucion de " + pokemon.getNombre() + ": ");
                                System.out.println(this.listaPokemons.get(IDPokemon).toString());
                                System.out.println("----------------------------------");
                            }
                            // Es Segunda Evolucion
                            else if (pokemon.getEtapa().equalsIgnoreCase("Segunda Evolucion")) {
                                System.out.println("-----------[EVOLUCIONES]-----------------------");
                                System.out.println("[] Etapa Base de " + pokemon.getNombre() + ": ");
                                System.out.println(this.listaPokemons.get(IDPokemon - 3).toString());
                                System.out.println("[] Primera Evolucion de " + pokemon.getNombre() + ": ");
                                System.out.println(this.listaPokemons.get(IDPokemon - 2).toString());
                                System.out.println("----------------------------------");
                            }

                        }

                        else if (opcion.equals("2")) { // SALIR
                            salir = true;
                            return;
                        }

                        else { // VALIDACION
                            System.out.println("[] Opción inválida");
                        }
                    }
                    break;
                }
            }
            System.out.println("--------[ NO ENCONTRADO! ]---------");
            System.out.println("[] El Pokemon ID "+ID+" no está en la Pokedex.");
        }


        /**
         * Metodo para leer el archivo
         */
        public void LecturaArchivo () {

            ArchivoEntrada file = null;
            try {
                file = new ArchivoEntrada("kanto.txt"); // lee kanto
            } catch (IOException e) {
                System.out.println("[] Error al intentar leer archivo"); // si no existe, error
                return;
            }

            while (!file.isEndFile()) {
                Registro line = null;
                try {
                    line = file.getRegistro(); // Toma toda la linea y la guarda
                } catch (IOException e) {
                    System.out.println("[] No hay linea");
                }

                if (line == null) break;

                // Primer dato (primera ,)
                int ID;
                try {
                    String idStr = line.getString().strip(); // .strip quita espacios del principio y final
                    ID = Integer.parseInt(idStr); // Lo pasa a int porque lo tenia String para quitar espacio
                } catch (NumberFormatException e) {
                    continue;
                }

                // Segundo dato NOMBRE
                String Nombre = line.getString().strip();
                // Tercer dato ETAPA
                String Etapa = line.getString().strip();

                // Cuarto dato SIGUIENTE
                String EvolucionSiguiente = line.getString().strip();


                // Quinto dato ANTERIOR (para Eevee es SIGUIENTE2)
                String EvolucionPrevia = line.getString().strip();

                // (CASO ESPECIAL) dato SIGUIENTE3 para Eevee
                String EvolucionOpcional = null;
                if (ID == 27) {
                    EvolucionOpcional = line.getString().strip();
                }

                String Tipo1 = line.getString(); // Podria ser null por la cantidad de separaciones ","
                // VALIDACION //
                boolean no1 = false;

                // Caso1: NO hay SIGUIENTE
                if (Tipo1 == null) {
                    no1 = true;
                    Tipo1 = EvolucionSiguiente;
                    EvolucionSiguiente = "Ninguna";
                }
                // Caso2: HAY SIGUIENTE
                if (no1 == false) {
                    Tipo1.strip();
                }

                String Tipo2 = line.getString(); // Podria ser null por la cantidad de separaciones ","
                // VALIDACION //
                boolean no2 = false;

                // Caso1: NO hay ANTERIOR
                if (Tipo2 == null) {
                    no2 = true;
                    Tipo2 = EvolucionPrevia;
                    EvolucionPrevia = "Ninguna";
                }
                if (no2 == false) {
                    Tipo2.strip();
                }

                // Validacion lineas vacias
                if (Nombre == null || Tipo1 == null || Etapa == null) {
                    continue;
                }
                if (ID != 27) {
                    Pokemon pokemon = new Pokemon(ID, Nombre, Etapa, EvolucionSiguiente.strip(), EvolucionPrevia.strip(), Tipo1.strip(), Tipo2.strip());
                    this.listaPokemons.add(pokemon);
                }
                if (ID == 27) {
                    Pokemon pokemon = new Pokemon(ID, Nombre, Etapa, EvolucionSiguiente.strip(), EvolucionPrevia.strip(), EvolucionOpcional.strip(), Tipo1.strip(), Tipo2.strip());
                    this.listaPokemons.add(pokemon);
                }
            }
            Collections.sort(this.listaPokemons);

            // Añade los pokemon de la listaPokemons (ArrayList) a la listaNexoPokemon (ListaNexoDoble)
            for (Pokemon pokemon : this.listaPokemons) {
                this.listaNexoPokemons.agregarFinal(pokemon);
                this.listaPokemonsABC.add(pokemon);
            }
        }
}


