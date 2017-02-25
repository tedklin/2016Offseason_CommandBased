package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants;
import org.usfirst.frc.team687.robot.utilities.NerdyMath;
import org.usfirst.frc.team687.robot.utilities.NerdyPID;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drive straight distance using PID (mainly for crossing defenses during auto)
 * 
 * @author Wesley
 * 
 * Modified by tedfoodlin to work as a Command
 */

public class DriveStraightDistancePID extends Command {
	
	private final double m_kRotP = DrivetrainConstants.kDriveRotationP;
	private final double m_kRotI = DrivetrainConstants.kDriveRotationI;
	private final double m_kRotD = DrivetrainConstants.kDriveRotationD;
	
	private final double m_kDistP = DrivetrainConstants.kDriveTranslationP;
	private final double m_kDistI = DrivetrainConstants.kDriveTranslationI;
	private final double m_kDistD = DrivetrainConstants.kDriveTranslationD;
	
	private NerdyPID m_pidRotController;
	private NerdyPID m_pidDistController;
	
	private double m_setpoint;

	private double rotPow;
	private double straightPow;
	
	public DriveStraightDistancePID(double distance) {
		m_pidDistController = new NerdyPID(m_kDistP, m_kDistI, m_kDistD);
		m_pidDistController.setDesired(distance);
		
		m_pidRotController = new NerdyPID(m_kRotP, m_kRotI, m_kRotD);
		m_pidRotController.setDesired(0);
		
    	// subsystem dependencies
		requires(Robot.drive);
	}

	@Override
	protected void initialize() {
		Robot.drive.stopDrive();
		Robot.drive.shiftDown();
		Robot.drive.resetSensors();
	}

	@Override
	protected void execute() {
    	rotPow = m_pidRotController.calculate((Robot.drive.getYaw() + 360) % 360);
    	straightPow = m_pidDistController.calculate((Robot.drive.getLeftEncoderTicks() + Robot.drive.getRightEncoderTicks())/2);
    	double[] pow = {rotPow + straightPow, -rotPow + straightPow};
		pow = NerdyMath.normalize(pow, false);
		
		Robot.drive.setPower(pow[0], pow[1]);
	}

	@Override
	protected boolean isFinished() {
		return (Math.abs(m_setpoint - Robot.drive.getCurrentPosition()) < 5);
	}

	@Override
	protected void end() {
		Robot.drive.stopDrive();
	}

	@Override
	protected void interrupted() {
	}

}
