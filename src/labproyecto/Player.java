package labproyecto;

import java.util.Scanner;

public class Player {

    private static String dificultad = "Normal"; 
    private static String modoJuego = "Aleatorio"; 

    public static void setDificultad(String dificultad) {
        Player.dificultad = dificultad;
        System.out.println("Dificultad configurada a: " + dificultad);
    }

    public static void setModoJuego(String modoJuego) {
        Player.modoJuego = modoJuego;
        System.out.println("Modo de juego configurado a: " + modoJuego);
    }

    public static String[][] player = new String[10][2];
    public static int cantus = 0;
    public static boolean encontrado;

    public static void configuracion(Scanner entrada) {
        System.out.println("Has entrado a Configuración.");
        while (true) {
            System.out.println("1. Cambiar Dificultad");
            System.out.println("2. Modo de Juego");
            System.out.println("3. Regresar al Menú Principal");
            System.out.print("Elige una opción: ");
            int opcion = entrada.nextInt();
            entrada.nextLine(); // Consumir la línea restante

            switch (opcion) {
                case 1:
                    System.out.println("Dificultades disponibles:");
                    System.out.println("1. Normal");
                    System.out.println("2. Expert");
                    System.out.println("3. Genius");
                    System.out.print("Elige la dificultad: ");
                    int dificultad = entrada.nextInt();
                    entrada.nextLine(); // Consumir la línea restante
                    switch (dificultad) {
                        case 1:
                            setDificultad("Normal");
                            break;
                        case 2:
                            setDificultad("Expert");
                            break;
                        case 3:
                            setDificultad("Genius");
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }
                    System.out.println("Dificultad configurada.");
                    break;
                case 2:
                    System.out.println("Modos de Juego:");
                    System.out.println("1. Aleatorio");
                    System.out.println("2. Manual");
                    System.out.print("Elige el modo de juego: ");
                    int modo = entrada.nextInt();
                    entrada.nextLine(); 
                    switch (modo) {
                        case 1:
                            setModoJuego("Aleatorio");
                            break;
                        case 2:
                            setModoJuego("Manual");
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }
                    System.out.println("Modo de juego configurado.");
                    break;
                case 3:
                    System.out.println("Regresando al menú principal");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    public static void reportes(Scanner entrada) {
        System.out.println("Has entrado a Reportes.");
        System.out.println("1. Ver mis últimos juegos");
        System.out.println("2. Ranking de jugadores");
        System.out.println("3. Regresar al Menú Principal");
        System.out.print("Elige una opción: ");
        int op = entrada.nextInt();
        entrada.nextLine(); 
        switch(op) {
            case 1:
                System.out.println("Mis últimos juegos:");
                
                break;
            case 2:
                System.out.println("Ranking de Jugadores:");
                
                break;
            case 3:
                System.out.println("De regreso al menú principal.");
                return;
            default:
                System.out.println("Favor ingrese una opción existente.");
        }
    }

    public static void perfil(Scanner entrada) {
        System.out.println("Has entrado a tu Perfil.");
        while (true) {
            System.out.println("1. Ver mis datos");
            System.out.println("2. Cambiar contraseña");
            System.out.println("3. Eliminar mi cuenta");
            System.out.println("4. Regresar al Menú Principal");
            System.out.print("Elige una opción: ");
            int opcion = entrada.nextInt();
            entrada.nextLine(); // Consumir la línea restante

            switch (opcion) {
                case 1:
                    System.out.println("Tus datos:");
                    
                        System.out.println("Usuario: " + player[cantus][0] + ", Contraseña: " + player[cantus][1]);
                    
                    break;
                case 2:
                    System.out.print("Introduce tu nuevo password: ");
                    String nuevaPassword = entrada.nextLine();
                    if (nuevaPassword.length() >= 8) {
                        // Aquí actualiza la contraseña del usuario que ha iniciado sesión
                        player[Player.cantus][1] = nuevaPassword;
                        System.out.println("Contraseña actualizada.");
                    } else {
                        System.out.println("La contraseña debe tener al menos 8 caracteres.");
                    }
                    break;
                case 3:
                    System.out.print("¿Estás seguro de eliminar tu cuenta? (S/N): ");
                    char confirm = entrada.next().charAt(0);
                    entrada.nextLine(); // Consumir la línea restante
                    if (confirm == 'S' || confirm == 's') {
                        eliminarCuenta();
                        System.out.println("Cuenta eliminada.");
                        return;
                    } else {
                        System.out.println("Operación cancelada.");
                    }
                    break;
                case 4:
                    System.out.println("Regresando al Menú Principal...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void eliminarCuenta() {
        for (int i = 0; i < cantus; i++) {
            // Aquí eliminamos la cuenta del usuario que ha iniciado sesión
            if (player[i][0].equals(player[Player.cantus][0])) {
                for (int j = i; j < cantus - 1; j++) {
                    player[j] = player[j + 1];
                }
                player[cantus - 1] = null;
                cantus--;
                break;
            }
        }
    }
}
