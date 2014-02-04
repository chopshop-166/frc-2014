// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc166.Robot166.subsystems;

import org.usfirst.frc166.Robot166.RobotMap;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc166.Robot166.Robot;

public class Shoulder extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController motor = RobotMap.shoulderMotor;
    AnalogChannel potentiometer = RobotMap.shoulderPotentiometer;
    DigitalInput ballAcquiredLimit = RobotMap.shoulderBallAcquiredLimit;
    DigitalInput outGuardLimit = RobotMap.shoulderOutGuardLimit;
    DigitalInput inGuardLimt = RobotMap.shoulderInGuardLimt;
//    Joystick shoulderJoy = Robot.oi.copilotJoy;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATION
    public static final double SHOULDER_SPEED = 10;
    public static final double HOME_POSITION_HIGH = 20;//rename to home position
    public static final double HOME_POSITION_LOW = 10;//rename to home position
    public static double yJoy;

    // Put methods for controlling this subsystem
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public boolean isHomePosition() {
        return (getPotentiometerValue() >= HOME_POSITION_LOW && getPotentiometerValue() <= HOME_POSITION_HIGH);
    }

    public boolean isShoulderOut() {
        return outGuardLimit.get();
    }

    public boolean isShoulderIn() {
        return inGuardLimt.get();
    }

    private double getPotentiometerValue() {
        return potentiometer.getVoltage();
    }

    public void moveToHome() {
        if (getPotentiometerValue() > HOME_POSITION_HIGH) {
            motor.set(SHOULDER_SPEED * -1);
        }
        if (getPotentiometerValue() < HOME_POSITION_LOW) {
            motor.set(SHOULDER_SPEED);
        }
    }

    public void moveToOutPosition() {
        motor.set(SHOULDER_SPEED);
    }

    public void moveToInPosition() {
        motor.set(SHOULDER_SPEED * -1);
    }

    public void shoulderDrive() {
        // yJoy = shoulderJoy.getAxis(Joystick.AxisType.kY);
        motor.set(yJoy);
    }
}
