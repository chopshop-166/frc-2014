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
import edu.wpi.first.wpilibj.command.StartCommand;
import org.usfirst.frc166.Robot166.Robot;
import org.usfirst.frc166.Robot166.subsystems.Shoulder;
/**
 *
 */
public class CheckShoulderPosition extends Command {
    public CheckShoulderPosition() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shoulder);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    // Called just before this Command runs the first time
    protected void initialize() {
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Shoulder.Position position = Robot.shoulder.getCurrentPosition();
        // If supposed to be 'Home', but not there, drive to Home
        if (position == Shoulder.Position.Home && !Robot.shoulder.isHomePosition()) {
            new StartCommand(new MoveShoulderToHomePosition()).start();
        }
        // If supposed to be 'In', but not there, drive to In
        else if (position == Shoulder.Position.In && !Robot.shoulder.isShoulderIn()) {
            new StartCommand(new MoveShoulderToLoadPosition()).start();
        }
        // If supposed to be 'Out', but not there, drive to Out
        else if (position == Shoulder.Position.Out && !Robot.shoulder.isShoulderOut()) {
            new StartCommand(new MoveShoulderToOutPosition()).start();
        }
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {
    }
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
