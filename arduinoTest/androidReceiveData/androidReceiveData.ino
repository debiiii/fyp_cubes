#define MUX0MAX 16
#define MUX1MAX 11

int sensePin = A0;
int sensePin2 = A1;

int sensorValue[MUX0MAX] = {0};
int sensorValue2[MUX1MAX] = {0};

void setup() {
  //for pin 8 - 11
  DDRB = B00001111;
  //for pin 4 - 7
  DDRD = B11110010;
  Serial.begin(9600);
}

void loop() {
  readSensors();
  sendAndroidValues();
}

void readSensors(){
  //pinA0
  PORTB = B00000000;
  sensorValue[0] = analogRead(sensePin); 

  PORTB = B00000001;
  sensorValue[1] = analogRead(sensePin); 

  PORTB = B00000010;
  sensorValue[2] = analogRead(sensePin); 

  PORTB = B00000011;
  sensorValue[3] = analogRead(sensePin); 

  PORTB = B00000100;
  sensorValue[4] = analogRead(sensePin); 

  PORTB = B00000101;
  sensorValue[5] = analogRead(sensePin); 

  PORTB = B00000110;
  sensorValue[6] = analogRead(sensePin); 

  PORTB = B00000111;
  sensorValue[7] = analogRead(sensePin); 

  PORTB = B00001000;
  sensorValue[8] = analogRead(sensePin); 

  PORTB = B00001001;
  sensorValue[9] = analogRead(sensePin); 

  PORTB = B00001010;
  sensorValue[10] = analogRead(sensePin); 

  PORTB = B00001011;
  sensorValue[11] = analogRead(sensePin); 

  PORTB = B00001100;
  sensorValue[12] = analogRead(sensePin); 

  PORTB = B00001101;
  sensorValue[13] = analogRead(sensePin); 

  PORTB = B00001110;
  sensorValue[14] = analogRead(sensePin); 

  PORTB = B00001111;
  sensorValue[15] = analogRead(sensePin); 
  
  //-------------------------------------
  
  //pinA1
  PORTD = B00000000;
  sensorValue2[0] = analogRead(sensePin2);
  
  PORTD = B00010000;
  sensorValue2[1] = analogRead(sensePin2); 

  PORTD = B00100000;
  sensorValue2[2] = analogRead(sensePin2);

  PORTD = B00110000;
  sensorValue2[3] = analogRead(sensePin2);

  PORTD = B01000000;
  sensorValue2[4] = analogRead(sensePin2);

  PORTD = B01010000;
  sensorValue2[5] = analogRead(sensePin2);

  PORTD = B01100000;
  sensorValue2[6] = analogRead(sensePin2);

  PORTD = B01110000;
  sensorValue2[7] = analogRead(sensePin2);

  PORTD = B10000000;
  sensorValue2[8] = analogRead(sensePin2);

  PORTD = B10010000;
  sensorValue2[9] = analogRead(sensePin2);

  PORTD = B10100000;
  sensorValue2[10] = analogRead(sensePin2);

  delay(1000);
}

void sendAndroidValues(){
  Serial.print('#');
  for(int i = 0; i < MUX0MAX; i++){
    if(sensorValue[i] > 999){
      Serial.print("000");
    }
    else if(sensorValue[i] > 99){
      Serial.print(sensorValue[i]);
    }
    else if(sensorValue[i] > 9){
      Serial.print("0");
      Serial.print(sensorValue[i]);
    }
    else if(sensorValue[i] >= 0){
      Serial.print("00");
      Serial.print(sensorValue[i]);
    }
    else{
      Serial.print("000");
    }
    Serial.print('+');
  }
  for(int i = 0; i < MUX1MAX; i++){
    if(sensorValue2[i] > 999){
      Serial.print("000");
    }
    else if(sensorValue2[i] > 99){
      Serial.print(sensorValue2[i]);
    }
    else if(sensorValue2[i] > 9){
      Serial.print("0");
      Serial.print(sensorValue2[i]);
    }
    else if(sensorValue2[i] >= 0){
      Serial.print("00");
      Serial.print(sensorValue2[i]);
    }
    else{
      Serial.print("000");
    }
    Serial.print('+');
  }
  Serial.print('~');
  Serial.println();
  delay(10);
}

