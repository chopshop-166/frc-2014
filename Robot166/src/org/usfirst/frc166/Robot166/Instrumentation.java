/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc166.Robot166;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author Mark
 */
public class Instrumentation {

    private class TimingRecord {

        private double startTime;
        private boolean isTimerRunning;

        private double lastTime;

        private double totalTime;
        private int samples;

        public void start() {
            startTime = Timer.getFPGATimestamp();
            isTimerRunning = true;
        }

        public void stop() {
            lastTime = Timer.getFPGATimestamp() - startTime;
            isTimerRunning = false;
            totalTime += lastTime;
            samples++;
        }

        public double getTime() {
            if (isTimerRunning) {
                stop();
            }
            return lastTime;
        }

        public double getAverageTime() {
            return totalTime / samples;
        }

    }

    private final java.util.Hashtable timeRecords;

    public Instrumentation() {
        timeRecords = new java.util.Hashtable();
    }

    /**
     * Record the start time to associated with an object
     *
     * @param obj The object to associate with a time
     */
    public void startTiming(Object obj) {
        TimingRecord record = (TimingRecord) (timeRecords.get(obj));

        if (record == null) {
            record = new TimingRecord();
            record.start();
            timeRecords.put(obj, record);
        }
        else {
            record.start();
        }

    }

    /**
     * Record the end time to associated with an object
     *
     * @param obj The object to associate with a time
     */
    public void stopTiming(Object obj) {
        TimingRecord record = (TimingRecord) (timeRecords.get(obj));

        if (record != null) {
            record.stop();
        }
    }

    /**
     * Calculate the execution time of the object and put it on the Smart
     * Dashboard
     *
     * @param obj The object to get the execution time of
     */
    public void logTime(Object obj) {
        TimingRecord record = (TimingRecord) (timeRecords.get(obj));

        if (record != null) {
            record.stop();
            String objTime = obj + " time";
            String objAvg = obj + " average time";

            SmartDashboard.putNumber(objTime, record.getTime());
            SmartDashboard.putNumber(objAvg, record.getAverageTime());
        }
    }

    /**
     * Get the current time since startup
     *
     * @return The number of seconds since startup (microseconds resolution)
     */
    private static Double getCurrentTime() {
        // Has microsecond resolution
        return new Double(Timer.getFPGATimestamp());
    }
}
