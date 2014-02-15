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
    Ultrasonic ultrasonic = RobotMap.driveUltrasonic;
    SpeedController leftvictor1 = RobotMap.driveLeftvictor1;
    SpeedController leftvictor2 = RobotMap.driveLeftvictor2;
    SpeedController rightvictor1 = RobotMap.driveRightvictor1;
    SpeedController rightvictor2 = RobotMap.driveRightvictor2;
    RobotDrive driveVictors = RobotMap.driveDriveVictors;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private static final double DRIVE_SPEED = 0.5;
    private static final double DRIVE_TURN_CONSTANT = 0.05;
//method that causes the robot to drive using joysticks.

    public void joystickDrive4() {
        driveVictors.tankDrive(-Robot.oi.getLeftDriveJoy().getY(),
                -Robot.oi.getRightDriveJoy().getY());
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
        if (Robot.drive.gyro.getAngle() != 0) {
            if (getCurrentAngle() - 180 > 0) {
                driveVictors.drive(DRIVE_SPEED, Robot.drive.gyro.getAngle() * DRIVE_TURN_CONSTANT);

            }
            else if (getCurrentAngle() - 180 < 0) {
                driveVictors.drive(DRIVE_SPEED, -1 * Robot.drive.gyro.getAngle() * DRIVE_TURN_CONSTANT);
            }
            else {
                driveVictors.drive(DRIVE_SPEED, -1 * Robot.drive.gyro.getAngle() * DRIVE_TURN_CONSTANT);
            }
        }
    }
//determines whether wall is close enough for stop

    public void driveStraightNoGyro() {
        //double startTime = Robot.timing.getCurrentAutonomousTime();
        //while ((Robot.timing.getCurrentAutonomousTime() - startTime) < .75) {
        driveVictors.drive(DRIVE_SPEED, 0.0);
        //}
        //driveVictors.drive(0.0, 0.0);
    }

    public boolean isWallNear() {
        return false;
//        try {
//            int distance = getUltrasonicVal();
//            if (distance == 0) {
//                distance = 666;
//            }
//            SmartDashboard.putNumber("wall distance: ", distance);
//            return distance < 90;
//        }
//        catch (IOException e) {
//            return false;
//        }
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
