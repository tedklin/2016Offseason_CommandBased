package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Open loop control for intake angle
 * 
 * @author tedfoodlin
 *
 */

public class ManualIntakeAngle extends Command {
	
	public ManualIntakeAngle() {
		// subsystem dependencies
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.intake.setAnglePower(Robot.oi.getArticJoy());
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
