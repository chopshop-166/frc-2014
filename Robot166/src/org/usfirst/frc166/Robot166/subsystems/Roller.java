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
/**
 *
 */
public class Roller extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    SpeedController motor = RobotMap.rollerMotor;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    private static final double ROLLSPEED = .800;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void ballIntake() {
        motor.set(ROLLSPEED);
    }
    public void ballExpel() {
        motor.set(ROLLSPEED * (-1));
    }
    public void stopRolling() {
        motor.set(0);
    }
}
