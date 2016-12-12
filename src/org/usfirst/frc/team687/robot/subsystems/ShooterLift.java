package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.constants.ShooterConstants;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Shooter lifter subsystem (to fix subsystem dependencies)
 * 
 * @author tedfoodlin
 *
 */

public class ShooterLift extends Subsystem {

	private CANTalon m_lifter;
	
	public ShooterLift() {
		super();
		
		m_lifter = new CANTalon(RobotMap.shooterLiftID);
		m_lifter.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Absolute);
		
		m_lifter.changeControlMode(TalonControlMode.PercentVbus);
		
		m_lifter.reverseSensor(false);
		m_lifter.reverseOutput(false);

		m_lifter.setP(ShooterConstants.kLiftP);
		m_lifter.setI(ShooterConstants.kLiftI);
		m_lifter.setD(ShooterConstants.kLiftD);
	}
	
	@Override
    public void initDefaultCommand() { /* food */ };
	
    /**
     * Set lifter power 
     * 
     * @param power (between +1.0 and -1.0)
     */
    public void setLifterPower(double pow) {
    	m_lifter.changeControlMode(TalonControlMode.PercentVbus);
    	m_lifter.set(pow);
    }
    
    /**
     * @return current shooter angle
     */
    public double getCurrentAngle() {
    	return m_lifter.getPosition();
    }
    
    /**
     * Stop shooter lift and reset sensors
     */
    public void stop() {
    	setLifterPower(0);
    	resetSensors();
    }
    
    /**
     * Reset sensors
     */
    public void resetSensors() {
    	m_lifter.reset();
    }

}
