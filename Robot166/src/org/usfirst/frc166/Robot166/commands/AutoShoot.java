// RobotBuilder Version: 1.0
//
package org.usfirst.frc166.Robot166.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShoot extends CommandGroup {

    public AutoShoot() {

        addParallel(new MoveShoulderToHomePosition());
        addParallel(new SpinRollerToRetrieveBall());
        addSequential(new WaitForBallAcquired());
        addSequential(new MoveBallManipulatorUp());
        addSequential(new WaitForBallLoaded());
        addParallel(new StopRollerSpinning());
        addParallel(new MoveShoulderToOutPosition());
        addSequential(new ReleaseShooter());
        addSequential(new PullShooterBack());

    }
}
