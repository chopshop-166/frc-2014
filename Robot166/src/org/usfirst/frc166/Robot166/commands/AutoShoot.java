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
        
        //Old AutoShoot from before the Ball Holder was added
//        addSequential(new PullShooterBack());
//        addParallel(new MoveShoulderToInPosition());
//        addParallel(new SpinRollerToRetrieveBall());
//        addSequential(new WaitForBallLoaded());
//        addSequential(new MoveBallManipulatorUp());
//        addParallel(new StopRollerSpinning());
//        addSequential(new WaitCommand(1.0));
//        addParallel(new MoveShoulderToOutPosition());
//        addSequential(new WaitCommand(0.25));
//        addSequential(new ReleaseShooter());
//        addSequential(new PullShooterBack());
//        addSequential(new MoveBallManipulatorDown());
//        addSequential(new MoveShoulderToInPosition());
        
        
        addSequential(new PullShooterBack());
        addSequential(new MoveShoulderToOutPosition());
        addSequential(new MoveBallHolderOut());
        addSequential(new ReleaseShooter());
        addSequential(new PullShooterBack());

        
        

    }
}
