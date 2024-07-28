#include <iostream>
#include <cstdlib>
#include <windows.h>
#include <unistd.h>
#include <conio.h>
#include <time.h>
#include <stdio.h>
#include <fstream>
#include <cstring>
#include <string>
#include <sstream>
#include <random>
#include <cctype>

using namespace std;

HANDLE hConsole = GetStdHandle(STD_OUTPUT_HANDLE);

struct Efecto
{
    int turnos;
    Efecto *prox = NULL;
};
struct Tablero
{
    int casilla;
    bool recurso = false;
    int obstaculo = 0;
    int reto = 0;
    Tablero *ant;
    Tablero *sig;
};
struct Jugador
{
    string nombre;
    int posicion = 1;
    int efectos = 0;
    int recursos = 3;
    int numero;
    int dado = 2;
    bool turno = false;
    Efecto *efecto = new Efecto;
    Jugador *anterior = NULL;
    Jugador *prox = NULL;
    Tablero *turn;
};
int Aleatorio(int min, int max)
{
    static default_random_engine generador(time(nullptr));
    uniform_int_distribution<int> distribucion(min, max);
    return distribucion(generador);
}
void hideCursor()
{
    HANDLE consoleHandle = GetStdHandle(STD_OUTPUT_HANDLE);
    CONSOLE_CURSOR_INFO info;
    info.dwSize = 100;
    info.bVisible = FALSE;
    SetConsoleCursorInfo(consoleHandle, &info);
}
void showCursor()
{
    HANDLE consoleHandle = GetStdHandle(STD_OUTPUT_HANDLE);
    CONSOLE_CURSOR_INFO info;
    info.dwSize = 100;
    info.bVisible = TRUE;
    SetConsoleCursorInfo(consoleHandle, &info);
}
void gotoxy(int x, int y)
{
    COORD coord;
    coord.X = x;
    coord.Y = y;
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord);
}
void textcolor(int n)
{
    SetConsoleTextAttribute(hConsole, n);
}
void linea(int inicio, int largo, int y)
{
    for (int i = inicio; i < inicio + largo; i++)
    {
        gotoxy(i, y);
        cout << char(219);
    }
}
void box(int x, int y, int ancho, int largo, int borde, int relleno)
{
    textcolor(borde);
    gotoxy(x, y);
    cout << char(220);
    gotoxy(x + ancho + 1, y);
    cout << char(220);
    gotoxy(x, y + largo);
    cout << char(223);
    gotoxy(x + ancho + 1, y + largo);
    cout << char(223);
    x++;
    for (int i = x; i < x + ancho; i++)
    {
        gotoxy(i, y);
        cout << char(220);
        gotoxy(i, y + largo);
        cout << char(223);
    }
    ancho = x + ancho;
    largo = y + largo;
    y++;
    x--;
    for (int i = y; i < largo; i++)
    {
        gotoxy(x, i);
        cout << char(219);
        gotoxy(ancho, i);
        cout << char(219);
    }
    x++;
    ancho -= x;
    largo -= y;
    textcolor(relleno);
    for (int i = x; i < x + ancho; i++)
    {
        for (int j = y; j < y + largo; j++)
        {
            gotoxy(i, j);
            cout << char(219);
        }
    }
}
void print(string texto, int color, int x, int y)
{
    textcolor(color);
    gotoxy(x, y);
    cout << texto;
}
void printwriter(string texto, int color, int x, int y)
{
    textcolor(color);
    gotoxy(x, y);
    int tamano = texto.length();
    for (int i = 0; i < tamano; i++)
    {
        Sleep(20);
        cout << texto[i];
    }
}
void Mensaje(string linea1, string linea2, string linea3, string linea4, string linea5, string linea6, int y, int l, int centrado)
{
    if (centrado == 1)
    {
        if (l > 4)
            printwriter(linea1, 111, ((235 - linea1.length()) / 2), y + 1);
        if (l > 3)
            printwriter(linea2, 111, ((235 - linea2.length()) / 2), y + 2);
        if (l > 1)
            printwriter(linea3, 111, ((235 - linea3.length()) / 2), y + 3);
        if (l > 1)
            printwriter(linea4, 111, ((235 - linea4.length()) / 2), y + 4);
        if (l > 2)
            printwriter(linea5, 111, ((235 - linea5.length()) / 2), y + 5);
        if (l > 4)
            printwriter(linea6, 111, ((235 - linea6.length()) / 2), y + 6);
    }
    else
    {
        if (l > 4)
            printwriter(linea1, 95, ((163 - linea1.length()) / 2), y + 1);
        if (l > 3)
            printwriter(linea2, 95, ((163 - linea2.length()) / 2), y + 2);
        if (l > 1)
            printwriter(linea3, 95, ((163 - linea3.length()) / 2), y + 3);
        if (l > 1)
            printwriter(linea4, 95, ((163 - linea4.length()) / 2), y + 4);
        if (l > 2)
            printwriter(linea5, 95, ((163 - linea5.length()) / 2), y + 5);
        if (l > 4)
            printwriter(linea6, 95, ((163 - linea6.length()) / 2), y + 6);
    }
}
void Figura(int inicio, int largo, int yi, int yf, int estilo)
{
    for (yi = yi; yi <= yf; yi++)
    {
        linea(inicio, largo, yi);
        if (estilo == 1)
        {
            inicio += 2;
            largo -= 4;
        }
        if (estilo == 2)
        {
            inicio -= 2;
            largo += 4;
        }
    }
}
void Nivel(int i, int inicio, int fin, int x, int y)
{
    int p = 14;
    int cont = 1;
    for (inicio = inicio; inicio >= fin; inicio--)
    {
        if (i >= inicio)
        {
            linea(x + p, 2, y);
        }
        if ((inicio % 2) != 0)
            p -= 2 * cont;
        else
            p += 2 * cont;
        cont++;
    }
}
void Montana(int i, int inicio, int fin, int x, int y)
{
    int p = 14;
    int cont = 1;
    for (fin = fin; fin <= inicio; fin++)
    {
        if (i >= fin)
        {
            linea(x + p, 2, y);
        }
        if ((fin % 2) != 0)
            p -= 2 * cont;
        else
            p += 2 * cont;
        cont++;
    }
}
void Reloj(int x, int y, int n)
{
    hideCursor();
    textcolor(0);
    Figura(x, 30, y, y + 2, 0);
    Figura(x + 2, 26, y + 3, y + 6, 0);
    Figura(x + 4, 22, y + 7, y + 11, 1);
    Figura(x + 10, 10, y + 12, y + 15, 2);
    Figura(x + 2, 26, y + 16, y + 19, 0);
    Figura(x, 30, y + 20, y + 22, 0);
    textcolor(102);
    linea(x + 2, 26, y + 1);
    linea(x + 2, 26, y + 21);
    textcolor(187);
    Figura(x + 4, 22, y + 3, y + 5, 0);
    Figura(x + 6, 18, y + 6, y + 9, 1);
    Figura(x + 14, 2, y + 10, y + 12, 0);
    Figura(x + 12, 6, y + 13, y + 16, 2);
    Figura(x + 4, 22, y + 17, y + 19, 0);
    if (n == 0)
    {
        int oracion = Aleatorio(1, 5);
        switch (oracion)
        {
        case 1:
            box(41, 31, 78, 5, 63, 85);
            Mensaje(" ", " ", "Usa el transporte p" + string(1, char(163)) + "blico o comparte el transporte privado, caminar", "o andar en bicicleta son opciones saludables y ecol" + string(1, char(162)) + "gicas", " ", " ", 30, 2, 0);
            break;

        case 2:
            box(31, 31, 98, 5, 63, 85);
            Mensaje(" ", " ", "Ahorra energ" + string(1, char(161)) + "a en tu hogar y en tu trabajo. Apaga y desenchufa los aparatos que no est" + string(1, char(130)) + "s usando", "Usar bombillos de bajo consumo son opciones saludables y ecol" + string(1, char(162)) + "gicas", " ", " ", 30, 2, 0);
            break;
        case 3:
            box(32, 31, 96, 5, 63, 85);
            Mensaje(" ", " ", "Reduce el consumo de carne y opta por una dieta m" + string(1, char(160)) + "s vegetal. La ganader" + string(1, char(161)) + "a", "es una de las principales fuentes de emisi" + string(1, char(162)) + "n de gases de efecto invernadero y de deforestaci" + string(1, char(162)) + "n", " ", " ", 30, 2, 0);
            break;
        case 4:
            box(41, 31, 78, 5, 63, 85);
            Mensaje(" ", " ", "Consume productos de temporada y locales As" + string(1, char(161)) + " evitar" + string(1, char(160)) + "s el transporte de larga", "distancia y el uso de invernaderos que tambi" + string(1, char(130)) + "n generan emisiones", " ", " ", 30, 2, 0);
            break;
        case 5:
            box(41, 31, 78, 5, 63, 85);
            Mensaje(" ", " ", "Recoge y recicla los residuos que encuentres en la naturaleza. La basura,", "contamina el suelo el agua y el aire, y afecta a la biodiversidad", " ", " ", 30, 2, 0);
            break;
        }
    }
    hideCursor();
    textcolor(136);
    Figura(x + 4, 22, y + 3, y + 5, 0);
    Figura(x + 6, 18, y + 6, y + 9, 1);
    Figura(x + 14, 2, y + 10, y + 18, 0);
    for (int i = 1; i <= 58; i++)
    {

        textcolor(187);
        Nivel(i, 58, 58, x, y + 10);
        Nivel(i, 57, 55, x, y + 9);
        Nivel(i, 54, 50, x, y + 8);
        Nivel(i, 49, 43, x, y + 7);
        Nivel(i, 42, 34, x, y + 6);
        Nivel(i, 33, 23, x, y + 5);
        Nivel(i, 22, 12, x, y + 4);
        Nivel(i, 11, 1, x, y + 3);
        Sleep(30);
        textcolor(136);
        Montana(i, 58, 58, x, y + 12);
        Montana(i, 57, 55, x, y + 13);
        Montana(i, 54, 50, x, y + 14);
        Montana(i, 49, 43, x, y + 15);
        Montana(i, 42, 34, x, y + 16);
        Montana(i, 33, 23, x, y + 17);
        Montana(i, 22, 12, x, y + 18);
        Montana(i, 11, 1, x, y + 19);
    }
}

