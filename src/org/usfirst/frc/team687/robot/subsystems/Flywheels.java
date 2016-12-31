package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.constants.ShooterConstants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Flywheels subsystem
 * 
 * @author tedfoodlin
 *
 */

public class Flywheels extends Subsystem {
    
	private CANTalon m_leftFly, m_rightFly;
	
	public Flywheels() {
		super();
		
		m_leftFly = new CANTalon(RobotMap.lFlyID);
		m_rightFly = new CANTalon(RobotMap.rFlyID);
		
		m_leftFly.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		m_rightFly.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		
		m_leftFly.changeControlMode(TalonControlMode.Speed);
		m_rightFly.changeControlMode(TalonControlMode.Speed);
		
		m_leftFly.enableBrakeMode(false);
		m_rightFly.enableBrakeMode(false);

		m_leftFly.setP(ShooterConstants.kFlywheelP);
		m_leftFly.setI(ShooterConstants.kFlywheelI);
		m_leftFly.setD(ShooterConstants.kFlywheelD);

		m_rightFly.setP(ShooterConstants.kFlywheelP);
		m_rightFly.setI(ShooterConstants.kFlywheelI);
		m_rightFly.setD(ShooterConstants.kFlywheelD);
		
		m_leftFly.reverseSensor(true);
		m_leftFly.reverseOutput(true);
		m_rightFly.reverseSensor(false);
		m_rightFly.reverseOutput(false);
	}

	@Override
    public void initDefaultCommand() { /* food */ };
    
	public void setFlySpeed(double desiredRate) {
		if (desiredRate == 0){
			stopFly();
		} 
		else {
			m_leftFly.changeControlMode(TalonControlMode.Speed);
			m_rightFly.changeControlMode(TalonControlMode.Speed);
			m_leftFly.set(desiredRate);
			m_rightFly.set(desiredRate);
		}
	}
	
	public void stopFly() {
		m_leftFly.changeControlMode(TalonControlMode.PercentVbus);
		m_rightFly.changeControlMode(TalonControlMode.PercentVbus);
		m_leftFly.set(0);
		m_rightFly.set(0);
	}
    
    public double getFlySpeed() {
    	return (m_leftFly.getSpeed() + m_rightFly.getSpeed()) / 2;
    }
    
    /**
     * Stop flywheels and reset sensors
     */
    public void stop() {
    	stopFly();
    	resetSensors();
    }
    
    public void resetSensors() {
    	m_leftFly.reset();
    	m_rightFly.reset();
    }
    
    public void reportToSmartDashboard() {
    	SmartDashboard.putNumber("Left Flywheel speed", m_leftFly.getSpeed());
    	SmartDashboard.putNumber("Right Flywheel speed", m_rightFly.getSpeed());
    }
}
