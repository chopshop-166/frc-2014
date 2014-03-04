// RobotBuilder Version: 1.0
//
package org.usfirst.frc166.Robot166.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoShoot extends CommandGroup {

    public AutoShoot() {
        addSequential(new PullShooterBack());
        addParallel(new MoveShoulderToLoadPosition());
        addParallel(new SpinRollerToRetrieveBall());
        addSequential(new WaitForBallLoaded());
        addSequential(new MoveBallManipulatorUp());
        addParallel(new StopRollerSpinning());
        addSequential(new WaitCommand(1.0));
        addParallel(new MoveShoulderToOutPosition());
        addSequential(new ReleaseShooter());
        addSequential(new PullShooterBack());
        addSequential(new MoveBallManipulatorDown());

    }
}