void A(int &x, int y)
{
    linea(x + 1, 5, y);
    linea(x, 2, y + 1);
    linea(x + 5, 2, y + 1);
    linea(x, 7, y + 2);
    linea(x, 2, y + 3);
    linea(x + 5, 2, y + 3);
    linea(x, 2, y + 4);
    linea(x + 5, 2, y + 4);
    x += 8;
}
void C(int &x, int y)
{
    linea(x + 1, 6, y);
    linea(x, 2, y + 1);
    linea(x, 2, y + 2);
    linea(x, 2, y + 3);
    linea(x + 1, 6, y + 4);
    x += 8;
}
void D(int &x, int y)
{
    linea(x, 6, y);
    linea(x, 2, y + 1);
    linea(x + 5, 2, y + 1);
    linea(x, 2, y + 2);
    linea(x + 5, 2, y + 2);
    linea(x, 2, y + 3);
    linea(x + 5, 2, y + 3);
    linea(x, 6, y + 4);
    x += 8;
}
void E(int &x, int y)
{
    linea(x, 7, y);
    linea(x, 2, y + 1);
    linea(x, 5, y + 2);
    linea(x, 2, y + 3);
    linea(x, 7, y + 4);
    x += 8;
}
void H(int &x, int y)
{
    linea(x + 1, 2, y);
    linea(x + 5, 2, y);
    linea(x + 1, 2, y + 1);
    linea(x + 5, 2, y + 1);
    linea(x + 1, 6, y + 2);
    linea(x + 1, 2, y + 3);
    linea(x + 5, 2, y + 3);
    linea(x + 1, 2, y + 4);
    linea(x + 5, 2, y + 4);
    x += 8;
}
void I(int &x, int y)
{
    linea(x, 2, y);
    linea(x, 2, y + 1);
    linea(x, 2, y + 2);
    linea(x, 2, y + 3);
    linea(x, 2, y + 4);
    x += 3;
}
void J(int &x, int y)
{
    linea(x + 6, 2, y);
    linea(x + 6, 2, y + 1);
    linea(x + 6, 2, y + 2);
    linea(x, 2, y + 3);
    linea(x + 6, 2, y + 3);
    linea(x + 1, 6, y + 4);
    x += 9;
}
void L(int &x, int y)
{
    linea(x, 2, y);
    linea(x, 2, y + 1);
    linea(x, 2, y + 2);
    linea(x, 2, y + 3);
    linea(x, 7, y + 4);
    x += 8;
}
void M(int &x, int y)
{
    linea(x, 3, y);
    linea(x + 7, 3, y);
    linea(x, 4, y + 1);
    linea(x + 6, 4, y + 1);
    linea(x, 2, y + 2);
    linea(x + 3, 4, y + 2);
    linea(x + 8, 2, y + 2);
    linea(x, 2, y + 3);
    linea(x + 4, 2, y + 3);
    linea(x + 8, 2, y + 3);
    linea(x, 2, y + 4);
    linea(x + 8, 2, y + 4);
    x += 11;
}
void N(int &x, int y)
{
    linea(x, 3, y);
    linea(x + 7, 2, y);
    linea(x, 4, y + 1);
    linea(x + 7, 2, y + 1);
    linea(x, 2, y + 2);
    linea(x + 3, 2, y + 2);
    linea(x + 7, 2, y + 2);
    linea(x, 2, y + 3);
    linea(x + 4, 2, y + 3);
    linea(x + 7, 2, y + 3);
    linea(x, 2, y + 4);
    linea(x + 5, 4, y + 4);
    x += 10;
}
void O(int &x, int y)
{
    linea(x + 1, 6, y);
    linea(x, 2, y + 1);
    linea(x + 6, 2, y + 1);
    linea(x, 2, y + 2);
    linea(x + 6, 2, y + 2);
    linea(x, 2, y + 3);
    linea(x + 6, 2, y + 3);
    linea(x + 1, 6, y + 4);
    x += 9;
}
void P(int &x, int y)
{
    linea(x, 6, y);
    linea(x, 2, y + 1);
    linea(x + 5, 2, y + 1);
    linea(x, 6, y + 2);
    linea(x, 2, y + 3);
    linea(x, 2, y + 4);
    x += 8;
}
void R(int &x, int y)
{
    linea(x, 6, y);
    linea(x, 2, y + 1);
    linea(x + 5, 2, y + 1);
    linea(x, 6, y + 2);
    linea(x, 2, y + 3);
    linea(x + 5, 2, y + 3);
    linea(x, 2, y + 4);
    linea(x + 5, 2, y + 4);
    x += 8;
}
void S(int &x, int y)
{
    linea(x, 7, y);
    linea(x, 2, y + 1);
    linea(x, 7, y + 2);
    linea(x + 5, 2, y + 3);
    linea(x, 7, y + 4);
    x += 8;
}
void T(int &x, int y)
{
    linea(x, 8, y);
    linea(x + 3, 2, y + 1);
    linea(x + 3, 2, y + 2);
    linea(x + 3, 2, y + 3);
    linea(x + 3, 2, y + 4);
    x += 9;
}
void U(int &x, int y)
{
    linea(x, 2, y);
    linea(x + 6, 2, y);
    linea(x, 2, y + 1);
    linea(x + 6, 2, y + 1);
    linea(x, 2, y + 2);
    linea(x + 6, 2, y + 2);
    linea(x, 2, y + 3);
    linea(x + 6, 2, y + 3);
    linea(x + 1, 6, y + 4);
    x += 9;
}
void V(int &x, int y)
{
    linea(x, 2, y);
    linea(x + 6, 2, y);
    linea(x, 2, y + 1);
    linea(x + 6, 2, y + 1);
    linea(x, 2, y + 2);
    linea(x + 6, 2, y + 2);
    linea(x + 1, 2, y + 3);
    linea(x + 5, 2, y + 3);
    linea(x + 2, 4, y + 4);
    x += 9;
}

