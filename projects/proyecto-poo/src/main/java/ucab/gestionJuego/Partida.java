package ucab.gestionJuego;

import javafx.event.ActionEvent;
import ucab.SceneController;
import ucab.clasesCarta.*;
import ucab.gestionJugador.Jugador;
import ucab.gestionJugador.ListaJugadores;
import ucab.clasesUtilidad.ArchivoJson;
import java.io.IOException;
import java.util.LinkedList;

public class Partida {

    private static ListaJugadores jugadores;
    private static ListaTurnos turnos;
    private static ListaCartas mazoGeneral;
    private static ListaCartas mazoDescarte;
    private static int indiceTurno;
    private static boolean sentidoRegular;
    private static boolean primeraVuelta;
    private static Boolean invalido;
    private static Boolean saltado;

    public Partida(ListaJugadores jugadores, ListaTurnos turnos, ListaCartas mazoGeneral, ListaCartas mazoDescarte,
            int indiceTurno, boolean sentidoRegular, boolean primeraVuelta) {
        Partida.jugadores = jugadores;
        Partida.turnos = turnos;
        Partida.mazoGeneral = mazoGeneral;
        Partida.mazoDescarte = mazoDescarte;
        Partida.indiceTurno = indiceTurno;
        Partida.sentidoRegular = sentidoRegular;
        Partida.primeraVuelta = primeraVuelta;
        Partida.invalido = false;
        Partida.saltado = false;
    }

    public void iniciar(ActionEvent event) throws IOException {
        Jugador maquina = jugadores.buscar("#00");
        Jugador jugador = jugadores.buscar("#001");
        SceneController.escenaPartida(event, jugador.getNombre(), maquina.getMazo().size(),
                mazoDescarte.get(0).getIdCarta(), jugador.getMazo(),
                turnos.getListaTurnos().get(0));
    }

    public static int gestorTurnos(int indiceTurno, ListaCartas mazoDescarte, ListaTurnos turnos,
            boolean sentidoRegular) {
        if (sentidoRegular) {
            indiceTurno++;
            if (indiceTurno >= turnos.size()) {
                indiceTurno = 0;
            }
        } else {
            indiceTurno--;
            if (indiceTurno < 0) {
                indiceTurno = turnos.size() - 1;
            }
        }
        return indiceTurno;
    }

