package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Open loop tank drive with squared inputs
 * 
 * @author tedfoodlin
 *
 */

public class TankDrive extends Command{
	
	private double m_lPow, m_rPow;

    public TankDrive() {
    	// subsystem dependencies
        requires(Robot.drive);
    }

	@Override
	protected void initialize() {
		Robot.drive.stopDrive();
	}

	@Override
	protected void execute() {
		m_lPow = Robot.drive.squaredInput(Robot.oi.getDriveJoyL());
		m_rPow = Robot.drive.squaredInput(Robot.oi.getDriveJoyR());
		Robot.drive.setPower(m_lPow, -m_rPow);
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drive.stopDrive();
	}

	@Override
	protected void interrupted() {
		end();
	}
}
