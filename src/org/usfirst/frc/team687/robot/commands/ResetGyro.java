package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Zero gyro
 * 
 * @author tedfoodlin
 *
 */

public class ResetGyro extends Command {
	
	public ResetGyro() {
		// subsystem dependencies
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		Robot.drive.resetGyro();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
