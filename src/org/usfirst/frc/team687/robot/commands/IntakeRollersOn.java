package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Intake rollers on
 * 
 * @author tedfoodlin
 *
 */

public class IntakeRollersOn extends Command {
	
	public IntakeRollersOn() {
		// subsystem dependencies
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.intake.intake();
	}

	@Override
	protected boolean isFinished() {
		return Robot.oi.intakeOff_1.get() || Robot.oi.outtake_7.get();
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
