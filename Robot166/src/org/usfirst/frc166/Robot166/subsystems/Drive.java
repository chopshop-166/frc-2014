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
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import java.io.IOException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import org.usfirst.frc166.Robot166.Robot;
import org.usfirst.frc166.Robot166.commands.TankDrive;

/**
 *
 */
public class Drive extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    Gyro gyro = RobotMap.driveGyro;
    AnalogChannel temperature = RobotMap.driveTemperature;
    SpeedController leftvictor1 = RobotMap.driveLeftvictor1;
    SpeedController leftvictor2 = RobotMap.driveLeftvictor2;
    SpeedController rightvictor1 = RobotMap.driveRightvictor1;
    SpeedController rightvictor2 = RobotMap.driveRightvictor2;
    RobotDrive driveVictors = RobotMap.driveDriveVictors;
    AnalogChannel ultrasonic = RobotMap.driveUltrasonic;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private static final double DRIVE_SPEED = .55; //added to this without testing;
    private static final double DRIVE_TURN_CONSTANT = 0.025;
    private static final double GYRO_DEADZONE = 0.5;
    private static final double DISTANCE_TO_WALL = 100.0;
    private static final double ULTRASONIC_VOLTSTOIN = 1 / 0.0098;
    private static final double OFFSET_PROPORTION = 1.0 / 16.0;
    private static final double OFFSET_DEADZONE = 4;
    private static final double OFFSET_MAX_SPEED = .7;
    private double distance = 0;
    private int distanceCount = 0;
    private double offset = 0;
    private double alignSpeed = 0;
//method that causes the robot to drive using joysticks.

    public void joystickDrive4() {
        /* double deadZone = 0.08;
         * if (((Robot.oi.getLeftDriveJoy().getY() > deadZone) && (Robot.oi.getRightDriveJoy().getY() > deadZone))
         * || ((Robot.oi.getLeftDriveJoy().getY() < -deadZone) && (Robot.oi.getRightDriveJoy().getY() < -deadZone))) { */
        driveVictors.tankDrive(-Robot.oi.getLeftDriveJoy().getY(), -Robot.oi.getRightDriveJoy().getY(), true);
        //SmartDashboard.putString("DriveState", "Straight/Backwards");
        /* }
         * else {
         * double maxLeftJoy = Math.max(Math.abs(Robot.oi.getLeftDriveJoy().getY()), 0.15) * (Robot.oi.getLeftDriveJoy().getY() / Math.abs(Robot.oi.getLeftDriveJoy().getY()));
         * double maxRightJoy = Math.max(Math.abs(Robot.oi.getRightDriveJoy().getY()), 0.15) * (Robot.oi.getRightDriveJoy().getY() / Math.abs(Robot.oi.getRightDriveJoy().getY()));
         * driveVictors.tankDrive(-maxLeftJoy, -maxRightJoy, false);
         * SmartDashboard.putString("DriveState", "Turnin-g");
         * } */
    }

    //Method causes the robt to drive toward the gyro 0 value
    public void resetGyro() {
        Robot.drive.gyro.reset();
    }

    private double getCurrentAngle() {
        return Robot.drive.gyro.getAngle() % 360;
    }

    public void driveStraight() {
        SmartDashboard.putNumber("Gyro", Robot.drive.gyro.getAngle());
        driveVictors.drive(DRIVE_SPEED, -1 * Robot.drive.gyro.getAngle() * DRIVE_TURN_CONSTANT);
    }

    public void driveStraightFast() {
        SmartDashboard.putNumber("Gyro", Robot.drive.gyro.getAngle());
        driveVictors.drive(DRIVE_SPEED + .25, -1 * Robot.drive.gyro.getAngle() * DRIVE_TURN_CONSTANT);
    }
