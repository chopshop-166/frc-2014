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
public class PullShooterBack extends Command {

    public PullShooterBack() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooter);
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        //not necessary; the limit switch tells you when to stop
        //this.setTimeout(3); //Runs for 3 second(s).
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.shooter.pullShooterBack();
    }

    // When this Command no longer needs to run execute() return value of reed sensor
    protected boolean isFinished() {
        return Robot.shooter.isCockedBack();
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.shooter.stopShooter();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
       end();
    }
}
