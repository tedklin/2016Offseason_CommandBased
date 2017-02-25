package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Adjust intake angle to specified encoder position (ticks) using PID and a low-pass filter
 * 
 * @author tedfoodlin
 *
 */

public class SetIntakeAngle extends Command {

	private double m_desired;
	
    public SetIntakeAngle(double desired){
    	m_desired = desired;
    	
        // subsystem dependencies
        requires(Robot.intake);
    }
	
	@Override
	protected void initialize() {
	}

	@Override
	public void execute() {
		Robot.intake.setAnglePos(m_desired);
	}

	@Override
	protected boolean isFinished() {
		return (Math.abs(m_desired - Robot.intake.getCurrentAngle()) < 5) || Robot.oi.intakeReset_12.get();
	}

	@Override
	protected void end() {
    	Robot.intake.setAnglePower(0);
	}

	@Override
	protected void interrupted() {
		end();
	}

}
