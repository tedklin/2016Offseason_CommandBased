package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.constants.ShooterConstants;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Shooter subsystem
 * 
 * @author tedfoodlin
 *
 */

public class Shooter extends Subsystem {
    
	private CANTalon m_leftFly, m_rightFly;
	
	private DoubleSolenoid m_punch;
	private DoubleSolenoid m_compress;
	private double m_compression;
	private AnalogInput m_lCompress, m_rCompress;
	
	public Shooter() {
		super();
		
		m_leftFly = new CANTalon(RobotMap.lFlyID);
		m_rightFly = new CANTalon(RobotMap.rFlyID);
		
		m_punch = new DoubleSolenoid(RobotMap.punchID1, RobotMap.punchID2);
		m_compress = new DoubleSolenoid(RobotMap.compressID1, RobotMap.compressID2);
		m_lCompress = new AnalogInput(RobotMap.lCompressID);
		m_rCompress = new AnalogInput(RobotMap.rCompressID);
		
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
    
    public void punch() {
    	m_punch.set(DoubleSolenoid.Value.kForward);
    }
    
    public void retract() {
    	m_punch.set(DoubleSolenoid.Value.kReverse);
    }
    
    public double getFlySpeed() {
    	return (m_leftFly.getSpeed() + m_rightFly.getSpeed()) / 2;
    }
    
	/**
	 * Compression to hold ball in place
	 * @param compress (boolean)
	 */
	public void compress(boolean compress) {
		if(compress) {
			m_compress.set(DoubleSolenoid.Value.kForward);
			m_compression = m_lCompress.getVoltage() + m_rCompress.getVoltage();
		} else {
			m_compress.set(DoubleSolenoid.Value.kReverse);
		}
	}

	public double getCompression() {
		return m_compression;
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
    	
    	SmartDashboard.putNumber("Compression", getCompression());
    }
}
