package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.constants.ShooterConstants;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Shooter subsystem
 * 
 * @author tedfoodlin
 *
 */

public class Shooter extends Subsystem {

	private CANTalon m_lifter;
	
	private DoubleSolenoid m_punch;
	private DoubleSolenoid m_compress;
	private double m_compression;
	private AnalogInput m_lCompress, m_rCompress;
	
	public Shooter() {
		super();
		
		m_lifter = new CANTalon(RobotMap.shooterLiftID);
		m_lifter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		
		m_lifter.changeControlMode(TalonControlMode.PercentVbus);
		
		m_lifter.reverseSensor(false);
		m_lifter.reverseOutput(false);

		m_lifter.setP(ShooterConstants.kLiftP);
		m_lifter.setI(ShooterConstants.kLiftI);
		m_lifter.setD(ShooterConstants.kLiftD);
		
		m_punch = new DoubleSolenoid(RobotMap.punchID1, RobotMap.punchID2);
		m_compress = new DoubleSolenoid(RobotMap.compressID1, RobotMap.compressID2);
		m_lCompress = new AnalogInput(RobotMap.lCompressID);
		m_rCompress = new AnalogInput(RobotMap.rCompressID);
	}
	
	@Override
    public void initDefaultCommand() { /* food */ };
	
    public void setLifterPower(double pow) {
    	m_lifter.changeControlMode(TalonControlMode.PercentVbus);
    	m_lifter.set(pow);
    }
    
    public double getCurrentAngle() {
    	return m_lifter.getPosition();
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
    
    public void punch() {
    	m_punch.set(DoubleSolenoid.Value.kForward);
    }
    
    public void retract() {
    	m_punch.set(DoubleSolenoid.Value.kReverse);
    }
    
    /**
     * Stop shooter lift and reset sensors
     */
    public void stop() {
    	setLifterPower(0);
    	resetSensors();
    }
    
    public void resetSensors() {
    	m_lifter.reset();
    }

    public void reportToSmartDashboard() {
    	SmartDashboard.putNumber("Shooter Angle", getCurrentAngle());
    	
    	SmartDashboard.putNumber("Compression", getCompression());
    }

}
