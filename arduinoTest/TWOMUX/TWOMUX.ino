int valC0;
int valC1;
int valC2;
int valC3;
int valC4;
int valC5;
int valC6;
int valC7;
int valC8;
int valC9;
int valC10;
int valC11;
int valC12;
int valC13;
int valC14;
int valC15;

int val2C0;
int val2C1;
int val2C2;
int val2C3;
int val2C4;
int val2C5;
int val2C6;
int val2C7;
int val2C8;
int val2C9;
int val2C10;
int val2C11;
int val2C12;
int val2C13;
int val2C14;
int val2C15;

int sensePin = A0;
int sensePin2 = A1;

void setup() {
  // put your setup code here, to run once:
  //for pin 8 - 11
  DDRB = B00001111;
  //for pin 4 - 7
  DDRD = B11110010;
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  //

  /// NOTE pinA0
//  PORTB = B00000000;
//  valC0 = analogRead(sensePin); 
//
//  PORTB = B00000001;
//  valC1 = analogRead(sensePin); 
//
//  PORTB = B00000010;
//  valC2 = analogRead(sensePin); 
//
//  PORTB = B00000011;
//  valC3 = analogRead(sensePin); 
//
//  PORTB = B00000100;
//  valC4 = analogRead(sensePin); 
//
//  PORTB = B00000101;
//  valC5 = analogRead(sensePin); 
//
//  PORTB = B00000110;
//  valC6 = analogRead(sensePin); 
//
//  PORTB = B00000111;
//  valC7 = analogRead(sensePin); 
//
//  PORTB = B00001000;
//  valC8 = analogRead(sensePin); 
//
//  PORTB = B00001001;
//  valC9 = analogRead(sensePin); 
//
//  PORTB = B00001010;
//  valC10 = analogRead(sensePin); 
//
//  PORTB = B00001011;
//  valC11 = analogRead(sensePin); 
//
//  PORTB = B00001100;
//  valC12 = analogRead(sensePin); 
//
//  PORTB = B00001101;
//  valC13 = analogRead(sensePin); 
//
//  PORTB = B00001110;
//  valC14 = analogRead(sensePin); 
//
//  PORTB = B00001111;
//  valC15 = analogRead(sensePin); 

  //--------------------------
  
  /// NOTE pinA1
  PORTD = B00000000;
  val2C0 = analogRead(sensePin2);

  PORTD = B00010000;
  val2C1 = analogRead(sensePin2); 

  PORTD = B00100000;
  val2C2 = analogRead(sensePin2); 

  PORTD = B00110000;
  val2C3 = analogRead(sensePin2); 

  PORTD = B01000000;
  val2C4 = analogRead(sensePin2); 

  PORTD = B01010000;
  val2C5 = analogRead(sensePin2); 

//  PORTD = ;
//  val2C6 = analogRead(sensePin2); 
//
//  PORTD = ;
//  val2C7 = analogRead(sensePin2); 
//
//  PORTD = ;
//  val2C8 = analogRead(sensePin2); 
//
//  PORTD = ;
//  val2C9 = analogRead(sensePin2); 
//
//  PORTD = ;
//  val2C10 = analogRead(sensePin2); 
//
//  PORTD = ;
//  val2C11 = analogRead(sensePin2); 
//
//  PORTD = ;
//  val2C12 = analogRead(sensePin2); 
//
//  PORTD = ;
//  val2C13 = analogRead(sensePin2); 
//
//  PORTD = ;
//  val2C14 = analogRead(sensePin2);
//  
//  PORTD = ;
//  val2C15 = analogRead(sensePin2); 

  //-------------no multiplexer-------
//  val2C0 = analogRead(A0); 
//
//  val2C1 = analogRead(A1); 
//
//  val2C2 = analogRead(A2); 
//
//  val2C3 = analogRead(A3); 
//
//  val2C4 = analogRead(A4); 
//
//  val2C5 = analogRead(A5); 
  
//  val2C6 = analogRead(sensePin2); 
//
//  val2C7 = analogRead(sensePin2); 
//
//  val2C8 = analogRead(sensePin2); 
//
//  val2C9 = analogRead(sensePin2); 
//
//  val2C10 = analogRead(sensePin2); 
//
//  val2C11 = analogRead(sensePin2); 
//
//  val2C12 = analogRead(sensePin2); 
//
//  val2C13 = analogRead(sensePin2); 
//
//  val2C14 = analogRead(sensePin2);
//  
//  val2C15 = analogRead(sensePin2); 

  //------------pin a0---------------
//  Serial.print("Channel0 = ");
//  Serial.println(valC0);
//
//  Serial.print("Channel1 = ");
//  Serial.println(valC1);
//
//  Serial.print("Channel2 = ");
//  Serial.println(valC2);
//
//  Serial.print("Channel3 = ");
//  Serial.println(valC3);
//
//  Serial.print("Channel4 = ");
//  Serial.println(valC4);
//
//  Serial.print("Channel5 = ");
//  Serial.println(valC5);
//
//  Serial.print("Channel6 = ");
//  Serial.println(valC6);
//
//  Serial.print("Channel7 = ");
//  Serial.println(valC7);
//
//  Serial.print("Channel8 = ");
//  Serial.println(valC8);
//
//  Serial.print("Channel9 = ");
//  Serial.println(valC9);
//
//  Serial.print("Channel10 = ");
//  Serial.println(valC10);
//
//  Serial.print("Channel11 = ");
//  Serial.println(valC11);
//
//  Serial.print("Channel12 = ");
//  Serial.println(valC12);
//
//  Serial.print("Channel13 = ");
//  Serial.println(valC13);
//
//  Serial.print("Channel14 = ");
//  Serial.println(valC14);
//
//  Serial.print("Channel15 = ");
//  Serial.println(valC15);
  
  //------------pin a1---------------
  Serial.print("2nd Channel0 = ");
  Serial.println(val2C0);

  Serial.print("2nd Channel1 = ");
  Serial.println(val2C1);

  Serial.print("2nd Channel2 = ");
  Serial.println(val2C2);

  Serial.print("2nd Channel3 = ");
  Serial.println(val2C3);

  Serial.print("2nd Channel4 = ");
  Serial.println(val2C4);

  Serial.print("2nd Channel5 = ");
  Serial.println(val2C5);

//  Serial.print("2nd Channel6 = ");
//  Serial.println(val2C6);
//
//  Serial.print("2nd Channel7 = ");
//  Serial.println(val2C7);
//
//  Serial.print("2nd Channel8 = ");
//  Serial.println(val2C8);
//
//  Serial.print("2nd Channel9 = ");
//  Serial.println(val2C9);
//
//  Serial.print("2nd Channel10 = ");
//  Serial.println(val2C10);
//
//  Serial.print("2nd Channel11 = ");
//  Serial.println(val2C11);
//
//  Serial.print("2nd Channel12 = ");
//  Serial.println(val2C12);
//
//  Serial.print("2nd Channel13 = ");
//  Serial.println(val2C13);
//
//  Serial.print("2nd Channel14 = ");
//  Serial.println(val2C14);
//
//  Serial.print("2nd Channel15 = ");
//  Serial.println(val2C15);

  Serial.println(" ");

  delay(1000);


}
