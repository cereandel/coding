package ucab.clasesUtilidad;

/**
 * La clase Ascii proporciona métodos estáticos para mostrar presentaciones y
 * menús en formato de arte ASCII.
 * Estos métodos imprimen representaciones visuales en la consola.
 *
 *
 * @version 1.0
 */
public final class Ascii {
        /**
         * Muestra una presentación en formato de arte ASCII.
         * La presentación puede contener texto, gráficos o cualquier otro elemento
         * visual.
         */
        public static final void presentacion() {
                System.out.println("___  ___  ________   ________     ");
                Consola.delay(250);
                System.out.println("|\\  \\|\\  \\|\\   ___  \\|\\   __  \\    ");
                Consola.delay(250);
                System.out.println("\\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\|\\  \\   ");
                Consola.delay(250);
                System.out.println(" \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\  ");
                Consola.delay(250);
                System.out.println("  \\ \\  \\\\\\  \\ \\  \\\\ \\  \\ \\  \\\\\\  \\ ");
                Consola.delay(250);
                System.out.println("   \\ \\_______\\ \\__\\\\ \\__\\ \\_______\\");
                Consola.delay(250);
                System.out.println("    \\|_______|\\|__| \\|__|\\|_______|");
                Consola.delay(250);
                System.out.println("");
        }

        /**
         * Muestra un menú en formato de arte ASCII.
         * El menú puede contener opciones, títulos o cualquier otro elemento visual.
         */
        public static final void menu() {
                System.out.println(" _____                       _____ ");
                System.out.println("( ___ )                     ( ___ )");
                System.out.println(" |   |~~~~~~~~~~~~~~~~~~~~~~~|   | ");
                System.out.println(" |   |  _    _ _   _  ____   |   | ");
                System.out.println(" |   | | |  | | \\ | |/ __ \\  |   | ");
                System.out.println(" |   | | |  | |  \\| | |  | | |   | ");
                System.out.println(" |   | | |  | | . ` | |  | | |   | ");
                System.out.println(" |   | | |__| | |\\  | |__| | |   | ");
                System.out.println(" |   |  \\____/|_| \\_|\\____/  |   | ");
                System.out.println(" |___|~~~~~~~~~~~~~~~~~~~~~~~|___| ");
                System.out.println("(_____)                     (_____)");
        }

        /**
         * Muestra las instrucciones del juego UNO en formato de arte ASCII.
         * Proporciona detalles sobre la preparación, las reglas del juego y las
         * opciones del menú principal.
         */

        public static final void Mostrarinstrucciones() {
                String Instruccion = "¡Bienvenido al juego UNO!\n";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = "En esta ocasión, te enfrentarás al computador en una emocionante partida.";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = "El objetivo es deshacerse de todas tus cartas antes que tu oponente.";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = "\nPreparación:";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = "Se te solicitará tu nombre. Se barajarán 108 cartas: 75 cartas normales: 25 azules (B), 25 rojas (R), 25 amarillas (Y) y 25 verdes (G).";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = "33 cartas comodín: 4 'Cambiar color' (CC), 4 'Toma 4' (T4) y 25 'Toma 2' (CT2 de cada color). Recibirás 7 cartas. La carta superior del mazo se colocará boca arriba, iniciando la pila de descarte.";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = "\nComienza el juego:\n";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = "En tu turno, puedes:\n Lanzar una carta: Del mismo color que la carta superior del descarte. Del mismo número que la carta superior del descarte (sin importar el color).";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = " Una carta de acción (Reversa (R), Saltar (S), Toma 2 (CT2), Toma 4 (T4) o Cambiar color (CC)). Tomar una carta del mazo si no tienes cartas jugables.";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = " Si lanzas una carta de acción:\n Reversa (R): Invierte el orden del juego. Saltar (S): El siguiente jugador pierde su turno. Toma 2 (CT2): El siguiente jugador toma 2 cartas y pierde su turno.";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = " Toma 4 (T4): El siguiente jugador toma 4 cartas y pierde su turno. Tú eliges el nuevo color. Cambiar color (CC): Elige el nuevo color para continuar el juego. Cuando te quede una carta, debes decir 'UNO'. Si no lo haces, robarás una carta. Gana el primer jugador en deshacerse de todas sus cartas.";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = "\nReglas adicionales:";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = "Si el mazo se agota, se baraja la pila de descarte (excepto la carta superior) y se utiliza como nuevo mazo.";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = "El computador seguirá las mismas reglas que tú. Puedes guardar y continuar partidas anteriores.";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = "\nMenú principal:";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = "Iniciar partida nueva: Ingresa tu nombre y comienza una nueva partida.";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = "Jugar partida anterior: Continúa una partida guardada previamente.";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Instruccion = "Salir: Finaliza el juego.";
                Consola.mostrarMaquinaEscribir(Instruccion);
                Consola.delay(500);
                System.out.println("");
                Consola.enter("regresar al menú principal");
        }
}