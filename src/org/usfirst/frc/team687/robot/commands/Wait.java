package org.usfirst.frc.team687.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Wait command 
 * 
 * @author tedfoodlin
 *
 */

public class Wait extends Command {
	
	public double m_timeout;
	public double m_startTime;
	public double m_currentTime;

    public Wait(double timeout) {
    	m_timeout = timeout;
    }

	@Override
	protected void initialize() {
		m_startTime = Timer.getFPGATimestamp();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return Timer.getFPGATimestamp() >= (m_startTime + m_timeout);
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

}