//determines whether wall is close enough for stop

    public void driveStraightNoGyro() {
        //double startTime = Robot.timing.getCurrentAutonomousTime();
        //while ((Robot.timing.getCurrentAutonomousTime() - startTime) < .75) {
        driveVictors.drive(DRIVE_SPEED, 0.0);
        //}
        //driveVictors.drive(0.0, 0.0);
    }

    public void printUltrasonicValue() {
        SmartDashboard.putNumber("Current Ultra.", Robot.drive.getCrioUltrasonicVal());
    }

    public void alignToShootDistance() {
        distance = getCrioUltrasonicVal();
        offset = distance - DISTANCE_TO_WALL;
        if ((offset * OFFSET_PROPORTION > OFFSET_MAX_SPEED) && (offset * OFFSET_PROPORTION > 0)) {
            alignSpeed = OFFSET_MAX_SPEED;
            //  recommend limit for negative side too ...
        }
        else if ((offset * OFFSET_PROPORTION < OFFSET_MAX_SPEED) && (offset * OFFSET_PROPORTION > 0)) {
            alignSpeed = offset * OFFSET_PROPORTION;
        }
        else if ((offset * OFFSET_PROPORTION < -OFFSET_MAX_SPEED) && (offset * OFFSET_PROPORTION < 0)) {
            alignSpeed = -OFFSET_MAX_SPEED;
            //  recommend limit for negative side too ...
        }
        else if ((offset * OFFSET_PROPORTION > -OFFSET_MAX_SPEED) && (offset * OFFSET_PROPORTION < 0)) {
            alignSpeed = offset * OFFSET_PROPORTION * -1;
        }

        driveVictors.drive(alignSpeed, -1 * Robot.drive.gyro.getAngle() * DRIVE_TURN_CONSTANT);

    }

    public boolean isAtShootingRange() {
        return (((offset > 0) && (offset < OFFSET_DEADZONE)) || ((offset < 0) && (offset > -OFFSET_DEADZONE)));

    }

    public void zeroDistance() {
        distance = 0;
    }

    public boolean isWallNear() {
        distance = getCrioUltrasonicVal();
        System.out.println(distance);
        System.out.println(Robot.timing.getCurrentAutonomousTime());
        if ((distance < DISTANCE_TO_WALL) && (distance > 2)) {
            //distanceCount += 1;
            return true;
        }
        else {
            //distanceCount = 0;
            return false;
        }
        /*
         * if (distanceCount >= 2) {
         * SmartDashboard.putString("is wall near?: ", "yep");
         * return true;
         * }
         * else {
         * SmartDashboard.putString("is wall near?: ", "false");
         * return false;
         * }
         */
    }

    double getCrioUltrasonicVal() {
        return (ultrasonic.getVoltage() * ULTRASONIC_VOLTSTOIN);
    }

    public void stopMotors() {
        driveVictors.stopMotor();
    }

    int getUltrasonicVal() throws IOException {
        HttpConnection c = null;
        InputStream is = null;
        int rc;
        String DataURL = "http://10.1.66.228/";
        try {
            c = (HttpConnection) Connector.open(DataURL);
            // Getting the response code will open the connection,
            // send the request, and read the HTTP response headers.
            // The headers are stored until requested.
            rc = c.getResponseCode();
            System.out.println("rc: " + rc);
            if (rc != HttpConnection.HTTP_OK) {
                throw new IOException("HTTP response code: " + rc);
            }
            is = c.openInputStream();
            // Get the ContentType
            String type = c.getType();
            // Get the length and process the data
            int actual = 0;
            int bytesread = 0;
            byte[] data = new byte[4];
            while ((bytesread != data.length) && (actual != -1)) {
                actual = is.read(data, bytesread, data.length - bytesread);
                bytesread += actual;
            }
            // Do something here to convert 'data' to a string...
            String dataString = new String(data).trim();
            System.out.println(Integer.parseInt(dataString));
            return Integer.parseInt(dataString);
        }
        catch (ClassCastException e) {
            throw new IllegalArgumentException("-1");
        }
        finally {
            if (is != null) {
                is.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }
// Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new TankDrive());
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
