package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Open loop control for intake angle
 * 
 * @author tedfoodlin
 *
 */

public class MoveIntakeAngle extends Command {
	
	public MoveIntakeAngle() {
		// subsystem dependencies
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
		Robot.intake.setAnglePower(Robot.oi.getArticJoy());
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
