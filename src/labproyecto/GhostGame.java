package labproyecto;

import java.util.Random;
import java.util.Scanner;

public class GhostGame {

    private static final int tablerotam = 6; 
    private static final char casilla = '-'; 
    private final char[][] tablero; 
    public String currentPlayer; 
    private String opponent; 
    private int[] ghostsbcap ; 
    private int[] ghostsmcap; 
    private int[] ghostsbcap2 ; 
    private int[] ghostsmcap2; 
    private final String dificultad = "Normal"; 
    private static final String modoJuego = "Aleatorio"; 
    private final Scanner entrada = new Scanner(System.in); 

    public GhostGame() {
        this.tablero = new char[tablerotam][tablerotam];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < tablerotam; i++) {
            for (int j = 0; j < tablerotam; j++) {
                tablero[i][j] = casilla;
            }
        }
    }

        public void printBoard() {
    System.out.println("\nEstado del Tablero:");
    System.out.print("   "); 
    for (int col = 0; col < tablerotam; col++) {
        System.out.print(col + " "); 
    }
    System.out.println();

    for (int row = 0; row < tablerotam; row++) {
        System.out.print(row + " |"); 
        for (int col = 0; col < tablerotam; col++) {
            System.out.print(tablero[row][col] + " "); 
        }
        System.out.println("|"); 
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
        entrada.nextLine(); 

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
            break;
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

    if ((currentPlayer.equals(player1) && (target == 'B' || target == 'M' || target == 'T')) ||
        (currentPlayer.equals(player2) && (target == 'G' || target == 'E' || target == 'P'))) {
        System.out.println("No puedes capturar tus propios fantasmas.");
        return false;
    }

    if (target == 'G') {
        System.out.println(currentPlayer + " capturó un fantasma bueno.");
        if (currentPlayer.equals(player1)) ghostsbcap[0]++;
        else ghostsbcap[1]++;
    } else if (target == 'E') {
        System.out.println(currentPlayer + " capturó un fantasma malo.");
        if (currentPlayer.equals(player1)) ghostsmcap[0]++;
        else ghostsmcap[1]++;
    }
    if (target == 'B') {
        System.out.println(currentPlayer + " capturó un fantasma bueno.");
        if (currentPlayer.equals(player2)) ghostsbcap[1]++;
        else ghostsbcap[0]++;
    } else if (target == 'M') {
        System.out.println(currentPlayer + " capturó un fantasma malo.");
        if (currentPlayer.equals(player2)) ghostsmcap[1]++;
        else ghostsmcap2[0]++;
    }


    tablero[endX][endY] = ghost;
    tablero[startX][startY] = casilla;

    
    if (checkVictory()) {
        return true; 
    }

    
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

    if (ghostsbcap[0] == 4) {
        System.out.println("¡" + player1 + " gana porque capturó todos los fantasmas buenos de " + player2 + "!");
        return true;
    } else if (ghostsbcap[1] == 4) {
        System.out.println("¡" + player2 + " gana porque capturó todos los fantasmas buenos de " + player1 + "!");
        return true;
    }

    if (ghostsmcap[0] == 4) {
        System.out.println("¡" + player2 + " gana porque " + player1 + " perdió todos sus fantasmas malos!");
        return true;
    } else if (ghostsmcap[1] == 4) {
        System.out.println("¡" + player1 + " gana porque " + player2 + " perdió todos sus fantasmas malos!");
        return true;
    }

    
    for (int i = 0; i < tablerotam; i++) {
        if (tablero[tablerotam - 1][i] == 'B') { 
            System.out.println("¡" + player1 + " gana llevando un fantasma bueno a la salida de " + player2 + "!");
            return true;
        } else if (tablero[0][i] == 'G') { 
            System.out.println("¡" + player2 + " gana llevando un fantasma bueno a la salida de " + player1 + "!");
            return true;
        }
    }

    return false; 
}



}