void PAUSA(int x, int y)
{
    textcolor(15);
    P(x, y);
    A(x, y);
    U(x, y);
    S(x, y);
    A(x, y);
}
void ODISEACLIMATICA(int x, int y)
{
    box(x - 4, 2, 119, 8, 63, 34);
    textcolor(255);
    O(x, y);
    D(x, y);
    I(x, y);
    S(x, y);
    E(x, y);
    A(x, y);
    x += 4;
    C(x, y);
    L(x, y);
    I(x, y);
    M(x, y);
    A(x, y);
    T(x, y);
    I(x, y);
    C(x, y);
    A(x, y);
}
void ESTADISTICAS(int x, int y)
{
    textcolor(31);
    E(x, y);
    S(x, y);
    T(x, y);
    A(x, y);
    D(x, y);
    I(x, y);
    S(x, y);
    T(x, y);
    I(x, y);
    C(x, y);
    A(x, y);
    S(x, y);
}
void INSTRUCCIONES(int x, int y)
{
    box(23, 2, 106, 8, 63, 34);
    textcolor(255);
    I(x, y);
    N(x, y);
    S(x, y);
    T(x, y);
    R(x, y);
    U(x, y);
    C(x, y);
    C(x, y);
    I(x, y);
    O(x, y);
    N(x, y);
    E(x, y);
    S(x, y);
}
void aspectos(int y, int relleno, int color, string linea1, string linea2, string linea3, string linea4)
{
    box(30, y, 92, 10, 63, relleno);
    printwriter(linea1, color, ((155 - linea1.length()) / 2), y + 2);
    printwriter(linea2, color, ((155 - linea2.length()) / 2), y + 4);
    printwriter(linea3, color, ((155 - linea3.length()) / 2), y + 6);
    printwriter(linea4, color, ((155 - linea4.length()) / 2), y + 8);
}
void instrucciones()
{
    textcolor(51);
    Figura(20, 124, 2, 34, 0);
    system("cls");
    INSTRUCCIONES(27, 4);
    string linea1 = "OBJETIVO";
    string linea2 = "Llegar a Australia con suficientes recursos para activar la B" + string(1, char(162)) + "veda";
    string linea3 = "Fin del Mundo, que contiene semillas esenciales para la vida humana.";
    string linea4 = " ";
    aspectos(12, 17, 31, linea1, linea2, linea3, linea4);

    linea1 = "PREPARACI" + string(1, char(224)) + "N";
    linea2 = " Cada jugador comienza con una cantidad inicial de recursos. El tablero";
    linea3 = " representa un mapa mundial y cada jugador coloca su ficha en el punto de partida.";
    aspectos(24, 68, 79, linea1, linea2, linea3, linea4);

    linea1 = "MOVIMIENTO";
    linea2 = " Los jugadores tiran un dado para avanzar por el tablero, que est" + string(1, char(160)) + " dividido";
    linea3 = " en continentes y regiones afectadas por el cambio clim" + string(1, char(160)) + "tico.";
    aspectos(36, 85, 95, linea1, linea2, linea3, linea4);

    linea1 = "DESAF" + string(1, char(214)) + "OS CLIM" + string(1, char(181)) + "TICOS";
    linea2 = " Al caer en ciertas casillas, los jugadores enfrentar" + string(1, char(160)) + "n desaf" + string(1, char(161)) + "os";
    linea3 = " como sequ" + string(1, char(161)) + "as, inundaciones, incendios forestales y tormentas. Deber" + string(1, char(160)) + "n usar recursos";
    linea4 = " para superar estos obst" + string(1, char(160)) + "culos o buscar rutas alternativas.";
    aspectos(48, 102, 111, linea1, linea2, linea3, linea4);

    linea1 = "RECOLECCI" + string(1, char(224)) + "N DE RECURSOS";
    linea2 = " En algunas regiones, los jugadores pueden recolectar recursos";
    linea3 = " adicionales. Estos son vitales para sobrevivir a los desaf" + string(1, char(161)) + "os y activar la B" + string(1, char(162)) + "veda.";
    linea4 = " ";
    aspectos(60, 136, 143, linea1, linea2, linea3, linea4);

    linea1 = "USO DE RECURSOS";
    linea2 = " Los recursos se gastan para mitigar desastres, comprar equipo, o";
    linea3 = " intercambiar con otros jugadores. Administra sabiamente tus recursos.";
    aspectos(72, 17, 31, linea1, linea2, linea3, linea4);

    linea1 = "RETORNO DE RECURSOS";
    linea2 = " Si te quedas sin recursos antes de llegar a Australia, debes";
    linea3 = " regresar al " + string(1, char(163)) + "ltimo punto de recolecci" + string(1, char(162)) + "n para obtener m" + string(1, char(160)) + "s.";
    aspectos(84, 68, 79, linea1, linea2, linea3, linea4);

    linea1 = "GANAR EL JUEGO";
    linea2 = " El primer jugador en llegar a Australia con los recursos necesarios";
    linea3 = " gana. Si ning" + string(1, char(163)) + "n jugador lo logra, el juego contin" + string(1, char(163)) + "a hasta que se cumpla este objetivo.";
    aspectos(96, 85, 95, linea1, linea2, linea3, linea4);

    linea1 = "CONCIENCIA CLIM" + string(1, char(181)) + "TICA";
    linea2 = " Durante el juego, los jugadores aprender" + string(1, char(160)) + "n sobre el impacto del";
    linea3 = " cambio clim" + string(1, char(160)) + "tico y la importancia de la sostenibilidad.";
    aspectos(108, 102, 111, linea1, linea2, linea3, linea4);

    box(49, 120, 56, 4, 63, 136);
    print("PRESIONA CUALQUIER TECLA PARA VOLVER AL MEN" + string(1, char(233)) + " ANTERIOR", 143, 51, 122);
    textcolor(51);
    getch();
    system("cls");
}
void Australia(int largo)
{
    textcolor(187);
    int x = largo - 25;
    linea(x + 17, 2, 40);
    linea(x + 17, 4, 39);
    linea(x + 19, 4, 38);
    linea(x + 11, 4, 37);
    linea(x + 21, 2, 37);
    linea(x, 2, 36);
    linea(x + 9, 6, 36);
    linea(x + 19, 2, 36);
    linea(x, 17, 34);
    linea(x, 17, 35);
    linea(x, 15, 33);
    linea(x + 1, 14, 32);
    linea(x + 3, 8, 31);
    linea(x + 5, 4, 30);
    linea(x + 13, 2, 29);
    linea(x + 9, 2, 28);
    linea(x + 7, 2, 27);
    linea(x + 1, 4, 27);
    linea(x + 5, 2, 26);
    textcolor(0);
    linea(134, 1, 31); // 44
    linea(143, 1, 32); // 45
}
void Asia(int largo)
{
    textcolor(170);
    int x = largo - 25;
    linea(x - 7, 2, 2);
    linea(x - 7, 4, 3);
    linea(x - 13, 10, 4);
    linea(x + 3, 4, 5);
    linea(x - 17, 18, 5);
    linea(x + 23, 2, 6);
    linea(x - 17, 35, 6);
    linea(x - 21, 2, 6);
    linea(x - 29, 4, 6);
    linea(x - 29, 57, 7);
    linea(x - 29, 59, 8);
    linea(x - 29, 55, 9);
    linea(x - 29, 52, 10);
    linea(x - 31, 50, 11);
    linea(x - 31, 42, 12);
    linea(x + 14, 3, 12);
    linea(x - 29, 38, 13);
    linea(x + 12, 3, 13);
    linea(x - 27, 31, 14);
    linea(x + 12, 3, 14);
    linea(x - 26, 32, 15);
    linea(x + 12, 2, 15);
    linea(x - 25, 5, 16);
    linea(x - 18, 22, 16);
    linea(x - 26, 2, 17);
    linea(x - 22, 24, 17);
    linea(x - 31, 7, 18);
    linea(x - 22, 22, 18);
    linea(x + 4, 2, 18);
    linea(x - 31, 22, 19);
    linea(x - 5, 3, 19);
    linea(x - 27, 20, 20);
    linea(x - 5, 3, 20);
    linea(x + 2, 2, 20);
    linea(x - 27, 4, 21);
    linea(x - 19, 12, 21);
    linea(x - 2, 4, 21);
    linea(x - 29, 8, 22);
    linea(x - 16, 4, 22);
    linea(x - 11, 4, 22);
    linea(x - 2, 2, 22);
    linea(x - 27, 7, 23);
    linea(x - 16, 2, 23);
    linea(x - 9, 3, 23);
    linea(x - 27, 5, 24);
    linea(x - 16, 3, 24);
    linea(x - 9, 4, 24);
    linea(x - 15, 2, 25);
    linea(x - 10, 1, 25);
    linea(x - 13, 2, 26);
    linea(x - 9, 1, 26);
    linea(x - 7, 2, 26);
    linea(x - 4, 5, 26);
    linea(x - 1, 2, 27);
    linea(x - 8, 2, 27);
    linea(x - 20, 1, 0);
    linea(x - 22, 1, 1);
    linea(x + 9, 2, 4);
    linea(x - 19, 4, 3);
    linea(x - 20, 1, 4);
    linea(x - 23, 1, 5);
    textcolor(0);
    linea(105, 1, 20); // 31
    linea(109, 1, 17); // 32
    linea(108, 1, 13); // 33
    linea(104, 1, 10); // 34
    linea(114, 1, 9);  // 35
    linea(125, 1, 8);  // 36
    linea(136, 1, 10); // 37
    linea(129, 1, 13); // 38
    linea(122, 1, 15); // 39
    linea(125, 1, 18); // 40
    linea(128, 1, 22); // 41
    linea(124, 1, 24); // 42
    linea(129, 1, 26); // 43
}
void Europa(int largo)
{
    textcolor(102);
    int x = (largo / 2) + 4;
    linea(x, 2, 20);
    linea(x - 2, 6, 19);
    linea(x + 6, 2, 19);
    linea(x + 12, 4, 19);
    linea(x + 14, 2, 18);
    linea(x - 2, 14, 18);
    linea(x, 20, 17);
    linea(x, 24, 16);
    linea(x, 24, 15);
    linea(x, 22, 14);
    linea(x, 20, 13);
    linea(x - 9, 2, 13);
    linea(x - 4, 2, 13);
    linea(x - 4, 6, 12);
    linea(x + 10, 8, 12);
    linea(x + 4, 9, 11);
    linea(x + 16, 4, 11);
    linea(x - 3, 2, 11);
    linea(x + 6, 9, 11);
    linea(x + 18, 4, 11);
    linea(x + 6, 10, 10);
    linea(x + 17, 3, 10);
    linea(x + 8, 12, 9);
    linea(x + 10, 10, 8);
    linea(x + 12, 8, 7);
    linea(x + 9, 3, 4);
    linea(x + 10, 1, 5);
    textcolor(0);
    linea(78, 1, 13); // 17
    linea(84, 1, 14); // 18
    linea(91, 1, 13); // 19
    linea(99, 1, 15); // 20
    linea(96, 1, 18); // 21
    linea(88, 1, 18); // 22
    linea(82, 1, 19); // 23
}
void Africa(int largo)
{
    textcolor(204);
    int x = (largo / 2) + 16;
    linea(x - 1, 2, 38);
    linea(x - 3, 7, 37);
    linea(x - 3, 7, 36);
    linea(x + 8, 2, 36);
    linea(x - 3, 8, 35);
    linea(x + 8, 2, 35);
    linea(x - 3, 8, 34);
    linea(x + 8, 3, 34);
    linea(x - 5, 11, 34);
    linea(x - 5, 11, 33);
    linea(x + 9, 2, 33);
    linea(x - 5, 13, 32);
    linea(x - 5, 11, 31);
    linea(x - 5, 13, 30);
    linea(x - 5, 13, 29);
    linea(x - 7, 15, 28);
    linea(x - 14, 4, 27);
    linea(x - 8, 21, 27);
    linea(x - 16, 29, 26);
    linea(x - 16, 25, 25);
    linea(x + 10, 3, 25);
    linea(x - 16, 25, 24);
    linea(x - 16, 25, 23);
    linea(x - 14, 22, 22);
    linea(x - 12, 12, 21);
    linea(x + 2, 2, 21);
    textcolor(0);
    linea(82, 1, 22);  // 24
    linea(84, 1, 25);  // 25
    linea(89, 1, 28);  // 26
    linea(92, 1, 32);  // 27
    linea(97, 1, 30);  // 28
    linea(99, 1, 26);  // 29
    linea(100, 1, 23); // 30
}
void NorteAmerica(int largo)
{
    textcolor(17);
    int x = (largo / 3);
    linea(x - 5, 4, 26);
    linea(x + 9, 2, 25);
    linea(x + 1, 5, 25);
    linea(x - 9, 7, 25);
    linea(x + 3, 3, 24);
    linea(x - 11, 11, 24);
    linea(x + 3, 2, 23);
    linea(x - 15, 16, 23);
    linea(x - 18, 23, 22);
    linea(x - 18, 25, 21);
    linea(x - 21, 28, 20);
    linea(x - 21, 30, 19);
    linea(x - 21, 33, 18);
    linea(10, 2, 18);
    linea(x - 23, 27, 17);
    linea(x + 5, 7, 17);
    linea(12, 2, 17);
    linea(x - 27, 29, 16);
    linea(x + 5, 5, 16);
    linea(14, 2, 16);
    linea(x - 27, 26, 15);
    linea(x + 6, 2, 15);
    linea(16, 2, 15);
    linea(x + 8, 3, 14);
    linea(x - 37, 5, 14);
    linea(x - 28, 31, 14);
    linea(x + 7, 4, 13);
    linea(x - 39, 42, 13);
    linea(x + 6, 5, 12);
    linea(x - 40, 43, 12);
    linea(x + 5, 5, 11);
    linea(x - 3, 6, 11);
    linea(x - 9, 2, 11);
    linea(x - 40, 29, 11);
    linea(x + 1, 5, 10);
    linea(x - 16, 15, 10);
    linea(30, 2, 10);
    linea(10, 18, 10);
    linea(x - 38, 12, 9);
    linea(x - 19, 18, 9);
    linea(x + 1, 4, 9);
    linea(x - 35, 4, 8);
    linea(x - 17, 6, 8);
    linea(x - 8, 7, 8);
    linea(x + 1, 3, 8);
    linea(x - 17, 2, 7);
    linea(x - 11, 5, 7);
    linea(x - 4, 2, 7);
    linea(x, 6, 7);
    linea(x - 13, 4, 6);
    linea(x - 7, 14, 6);
    linea(x + 10, 6, 6);
    linea(x - 11, 2, 5);
    linea(x - 7, 14, 5);
    linea(x + 10, 4, 5);
    linea(x - 4, 20, 4);
    linea(x - 4, 21, 4);
    linea(x + 1, 2, 3);
    linea(x + 6, 4, 3);
    linea(x + 13, 4, 3);
    // aqui va Alaska
    linea(x + 23, 7, 0);
    linea(x + 20, 12, 1);
    linea(x + 20, 14, 2);
    linea(x + 18, 14, 3);
    linea(x + 17, 16, 4);
    linea(x + 19, 12, 5);
    linea(x + 20, 7, 6);
    linea(x + 29, 2, 6);
    linea(x + 22, 5, 7);
    linea(x + 22, 1, 8);
    textcolor(0);
    linea(47, 1, 24); // 9
    linea(39, 1, 22); // 10
    linea(34, 1, 18); // 11
    linea(29, 1, 13); // 12
    linea(42, 1, 16); // 13
    linea(49, 1, 19); // 14
    linea(59, 1, 19); // 15
    linea(61, 1, 14); // 16
}
void SurAmerica(int largo)
{
    textcolor(85);
    int x = (largo / 3);
    linea(x + 2, 3, 40);
    linea(x + 1, 2, 39);
    linea(x + 1, 3, 38);
    linea(x + 1, 5, 37);
    linea(x + 2, 6, 36);
    linea(x + 2, 6, 35);
    linea(x + 2, 10, 34);
    linea(x + 1, 14, 33);
    linea(x + 1, 14, 32);
    linea(x - 1, 17, 31);
    linea(x - 2, 19, 30);
    linea(x - 1, 17, 29);
    linea(x + 1, 14, 28);
    linea(x - 3, 14, 27);
    textcolor(0);
    linea(54, 1, 35); // 4
    linea(59, 1, 33); // 5
    linea(63, 1, 31); // 6
    linea(60, 1, 28); // 7
    linea(53, 1, 27); // 8
}
void Antartida(int largo)
{
    textcolor(255);
    int x = (largo / 2) - 31;
    linea(x - 2, 80, 46);
    linea(x + 1, 73, 45);
    linea(x, 74, 44);
    linea(x, 3, 43);
    linea(x + 5, 17, 43);
    linea(x + 23, 48, 43);
    linea(x + 20, 14, 42);
    linea(x + 35, 8, 42);
    linea(x + 46, 3, 42);
    linea(x + 50, 19, 42);
    linea(x + 24, 9, 41);
    linea(x + 43, 8, 41);
    linea(x + 17, 1, 41);
    linea(x + 63, 1, 41);
    linea(x + 65, 1, 40);
    textcolor(0);
    linea(97, 1, 44); // 1
    linea(82, 1, 45); // 2
    linea(66, 1, 42); // 3
}
void printMap(int largoMap)
{
    Antartida(largoMap);
    SurAmerica(largoMap);
    NorteAmerica(largoMap);
    Africa(largoMap);
    Europa(largoMap);
    Asia(largoMap);
    Australia(largoMap);
}
void PJ(int x, int y, int op)
{
    gotoxy(x, y);
    if (op == 1)
    {
        textcolor(14);
        cout << char(2);
    }
    else
    {
        textcolor(0);
        cout << char(219);
    }
}
void NDado(int total, int coord, int y)
{
    Sleep(100);
    textcolor(0);
    if (total == 1)
    {
        linea(coord + 4, 2, y + 1);
    }
    if (total == 2)
    {
        linea(coord + 2, 2, y);
        linea(coord + 6, 2, y + 2);
    }
    if (total == 3)
    {
        linea(coord + 1, 2, y);
        linea(coord + 4, 2, y + 1);
        linea(coord + 7, 2, y + 2);
    }
    if (total == 4)
    {
        linea(coord + 2, 2, y);
        linea(coord + 2, 2, y + 2);
        linea(coord + 6, 2, y);
        linea(coord + 6, 2, y + 2);
    }
    if (total == 5)
    {
        linea(coord + 1, 2, y);
        linea(coord + 1, 2, y + 2);
        linea(coord + 4, 2, y + 1);
        linea(coord + 7, 2, y);
        linea(coord + 7, 2, y + 2);
    }
    if (total == 6)
    {
        linea(coord + 1, 2, y);
        linea(coord + 1, 2, y + 2);
        linea(coord + 4, 2, y);
        linea(coord + 4, 2, y + 2);
        linea(coord + 7, 2, y);
        linea(coord + 7, 2, y + 2);
    }
}
void Dados(int numdados, int total)
{
    box(11, 36, 10, 6, 48, 255);

    if (numdados == 1)
    {
        NDado(total, 12, 38);
    }
    else
    {
        box(25, 36, 10, 6, 48, 255);
        int dado1;
        if (total > 7)
            dado1 = Aleatorio(total - 6, 6);
        else
            dado1 = Aleatorio(1, total - 1);
        int dado2 = total - dado1;
        NDado(dado1, 12, 38);
        NDado(dado2, 26, 38);
        textcolor(15);
    }
}
void Posicion(int casilla, int s)
{
    if (casilla == 1)
        PJ(97, 44, s);
    if (casilla == 2)
        PJ(82, 45, s);
    if (casilla == 3)
        PJ(66, 42, s);
    if (casilla == 4)
        PJ(54, 35, s);
    if (casilla == 5)
        PJ(59, 33, s);
    if (casilla == 6)
        PJ(63, 31, s);
    if (casilla == 7)
        PJ(60, 28, s);
    if (casilla == 8)
        PJ(53, 27, s);
    if (casilla == 9)
        PJ(47, 24, s);
    if (casilla == 10)
        PJ(39, 22, s);
    if (casilla == 11)
        PJ(34, 18, s);
    if (casilla == 12)
        PJ(29, 13, s);
    if (casilla == 13)
        PJ(42, 16, s);
    if (casilla == 14)
        PJ(49, 19, s);
    if (casilla == 15)
        PJ(59, 19, s);
    if (casilla == 16)
        PJ(61, 14, s);
    if (casilla == 17)
        PJ(78, 13, s);
    if (casilla == 18)
        PJ(84, 14, s);
    if (casilla == 19)
        PJ(91, 13, s);
    if (casilla == 20)
        PJ(99, 15, s);
    if (casilla == 21)
        PJ(96, 18, s);
    if (casilla == 22)
        PJ(88, 18, s);
    if (casilla == 23)
        PJ(82, 19, s);
    if (casilla == 24)
        PJ(82, 22, s);
    if (casilla == 25)
        PJ(84, 25, s);
    if (casilla == 26)
        PJ(89, 28, s);
    if (casilla == 27)
        PJ(92, 32, s);
    if (casilla == 28)
        PJ(97, 30, s);
    if (casilla == 29)
        PJ(99, 26, s);
    if (casilla == 30)
        PJ(100, 23, s);
    if (casilla == 31)
        PJ(105, 20, s);
    if (casilla == 32)
        PJ(109, 17, s);
    if (casilla == 33)
        PJ(108, 13, s);
    if (casilla == 34)
        PJ(104, 10, s);
    if (casilla == 35)
        PJ(114, 9, s);
    if (casilla == 36)
        PJ(125, 8, s);
    if (casilla == 37)
        PJ(136, 10, s);
    if (casilla == 38)
        PJ(129, 13, s);
    if (casilla == 39)
        PJ(122, 15, s);
    if (casilla == 40)
        PJ(125, 18, s);
    if (casilla == 41)
        PJ(128, 22, s);
    if (casilla == 42)
        PJ(124, 24, s);
    if (casilla == 43)
        PJ(129, 26, s);
    if (casilla == 44)
        PJ(134, 31, s);
    if (casilla == 45)
        PJ(143, 32, s);
    textcolor(63);
}
void CargarPartida(string direccion, int &cant, Jugador *&jugadores, int &dificultad)
{
    ifstream archivo(direccion);
    string dato;
    if (!archivo.eof())
    {
        getline(archivo, dato);
        cant = atoi(dato.c_str());
        getline(archivo, dato);
        dificultad = atoi(dato.c_str());
        Jugador *auxiliar = jugadores;
        for (int i = 0; i < cant; i++)
        {
            getline(archivo, dato);
            auxiliar->nombre = dato;
            getline(archivo, dato);
            auxiliar->numero = atoi(dato.c_str());
            getline(archivo, dato);
            auxiliar->posicion = atoi(dato.c_str());
            getline(archivo, dato);
            auxiliar->recursos = atoi(dato.c_str());
            getline(archivo, dato);
            auxiliar->dado = atoi(dato.c_str());
            getline(archivo, dato);
            auxiliar->efectos = atoi(dato.c_str());
            if (auxiliar->efectos == 1)
            {
                getline(archivo, dato);
                auxiliar->efecto->turnos = atoi(dato.c_str());
            }
            if (i + 1 < cant)
            {
                auxiliar->prox = new Jugador;
                auxiliar = auxiliar->prox;
            }
        }
        auxiliar->prox = jugadores;
    }
    archivo.close();
}

