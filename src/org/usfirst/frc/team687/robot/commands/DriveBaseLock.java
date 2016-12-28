package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants;
import org.usfirst.frc.team687.robot.utilities.NerdyMath;
import org.usfirst.frc.team687.robot.utilities.NerdyPID;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Locks the drivetrain in place (to counter defense when shooting)
 * 
 * @author tedlin
 *
 */

public class DriveBaseLock extends Command {
	
	private final double m_kRotP = DrivetrainConstants.kDriveRotationP;
	private final double m_kRotI = DrivetrainConstants.kDriveRotationI;
	private final double m_kRotD = DrivetrainConstants.kDriveRotationD;
	
	private final double m_kDistP = DrivetrainConstants.kDriveTranslationP;
	private final double m_kDistI = DrivetrainConstants.kDriveTranslationI;
	private final double m_kDistD = DrivetrainConstants.kDriveTranslationD;
	
	private NerdyPID m_pidRotController;
	private NerdyPID m_pidLeftDistController;
	private NerdyPID m_pidRightDistController;

	private double m_rotPow;
	private double m_leftStraightPow;
	private double m_rightStraightPow;
	
	public DriveBaseLock() {
		m_pidLeftDistController = new NerdyPID(m_kDistP, m_kDistI, m_kDistD);
		m_pidLeftDistController.setDesired(0);
		m_pidRightDistController = new NerdyPID(m_kDistP, m_kDistI, m_kDistD);
		m_pidRightDistController.setDesired(0);
		
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
    	m_rotPow = m_pidRotController.calculate((Robot.drive.getYaw() + 360) % 360);
    	m_leftStraightPow = m_pidLeftDistController.calculate(Robot.drive.getLeftEncoderTicks());
    	m_rightStraightPow = m_pidRightDistController.calculate(Robot.drive.getRightEncoderTicks());
    	double[] pow = {m_rotPow + m_leftStraightPow, -m_rotPow + m_rightStraightPow};
		pow = NerdyMath.normalize(pow, false);
		
		Robot.drive.setSpeed(pow[0], pow[1]);
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
