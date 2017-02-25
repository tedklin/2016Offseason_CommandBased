package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.ShooterConstants;
import org.usfirst.frc.team687.robot.constants.ShooterConstants.Location;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Adjust intake angle to specified encoder position (ticks) using PID and a low-pass filter
 * 
 * @author tedfoodlin
 *
 */

public class SetShooterAngle extends Command {

	private double m_desired;
	
    public SetShooterAngle(double desired){
    	m_desired = desired;
    	
        // subsystem dependencies
        requires(Robot.shooter);
    }
	
	public SetShooterAngle(Location location) {
		m_desired = ShooterConstants.getShooterAngle(location);
		
        // subsystem dependencies
        requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
	}

	@Override
	public void execute() {
		Robot.shooter.setLifterPos(m_desired);
	}

	@Override
	protected boolean isFinished() {
		return (Math.abs(m_desired - Robot.shooter.getCurrentAngle()) < 5) || Robot.oi.shotReset_3.get();
	}

	@Override
	protected void end() {
    	Robot.shooter.setLifterPower(0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