    public static void actualizarJuego(String inputUsuario) throws IOException, InterruptedException {

        Turno turno = null;
        String input = "";
        Jugador maquina = jugadores.buscar("#00");
        Jugador jugador = jugadores.buscar("#001");

        while (primeraVuelta) {
            Carta primeraCarta = mazoDescarte.get(0);
            if (primeraCarta instanceof TomaDos) {
                PartidaUtilidades.tomarDosCartas(
                        turnos.getListaTurnos().get(1).getReferenciaJugador(),
                        mazoGeneral, mazoDescarte, 0);
                primeraVuelta = false;
            } else if (primeraCarta instanceof PierdeTurno) {
                indiceTurno = PartidaUtilidades.saltarTurno(sentidoRegular, indiceTurno, turnos.size());
                primeraVuelta = false;
            } else if (primeraCarta instanceof CambiarSentido) {
                sentidoRegular = PartidaUtilidades.accionReversa(sentidoRegular);
                indiceTurno = PartidaUtilidades.saltarTurno(sentidoRegular, indiceTurno, turnos.size());
                primeraVuelta = false;
            } else if (primeraCarta instanceof TomaCuatro || primeraCarta instanceof CambiarColor) {
                mazoGeneral.agregarCarta(mazoDescarte.get(0));
                mazoDescarte.remove(0);
                mazoGeneral.shuffle();
                ListaCartas.eliminarPrimeraCarta(mazoGeneral, mazoDescarte);
                SceneController.actualizarEscena(mazoDescarte.get(0).getIdCarta(), jugador.getMazo(),
                        maquina.getMazo().size(), maquina.getNombre(), indiceTurno);
            } else {
                primeraVuelta = false;
                break;
            }
        }

        if (mazoGeneral.size() < 10)
            PartidaUtilidades.vaciarDescartes(mazoDescarte, mazoGeneral);
        turno = turnos.getListaTurnos().get(indiceTurno);
        if (!PartidaUtilidades.cartasJugables(turno.getReferenciaJugador().getMazo(), mazoDescarte.get(0))) {
            if (!turno.getReferenciaJugador().getId().equals("#00"))
                SceneController.sinCartas();
            turno.getReferenciaJugador().getMazo().agregarCarta(mazoGeneral.get(0));
            mazoGeneral.remove(0);
            indiceTurno = gestorTurnos(indiceTurno, mazoDescarte, turnos, sentidoRegular);
        } else {
            if (!turno.getReferenciaJugador().getId().equals("#00")) {
                if (jugador.getMazo().size() > 7) {
                    input = SceneController.masDeSieteCartas(jugador.getMazo());
                } else {
                    input = inputUsuario;
                }
            } else {
                input = IoPartida.seleccionarCartaMaquina(input, turno, mazoDescarte);
            }
            if (!PartidaUtilidades.cartaExiste(input, turno.getReferenciaJugador().getMazo())) {
                SceneController.cartaInvalida();
                invalido = true;
            } else {
                if (!PartidaUtilidades.validarCartaColor(input, mazoDescarte.get(0))
                        && !PartidaUtilidades.validarCartaNumero(input, mazoDescarte.get(0))) {
                    SceneController.cartaInvalida();
                    invalido = true;
                } else {
                    if (turno.getReferenciaJugador().getMazo().size() == 2) {
                        if (!turno.getReferenciaJugador().getId().equals("#00")) {
                            SceneController.cantarUno(0);
                        } else {
                            SceneController.cantarUno(1);
                        }
                    }
                    PartidaUtilidades.lanzarCarta(turno.getReferenciaJugador().getMazo(), mazoDescarte,
                            PartidaUtilidades.regresarCartaPosicion(input,
                                    turno.getReferenciaJugador().getMazo()));

                    if (mazoDescarte.get(0).getIdCarta().substring(1).equals("T2")) {
                        int cantidadDeCartasLanzadas = 0;
                        if (sentidoRegular) {
                            if (indiceTurno == turnos.size() - 1) {
                                cantidadDeCartasLanzadas = PartidaUtilidades.tomarDosCartas(
                                        turnos.getListaTurnos().get(0).getReferenciaJugador(),
                                        mazoGeneral, mazoDescarte,
                                        cantidadDeCartasLanzadas);
                            } else {
                                cantidadDeCartasLanzadas = PartidaUtilidades.tomarDosCartas(
                                        turnos.getListaTurnos().get(indiceTurno + 1).getReferenciaJugador(),
                                        mazoGeneral, mazoDescarte,
                                        cantidadDeCartasLanzadas);
                            }
                        } else {
                            if (indiceTurno == 0) {
                                cantidadDeCartasLanzadas = PartidaUtilidades.tomarDosCartas(
                                        turnos.getListaTurnos().get(turnos.size() - 1).getReferenciaJugador(),
                                        mazoGeneral, mazoDescarte,
                                        cantidadDeCartasLanzadas);
                            } else {
                                cantidadDeCartasLanzadas = PartidaUtilidades.tomarDosCartas(
                                        turnos.getListaTurnos().get(indiceTurno - 1).getReferenciaJugador(),
                                        mazoGeneral, mazoDescarte,
                                        cantidadDeCartasLanzadas);
                            }
                        }
                        indiceTurno = PartidaUtilidades.saltarTurno(sentidoRegular, indiceTurno, turnos.size());
                        saltado = true;
                    }

                    if (mazoDescarte.get(0).getIdCarta().substring(1).equals("R")) {
                        sentidoRegular = PartidaUtilidades.accionReversa(sentidoRegular);
                        indiceTurno = PartidaUtilidades.saltarTurno(sentidoRegular, indiceTurno, turnos.size());
                        saltado = true;
                    }

                    if (mazoDescarte.get(0).getIdCarta().substring(1).equals("S")) {
                        indiceTurno = PartidaUtilidades.saltarTurno(sentidoRegular, indiceTurno, turnos.size());
                        saltado = true;
                    }

                    if (mazoDescarte.get(0).getIdCarta().equals("CC")) {
                        PartidaUtilidades.cambiarColor(turno, turnos, indiceTurno, mazoDescarte);
                        if (sentidoRegular) {
                            indiceTurno++;
                            if (indiceTurno >= turnos.size()) {
                                indiceTurno = 0;
                            }
                        } else {
                            indiceTurno--;
                            if (indiceTurno < 0) {
                                indiceTurno = turnos.size() - 1;
                            }
                        }
                    }

                    if (mazoDescarte.get(0).getIdCarta().equals("CT4")) {
                        int cantidadDeCartasLanzadas = 0;
                        if (sentidoRegular) {
                            if (indiceTurno == turnos.size() - 1) {
                                cantidadDeCartasLanzadas = PartidaUtilidades.tomarCuatroCartas(
                                        turnos.getListaTurnos().get(0).getReferenciaJugador(),
                                        mazoGeneral, mazoDescarte,
                                        cantidadDeCartasLanzadas, turnos, turno);
                            } else {
                                cantidadDeCartasLanzadas = PartidaUtilidades.tomarCuatroCartas(
                                        turnos.getListaTurnos().get(indiceTurno + 1).getReferenciaJugador(),
                                        mazoGeneral, mazoDescarte,
                                        cantidadDeCartasLanzadas, turnos, turno);
                            }
                        } else {
                            if (indiceTurno == 0) {
                                cantidadDeCartasLanzadas = PartidaUtilidades.tomarCuatroCartas(
                                        turnos.getListaTurnos().get(turnos.size() - 1).getReferenciaJugador(),
                                        mazoGeneral, mazoDescarte,
                                        cantidadDeCartasLanzadas, turnos, turno);
                            } else {
                                cantidadDeCartasLanzadas = PartidaUtilidades.tomarCuatroCartas(
                                        turnos.getListaTurnos().get(indiceTurno - 1).getReferenciaJugador(),
                                        mazoGeneral, mazoDescarte,
                                        cantidadDeCartasLanzadas, turnos, turno);
                            }
                        }
                        indiceTurno = PartidaUtilidades.saltarTurno(sentidoRegular, indiceTurno, turnos.size());
                        saltado = true;
                    }
                    if (mazoDescarte.getListaCartas().get(0) instanceof CartaEnumerada) {
                        indiceTurno = gestorTurnos(indiceTurno, mazoDescarte, turnos,
                                sentidoRegular);
                    }
                }
            }
        }

        if (PartidaUtilidades.hayGanador(jugadores)) {
            ArchivoJson.guardar(jugadores, turnos, mazoGeneral, mazoDescarte, indiceTurno, sentidoRegular);
            int puntaje = 0;
            if (jugador.getMazo().size() == 0) {
                puntaje = PartidaUtilidades.puntaje(maquina.getMazo());
                LinkedList<String> estadisticas = new LinkedList<>();
                estadisticas.add(jugador.getNombre() + ",puntaje:" + puntaje);
            } else {
                puntaje = PartidaUtilidades.puntaje(jugador.getMazo());
            }
            SceneController.mostrarGanador(turno.getReferenciaJugador().getNombre(), puntaje);
            return;
        }
        if (!turno.getReferenciaJugador().getId().equals("#00") && !saltado && !invalido) {
            ArchivoJson.guardar(jugadores, turnos, mazoGeneral, mazoDescarte, indiceTurno, sentidoRegular);
        } else {
            saltado = false;
            invalido = false;
        }
        ArchivoJson.guardar(jugadores, turnos, mazoGeneral, mazoDescarte, indiceTurno, sentidoRegular);
        SceneController.actualizarEscena(mazoDescarte.get(0).getIdCarta(),
                jugador.getMazo(), maquina.getMazo().size(), turno.getReferenciaJugador().getNombre(), indiceTurno);
    }
}