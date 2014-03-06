// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc166.Robot166.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc166.Robot166.Robot;
/**
 *
 */
public class DriveStraight extends Command {
    boolean hasDriven = false;
    public DriveStraight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.drive);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.drive.resetGyro();
        Robot.drive.zeroDistance();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drive.driveStraight();
        //Robot.drive.driveStraight();
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.drive.isWallNear();
    }
    // Called once after isFinished returns true
    protected void end() {
        Robot.drive.stopMotors();
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    //    Robot.drive.initDefaultCommand();
        end();
    }
}
