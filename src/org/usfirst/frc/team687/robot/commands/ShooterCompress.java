package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ShooterCompress extends Command {
	
	private boolean m_compress;
	
	public ShooterCompress(boolean compress) {
		m_compress = compress;
		
		// subsystem dependencies
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.shooter.compress(m_compress);
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
