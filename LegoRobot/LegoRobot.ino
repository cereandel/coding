// C++ code
//
int velocidad = 0;
int fotoReceptor = 0;

void setup()
{
    pinMode(5, OUTPUT);
    pinMode(6, OUTPUT);
    pinMode(10, OUTPUT);
    pinMode(11, OUTPUT);
    pinMode(A0, INPUT);
    velocidad = 200;
    Serial.begin(9600);
}

void loop()
{
  fotoReceptor = analogRead(A0);
  Serial.println(fotoReceptor);
  analogWrite(6, 0);
  analogWrite(10, 0);
  analogWrite(11, velocidad);
  while (velocidad <= 400) {
    fotoReceptor = analogRead(A0);
   velocidad = (velocidad + 50);
    delay(1000); // Wait for 1000 millisecond(s)
  }
  
    velocidad = 200;
    analogWrite(5, 0);
    analogWrite(6, velocidad);
    analogWrite(10, velocidad);
    analogWrite(11, 0);
    while (velocidad <= 400) {
      fotoReceptor = analogRead(A0);
    velocidad = (velocidad + 50);
    delay(1000); // Wait for 1000 millisecond(s)
  }
  velocidad = 200;
}
