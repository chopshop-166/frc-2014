// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc166.Robot166;

import org.usfirst.frc166.Robot166.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public Joystick leftDriveJoy;
    public Joystick rightDriveJoy;
    public Joystick copilotJoy;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        copilotJoy = new Joystick(3);

        rightDriveJoy = new Joystick(2);

        leftDriveJoy = new Joystick(1);

        // SmartDashboard Buttons
        SmartDashboard.putData("Perform Autonomous", new PerformAutonomous());
        SmartDashboard.putData("Tank Drive", new TankDrive());
        SmartDashboard.putData("Align Robot To Shooting Distance", new AlignRobotToShootingDistance());
        SmartDashboard.putData("Control Shoulder With Joystick", new ControlShoulderWithJoystick());
        SmartDashboard.putData("Move Shoulder To Out Position", new MoveShoulderToOutPosition());
        SmartDashboard.putData("Move Shoulder To Home Position", new MoveShoulderToHomePosition());
        SmartDashboard.putData("Move Shoulder To Load Position", new MoveShoulderToLoadPosition());
        SmartDashboard.putData("Wait For Ball Acquired", new WaitForBallAcquired());
        SmartDashboard.putData("Spin Roller To Retrieve Ball", new SpinRollerToRetrieveBall());
        SmartDashboard.putData("Spin Roller To Eject Ball", new SpinRollerToEjectBall());
        SmartDashboard.putData("Stop Roller Spinning", new StopRollerSpinning());
        SmartDashboard.putData("Pull Shooter Back", new PullShooterBack());
        SmartDashboard.putData("Release Shooter", new ReleaseShooter());
        SmartDashboard.putData("Shift Transmission Power", new ShiftTransmissionPower());
        SmartDashboard.putData("Shift Transmission Speed", new ShiftTransmissionSpeed());
        SmartDashboard.putData("Auto Feed", new AutoFeed());
        SmartDashboard.putData("Auto Shoot", new AutoShoot());
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getLeftDriveJoy() {
        return leftDriveJoy;
    }

    public Joystick getRightDriveJoy() {
        return rightDriveJoy;
    }

    public Joystick getCopilotJoy() {
        return copilotJoy;
    }
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}