// RETOS
const int anchoPantalla = 145;
const int alturaPantalla = 34;
const int anchoVentana = 85;
int jugadorPos = anchoVentana / 2;
int score = 0;
bool escombroFlag1, escombroFlag2;
int escombroY1, escombroY2, escombroX1, escombroX2;
struct player
{
    char composicion;
    player *prox = NULL;
};
player *crearNodo(char valor)
{
    player *nuevo = new player;
    nuevo->composicion = valor;
    nuevo->prox = NULL;
    return nuevo;
}
bool listaVacia(player *inicio)
{
    return inicio == NULL;
}
void insertarUltimo(player **inicio, char valor)
{
    player *nuevo = crearNodo(valor);
    if (listaVacia(*inicio))
        *inicio = nuevo;
    else
    {
        player *auxiliar = *inicio;
        while (auxiliar->prox != NULL)
        {
            auxiliar = auxiliar->prox;
        }
        auxiliar->prox = nuevo;
    }
}
void dibujarBorde()
{
    Figura(15, 17, 5, alturaPantalla - 1, 0);
    Figura(69, 17, 5, alturaPantalla - 1, 0);
    Figura(anchoPantalla, 1, 5, alturaPantalla - 1, 0);
    Figura(anchoPantalla - 19, 1, 5, alturaPantalla - 1, 0);
    linea(15, 131, alturaPantalla);
    linea(15, 131, 4);
}
void genEscombro(int n)
{
    if (n == 1)
        escombroX1 = 33 + (rand() % (33));
    else
        escombroX2 = 33 + (rand() % (33));
}
void dibujarEscombro(int n)
{
    if (escombroFlag1 == true && n == 1)
    {
        gotoxy(escombroX1, escombroY1);
        cout << string(4, char(219));
        gotoxy(escombroX1, escombroY1 + 1);
        cout << string(3, char(219));
        gotoxy(escombroX1, escombroY1 + 2);
        cout << string(1, char(32)) + string(3, char(219));
        gotoxy(escombroX1, escombroY1 + 3);
        cout << string(2, char(32)) + string(1, char(219));
    }
    else if (escombroFlag2 == true && n == 2)
    {
        gotoxy(escombroX2, escombroY2);
        cout << string(4, char(219));
        gotoxy(escombroX2, escombroY2 + 1);
        cout << string(3, char(219));
        gotoxy(escombroX2, escombroY2 + 2);
        cout << string(1, char(32)) + string(3, char(219));
        gotoxy(escombroX2, escombroY2 + 3);
        cout << string(2, char(32)) + string(1, char(219));
    }
}
void dibujarEscombro2(int n)
{
    if (escombroFlag1 == true && n == 1)
    {
        textcolor(15);
        gotoxy(escombroX1, escombroY1);
        cout << string(3, char(176));
        gotoxy(escombroX1, escombroY1 + 1);
        cout << string(4, char(176));
        gotoxy(escombroX1, escombroY1 + 2);
        cout << string(2, char(176));
        gotoxy(escombroX1, escombroY1 + 3);
        textcolor(51);
        cout << string(2, char(32));
        textcolor(15);
        cout << string(1, char(176));
    }
    else if (escombroFlag2 == true && n == 2)
    {
        textcolor(15);
        gotoxy(escombroX2, escombroY2);
        cout << string(3, char(176));
        gotoxy(escombroX2, escombroY2 + 1);
        cout << string(4, char(176));
        gotoxy(escombroX2, escombroY2 + 2);
        cout << string(2, char(176));
        gotoxy(escombroX2, escombroY2 + 3);
        textcolor(51);
        cout << string(2, char(32));
        textcolor(15);
        cout << string(1, char(176));
    }
}
void borrarEscombro(int n)
{
    if (n == 1)
    {
        textcolor(51);
        gotoxy(escombroX1, escombroY1);
        cout << "    ";
        gotoxy(escombroX1, escombroY1 + 1);
        cout << "    ";
        gotoxy(escombroX1, escombroY1 + 2);
        cout << "    ";
        gotoxy(escombroX1, escombroY1 + 3);
        cout << "    ";
    }
    else
    {
        textcolor(51);
        gotoxy(escombroX2, escombroY2);
        cout << "    ";
        gotoxy(escombroX2, escombroY2 + 1);
        cout << "    ";
        gotoxy(escombroX2, escombroY2 + 2);
        cout << "    ";
        gotoxy(escombroX2, escombroY2 + 3);
        cout << "    ";
    }
}
void resetEscombro(int n)
{
    borrarEscombro(n);
    if (n == 1)
        escombroY1 = 6;
    else
        escombroY2 = 6;
    genEscombro(n);
}
void dibujarJugador(int jugadorPos)
{
    player *p1 = NULL;

    insertarUltimo(&p1, char(32));
    insertarUltimo(&p1, char(79));
    insertarUltimo(&p1, char(32));
    insertarUltimo(&p1, char(220));

    insertarUltimo(&p1, char(47));
    insertarUltimo(&p1, '|');
    insertarUltimo(&p1, char(196));
    insertarUltimo(&p1, char(217));

    insertarUltimo(&p1, char(32));
    insertarUltimo(&p1, '|');
    insertarUltimo(&p1, char(32));
    insertarUltimo(&p1, char(32));

    insertarUltimo(&p1, char(47));
    insertarUltimo(&p1, char(32));
    insertarUltimo(&p1, char(92));
    insertarUltimo(&p1, char(32));

    for (int i = 0; i < 4; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            textcolor(63);
            gotoxy(j + jugadorPos, i + 27);
            cout << p1->composicion;
            p1 = p1->prox;
        }
    }
}
void borrarJugador()
{
    for (int i = 0; i < 4; i++)
    {
        for (int j = 0; j < 4; j++)
        {
            textcolor(51);
            gotoxy(j + jugadorPos, i + 27);
            cout << " ";
        }
    }
}
bool colision(int jugadorPos, int &escombro)
{
    if (escombroY1 + 4 >= 28)
    {
        if (escombroX1 + 4 - jugadorPos >= 0 && escombroX1 + 4 - jugadorPos < 9)
        {
            escombro = 0;
            return true;
        }
    }
    if (escombroY2 + 4 >= 28)
    {
        if (escombroX2 + 4 - jugadorPos >= 0 && escombroX2 + 4 - jugadorPos < 9)
        {
            escombro = 1;
            return true;
        }
    }
    return false;
}
void actualizarScore(int score)
{
    textcolor(51);
    gotoxy(anchoVentana + 46, 10);
    cout << "            ";
    textcolor(63);
    gotoxy(anchoVentana + 46, 10);
    cout << "Puntos: " << score << endl;
}
void dibujarMontana()
{
    gotoxy(anchoVentana + 19, 5);
    cout << "        .";
    gotoxy(anchoVentana + 19, 6);
    cout << "      \\ | /";
    gotoxy(anchoVentana + 19, 7);
    cout << "    '-.;;;.-'";
    gotoxy(anchoVentana + 19, 8);
    cout << "   -==;;;;;==-";
    gotoxy(anchoVentana + 19, 9);
    cout << "    .-';;;'-.";
    gotoxy(anchoVentana + 19, 10);
    cout << "      / | \\";
    gotoxy(anchoVentana + 19, 11);
    cout << "        '";

    gotoxy(anchoVentana + 1, 10);
    cout << "   /\\";
    gotoxy(anchoVentana + 1, 11);
    cout << "         /**\\";
    gotoxy(anchoVentana + 1, 12);
    cout << "        /****\\   /\\";
    gotoxy(anchoVentana + 1, 13);
    cout << "       /      \\ /**\\";
    gotoxy(anchoVentana + 1, 14);
    cout << "      /  /\\    /    \\        /\\    /\\  /";
    gotoxy(anchoVentana + 1, 15);
    cout << "     /  /  \\  /      \\      /  \\/\\/  \\/";
    gotoxy(anchoVentana + 1, 16);
    cout << "    /  /    \\/ /\\     \\    /    \\ \\  /";
    gotoxy(anchoVentana + 1, 17);
    cout << "   /  /      \\/  \\/\\   \\  /      \\    /";
    gotoxy(anchoVentana + 1, 18);
    cout << "__/__/_______/___/__\\___\\_______________";
    gotoxy(anchoVentana + 1, 19);
    cout << "                    /|\\";
    gotoxy(anchoVentana + 1, 20);
    cout << "                   / | \\";
    gotoxy(anchoVentana + 1, 21);
    cout << "                  /  |  \\";
    gotoxy(anchoVentana + 1, 22);
    cout << "                 /   |   \\";
    gotoxy(anchoVentana + 1, 23);
    cout << "                /    |    \\";
    gotoxy(anchoVentana + 1, 24);
    cout << "               /     |     \\";
    gotoxy(anchoVentana + 1, 25);
    cout << "              /      |      \\";
    gotoxy(anchoVentana + 1, 26);
    cout << "             /       |       \\";
    gotoxy(anchoVentana + 1, 27);
    cout << "            /        |        \\";
    gotoxy(anchoVentana + 1, 28);
    cout << "           /         |         \\";
    gotoxy(anchoVentana + 1, 29);
    cout << "          /          |          \\";
    gotoxy(anchoVentana + 1, 30);
    cout << "         /           |           \\";
    gotoxy(anchoVentana + 1, 31);
    cout << "        /            |            \\";
    gotoxy(anchoVentana + 1, 32);
    cout << "       /             |             \\";
    gotoxy(anchoVentana + 1, 33);
    cout << "      /              |              \\";
}
void dibujarPaisaje()
{
    gotoxy(anchoVentana + 1, 10);
    cout << "     .-.                                ";
    gotoxy(anchoVentana + 1, 11);
    cout << "  .-(   )-.                             ";
    gotoxy(anchoVentana + 1, 12);
    cout << " (     __) )-.                        ,-";
    gotoxy(anchoVentana + 1, 13);
    cout << "  `-(       __)                      (_ ";
    gotoxy(anchoVentana + 1, 14);
    cout << "    `(____)-',                        `-";
    gotoxy(anchoVentana + 1, 15);
    cout << "  - -  :   :  - -";
    gotoxy(anchoVentana + 1, 16);
    cout << "      / `-' \\";
    gotoxy(anchoVentana + 1, 17);
    cout << "    ,    |   .";
    gotoxy(anchoVentana + 1, 18);
    cout << "         .                         _";
    gotoxy(anchoVentana + 1, 19);
    cout << "                                  >')";
    gotoxy(anchoVentana + 1, 20);
    cout << "               _   /              (\\\\   ";
    gotoxy(anchoVentana + 1, 21);
    cout << "              =') //               = \\";
    gotoxy(anchoVentana + 1, 22);
    cout << "               ))////)             = ,-";
    gotoxy(anchoVentana + 1, 23);
    cout << "              ( (///))           ( |/";
    gotoxy(anchoVentana + 1, 24);
    cout << "~~~~~~~~~~~~~~~`~~~~'~~~~~~~~~~~~~\\|,-'";
    gotoxy(anchoVentana + 1, 25);
    cout << "            _                 ,----':::";
    gotoxy(anchoVentana + 1, 26);
    cout << "         {><_'c   _      _.--':::::::::";
    gotoxy(anchoVentana + 1, 27);
    cout << "__,'`----._,-. {><_'c  _-':::::::::::::";
    gotoxy(anchoVentana + 1, 28);
    cout << ":.:.:.:.:.:.:.\\_    ,-'.:.:.:.:.:.:.:.:";
    gotoxy(anchoVentana + 1, 29);
    cout << ".:.:.:.:.:.:.:.:`--'.:.:.:.:.:.:.:.:.:.";
    gotoxy(anchoVentana + 1, 30);
    cout << ".......................................";
}
void AVALANCHA(int x, int y)
{
    textcolor(255);
    A(x, y);
    Sleep(100);
    V(x, y);
    Sleep(100);
    A(x, y);
    Sleep(100);
    L(x, y);
    Sleep(100);
    A(x, y);
    Sleep(100);
    N(x, y);
    Sleep(100);
    C(x, y);
    Sleep(100);
    H(x, y);
    Sleep(100);
    A(x, y);
}
void RECICLAJE(int x, int y)
{
    textcolor(255);
    R(x, y);
    Sleep(100);
    E(x, y);
    Sleep(100);
    C(x, y);
    Sleep(100);
    I(x, y);
    Sleep(100);
    C(x, y);
    Sleep(100);
    L(x, y);
    Sleep(100);
    A(x, y);
    Sleep(100);
    J(x, y);
    Sleep(100);
    E(x, y);
}
void TRIVIA(int x, int y)
{
    textcolor(255);
    T(x, y);
    Sleep(100);
    R(x, y);
    Sleep(100);
    I(x, y);
    Sleep(100);
    V(x, y);
    Sleep(100);
    I(x, y);
    Sleep(100);
    A(x, y);
}
void jugar(int &score, int juego)
{
    jugadorPos = -1 + (anchoVentana / 2);
    score = 0;
    int resp = 0;
    int escombro = 2;
    escombroFlag1 = true;
    escombroFlag2 = false;
    escombroY1 = escombroY2 = 6;

    textcolor(63);
    dibujarBorde();
    if (juego == 1)
    {
        dibujarMontana();
    }
    else
    {
        dibujarPaisaje();
    }

    actualizarScore(score);
    genEscombro(1);
    genEscombro(2);

    if (juego == 1)
    {
        gotoxy(anchoVentana + 45, 7);
        cout << char(173) << "Avalancha!";
    }
    else
    {
        gotoxy(anchoVentana + 43, 7);
        cout << char(173) << "Recicla basura!";
    }

    gotoxy(anchoVentana + 46, 9);
    cout << "----------";
    gotoxy(anchoVentana + 46, 11);
    cout << "----------";
    gotoxy(anchoVentana + 47, 17);
    cout << "Control ";
    gotoxy(anchoVentana + 47, 18);
    cout << "-------- ";
    gotoxy(anchoVentana + 42, 19);
    cout << "  A  - Izquierda";
    gotoxy(anchoVentana + 42, 20);
    cout << "  D  - Derecha";
    gotoxy(33, 5);
    cout << "                      ";
    int tiempo = 50;
    int time = 100;
    while (resp != 1)
    {
        switch (juego)
        {
        case 1:
            if (kbhit())
            {
                char ch = getch();
                if (ch == 'a' || ch == 'A')
                {
                    if (jugadorPos > 33)
                        jugadorPos -= 4;
                }
                if (ch == 'd' || ch == 'D')
                {
                    if (jugadorPos < 64)
                        jugadorPos += 4;
                }
            }

            dibujarJugador(jugadorPos);
            dibujarEscombro(1);
            dibujarEscombro(2);

            if (colision(jugadorPos, escombro))
            {
                textcolor(51);
                Figura(15, 131, 4, 34, 0);
                resp = 1;
                return;
            }

            Sleep(tiempo);
            if (score == 10)
            {
                tiempo = 40;
            }
            if (score == 20)
            {
                tiempo = 30;
            }
            if (score == 30)
            {
                tiempo = 20;
            }
            if (score == 40)
            {
                tiempo = 10;
            }

            borrarJugador();
            borrarEscombro(1);
            borrarEscombro(2);

            if (escombroY1 == 15)
                if (!escombroFlag2)
                    escombroFlag2 = true;

            if (escombroFlag1)
                escombroY1++;

            if (escombroFlag2)
                escombroY2++;

            if (escombroY1 > alturaPantalla - 4)
            {
                resetEscombro(1);
                score++;
                actualizarScore(score);
            }
            if (escombroY2 > alturaPantalla - 4)
            {
                resetEscombro(2);
                score++;
                actualizarScore(score);
            }
            break;
        case 2:
            if (kbhit())
            {
                char ch = getch();
                if (ch == 'a' || ch == 'A')
                {
                    if (jugadorPos > 33)
                        jugadorPos -= 4;
                }
                if (ch == 'd' || ch == 'D')
                {
                    if (jugadorPos < 64)
                        jugadorPos += 4;
                }
            }
            dibujarJugador(jugadorPos);
            dibujarEscombro2(1);
            dibujarEscombro2(2);

            if (colision(jugadorPos, escombro))
            {
                if (escombro == 0)
                    resetEscombro(1);
                if (escombro == 1)
                    resetEscombro(2);
                score++;
                actualizarScore(score);
            }

            Sleep(time);
            if (score == 10)
            {
                time = 80;
            }
            if (score == 20)
            {
                time = 60;
            }
            if (score == 30)
            {
                time = 40;
            }
            if (score == 40)
            {
                time = 20;
            }
            borrarJugador();
            borrarEscombro(1);
            borrarEscombro(2);

            if (escombroY1 == 15)
                if (!escombroFlag2)
                    escombroFlag2 = 1;

            if (escombroFlag1)
                escombroY1++;

            if (escombroFlag2)
                escombroY2++;

            if (escombroY1 > alturaPantalla - 4)
            {
                textcolor(51);
                Figura(15, 131, 4, 34, 0);
                resp = 1;
                return;
            }
            if (escombroY2 > alturaPantalla - 4)
            {
                textcolor(51);
                Figura(15, 131, 4, 34, 0);
                resp = 1;
                return;
            }
            break;
        }
    }
}
int Minijuegos(int juego)
{
    int score;
    textcolor(51);
    Figura(0, 80, 0, 46, 0);
    Figura(80, 163, 0, 46, 0);
    gotoxy(10, 0);
    if (juego == 1)
    {
        AVALANCHA(42, 15);
        string texto = string(1, char(173)) + string(1, char(173)) + string(1, char(173)) + " ESQUIVA LOS ESCOMBROS !!!";
        printwriter(texto, 96, ((155 - texto.length()) / 2), 21);
    }
    else
    {
        RECICLAJE(46, 15);
        string texto = string(1, char(173)) + string(1, char(173)) + string(1, char(173)) + " RECICLA LOS DESECHOS !!!";
        printwriter(texto, 96, ((155 - texto.length()) / 2), 21);
    }
    box(55, 27, 42, 4, 63, 85);
    string texto = "PRESIONE CUALQUIER TECLA PARA COMENZAR";
    print(texto, 95, ((155 - texto.length()) / 2), 29);
    getch();
    textcolor(51);
    Figura(15, 131, 4, 34, 0);
    jugar(score, juego);
    return score;
}
// JUEGO PRINCIPAL
int Opcion(char &tcl, int posicion, int min, int max, int direccion)
{
    tcl = getch();
    tcl = toupper(tcl);
    char arriba, abajo;
    if (direccion == 1)
    {
        arriba = 'W';
        abajo = 'S';
    }
    else
    {
        arriba = 'S';
        abajo = 'W';
    }
    if (tcl == arriba && posicion > min)
        posicion--;
    if (tcl == abajo && posicion < max)
        posicion++;

    return posicion;
}
void Seleccion(string opcion, int y, int relleno, int color)
{
    box(64, y, 24, 4, 63, relleno);
    print(opcion, color, ((155 - opcion.length()) / 2), y + 2);
}
int Seleccionar(string opcion1, string opcion2, string opcion3, string opcion4, string opcion5)
{
    Seleccion(opcion1, 12, 17, 31);
    Seleccion(opcion2, 17, 85, 95);
    Seleccion(opcion3, 22, 85, 95);
    Seleccion(opcion4, 27, 85, 95);
    Seleccion(opcion5, 32, 85, 95);
    textcolor(63);
    gotoxy(60, 37);
    cout << "(w): " << char(30) << "     (ESPACIO): " << char(16) << "     (s): " << char(31);
    char tcl = '*';
    int seleccion = 1;
    while (tcl != ' ')
    {
        seleccion = Opcion(tcl, seleccion, 1, 5, 1);
        switch (seleccion)
        {
        case 1:
            Seleccion(opcion1, 12, 17, 31);
            Seleccion(opcion2, 17, 85, 95);
            break;
        case 2:
            Seleccion(opcion1, 12, 85, 95);
            Seleccion(opcion2, 17, 17, 31);
            Seleccion(opcion3, 22, 85, 95);
            break;
        case 3:
            Seleccion(opcion2, 17, 85, 95);
            Seleccion(opcion3, 22, 17, 31);
            Seleccion(opcion4, 27, 85, 95);
            break;
        case 4:
            Seleccion(opcion3, 22, 85, 95);
            Seleccion(opcion4, 27, 17, 31);
            Seleccion(opcion5, 32, 85, 95);
            break;
        case 5:
            Seleccion(opcion4, 27, 85, 95);
            Seleccion(opcion5, 32, 17, 31);
            break;
        }
    }
    return seleccion;
}
void eliminar(Jugador *&inicio, int dato, int &cant)
{
    Jugador *actual = inicio;
    while (actual->numero != dato)
    {
        actual = actual->prox;
    }
    actual->anterior->prox = actual->prox;
    actual->prox->anterior = actual->anterior;
    delete actual;
    int i = 1;
    actual = inicio;
    while(i<cant)
	{
		actual->numero = i;
		actual = actual->prox;
		i++;
	}
}

