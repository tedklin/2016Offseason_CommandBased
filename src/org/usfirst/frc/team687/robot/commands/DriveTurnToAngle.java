package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Turn drivetrain to specified angle
 * 
 * @author tedfoodlin
 *
 */

public class DriveTurnToAngle extends Command {
	
	private double m_angleToTurn;
	private double m_robotAngle;
	private double m_error;
	private int m_counter = 0;
	private double m_startTime;
	private double m_timeout;
	
	public DriveTurnToAngle(double angle) {
		m_angleToTurn = angle;
		m_timeout = 3; // default timeout is 3 seconds
		
		// subsystem dependencies
		requires(Robot.drive);
	}
	
	public DriveTurnToAngle(double angle, double timeout) {
		m_angleToTurn = angle;
		m_timeout = timeout;
		
		// subsystem dependencies
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		m_startTime = Timer.getFPGATimestamp();
	}

	@Override
	protected void execute() {
		m_robotAngle = (360-Robot.drive.getYaw()) % 360;
		m_error = m_angleToTurn - m_robotAngle;
		m_error = (m_error > 180) ? m_error-360 : m_error;
		m_error = (m_error < -180) ? m_error+360 : m_error;
		double power = DrivetrainConstants.kDriveRotationP * m_error;
		if (Math.abs(m_error) <= DrivetrainConstants.kDriveRotationTolerance) {
			m_counter += 1;
		}	else	{
			m_counter = 0;
		}
		Robot.drive.setPower(power, -power);
	}

	@Override
	protected boolean isFinished() {
		return m_counter > DrivetrainConstants.kDriveRotationOscillationCount || Timer.getFPGATimestamp() - m_startTime > m_timeout;
	}

	@Override
	protected void end() {
		Robot.drive.stopDrive();
	}

	@Override
	protected void interrupted() {
		end();
	}

}
