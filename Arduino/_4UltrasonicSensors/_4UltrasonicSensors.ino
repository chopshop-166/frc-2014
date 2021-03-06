// ---------------------------------------------------------------------------
// This example code was used to successfully communicate with 15 ultrasonic sensors. You can adjust
// the number of sensors in your project by changing SONAR_NUM and the number of NewPing objects in the
// "sonar" array. You also need to change the pins for each sensor for the NewPing objects. Each sensor
// is pinged at 33ms intervals. So, one cycle of all sensors takes 495ms (33 * 15 = 495ms). The results
// are sent to the "oneSensorCycle" function which currently just displays the distance data. Your project
// would normally process the sensor results in this function (for example, decide if a robot needs to
// turn and call the turn function). Keep in mind this example is event-driven. Your complete sketch needs
// to be written so there's no "delay" commands and the loop() cycles at faster than a 33ms rate. If other
// processes take longer than 33ms, you'll need to increase PING_INTERVAL so it doesn't get behind.
// ---------------------------------------------------------------------------
#include <NewPing.h>

#define MAX_DISTANCE 200 // Maximum distance (in cm) to ping.

int incomingByte ;
#define S1_TRIGGER_PIN 2
#define S1_ECHO_PIN 3
#define S2_TRIGGER_PIN 4
#define S2_ECHO_PIN 5
#define S3_TRIGGER_PIN 6
#define S3_ECHO_PIN 7
#define S4_TRIGGER_PIN 8
#define S4_ECHO_PIN 9

#define DEBUG 0


NewPing sonar1(S1_TRIGGER_PIN , S1_ECHO_PIN, MAX_DISTANCE ); // NewPing setup of pins and maximum distance.
NewPing sonar2(S2_TRIGGER_PIN , S2_ECHO_PIN, MAX_DISTANCE );
NewPing sonar3(S3_TRIGGER_PIN , S3_ECHO_PIN, MAX_DISTANCE );
NewPing sonar4(S4_TRIGGER_PIN , S4_ECHO_PIN, MAX_DISTANCE );



void setup() {
  Serial.begin(115200);

}

void loop() {
  unsigned int uS = 0; 
  if (Serial.available() > 0) {
    // read the incoming byte:
    incomingByte = Serial.read();
    // say what you got:

    switch (incomingByte){
    case 49: //character 1
      uS = sonar1.ping();
      if (DEBUG) {
        Serial.print("Sensor 1: ");
      }
      Serial.println(uS / US_ROUNDTRIP_CM); // Convert ping time to distance in cm and print result (0 = outside set distance range)
      break;
    case 50: //character 2
      uS = sonar2.ping();
       if (DEBUG) {
        Serial.print("Sensor 2: ");
      }
      Serial.println(uS / US_ROUNDTRIP_CM); // Convert ping time to distance in cm and print result (0 = outside set distance range)

      break;
    case 51: //character 3
      uS = sonar3.ping();
       if (DEBUG) {
        Serial.print("Sensor 3: ");
      }
      Serial.println(uS / US_ROUNDTRIP_CM); // Convert ping time to distance in cm and print result (0 = outside set distance range)

      break;
    case 52: //character 4
      uS = sonar4.ping();
       if (DEBUG) {
        Serial.print("Sensor 4: ");
      }
      Serial.println(uS / US_ROUNDTRIP_CM); // Convert ping time to distance in cm and print result (0 = outside set distance range)

      break;
    default:

      break;
    }




  }
}

// The rest of your code would go here.









