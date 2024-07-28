#include "libreriasFINAL.h"
main()
{
    srand(time(NULL));
    hideCursor();
    textcolor(51);
    system("cls");
    for (int i = 0; i < 3; i++)
    {
        Reloj(65, 5, i);
        Sleep(5);
    }
    int ps = 1;
    while (ps != 5)
    {
        textcolor(51);
        system("cls");
        ODISEACLIMATICA(24, 4);
        ps = Seleccionar("NUEVA PARTIDA", "CARGAR PARTIDA", "INSTRUCCIONES", "NO DISPONIBLE", "SALIR");
        switch (ps)
        {
        case 1:
        {
            string arriba(1, char(30));
            string abajo(1, char(31));
            textcolor(51);
            Figura(60, 70, 12, 37, 0);
            box(52, 12, 47, 4, 63, 17);
            print("SELECCIONE LA DIFICULTAD DEL JUEGO", 31, 59, 14);
            box(64, 18, 24, 8, 63, 85);
            print(arriba, 90, 77, 20);
            print(abajo, 90, 77, 24);
            print("1", 95, 77, 22);
            textcolor(63);
            gotoxy(60, 28);
            cout << "(w): " << char(30) << "     (ESPACIO): " << char(16) << "     (s): " << char(31);
            int dificultad = 1;
            char tcl = '*';
            while (tcl != ' ')
            {
                dificultad = Opcion(tcl, dificultad, 1, 2, 2);
                box(64, 18, 24, 8, 63, 85);
                string sdificultad = to_string(dificultad);
                print(arriba, 90, 77, 20);
                print(abajo, 90, 77, 24);
                print(sdificultad, 95, ((155 - sdificultad.length()) / 2), 22);
            }
            box(52, 12, 47, 4, 63, 17);
            print("SELECCIONE LA CANTIDAD DE JUGADORES", 31, 59, 14);
            box(64, 18, 24, 8, 63, 85);
            print(arriba, 90, 77, 20);
            print(abajo, 90, 77, 24);
            print("1", 95, 77, 22);
            int players = 1;
            tcl = '*';
            while (tcl != ' ')
            {
                players = Opcion(tcl, players, 1, 100, 2);
                box(64, 18, 24, 8, 63, 85);
                string splayers = to_string(players);
                print(arriba, 90, 77, 20);
                print(abajo, 90, 77, 24);
                print(splayers, 95, ((155 - splayers.length()) / 2), 22);
            }
            Tablero *inicio;
            crearTablero(inicio, dificultad);
            Jugador *jugadores = NULL;

            string nombrejugador;
            for (int i = 1; i <= players; i++)
            {
                box(49, 12, 56, 4, 63, 17);
                string numerojugador = to_string(i);
                string indiquejugador = "INDIQUE EL NOMBRE DEL JUGADOR " + numerojugador;
                print(indiquejugador, 31, ((155 - indiquejugador.length()) / 2), 14);
                box(49, 18, 56, 10, 63, 85);
                gotoxy(73, 23);
                textcolor(95);
                showCursor();
                cin >> nombrejugador;
                hideCursor();
                insertarUltimo(jugadores, nombrejugador, inicio);
            }
            odisea(jugadores, dificultad, inicio, players, 0);
            break;
        }
        case 2:
        {
            textcolor(51);
            Figura(60, 70, 12, 37, 0);
            box(49, 12, 56, 4, 63, 17);
            print("INTRODUZCA LA DIRECCI" + string(1, char(224)) + "N DEL ARCHIVO QUE DESEA CARGAR", 31, 52, 14);
            box(49, 18, 56, 10, 63, 85);
            print("C:\\Usuarios\\NombreUsuario\\Documentos\\MiArchivo.txt", 87, 53, 23);
            print("PRESIONA ESPACIO PARA EMPEZAR A ESCRIBIR", 87, 58, 21);
            gotoxy(52, 23);
            showCursor();
            getch();
            hideCursor();
            box(49, 18, 56, 10, 63, 85);
            gotoxy(52, 23);
            textcolor(95);
            showCursor();
            string direccion;
            cin >> direccion;
            hideCursor();
            ifstream archivo(direccion);
            if (archivo.good())
            {
                int cant = 0;
                Jugador *jugadores = new Jugador;
                int dificultad;
                CargarPartida(direccion, cant, jugadores, dificultad);
                Tablero *inicio;
                crearTablero(inicio, dificultad);
                Jugador *auxiliar4 = jugadores;
                for (int i = 1; i <= cant; i++)
                {
                    Tablero *auxiliar5 = inicio;
                    for (int j = 0; j < auxiliar4->posicion; j++)
                    {
                        auxiliar5 = auxiliar5->sig;
                    }
                    auxiliar4->turn = auxiliar5;
                    auxiliar4 = auxiliar4->prox;
                }
                odisea(jugadores, dificultad, inicio, cant, 1);
            }
            else
            {
                box(49, 18, 56, 10, 63, 85);
                print("ERROR", 95, 76, 22);
                print("LA DIRECCI" + string(1, char(224)) + "N SUMINISTRADA NO ES V" + string(1, char(181)) + "LIDA", 95, 59, 24);
                box(49, 29, 56, 4, 63, 102);
                print("PRESIONA CUALQUIER TECLA PARA VOLVER AL MEN" + string(1, char(233)) + " ANTERIOR", 111, 51, 31);
                getch();
            }
            break;
        }
        case 3:
            instrucciones();
            break;
        case 4:

            break;
        }
    }
}