void elimJug(Jugador *&inicio, int &cant)
{
    string arriba(1, char(30));
    string abajo(1, char(31));
    string nom;
    textcolor(51);
    Figura(60, 70, 12, 37, 0);
    box(52, 12, 47, 4, 63, 17);
    print("SELECCIONE EL JUGADOR A ELIMINAR", 31, 59, 14);
    box(64, 18, 24, 8, 63, 85);
    print(arriba, 90, 77, 20);
    print(abajo, 90, 77, 24);
    print(inicio->nombre, 95, ((155 - (inicio->nombre).length()) / 2), 22);
    textcolor(63);
    gotoxy(60, 28);
    cout << "(w): " << char(30) << "     (ESPACIO): " << char(16) << "     (s): " << char(31);
    int jug = inicio->numero;
    Jugador *aux = inicio;
    char tcl = '*';
    while (tcl != ' ')
    {
        jug = Opcion(tcl, jug, 1, cant, 2);
        box(64, 18, 24, 8, 63, 85);
        while (aux->numero != jug)
        {
            aux = aux->prox;
        }
        nom = aux->nombre;
        print(arriba, 90, 77, 20);
        print(abajo, 90, 77, 24);
        print(nom, 95, ((155 - nom.length()) / 2), 22);
    }
    if ((*&inicio->nombre == aux->nombre) && (*&inicio->posicion == aux->posicion))
    {
        nom = "ERROR, NO SE PUEDE ELIMINAR A UN JUGADOR QUE NO HA TERMINADO SU TURNO";
        print(nom, 95, ((155 - nom.length()) / 2), 22);
    }
    else
    {
        eliminar(inicio, aux->numero, cant);
        cant--;
        box(64, 18, 24, 8, 63, 85);
        nom = "JUGADOR ELIMINADO";
        print(nom, 95, ((155 - nom.length()) / 2), 22);
    }
    getch();
}

void elimObs(Tablero *&casilla)
{
    string arriba(1, char(30));
    string abajo(1, char(31));
    textcolor(51);
    Figura(60, 70, 12, 37, 0);
    box(52, 12, 47, 4, 63, 17);
    print("SELECCIONE EL OBSTACULO A ELIMINAR", 31, 59, 14);
    box(64, 18, 24, 8, 63, 85);
    print(arriba, 90, 77, 20);
    print(abajo, 90, 77, 24);
    print("1", 95, 77, 22);
    textcolor(63);
    gotoxy(60, 28);
    cout << "(w): " << char(30) << "     (ESPACIO): " << char(16) << "     (s): " << char(31);
    int x = 1;
    string mnsj;
    Tablero *aux = casilla;
    char tcl = '*';
    while (tcl != ' ')
    {
        x = Opcion(tcl, x, 1, 45, 2);
        box(64, 18, 24, 8, 63, 85);
        print(arriba, 90, 77, 20);
        print(abajo, 90, 77, 24);
        print(to_string(x), 95, ((155 - to_string(x).length()) / 2), 22);
    }
    while (aux->casilla != x)
    {
        aux = aux->sig;
    }
    box(52, 12, 47, 4, 63, 17);
    if (aux->obstaculo != 0)
    {
        aux->obstaculo = 0;
        box(64, 18, 24, 8, 63, 85);
        mnsj = "OBSTACULO ELIMINADO";
        print(mnsj, 95, ((155 - mnsj.length()) / 2), 22);
        getch();
    }
    else
    {
        box(60, 18, 30, 8, 63, 85);
        mnsj = "NO EXISTEN OBSTACULOS EN ESTA CASILLA";
        print(mnsj, 95, ((155 - mnsj.length()) / 2), 22);
        getch();
    }
}

