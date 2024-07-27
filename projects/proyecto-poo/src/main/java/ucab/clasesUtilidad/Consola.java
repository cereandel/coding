package ucab.clasesUtilidad;

import java.io.IOException;
import java.util.Scanner;

/**
 * La clase Consola proporciona métodos para interactuar con la consola y
 * realizar operaciones
 * relacionadas con la visualización y entrada de datos.
 * Incluye métodos para limpiar la pantalla, agregar retrasos, mostrar una
 * máquina de escribir,
 * esperar a que el usuario presione ENTER y más.
 *
 * Nota: Los métodos de esta clase están diseñados para fines específicos y
 * pueden requerir
 * ajustes o adaptaciones según el contexto de uso.
 *
 * @author Tu Nombre
 * @version 1.0
 */
public final class Consola {
  static Scanner scanner = new Scanner(System.in);

  /**
   * Limpia la pantalla de la consola.
   */
  public static void clrscr() {
    System.out.print("clear");
    try {
      if (System.getProperty("os.name").contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        System.out.print("\033[H\033[2J");
        System.out.flush();
      }
    } catch (IOException | InterruptedException ex) {
      // Manejo de excepciones (opcional)
    }
  }

  /**
   * Agrega un retraso en milisegundos.
   *
   * @param n El tiempo de retraso en milisegundos.
   */
  public static void delay(int n) {
    try {
      Thread.sleep(n);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Muestra una animación de carga con caracteres de bloque (█).
   *
   * @param n La cantidad de bloques en la animación.
   */
  public static void loading(int n) {
    System.out.print("Cargando ");
    for (int i = 0; i < n; i++) {
      System.out.print(' ');
      delay(100);
    }
    System.out.println(" ");
  }

  /**
   * Muestra un mensaje como si estuviera siendo escrito por una máquina de
   * escribir.
   *
   * @param mensaje El mensaje a mostrar.
   */
  public static void mostrarMaquinaEscribir(String mensaje) {
    for (char letra : mensaje.toCharArray()) {
      System.out.print(letra);
      delay(10);
    }
    System.out.println("");
  }

  /**
   * Espera a que el usuario presione ENTER y luego limpia la pantalla.
   *
   * @param mensaje El mensaje que se muestra antes de esperar.
   */
  public static void enter(String mensaje) {
    System.out.println("Pulse ENTER para " + mensaje);
    System.out.print(">> ");
    scanner.nextLine();
    Consola.clrscr();
  }
}
