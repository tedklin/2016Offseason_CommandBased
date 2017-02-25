package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 * Target high goal with vision info from NerdyVision (pynetworktables + OpenCV Python)
 * 
 * @author tedfoodlin
 *
 */

public class SnapToTarget extends Command {
	
	private NetworkTable m_table;
	private double m_error;
	private double m_angleToTurn;

	public SnapToTarget() {
		// subsystem dependencies
		requires(Robot.drive);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	protected void initialize() {
		m_table = NetworkTable.getTable("NerdyVision");
		m_angleToTurn = m_table.getDouble("ANGLE_TO_TURN");
	}

	@Override
	protected void execute() {
		m_error = m_angleToTurn - Robot.drive.getYaw();
		double power = DrivetrainConstants.kDriveRotationP * m_error;
		Robot.drive.setPower(power, -power);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return Math.abs(m_error) < 1;
	}

	@Override
	protected void end() {
		Robot.drive.stopDrive();
	}

	@Override
	protected void interrupted() {
	}
	
}