void elimReto(Tablero *&casilla)
{
    string arriba(1, char(30));
    string abajo(1, char(31));
    textcolor(51);
    Figura(60, 70, 12, 37, 0);
    box(52, 12, 47, 4, 63, 17);
    print("SELECCIONE EL RETO A ELIMINAR", 31, 59, 14);
    box(64, 18, 24, 8, 63, 85);
    print(arriba, 90, 77, 20);
    print(abajo, 90, 77, 24);
    print("1", 95, 77, 22);
    textcolor(63);
    gotoxy(60, 28);
    cout << "(w): " << char(30) << "     (ESPACIO): " << char(16) << "     (s): " << char(31);
    int x = 1;
    string mnsj;
    Tablero *aux = casilla;
    char tcl = '*';
    while (tcl != ' ')
    {
        x = Opcion(tcl, x, 1, 45, 2);
        box(64, 18, 24, 8, 63, 85);

        print(arriba, 90, 77, 20);
        print(abajo, 90, 77, 24);
        print(to_string(x), 95, ((155 - to_string(x).length()) / 2), 22);
    }
    while (aux->casilla != x)
    {
        aux = aux->sig;
    }
    box(52, 12, 47, 4, 63, 17);
    if (aux->reto != 0)
    {
        aux->reto = 0;
        box(64, 18, 24, 8, 63, 85);
        mnsj = "RETO ELIMINADO";
        print(mnsj, 95, ((155 - mnsj.length()) / 2), 22);
        getch();
    }
    else
    {
        box(64, 18, 24, 8, 63, 85);
        mnsj = "NO EXISTE RETO EN ESTA CASILLA";
        print(mnsj, 95, ((155 - mnsj.length()) / 2), 22);
        getch();
    }
}

void editRec(Jugador *&inicio, int cant)
{
    string arriba(1, char(30));
    string abajo(1, char(31));
    string nom;
    textcolor(51);
    Figura(60, 70, 12, 37, 0);
    box(52, 12, 47, 4, 63, 17);
    print("SELECCIONE EL JUGADOR A EDITAR", 31, 59, 14);
    box(64, 18, 24, 8, 63, 85);
    print(arriba, 90, 77, 20);
    print(abajo, 90, 77, 24);
    print(inicio->nombre, 95, 77, 22);
    textcolor(63);
    gotoxy(60, 28);
    cout << "(w): " << char(30) << "     (ESPACIO): " << char(16) << "     (s): " << char(31);
    int jug = inicio->numero;
    Jugador *aux = inicio;
    char tcl = '*';
    while (tcl != ' ')
    {
        jug = Opcion(tcl, jug, 1, cant, 2);
        box(64, 18, 24, 8, 63, 85);
        while (aux->numero != jug)
        {
            aux = aux->prox;
        }
        nom = aux->nombre;
        print(arriba, 90, 77, 20);
        print(abajo, 90, 77, 24);
        print(nom, 95, ((155 - nom.length()) / 2), 22);
    }
    print("SELECCIONE LA NUEVA CANTIDAD DE RECURSOS", 31, 54, 14);
    box(64, 18, 24, 8, 63, 85);
    print(arriba, 90, 77, 20);
    print(abajo, 90, 77, 24);
    print("1", 95, 77, 22);
    int x = 1;
    tcl = '*';
    while (tcl != ' ')
    {
        x = Opcion(tcl, x, 1, 500, 2);
        box(64, 18, 24, 8, 63, 85);

        print(arriba, 90, 77, 20);
        print(abajo, 90, 77, 24);
        print(to_string(x), 95, ((155 - to_string(x).length()) / 2), 22);
    }
    aux->recursos = x;
    nom = "RECURSOS MODIFICADOS";
    print(nom, 95, ((155 - nom.length()) / 2), 22);
    getch();
}

void configuracion(Tablero *casilla, Jugador *inicio, int &cant)
{
    int ps = 0;
    while (ps != 5)
    {
        textcolor(51);
        ps = Seleccionar("JUGADOR", "OBSTACULO", "RETO", "RECURSOS", "SALIR");
        switch (ps)
        {
        case 1:
            elimJug(inicio, cant);
            break;
        case 2:
            elimObs(casilla);
            break;
        case 3:
            elimReto(casilla);
            break;
        case 4:
            editRec(inicio, cant);
        }
        textcolor(51);
        Figura(30, 90, 12, 45, 0);
    }
}

