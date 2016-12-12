package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Shooter punch command
 * 
 * @author tedfoodlin
 *
 */
public class ShooterPunch extends Command {
	
	private double m_startingTime;
	
	public ShooterPunch() {
		// subsystem dependencies
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		m_startingTime = Timer.getFPGATimestamp();
	}

	@Override
	protected void execute() {
		Robot.shooter.punch();
	}

	@Override
	protected boolean isFinished() {
		return Timer.getFPGATimestamp() >= m_startingTime + 2;
	}

	@Override
	protected void end() {
		Robot.shooter.retract();
	}

	@Override
	protected void interrupted() {
		Robot.shooter.retract();
	}

}
