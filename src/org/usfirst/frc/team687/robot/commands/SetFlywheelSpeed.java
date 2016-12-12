package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.ShooterConstants;
import org.usfirst.frc.team687.robot.constants.ShooterConstants.Location;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Set flywheel speed
 * 
 * @author tedfoodlin
 *
 */
public class SetFlywheelSpeed extends Command {
	
	private double m_desiredRate;
	
	public SetFlywheelSpeed(Location location) {
		m_desiredRate = ShooterConstants.getFlywheelSpeed(location);
		
        // subsystem dependencies
        requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.shooter.setFlySpeed(m_desiredRate);
	}

	@Override
	protected boolean isFinished() {
		return m_desiredRate == 0;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
	
}
