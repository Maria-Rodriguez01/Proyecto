package labproyecto;

import java.util.Random;
import java.util.Scanner;

public class GhostGame {

    private static final int tablerotam = 6; // Tamaño del tablero
    private static final char casilla = '-'; // Casilla vacía
    private final char[][] tablero; // Tablero de juego
    public String currentPlayer; // Jugador actual
    private String opponent; // Jugador oponente
    private int[] ghostsbcap ; // Fantasmas buenos capturados
    private int[] ghostsmcap; // Fantasmas malos capturados
    private final String dificultad = "Normal"; // Dificultad (por defecto)
    private static final String modoJuego = "Aleatorio"; // Modo de juego (por defecto)
    private final Scanner entrada = new Scanner(System.in); // Entrada estándar

    // Constructor: Inicializar el tablero
    public GhostGame() {
        this.tablero = new char[tablerotam][tablerotam];
        initializeBoard();
    }

    // Inicializar el tablero con casillas vacías
    private void initializeBoard() {
        for (int i = 0; i < tablerotam; i++) {
            for (int j = 0; j < tablerotam; j++) {
                tablero[i][j] = casilla;
            }
        }
    }

        public void printBoard() {
    System.out.println("\nEstado del Tablero:");
    System.out.print("   "); // Espaciado inicial para las columnas
    for (int col = 0; col < tablerotam; col++) {
        System.out.print(col + " "); // Etiquetas de columnas
    }
    System.out.println();

    for (int row = 0; row < tablerotam; row++) {
        System.out.print(row + " |"); // Etiqueta de fila
        for (int col = 0; col < tablerotam; col++) {
            System.out.print(tablero[row][col] + " "); // Contenido de cada celda
        }
        System.out.println("|"); // Cierre de la fila
    }
    System.out.println();
}

    public String player1;
    public String player2;
    
    public void setPlayers(String player1, String player2) {
        Labproyecto.currentPlayer = player1;
        this.opponent = player2;
        System.out.println("Jugador 1: " + player1);
        System.out.println("Jugador 2: " + player2);
    }

   public void placeGhosts(String player, boolean isPlayerOne) {
    int buenos = 0, malos = 0, trampas = 0;

    // Determinar cantidad de fantasmas según la dificultad
    switch (dificultad) {
        case "Normal":
            buenos = 4;
            malos = 4;
            ghostsbcap=new int[4];
            ghostsmcap=new  int[4];
            break;
        case "Expert":
            buenos = 2;
            malos = 2;
            ghostsbcap=new int[2];
            ghostsmcap=new  int[2];
            break;
        case "Genius":
            buenos = 2;
            malos = 2;
            trampas = 2; 
            ghostsbcap=new int[2];
            ghostsmcap=new  int[2];
            break;
    }

    System.out.println("Colocando fantasmas para: " + player);
     if (modoJuego.equals("Manual")) {
        // Modo manual: permitir al jugador colocar fantasmas
        for (int i = 0; i < buenos; i++) {
            manualPlacement(isPlayerOne, 'B', "fantasma bueno");
        }
        for (int i = 0; i < malos; i++) {
            manualPlacement(isPlayerOne, 'M', "fantasma malo");
        }
        for (int i = 0; i < trampas; i++) {
            manualPlacement(isPlayerOne, 'T', "fantasma trampa");
        }
    } else {

    Random random = new Random();

    // Colocación aleatoria
    for (int i = 0; i < buenos; i++) {
        placeGhostRandom(isPlayerOne, 'B', random);
    }
    for (int i = 0; i < malos; i++) {
        placeGhostRandom(isPlayerOne, 'M', random);
    }
    for (int i = 0; i < trampas; i++) {
        placeGhostRandom(isPlayerOne, 'T', random);
    }
}
   }

    private void placeGhostRandom(boolean isPlayerOne, char ghostType, Random random) {
    while (true) {
        int row = isPlayerOne ? random.nextInt(2) : random.nextInt(2) + 4;
        int col = random.nextInt(tablerotam-2)+1;

        if (tablero[row][col] == casilla) {
            if (ghostType == 'B') {
                tablero[row][col] = isPlayerOne ? 'B' : 'G';
            } else if (ghostType == ('M')) { 
                tablero[row][col] = isPlayerOne ? 'M' : 'E';
            } else if (ghostType == 'T') { 
                tablero[row][col] = isPlayerOne ? 'T' : 'P';
            } else if(ghostType== 'G'){
                tablero[row][col] = isPlayerOne ? 'B' : 'G';
            } else if (ghostType== 'E'){
                tablero[row][col] = isPlayerOne ? 'M' : 'E';
            } else if(ghostType=='P'){
                tablero[row][col] = isPlayerOne ? 'T' : 'P';
            }
            break;
        }
    }
}
    private void manualPlacement(boolean isPlayerOne, char ghostType, String ghostName) {
    while (true) {
        System.out.print("Ingresa la fila y columna para el " + ghostName + " (ejemplo: 1 2): ");
        int row = entrada.nextInt();
        int col = entrada.nextInt();
        entrada.nextLine(); // Consumir la nueva línea

        if (isValidPlacement(row, col, isPlayerOne)) {
            // Asignar el tipo de fantasma
            if (ghostType == 'B') {
                tablero[row][col] = isPlayerOne ? 'B' : 'G';
            } else if (ghostType == ('M')) { 
                tablero[row][col] = isPlayerOne ? 'M' : 'E';
            } else if (ghostType == 'T') { 
                tablero[row][col] = isPlayerOne ? 'T' : 'P';
            } else if(ghostType== 'G'){
                tablero[row][col] = isPlayerOne ? 'B' : 'G';
            } else if (ghostType== 'E'){
                tablero[row][col] = isPlayerOne ? 'M' : 'E';
            } else if(ghostType=='P'){
                tablero[row][col] = isPlayerOne ? 'T' : 'P';
            }
            break;// Salir del bucle después de colocar el fantasma
        } else {
            System.out.println("Posición no válida. Intenta de nuevo.");
        }
    }
}


 

