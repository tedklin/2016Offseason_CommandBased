package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Shift to high gear
 * 
 * @author tedfoodlin
 *
 */

public class ShiftLow extends Command {
	
	public ShiftLow() {
		// subsystem dependencies
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		Robot.drive.shiftDown();
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
