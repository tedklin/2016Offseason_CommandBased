package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.commands.ManualIntakeAngle;
import org.usfirst.frc.team687.robot.constants.IntakeConstants;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Intake subsystem
 * 
 * @author tedfoodlin
 *
 */

public class Intake extends Subsystem {
	
	private CANTalon m_intake;
	private CANTalon m_intakeAngle;
    
	public Intake() {
		super();
		
    	m_intakeAngle.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
    	m_intake.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);

		m_intakeAngle.changeControlMode(TalonControlMode.Position);

		m_intakeAngle.reverseSensor(false);
		m_intakeAngle.reverseOutput(false);
		m_intakeAngle.enableBrakeMode(true);
		
		m_intakeAngle.setF(IntakeConstants.kIntakeF);
		m_intakeAngle.setP(IntakeConstants.kIntakeP);
		m_intakeAngle.setI(IntakeConstants.kIntakeI);
		m_intakeAngle.setD(IntakeConstants.kIntakeD);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new ManualIntakeAngle());
    }
    
    public double getCurrentAngle() {
    	return m_intakeAngle.getPosition();
    }
    
    public void setAnglePos(double pos) {
    	m_intakeAngle.changeControlMode(TalonControlMode.Position);
    	m_intakeAngle.set(pos);
    }
    
    public void setAnglePower(double pow) {
    	m_intakeAngle.changeControlMode(TalonControlMode.PercentVbus);
    	m_intakeAngle.set(pow);
    }

    public void intake() {
    	m_intake.set(0.7);
    }

    public void outtake() {
    	m_intake.set(-0.7);
    }
    
    /**
     * Stop intake rollers and angle adjustment
     * Reset sensors
     */
    public void stop() {
    	m_intake.set(0);
    	setAnglePower(0);
    	
    	resetSensors();
    }

    public void resetSensors() {
    	m_intake.reset();
    	m_intakeAngle.reset();
    }

    public void reportToSmartDashboard() {
    	SmartDashboard.putNumber("Intake Angle", getCurrentAngle());
    }
}