    private boolean isValidPlacement(int row, int col, boolean isPlayerOne) {
        if (row < 0 || row >= tablerotam || col < 0 || col >= tablerotam) {
            return false;
        }
        if (tablero[row][col] != casilla) {
            return false;
        }

        return isPlayerOne ? row < 2 : row >= 4;
    }

    private boolean isValidPlacement2(int row, int col, boolean isPlayerOne) {
        if (row < 0 || row >= tablerotam || col < 0 || col >= tablerotam) {
            return false;
        }
        if (tablero[row][col] != casilla) {
            return false;
        }
        return isPlayerOne ? row < 2 : row >= 4;
    }

  public boolean moveGhost(int startX, int startY, int endX, int endY) {
    if (!isValidMove(startX, startY, endX, endY)) {
        System.out.println("Movimiento no válido.");
        return false;
    }

    char ghost = tablero[startX][startY];
    char target = tablero[endX][endY];
    char target2 = tablero[endX][endY];

    if (ghost == casilla) {
        System.out.println("No hay ningún fantasma en la casilla seleccionada.");
        return false;
    }

    // Verificar si el destino tiene un fantasma propio
    if ((currentPlayer.equals(player1) && (target == 'B' || target == 'M' || target == 'T')) ||
        (currentPlayer.equals(player2) && (target == 'G' || target == 'E' || target == 'P'))) {
        System.out.println("No puedes capturar tus propios fantasmas.");
        return false;
    }

    // Capturar fantasmas del oponente
    if (target == 'G') {
        System.out.println(currentPlayer + " capturó un fantasma bueno.");
        if (currentPlayer.equals(player1)) ghostsbcap[1]++;
        else ghostsbcap[0]++;
    } else if (target == 'E') {
        System.out.println(currentPlayer + " capturó un fantasma malo.");
        if (currentPlayer.equals(player1)) ghostsmcap[1]++;
        else ghostsmcap[0]++;
    }
    if (target == 'B') {
        System.out.println(currentPlayer + " capturó un fantasma bueno.");
        if (currentPlayer.equals(player2)) ghostsbcap[1]++;
        else ghostsbcap[0]++;
    } else if (target == 'M') {
        System.out.println(currentPlayer + " capturó un fantasma malo.");
        if (currentPlayer.equals(player2)) ghostsmcap[1]++;
        else ghostsmcap[0]++;
    }


    // Mover el fantasma actual a la nueva posición
    tablero[endX][endY] = ghost;
    tablero[startX][startY] = casilla;

    // Verificar victoria después del movimiento
    if (checkVictory()) {
        return true; // Detener el juego si hay un ganador
    }

    // Cambiar turno al oponente
    switchTurn();
    return true;
}




    private boolean isValidMove(int startX, int startY, int endX, int endY) {
        if (startX < 0 || startX >= tablerotam || startY < 0 || startY >= tablerotam
                || endX < 0 || endX >= tablerotam || endY < 0 || endY >= tablerotam) {
            return false;
        }
        return Math.abs(startX - endX) + Math.abs(startY - endY) == 1; // Movimiento adyacente
    }

    private void switchTurn() {
        String temp = currentPlayer;
        currentPlayer = opponent;
        opponent = temp;
        System.out.println("Es el turno de: " + currentPlayer);
    }

    public String getTurn() {
        return currentPlayer;
    }

   
    public boolean checkVictory() {

    // Caso 1: Capturar todos los fantasmas buenos del oponente
    if (ghostsbcap[0] == 4) {
        System.out.println("¡" + player1 + " gana porque capturó todos los fantasmas buenos de " + player2 + "!");
        return true;
    } else if (ghostsbcap[1] == 4) {
        System.out.println("¡" + player2 + " gana porque capturó todos los fantasmas buenos de " + player1 + "!");
        return true;
    }

    // Caso 2: Perder todos los fantasmas malos propios
    if (ghostsmcap[0] == 4) {
        System.out.println("¡" + player2 + " gana porque " + player1 + " perdió todos sus fantasmas malos!");
        return true;
    } else if (ghostsmcap[1] == 4) {
        System.out.println("¡" + player1 + " gana porque " + player2 + " perdió todos sus fantasmas malos!");
        return true;
    }

    // Caso 3: Llevar un fantasma bueno a la salida del oponente
    for (int i = 0; i < tablerotam; i++) {
        if (tablero[tablerotam - 1][i] == 'B') { // Fantasma bueno del Jugador 1 en la salida de Jugador 2
            System.out.println("¡" + player1 + " gana llevando un fantasma bueno a la salida de " + player2 + "!");
            return true;
        } else if (tablero[0][i] == 'G') { // Fantasma bueno del Jugador 2 en la salida de Jugador 1
            System.out.println("¡" + player2 + " gana llevando un fantasma bueno a la salida de " + player1 + "!");
            return true;
        }
    }

    return false; 
}



}