bool MenuPausa(Tablero *&inicio, int &cantidadjugadores, Jugador *&jugadores, int dificultad)
{

    int ps = 0;
    while (ps != 5 && ps != 1)
    {
        textcolor(51);
        Figura(0, 80, 0, 50, 0);
        Figura(80, 166, 0, 50, 0);

        box(52, 2, 48, 8, 63, 34);
        PAUSA(57, 4);
        ps = Seleccionar("REANUDAR PARTIDA", "VER ESTAD" + string(1, char(214)) + "STICAS", "INSTRUCCIONES", "CONFIGURAR", " GUARDAR Y SALIR");
        switch (ps)
        {
        case 2:
        {
            textcolor(51);
            Figura(20, 124, 2, 46, 0);
            box(30, 2, 100, 8, 63, 17);
            ESTADISTICAS(36, 4);
            int y = 12;
            for (int i = 0; i < cantidadjugadores; i++)
            {
                Sleep(200);
                string nombre = jugadores->nombre;
                string numero = "N" + string(1, char(233)) + "MERO: " + to_string(jugadores->numero);
                string posicion = "POSICI" + string(1, char(224)) + "N: " + to_string(jugadores->posicion);
                string recurso = "RECURSOS: " + to_string(jugadores->recursos);
                box(61, y, 30, 10, 63, 85);
                print(nombre, 95, ((155 - nombre.length()) / 2), y + 2);
                print(numero, 95, ((155 - numero.length()) / 2), y + 4);
                print(posicion, 95, ((155 - posicion.length()) / 2), y + 6);
                print(recurso, 95, ((155 - recurso.length()) / 2), y + 8);
                jugadores = jugadores->prox;
                y += 12;
            }
            box(49, y, 56, 4, 63, 102);
            print("PRESIONA CUALQUIER TECLA PARA VOLVER AL MEN" + string(1, char(233)) + " ANTERIOR", 111, 51, y + 2);
            textcolor(51);
            getch();
        }
        break;
        case 3:
            instrucciones();
            break;
        case 4:
            configuracion(inicio, jugadores, cantidadjugadores);
            break;
        case 5:
            textcolor(51);
            Figura(20, 124, 2, 46, 0);
            ODISEACLIMATICA(24, 4);
            box(45, 12, 64, 4, 63, 17);
            print("INDIQUE EL NOMBRE DEL ARCHIVO DONDE DESEA GUARDAR LA PARTIDA", 31, 48, 14);
            box(49, 18, 56, 10, 63, 85);
            print("MIPARTIDA1", 87, 72, 23);
            print("PRESIONA ESPACIO PARA EMPEZAR A ESCRIBIR", 87, 58, 21);
            gotoxy(72, 23);
            showCursor();
            getch();
            hideCursor();
            box(49, 18, 56, 10, 63, 85);
            string nombrearchivo;
            gotoxy(72, 23);
            textcolor(95);
            showCursor();
            cin >> nombrearchivo;
            hideCursor();
            string narchivo = nombrearchivo + ".txt";
            ofstream archivo(narchivo);
            archivo << cantidadjugadores << endl;
            archivo << dificultad << endl;
            for (int i = 0; i < cantidadjugadores; i++)
            {
                archivo << jugadores->nombre << endl;
                archivo << jugadores->numero << endl;
                archivo << jugadores->posicion << endl;
                archivo << jugadores->recursos << endl;
                archivo << jugadores->dado << endl;
                archivo << jugadores->efectos << endl;
                if (jugadores->efectos == 1)
                {
                    archivo << jugadores->efecto->turnos;
                }
                jugadores = jugadores->prox;
            }
            archivo.close();
            break;
        }
    }
    return ps == 1;
}
bool tableroVacio(Tablero *inicio)
{
    return inicio == NULL;
}
Tablero *crearCasilla(int dato)
{
    Tablero *nuevaCasilla = new Tablero;
    nuevaCasilla->casilla = dato;
    nuevaCasilla->ant = NULL;
    nuevaCasilla->sig = NULL;
    return nuevaCasilla;
}
void insertarUltimaCasilla(Tablero *&inicio, int valor)
{

    Tablero *nuevo = crearCasilla(valor);
    if (tableroVacio(inicio))
        inicio = nuevo;
    else
    {
        Tablero *actual = inicio;
        while (actual->sig != NULL)
        {
            actual = actual->sig;
        }
        actual->sig = nuevo;
        actual->sig->ant = actual;
    }
}
bool Primo(int x)
{
    int i = 1, cont = 0;
    while (i <= x)
    {
        if (x % i == 0)
        {
            cont++;
        }
        i++;
    }
    return cont == 2;
}
void crearTablero(Tablero *&inicio, int dificultad)
{
    int i = 0;
    inicio = NULL;
    while (i < 45)
    {
        insertarUltimaCasilla(inicio, i + 1);
        i++;
    }
    Tablero *aux = inicio;
    while (aux != NULL)
    {
        if (aux->casilla % 2 == 0)
            aux->recurso = true;
        else
        {
            if (aux->casilla != 45)
            {
                if (aux->casilla == 15 || aux->casilla == 31 || aux->casilla == 41)
                    aux->reto = 1;
                else
                {
                    if (aux->casilla == 7 || aux->casilla == 23 || aux->casilla == 35)
                        aux->reto = 2;
                    else
                    {
                        if (aux->casilla < 13)
                            if (dificultad == 2)
                                aux->obstaculo = Aleatorio(1, 3);
                            else
                                aux->obstaculo = Aleatorio(1, 2);
                        else
                        {
                            if (dificultad == 2)
                                aux->obstaculo = Aleatorio(1, 4);
                            else
                                aux->obstaculo = Aleatorio(1, 2);
                        }
                    }
                }
            }
        }
        aux = aux->sig;
    }
}
Jugador *crearNodo(string nom, Tablero *inicio)
{
    Jugador *nuevo = new Jugador;
    nuevo->nombre = nom;
    nuevo->prox = NULL;
    nuevo->anterior = NULL;
    nuevo->turn = inicio;
    return nuevo;
}
bool listaVacia(Jugador *inicio)
{
    return inicio == NULL;
}
void asignarTurno(Jugador *&inicio)
{
    Jugador *aux = inicio;
    int x = rand() % 4 + 1;
    for (int i = 0; i < x; i++)
    {
        aux = aux->prox;
    }
    aux->numero = 1;
    aux = aux->prox;
    x = 1;
    while (aux->numero != 1)
    {
        x++;
        aux->numero = x;
        aux = aux->prox;
    }
}
void insertarUltimo(Jugador *&primero, string nom, Tablero *inicio)
{
    Jugador *nuevo = crearNodo(nom, inicio);

    if (listaVacia(primero))
    {
        primero = nuevo;
    }
    else
    {
        Jugador *auxiliar = primero;
        while (auxiliar->prox != NULL)
        {
            auxiliar = auxiliar->prox;
        }
        auxiliar->prox = nuevo;
        auxiliar->prox->anterior = auxiliar;
    }
}
int dado(Jugador *&primero, int x)
{

    if (x == 2)
    {
        return Aleatorio(2, 12);
    }
    else
    {
        if (primero->efecto->turnos <= 0)
        {
            primero->dado = 2;
            primero->efectos = 0;
        }
        return Aleatorio(1, 6);
    }
}
void Consecuencia(int tipo, string texto1, string texto2, int dificultad)
{
    int y = 29;
    int oracion = Aleatorio(1, 9);
    box(105, y, 22, 10, 48, 102);
    switch (tipo)
    {
    case -1:
        Mensaje(" ", " ", "Momento de QUIZ", "Sobre Cambio Clim" + string(1, char(160)) + "tico", " ", " ", y, 2, 1);
        break;
    case 0:
        oracion = Aleatorio(1, 11);
        switch (oracion)
        {
        case 1:
            Mensaje(" ", "Sobreviviste a", "una inundaci" + string(1, char(162)) + "n", "construyendo un", "refugio flotante", " ", y, 4, 1);
            break;
        case 2:
            Mensaje(" ", "Sobreviviste a", "una sequ" + string(1, char(161)) + "a", "recolectando y", "purificando agua", " ", y, 4, 1);
            break;
        case 3:
            Mensaje(" ", "Sobreviviste a un", "hurac" + string(1, char(160)) + "n construyendo", "un refugio a prueba", "de tormentas", " ", y, 4, 1);
            break;
        case 4:
            Mensaje(" ", "Superaste una", "avalancha creando", "una ruta de escape", "segura", " ", y, 4, 1);
            break;
        case 5:
            Mensaje(" ", "Superaste una", "tormenta de arena", "creando un sistema", "de filtraci" + string(1, char(162)) + "n", "de aire", y, 5, 1);
            break;
        case 6:
            Mensaje(" ", "Sobreviviste a un", "deslizamiento de", "tierra", "estabilizando el", "suelo", y, 5, 1);
            break;
        case 7:
            Mensaje(" ", "Sobreviviste a un", "tornado construyendo", "un refugio", "subterr" + string(1, char(160)) + "neo", " ", y, 4, 1);
            break;
        case 8:

            Mensaje(" ", "Superaste una", "plaga de langostas", "cultivando plantas", "resistentes", " ", y, 4, 1);
            break;
        case 9:
            Mensaje(" ", "Sobreviviste a una", "tormenta el" + string(1, char(130)) + "ctrica", "construyendo un", "pararrayos", " ", y, 4, 1);
            break;
        case 10:
            Mensaje(" ", "Superaste una", "explosi" + string(1, char(162)) + "n de metano", "creando un sistema", "de ventilaci" + string(1, char(162)) + "n", "eficiente", y, 5, 1);
            break;
        case 11:
            Mensaje(" ", "Sobreviviste a un", "cicl" + string(1, char(162)) + "n construyendo", "un refugio a prueba", "de tormentas", " ", y, 4, 1);
            break;
        }
        break;
    case 1:
        switch (oracion)
        {
        case 1:
            Mensaje(" ", "Un dado ha sido", "arrastrado por la", "corriente de un", "hurac" + string(1, char(160)) + "n furioso", " ", y, 4, 1);
            break;
        case 2:
            Mensaje(" ", "Parece que un dado", "se ha perdido en el", "caos de un tornado", "devastador", " ", y, 4, 1);
            break;
        case 3:
            Mensaje(" ", "Un dado ha sido", "tragado por la", "tierra durante un", "terremoto violento", " ", y, 4, 1);
            break;
        case 4:
            Mensaje(" ", "Un dado ha sido", "llevado por los", "vientos de un", "cicl" + string(1, char(162)) + "n inesperado", " ", y, 4, 1);
            break;
        case 5:
            Mensaje(" ", "Un dado ha sido", "cubierto por la", "nieve de una", "avalancha repentina", " ", y, 4, 1);
            break;
        case 6:
            Mensaje(" ", "Un dado se ha", "perdido en la", "oscuridad de un", "incendio forestal", "descontrolado", y, 5, 1);
            break;
        case 7:
            Mensaje(" ", "Un dado ha sido", "arrastrado por las", "aguas de una", "inundaci" + string(1, char(162)) + "n", "catastr" + string(1, char(162)) + "fica", y, 5, 1);
            break;
        case 8:
            Mensaje(" ", "Un dado ha caido en", "una grieta formada", "por una sequ" + string(1, char(161)) + "a", "extrema", " ", y, 4, 1);
            break;
        case 9:
            Mensaje(" ", "Un dado se ha", "perdido en la", "confusi" + string(1, char(162)) + "n de una", "tormenta de granizo", "intensa", y, 5, 1);
            break;
        case 10:
            Mensaje(" ", "Un dado ha sido", "llevado por la", "fuerza de un", "tsunami poderoso", " ", y, 4, 1);
            break;
        }
        break;
    case 2:
        switch (oracion)
        {
        case 1:
            Mensaje(" ", "Has causado un", "incendio forestal", "en el camino de", "otro jugador", " ", y, 4, 1);
            break;
        case 2:
            Mensaje(" ", "Has provocado una", "inundaci" + string(1, char(162)) + "n que ha", "bloqueado el camino", "de otro jugador", " ", y, 4, 1);
            break;
        case 3:
            Mensaje(" ", "Has causado una", "sequ" + string(1, char(161)) + "a que ha hecho", "que otro jugador", "tenga que buscar", "agua", y, 5, 1);
            break;
        case 4:
            Mensaje(" ", "Has provocado la", "erosi" + string(1, char(162)) + "n que ha hecho", "que el camino sea", "intransitable para", "otro jugador", y, 5, 1);
            break;
        case 5:
            Mensaje("Has causado un", "deslizamiento de", "tierra que ha", "obligado a otro", "jugador a buscar un", "camino alternativo", y, 6, 1);
            break;
        case 6:
            Mensaje(" ", "Has provocado un", "terremoto que ha", "da" + string(1, char(164)) + "ado el camino", "de otro jugador", " ", y, 4, 1);
            break;
        case 7:
            Mensaje(" ", "Has causado una", "tormenta de arena", "que ha obligado a", "otro jugador a", "refugiarse", y, 5, 1);
            break;
        case 8:
            Mensaje(" ", "Has provocado una", "plaga de langostas", "que ha devastado", "los suministros de", "otro jugador", y, 5, 1);
            break;
        case 9:
            Mensaje(" ", "Has causado un", "tsunami que ha", "obligado a otro", "jugador a buscar un", "terreno m" + string(1, char(160)) + "s alto", y, 5, 1);
            break;
        case 10:
            Mensaje(" ", "Has provocado un", "alud de nieve que", "ha obligado a otro", "jugador a detenerse", " ", y, 4, 1);
            break;
        }
        break;
    case 3:
        switch (oracion)
        {
        case 1:
            Mensaje(" ", " ", "Debido a un", "incendio forestal", " ", " ", y, 2, 1);
            break;
        case 2:
            Mensaje(" ", " ", "Una inundaci" + string(1, char(162)) + "n", "arraso tu refugio", " ", " ", y, 2, 1);
            break;
        case 3:
            Mensaje(" ", " ", "Una sequ" + string(1, char(161)) + "a ha", "agotado tus", "reservas de agua", " ", y, 3, 1);
            break;
        case 4:
            Mensaje(" ", " ", "Un terremoto ha", "danado tu refugio", " ", " ", y, 2, 1);
            break;
        case 5:
            Mensaje(" ", " ", "Un tsunami ha", "arrasado tu", "campamento", " ", y, 3, 1);
            break;
        case 6:
            Mensaje(" ", "Una tormenta", "el" + string(1, char(130)) + "ctrica ha", "danado tus", "equipos", " ", y, 4, 1);
            break;

        case 7:
            Mensaje(" ", "Un deslizamiento", "de tierra ha", "enterrado tus", "suministros", " ", y, 4, 1);
            break;
        case 8:
            Mensaje(" ", " ", "Una ola de fr" + string(1, char(161)) + "o", "ha danado tus", "cultivos", " ", y, 3, 1);
            break;
        case 9:
            Mensaje(" ", " ", "Un tornado ha", "arrasado tu", "campamento", " ", y, 3, 1);
            break;
        }
        break;
    case 4:
        switch (oracion)
        {
        case 1:
            Mensaje(" ", "Un incendio", "forestal ha", "bloqueado tu", "camino", " ", y, 4, 1);
            break;
        case 2:
            Mensaje(" ", " ", "La erosi" + string(1, char(162)) + "n ha", "destruido el camino", " ", " ", y, 2, 1);
            break;
        case 3:
            Mensaje(" ", " ", "Un terremoto ha", "da" + string(1, char(164)) + "ado el camino", " ", " ", y, 2, 1);
            break;
        case 4:
            Mensaje(" ", " ", "Un tsunami ha", "inundado el camino", " ", " ", y, 2, 1);
            break;
        case 5:
            Mensaje(" ", "Un deslizamiento", "de rocas ha", "bloqueado tu", "camino", " ", y, 4, 1);
            break;
        case 6:
            Mensaje(" ", "Un incendio de", "matorrales ha", "bloqueado tu", "camino", " ", y, 4, 1);
            break;
        case 7:
            Mensaje(" ", " ", "Una tormenta de", "nieve ha bloqueado", "tu camino", " ", y, 3, 1);
            break;
        case 8:
            Mensaje(" ", "Una tormenta de", "arena ha hecho que", "el camino sea", "intransitable", " ", y, 4, 1);
            break;
        case 9:
            Mensaje(" ", " ", "Un tornado ha", "destruido el camino", " ", " ", y, 2, 1);
            break;
        }
        break;
    case 8:
        Mensaje(string(1, char(173)) + "Emergencia!", "Los desechos estan", "cayendo. Es hora de", "reciclar y restaurar", "el equilibrio de", "nuestro planeta.", y + 1, 6, 1);
        break;
    case 9:
        Mensaje("Alerta! Tornado", "desata avalancha.", "Los fuertes vientos", "desplazan la nieve", "acumulada.", "Es hora de actuar!", y + 1, 6, 1);
        break;
    case 10:
    {
        if (dificultad == 2)
        {
            Mensaje(" ", "Llegaste al fin,", "pero no tienes", "500 recursos.", "Debes empezar de", "nuevo. Recuerda,", y, 5, 1);
        }
        else
        {
            Mensaje(" ", "Llegaste al fin,", "pero no tienes", "250 recursos.", "Debes empezar de", "nuevo. Recuerda,", y, 5, 1);
        }
        string texto = "el " + string(1, char(130)) + "xito esta en";
        printwriter(texto, 111, ((235 - texto.length()) / 2), y + 7);
        texto = "la perseverancia";
        printwriter(texto, 111, ((235 - texto.length()) / 2), y + 8);
        break;
    }
    case 11:
    {
        Mensaje("Felicidades. Has", "ganado el juego.", "Tu victoria es", "recordatorio de", "como en la", "lucha contra el", y, 6, 1);
        string texto = "cambio clim" + string(1, char(160)) + "tico";
        printwriter(texto, 111, ((235 - texto.length()) / 2), y + 7);
        texto = "Cada esfuerzo";
        printwriter(texto, 111, ((235 - texto.length()) / 2), y + 8);
        texto = "cuenta!";
        printwriter(texto, 111, ((235 - texto.length()) / 2), y + 9);

        break;
    }
    }
    if (tipo != 10 && tipo != 11)
    {
        printwriter(texto1, 111, ((235 - texto1.length()) / 2), y + 7);
        printwriter(texto2, 111, ((235 - texto2.length()) / 2), y + 8);
    }
    Sleep(300);
}
void regresar(Jugador *primero)
{
    char opc;
    do
    {
        gotoxy(60, 22);
        cout << "Pulsa espacio para seguir ayudando al planeta";
        opc = getch();
        if (opc == ' ')
        {
            textcolor(51);
            Figura(15, 131, 4, 34, 0);
            printMap(155);
            int y = 25;
            box(8, y, 30, 10, 48, 102);
            string nombre = primero->nombre;
            string posicion = "POSICI" + string(1, char(224)) + "N: " + to_string(primero->posicion);
            string recursos = "RECURSOS: " + to_string(primero->recursos);
            print(nombre, 111, ((48 - nombre.length()) / 2), y + 1);
            print(posicion, 111, ((48 - posicion.length()) / 2), y + 3);
            print(recursos, 111, ((48 - recursos.length()) / 2), y + 5);
        }
    } while (opc != ' ');
}
void reto(Jugador *&primero, int x)
{
    Consecuencia(10 - x, " ", " ", 0);
    int punt = Minijuegos(x);
    box(53, 15, 60, 10, 63, 85);
    textcolor(95);
    gotoxy(64, 18);
    cout << "T" << char(163) << " puntuaci" << char(162) << "n durante el desaf" << char(161) << "o fue " << punt;
    gotoxy(63, 19);
    cout << char(173) << "Has ganado ";
    textcolor(111);
    cout << punt << " recursos";
    textcolor(95);
    cout << " por tus esfuerzos!";
    primero->recursos = primero->recursos + punt;
    regresar(primero);
}
void retroceder(Jugador *&primero, int x)
{
    for (int i = 0; i < x; i++)
    {
        Sleep(300);
        Posicion(primero->posicion, 2);
        primero->turn = primero->turn->ant;
        primero->posicion--;
        Posicion(primero->posicion, 1);
    }
}
void Respuesta(int n, int r, Jugador *&primero)
{
    string texto;
    box(45, 19, 68, 6, 63, 85);
    textcolor(95);
    gotoxy(63, 19);
    textcolor(111);
    textcolor(95);
    if (n % 2 == 0)
    {
        if (r == 1)
        {
            texto = "Respuesta CORRECTA";
            print(texto, 95, ((155 - texto.length()) / 2) + 2, 21);
            texto = "Has ganado 50 recursos!";
            print(texto, 95, ((155 - texto.length()) / 2) + 2, 22);
            primero->recursos = primero->recursos + 50;
        }
        if (r == 0)
        {
            texto = "La respuesta es INCORRECTA";
            print(texto, 95, ((155 - texto.length()) / 2) + 2, 21);
            texto = "Has perdido 50 recursos";
            print(texto, 95, ((155 - texto.length()) / 2) + 2, 22);
            if (primero->recursos <= 50)
                primero->recursos = 0;
            else
                primero->recursos = primero->recursos - 50;
        }
    }
    if (n % 2 != 0)
    {
        if (r == 0)
        {
            texto = "Respuesta CORRECTA";
            print(texto, 95, ((155 - texto.length()) / 2) + 2, 21);
            texto = "Has ganado 50 recursos";
            print(texto, 95, ((155 - texto.length()) / 2) + 2, 22);
            primero->recursos = primero->recursos + 50;
        }
        if (r == 1)
        {
            texto = "La respuesta es INCORRECTA";
            print(texto, 95, ((155 - texto.length()) / 2) + 2, 21);
            texto = "Has perdido 50 recursos";
            print(texto, 95, ((155 - texto.length()) / 2) + 2, 22);
            if (primero->recursos <= 50)
                primero->recursos = 0;
            else
                primero->recursos = primero->recursos - 50;
        }
    }
    gotoxy(58, 23);
    texto = "Pulsa cualquier tecla para seguir ayudando al planeta";
    print(texto, 95, ((155 - texto.length()) / 2) + 2, 23);
}
void Preguntas(int n, Jugador *&primero)
{
    int r = 0;
    string arriba(1, char(30));
    string abajo(1, char(31));
    string texto = string(2, char(173)) + "DEMUESTRA TUS CONOCIMIENTOS SOBRE EL CAMBIO CLIM" + string(1, char(181)) + "TICO!!";
    textcolor(51);
    system("cls");
    ODISEACLIMATICA(24, 4);
    box(((155 - texto.length()) / 2) - 4, 11, ((155 - texto.length()) / 2) + 18, 2, 63, 34);
    print(texto, 47, ((155 - texto.length()) / 2) + 2, 12);
    switch (n)
    {
    case 1:
        texto = string(1, char(168)) + "Beneficia el cambio clim" + string(1, char(160)) + "tico a algunas zonas del planeta?";
        print(texto, 48, ((155 - texto.length()) / 2) + 2, 19);
        break;
    case 2:
        texto = string(1, char(168)) + "El uso de energ" + string(1, char(161)) + "as renovables puede ayudar a luchar contra el cambio clim" + string(1, char(160)) + "tico? ";
        print(texto, 48, ((155 - texto.length()) / 2) + 2, 19);
        break;
    case 3:
        texto = string(1, char(168)) + "Usar m" + string(1, char(160)) + "s combustibles f" + string(1, char(162)) + "siles es bueno para el planeta?";
        print(texto, 48, ((155 - texto.length()) / 2) + 2, 19);
        break;
    case 4:
        texto = string(1, char(168)) + "Arrojar pl" + string(1, char(160)) + "stico en el oc" + string(1, char(130)) + "ano es beneficioso para la vida marina?";
        print(texto, 48, ((155 - texto.length()) / 2) + 2, 19);
        break;
    case 5:
        texto = string(1, char(168)) + "Es el di" + string(1, char(162)) + "xido de carbono el " + string(1, char(163)) + "nico gas de efecto invernadero?";
        print(texto, 48, ((155 - texto.length()) / 2) + 2, 19);
        break;
    case 6:
        texto = string(1, char(168)) + "Se est" + string(1, char(160)) + " calentando el planeta debido al cambio clim" + string(1, char(160)) + "tico?";
        print(texto, 48, ((155 - texto.length()) / 2) + 2, 19);
        break;
    case 7:
        texto = string(1, char(168)) + "Es posible vivir sin emitir gases de efecto invernadero?";
        print(texto, 48, ((155 - texto.length()) / 2) + 2, 19);
        break;
    case 8:
        texto = string(1, char(168)) + "La deforestaci" + string(1, char(162)) + "n y la contaminaci" + string(1, char(162)) + "n de los bosques son causas del cambio clim" + string(1, char(160)) + "tico?";
        print(texto, 48, ((155 - texto.length()) / 2) + 2, 19);
        break;
    case 9:
        texto = string(1, char(168)) + "Es el cambio clim" + string(1, char(160)) + "tico un problema solo de los pa" + string(1, char(161)) + "ses ricos?";
        print(texto, 48, ((155 - texto.length()) / 2) + 2, 19);
        break;
    case 10:
        texto = string(1, char(168)) + "Es la humanidad la que est" + string(1, char(160)) + " causando el cambio clim" + string(1, char(160)) + "tico?";
        print(texto, 48, ((155 - texto.length()) / 2) + 2, 19);
        break;
    }
    textcolor(51);
    Figura(60, 70, 22, 44, 0);
    box(61, 22, 31, 4, 63, 17);
    print("VERDADERO [1] - FALSO [0]", 31, 65, 24);
    box(64, 28, 24, 8, 63, 85);
    print(arriba, 90, 77, 30);
    print(abajo, 90, 77, 34);
    print("0", 95, 77, 32);
    textcolor(63);
    gotoxy(60, 38);
    cout << "(w): " << char(30) << "     (ESPACIO): " << char(16) << "     (s): " << char(31);
    char tcl = '*';
    while (tcl != ' ')
    {
        r = Opcion(tcl, r, 0, 1, 2);
        box(64, 28, 24, 8, 63, 85);
        string sr = to_string(r);
        print(arriba, 90, 77, 30);
        print(abajo, 90, 77, 34);
        print(sr, 95, ((155 - sr.length()) / 2), 32);
    }
    if (r == 1 || r == 0)
    {
        textcolor(51);
        Figura(36, 104, 18, 36, 0);
        Respuesta(n, r, primero);
    }
    getch();
    textcolor(51);
    Figura(0, 166, 1, 46, 0);
    printMap(155);
}
void VerTrivia(Jugador *&primero)
{
    int n = (rand() % 10) + 1;
    textcolor(51);
    Figura(0, 80, 0, 46, 0);
    Figura(80, 163, 0, 46, 0);
    gotoxy(10, 0);
    TRIVIA(62, 15);
    string texto = string(1, char(173)) + string(1, char(173)) + string(1, char(173)) + " EX" + string(1, char(181)) + "MEN SORPRESA !!!";
    printwriter(texto, 96, ((155 - texto.length()) / 2) + 5, 21);
    box(60, 27, 42, 4, 63, 85);
    texto = "PRESIONE CUALQUIER TECLA PARA COMENZAR";
    print(texto, 95, ((155 - texto.length()) / 2) + 5, 29);
    getch();
    textcolor(51);
    Figura(15, 131, 4, 34, 0);
    Preguntas(n, primero);
}
void obstaculo(Jugador *&primero, int x, int cant, bool &verifTurno, int dificultad)
{
    switch (x)
    {
    case 1: // trivia
    {
        string texto1 = "     ";
        string texto2 = "     ";
        Consecuencia(-1, texto1, texto2, dificultad);
        VerTrivia(primero);
        break;
    }
    case 2: // perder dado
    {
        primero->dado = 1;
        primero->efectos = 1;
        primero->efecto->turnos = 2;
        string texto1 = "Perdiste un dado";
        string texto2 = "Por 1 turno";
        Consecuencia(1, texto1, texto2, dificultad);
        break;
    }
    case 3: // perder turno
    {
        verifTurno = false;
        if (cant > 1)
        {
            string texto1 = "Por eso " + primero->prox->nombre;
            string texto2 = "pierde un turno";
            Consecuencia(2, texto1, texto2, dificultad);
            box(8, 25, 30, 10, 48, 102);
            string pasar = "Pasar turno: (ESP)";
            print(pasar, 111, ((48 - pasar.length()) / 2), 30);
            getch();
            box(105, 29, 22, 10, 51, 51);
            Posicion(primero->posicion, 2);
            primero = primero->prox;
        }
        break;
    }
    case 4: // perder recursos
    {
        int pierde = Aleatorio(5, 50);
        if (primero->recursos >= pierde)
        {
            primero->recursos = primero->recursos - pierde;
            string texto1 = "Por eso has";
            string texto2 = "perdido " + to_string(pierde) + " recursos";
            Consecuencia(3, texto1, texto2, dificultad);
            string recursos = to_string(primero->recursos);
            print("    ", 111, 27, 29);
            print(recursos, 111, 28, 29);
        }
        break;
    }
    case 5:
    { // para retroceder el jugador

        box(8, 25, 30, 10, 48, 102);
        string lanzar = "Tirar los dados: (ESP)";
        print(lanzar, 111, ((48 - lanzar.length()) / 2), 29);
        getch();
        int casillas;
        casillas = dado(primero, primero->dado);
        Dados(primero->dado, casillas);

        string texto1 = "Debes retroceder";
        string texto2 = to_string(casillas) + " casillas";
        Consecuencia(4, texto1, texto2, dificultad);
        retroceder(primero, casillas);
        verifTurno = false;
        box(8, 25, 30, 10, 48, 102);
        string pasar = "Pasar turno: (ESP)";
        print(pasar, 111, ((48 - pasar.length()) / 2), 30);
        getch();
        box(105, 29, 22, 10, 51, 51);
        textcolor(51);
        Figura(10, 30, 36, 43, 0);
        Posicion(primero->posicion, 2);
        break;
    }
    }
}
bool fin(Jugador *primero, int dificultad)
{
    if (dificultad == 2)
        return (primero->turn->casilla == 45 && primero->recursos >= 500);
    else
        return (primero->turn->casilla == 45 && primero->recursos >= 250);
}
void partidaSigue(Jugador *&primero, Tablero *inicio, int dificultad)
{
    primero->turn = inicio;
    primero->posicion = primero->turn->casilla;
    Consecuencia(10, " ", " ", dificultad);
    getch();
}
void moverJugador(Jugador *&primero, Tablero *&inicio, int dado, int dificultad)
{
    int i = 0;
    Dados(primero->dado, dado);
    while (primero->turn->casilla != 45 && i < dado)
    {
        Sleep(300);
        Posicion(primero->posicion, 2);
        primero->turn = primero->turn->sig;
        primero->posicion = primero->turn->casilla;
        i++;
        if ((primero->turn->casilla == 45) && (!fin(primero, dificultad)))
            partidaSigue(primero, inicio, dificultad);
        Posicion(primero->posicion, 1);
        string posicion = to_string(primero->posicion);
        print("    ", 111, 28, 28);
        print(posicion, 111, 29, 28);
    }
    if (fin(primero, dificultad))
    {
        Consecuencia(11, " ", " ", dificultad);
        box(8, 25, 30, 10, 48, 102);
        string terminar = "Terminar juego: (ESP)";
        print(terminar, 111, ((48 - terminar.length()) / 2), 30);
        getch();
    }
    else
    {
        string texto;
        if (dado == 1)
            texto = " posici" + string(1, char(162)) + "n";
        else
            texto = " posiciones";
        texto = "Te has movido " + to_string(dado) + texto;
        print(texto, 111, ((48 - texto.length()) / 2), 32);
        texto = "                    ";
        print(texto, 111, ((48 - texto.length()) / 2), 34);
    }
}
void cuadro(Jugador *primero)
{
    int y = 25;
    box(8, y, 30, 10, 48, 102);
    string nombre = primero->nombre;
    string posicion = "POSICI" + string(1, char(224)) + "N: " + to_string(primero->posicion);
    string recursos = "RECURSOS: " + to_string(primero->recursos);
    print(nombre, 111, ((48 - nombre.length()) / 2), y + 1);
    print(posicion, 111, ((48 - posicion.length()) / 2), y + 3);
    print(recursos, 111, ((48 - recursos.length()) / 2), y + 5);
    string lanzar = "Tirar los dados: (ESP)";
    print(lanzar, 111, ((48 - lanzar.length()) / 2), y + 7);
    string pausa = "Menu de pausa: (P)";
    print(pausa, 111, ((48 - pausa.length()) / 2), y + 9);
}
void partida(Jugador *&primero, Tablero *&inicio, int cant, int dificultad)
{
    int sum = 0;
    bool menu = true;
    while (!fin(primero, dificultad) && menu)
    {
        Posicion(primero->posicion, 1);
        cuadro(primero);
        char tcl = getch();
        if (tcl == ' ')
        {
            bool verifTurno = true;
            moverJugador(primero, inicio, dado(primero, primero->dado), dificultad);
            if (!fin(primero, dificultad))
            {
                if (primero->turn->obstaculo != 0)
                {
                    obstaculo(primero, primero->turn->obstaculo, cant, verifTurno, dificultad);
                }
                if (verifTurno)
                {
                    if (primero->turn->reto != 0)
                    {
                        reto(primero, primero->turn->reto);
                    }
                    if (primero->turn->recurso && primero->turn->obstaculo != 3)
                    {
                        sum = Aleatorio(10, 50);
                        primero->recursos = primero->recursos + sum;
                        string recursos = to_string(primero->recursos);
                        print("    ", 111, 27, 30);
                        print(recursos, 111, 28, 30);
                        string texto1 = "Has conseguido";
                        string texto2 = to_string(sum) + " recursos";
                        Consecuencia(0, texto1, texto2, dificultad);
                    }
                    if (primero->efecto->turnos != 0)
                        primero->efecto->turnos = primero->efecto->turnos - 1;
                    string pasar = "Pasar turno: (ESP)";
                    print(pasar, 111, ((48 - pasar.length()) / 2), 34);
                    getch();
                    box(105, 29, 22, 10, 51, 51);
                    textcolor(51);
                    Figura(10, 30, 35, 43, 0);
                    Posicion(primero->posicion, 2);
                    primero = primero->prox;
                }
                else
                {
                    primero = primero->prox;
                }
            }
        }
        if (tcl == 'p' || tcl == 'P')
        {
            menu = MenuPausa(inicio, cant, primero, dificultad);
            textcolor(51);
            Figura(0, 140, 2, 45, 0);
            if (menu)
                printMap(155);
        }
    }
}
void odisea(Jugador *&primero, int dificultad, Tablero *&inicio, int &cant, int cont)
{
    crearTablero(inicio, dificultad);
    if (cont == 0)
    {
        Jugador *aux = primero;
        while (!listaVacia(aux->prox))
        {
            aux = aux->prox;
        }
        aux->prox = primero;
        primero->anterior = aux;
        asignarTurno(primero);
        while (primero->numero != 1)
        {
            primero = primero->prox;
        }
    }
    textcolor(51);
    Figura(20, 124, 2, 37, 0);
    printMap(155);
    partida(primero, inicio, cant, dificultad);
}