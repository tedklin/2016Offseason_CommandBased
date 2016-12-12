package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Turn drivetrain to specified angle
 * 
 * @author tedfoodlin
 *
 */

public class DriveTurnToAngle extends Command {
	
	private double m_angle;
	private double power;
	private double m_error;
	
	public DriveTurnToAngle(double angle) {
		m_angle = angle;
		
		// subsystem dependencies
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		m_error = m_angle - Robot.drive.getYaw();
		power = DrivetrainConstants.kDriveRotationP * m_error;
		Robot.drive.setSpeed(power, -power);
	}

	@Override
	protected boolean isFinished() {
		return Math.abs(m_error) <= 2;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
