package labproyecto;

import java.util.Scanner;

/**
 *
 * @author Maria Gabriela
 */
public class Labproyecto {

    public static GhostGame GhostGame = new GhostGame();
    public static Scanner entrada = new Scanner(System.in);
    public static String currentPlayer;

    public static String[][] player = new String[10][2];
    public static int cantus = 0;
    public static boolean encontrado;

    public static void registrarusuario() {
        if (cantus >= player.length) {
            System.out.println("No se pueden registrar más usuarios.");
            return;
        }

        System.out.print("Introduce el nombre de usuario: ");
        String usuario = entrada.nextLine();

        System.out.print("Introduce una contraseña que no sea menor que 8 dígitos: ");
        String contraseña = entrada.nextLine();

        if (contraseña.length() >= 8 && verificarUnico(usuario)) {
            player[cantus][0] = usuario;
            player[cantus][1] = contraseña;
            cantus++;
            System.out.println("Usuario registrado exitosamente.");
        } else {
            System.out.println("Contraseña muy corta o username no es único.");
        }
    }

    public static boolean verificarUnico(String usuario) {
        for (int i = 0; i < cantus; i++) {
            if (player[i][0].equals(usuario)) {
                return false;
            }
        }
        return true;
    }

    public static void loginusuario() {
        System.out.print("Introduce el nombre de usuario: ");
        String usuario = entrada.nextLine();

        System.out.print("Introduce la contraseña: ");
        String contraseña = entrada.nextLine();
        GhostGame.currentPlayer = usuario;

        encontrado = false;
        for (int i = 0; i < cantus; i++) {
            if (player[i][0].equals(usuario) && player[i][1].equals(contraseña)) {
                System.out.println("Login exitoso.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("Usuario o contraseña incorrectos.");
        } else {
            showMainMenu(player[cantus - 1][0]); // Pasar el último usuario autenticado
        }
    }

    public static void main(String[] args) {
        int inicial;

        while (true) {
            System.out.println("Bienvenido al juego 'GHOSTS'!");
            System.out.println("1. Crear player\n2. Login\n3. Salir de juego");
            inicial = entrada.nextInt();
            entrada.nextLine(); // Consumir nueva línea

            switch (inicial) {
                case 1:
                    registrarusuario();
                    break;
                case 2:
                    loginusuario();
                    break;
                case 3:
                    System.out.println("Gracias por usar el juego!");
                    System.exit(0);
                default:
                    System.out.println("Favor ingresar una opción válida!");
            }
        }
    }

    public static void showMainMenu(String player1) {
        while (true) {
            System.out.println("Menú Principal");
            System.out.println("1. Jugar Ghosts\n2. Configuración\n3. Reportes\n4. Mi Perfil\n5. Salir");
            System.out.print("Elige una opción: ");
            int option = entrada.nextInt();
            entrada.nextLine(); // Consumir nueva línea

            switch (option) {
                case 1:
                    playGame(player1);
                    break;
                case 2:
                    Player.configuracion(entrada);
                    break;
                case 3:
                    Player.reportes(entrada);
                    break;
                case 4:
                    Player.perfil(entrada);
                    break;
                case 5:
                    System.out.println("Saliendo del menú principal...");

                    return;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

    private static void playGame(String player1) {
        System.out.print("Ingresa el nombre del Jugador 2: ");
        String player2 = entrada.nextLine();

        if (!verificarUnico(player2)) {
            GhostGame.setPlayers(player1, player2);

            // Colocar fantasmas para ambos jugadores
            GhostGame.placeGhosts(player1, true);  // Fantasmas del Jugador 1
            GhostGame.placeGhosts(player2, false); // Fantasmas del Jugador 2


            GhostGame.printBoard();

            while (!GhostGame.checkVictory()) {
                System.out.println("Es turno de: " + GhostGame.getTurn());
                System.out.print("Selecciona la casilla de origen (fila y columna): ");
                int startX = entrada.nextInt();
                int startY = entrada.nextInt();

                System.out.print("Selecciona la casilla de destino (fila y columna): ");
                int endX = entrada.nextInt();
                int endY = entrada.nextInt();

                if (!GhostGame.moveGhost(startX, startY, endX, endY)) {
                    System.out.println("Movimiento no válido. Intenta nuevamente.");
                }

                GhostGame.printBoard();
            }

            System.out.println("¡Juego terminado!");
        } else {
            System.out.println("El nombre de usuario del Jugador 2 no existe. Intenta nuevamente.");
        }
    }
}
