package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants;
import org.usfirst.frc.team687.robot.utilities.NerdyMath;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A simplified implementation of 254's CheesyDrive
 *
 * @author tedfoodlin
 *
 */

public class HaloDrive extends Command{
	
	private boolean m_isQuickTurn;
	private double m_wheel, m_throttle;
	private double m_rPow, m_lPow;
	private double m_angularPow;
	private double m_sensitivity;
	
	public HaloDrive() {
		//subsystem requirements
		requires(Robot.drive);
	}
	
	@Override
	protected void initialize() {
		Robot.drive.stopDrive();
	}

	@Override
	protected void execute() {
		m_isQuickTurn = Robot.oi.getQuickTurn();
		
	    m_wheel = Robot.drive.handleDeadband(Robot.oi.driveJoyRight.getX(), DrivetrainConstants.wheelDeadband);
	    m_throttle = -Robot.drive.handleDeadband(Robot.oi.driveJoyLeft.getY(), DrivetrainConstants.throttleDeadband);
	    
	    m_rPow = m_lPow = m_throttle;
	    
	    if (Robot.drive.isHighGear()) {
	    	m_sensitivity = DrivetrainConstants.kSensitivityHigh;
	    } else {
	    	m_sensitivity = DrivetrainConstants.kSensitivityLow;
	    }
	    
	    if (m_isQuickTurn && Math.abs(m_throttle) <= 0.1) {
	    	m_angularPow = m_wheel;
	    } else {
	    	m_angularPow = Math.abs(m_throttle) * m_wheel * m_sensitivity;
	    }
	    
	    m_lPow += m_angularPow;
	    m_rPow -= m_angularPow;
	    
	    double[] pow = {m_lPow, m_rPow};
	    pow = NerdyMath.normalize(pow, false);
		Robot.drive.setPower(pow[0], pow[1]);
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
