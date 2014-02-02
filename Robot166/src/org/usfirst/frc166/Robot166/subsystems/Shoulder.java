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
import org.usfirst.frc166.Robot166.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shoulder extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController motor = RobotMap.shoulderMotor;
    AnalogChannel potentiometer = RobotMap.shoulderPotentiometer;
    DigitalInput ballAcquiredLimit = RobotMap.shoulderBallAcquiredLimit;
    DigitalInput outGuardLimit = RobotMap.shoulderOutGuardLimit;
    DigitalInput inGuardLimt = RobotMap.shoulderInGuardLimt;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATION
    public static final double Shoulder_Speed = 10;
    public static final double Home_Position_Hi = 20;//rename to home position
    public static final double Home_Position_Lo = 10;//rename to home position

    // Put methods for controlling this subsystem
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public boolean isHomePosition() {
        return (getPotValue() >= Home_Position_Lo && getPotValue() <= Home_Position_Hi);
    }

    public boolean isShoulderOut() {
        return outGuardLimit.get();
    }

    public boolean isShoulderIn() {
        return inGuardLimt.get();
    }

    private double getPotValue() {
        return potentiometer.getVoltage();
    }

    public void moveToHome() {
        if (getPotValue() > Home_Position_Hi) {
            motor.set(Shoulder_Speed * -1);
        }
        if (getPotValue() < Home_Position_Lo) {
            motor.set(Shoulder_Speed);
        }
    }

    public void moveToOutPosition() {
        motor.set(Shoulder_Speed);
    }

    public void moveToInPosition() {
        motor.set(Shoulder_Speed * -1);
    }
}
