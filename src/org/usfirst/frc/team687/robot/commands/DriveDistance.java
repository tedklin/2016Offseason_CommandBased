package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants;
import org.usfirst.frc.team687.robot.utilities.MotionProfileGenerator;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive a specified distance (encoder ticks)
 * Using motion profiling feedforward and PD feedback
 * 
 * @author tedfoodlin
 *
 */

public class DriveDistance extends Command {

	private double m_startPos;
	private double m_setpoint;
	private double m_setpointPos;
	private double m_distance;
	
	private double m_startingTime;
	
	private MotionProfileGenerator m_motionProfileGenerator;
	private double m_vmax = DrivetrainConstants.kDriveMaxVelocity;
	private double m_amax = DrivetrainConstants.kDriveMaxAccel;
	private double m_dmax = DrivetrainConstants.kDriveMaxDecel;
	
	private double m_error;
	private double m_lastError;
	
    public DriveDistance(double distance){
    	m_setpoint = distance;
    	
        // subsystem dependencies
        requires(Robot.drive);
    }

    /**
     * Set starting position, starting time, and generate motion profile
     */
    protected void initialize() {
    	m_startPos = Robot.drive.getCurrentPosition();
    	m_distance = m_setpoint - m_startPos;
    	
    	m_motionProfileGenerator = null;
    	m_motionProfileGenerator = new MotionProfileGenerator(m_vmax, m_amax, m_dmax);
    	m_motionProfileGenerator.generateProfile(m_distance);
    	
    	Robot.drive.resetSensors();
    	m_startingTime = Timer.getFPGATimestamp();
    	m_error = 0;
    }

    /**
     * Calculate power based on motion profile
     */
    protected void execute() {
    	m_lastError = m_error;
    	double currentTime = Timer.getFPGATimestamp() - m_startingTime;
		double goalVelocity = m_motionProfileGenerator.readVelocity(currentTime);
		double goalAcceleration = m_motionProfileGenerator.readAcceleration(currentTime);
		m_setpointPos = m_motionProfileGenerator.readPosition(currentTime);
		double actualPos = Robot.drive.getCurrentPosition();
		m_error = m_setpointPos - actualPos;
		double pow = DrivetrainConstants.kDriveTranslationP * m_error 
				+ DrivetrainConstants.kDriveTranslationD * ((m_error - m_lastError) / currentTime - goalVelocity)
				+ DrivetrainConstants.kV * goalVelocity 
				+ DrivetrainConstants.kA * goalAcceleration;
		
		Robot.drive.setPower(pow,  pow);
    }

    /**
     * Is finished when control changes to open-loop or when the elevator reaches setpoint (with tolerance)
     */
    protected boolean isFinished() {
    	return (Math.abs(m_setpoint - Robot.drive.getCurrentPosition()) < 5);
    }
    
    protected void end() {
    	m_motionProfileGenerator = null;
    	Robot.drive.stopDrive();
    }

    protected void interrupted() {
    	m_setpointPos = Robot.drive.getCurrentPosition();
    	m_motionProfileGenerator = null;
    }
    
}
