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

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc166.Robot166.Robot;

/**
 *
 */
public class PerformAutonomous extends CommandGroup {

    public PerformAutonomous() {
        addSequential(new ShiftTransmissionPower());

        addSequential(new PerformVisionAction(), 2);
        addParallel(new MoveShoulderToHomePosition());
        addSequential(new DriveStraight());
        addSequential(new AutonomousWait(), 4);
        addSequential(new AutoShoot());
        addSequential(new ShiftTransmissionSpeed());

        //addParallel(new PerformVisionAction());
        //addSequential(new PerformVisionAction());
        //addParallel(new DriveStraight());
        // Add Commands here:e
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.
        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
