// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc166.Robot166;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import java.util.Vector;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static SpeedController driveRightMotorA;
    public static SpeedController driveRightMotorB;
    public static SpeedController driveLeftMotorA;
    public static SpeedController driveLeftMotorB;
    public static RobotDrive driveDrive4;
    public static Gyro driveGyro;
    public static Accelerometer driveAccelerometer;
    public static Ultrasonic driveUltrasonic;
    public static SpeedController shoulderMotor;
    public static AnalogChannel shoulderPotentiometer;
    public static DigitalInput shoulderBallAcquiredLimit;
    public static DigitalInput shoulderOutGuardLimit;
    public static DigitalInput shoulderInGuardLimt;
    public static SpeedController rollerMotor;
    public static SpeedController shooterMotor;
    public static DigitalInput shooterReedSwitch;
    public static Solenoid transmissionSolenoid;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static void init() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveRightMotorA = new Victor(1, 1);
        LiveWindow.addActuator("Drive", "Right Motor A", (Victor) driveRightMotorA);

        driveRightMotorB = new Victor(1, 2);
        LiveWindow.addActuator("Drive", "Right Motor B", (Victor) driveRightMotorB);

        driveLeftMotorA = new Victor(1, 3);
        LiveWindow.addActuator("Drive", "Left Motor A", (Victor) driveLeftMotorA);

        driveLeftMotorB = new Victor(1, 4);
        LiveWindow.addActuator("Drive", "Left Motor B", (Victor) driveLeftMotorB);

        driveGyro = new Gyro(1, 1);
        LiveWindow.addSensor("Drive", "Gyro", driveGyro);
        driveGyro.setSensitivity(0.007);
        driveAccelerometer = new Accelerometer(1, 2);
        LiveWindow.addSensor("Drive", "Accelerometer", driveAccelerometer);
        driveAccelerometer.setSensitivity(1.0);
        driveAccelerometer.setZero(2.5);
        driveUltrasonic = new Ultrasonic(1, 1, 1, 2);
        LiveWindow.addSensor("Drive", "Ultrasonic", driveUltrasonic);

        shoulderMotor = new Victor(1, 5);
        LiveWindow.addActuator("Shoulder", "Motor", (Victor) shoulderMotor);

        shoulderPotentiometer = new AnalogChannel(1, 3);
        LiveWindow.addSensor("Shoulder", "Potentiometer", shoulderPotentiometer);

        shoulderBallAcquiredLimit = new DigitalInput(1, 3);
        LiveWindow.addSensor("Shoulder", "Ball Acquired Limit", shoulderBallAcquiredLimit);

        shoulderOutGuardLimit = new DigitalInput(1, 4);
        LiveWindow.addSensor("Shoulder", "Out Guard Limit", shoulderOutGuardLimit);

        shoulderInGuardLimt = new DigitalInput(1, 5);
        LiveWindow.addSensor("Shoulder", "In Guard Limt", shoulderInGuardLimt);

        rollerMotor = new Victor(1, 6);
        LiveWindow.addActuator("Roller", "Motor", (Victor) rollerMotor);

        shooterMotor = new Victor(1, 7);
        LiveWindow.addActuator("Shooter", "Motor", (Victor) shooterMotor);

        shooterReedSwitch = new DigitalInput(1, 6);
        LiveWindow.addSensor("Shooter", "Reed Switch", shooterReedSwitch);

        transmissionSolenoid = new Solenoid(1, 1);
        LiveWindow.addActuator("Transmission", "Solenoid", transmissionSolenoid);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }
}
