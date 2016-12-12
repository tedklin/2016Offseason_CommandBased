package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Outtake 
 * 
 * @author tedfoodlin
 *
 */

public class IntakeRollersReverse extends Command {
	
	public IntakeRollersReverse() {
		// subsystem dependencies
		requires(Robot.intake);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.intake.outtake();
	}

	@Override
	protected boolean isFinished() {
		return Robot.oi.intake_9.get() || Robot.oi.intakeOff_1.get();
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
	
}
