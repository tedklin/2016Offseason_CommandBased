package org.usfirst.frc.team687.robot.subsystems;

import org.usfirst.frc.team687.robot.RobotMap;
import org.usfirst.frc.team687.robot.commands.TankDrive;
import org.usfirst.frc.team687.robot.utilities.Gearbox;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drivetrain subsystem
 * 
 * @author tedfoodlin
 *
 */

public class Drive extends Subsystem {
   
	private VictorSP m_rDrive1, m_rDrive2, m_rDrive3, m_lDrive1, m_lDrive2, m_lDrive3;
	private Encoder m_rEncoder, m_lEncoder;
	private DoubleSolenoid m_shifter;
	private Gearbox m_leftGearbox, m_rightGearbox;
	private AHRS m_nav;
	
	public Drive() {
		super();
		
		m_rDrive1 = new VictorSP(RobotMap.rDriveID1);
		m_rDrive2 = new VictorSP(RobotMap.rDriveID2);
		m_rDrive3 = new VictorSP(RobotMap.rDriveID3);
		m_lDrive1 = new VictorSP(RobotMap.lDriveID1);
		m_lDrive2 = new VictorSP(RobotMap.lDriveID2);
		m_lDrive3 = new VictorSP(RobotMap.lDriveID3);
		
		m_rEncoder = new Encoder(RobotMap.rEncoderID1, RobotMap.rEncoderID2);
		m_lEncoder = new Encoder(RobotMap.lEncoderID1, RobotMap.lEncoderID2);
		
		m_leftGearbox = new Gearbox(m_lDrive1, m_lDrive2, m_lDrive3, m_lEncoder, m_shifter);
		m_rightGearbox = new Gearbox(m_rDrive1, m_rDrive2, m_rDrive3, m_rEncoder, m_shifter);
		m_rightGearbox.setReversed();
		
		m_nav = new AHRS(RobotMap.AHRSPort);
		
		m_shifter = new DoubleSolenoid(RobotMap.shifterID1, RobotMap.shifterID2);
		shiftDown();
	}

    public void initDefaultCommand() {
        setDefaultCommand(new TankDrive());
    }
    
    /**
     * Set drivetrain motor power to value between -1.0 and +1.0
     * 
     * @param left power
     * @param right power
     */
    public void setSpeed(double lPow, double rPow) {
    	m_leftGearbox.setSpeed(lPow);
    	m_rightGearbox.setSpeed(rPow);
    }
    
    /**
     * Shift to high gear
     */
    public void shiftUp() {
    	m_shifter.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * Shift to low gear
     */
    public void shiftDown() {
    	m_shifter.set(DoubleSolenoid.Value.kReverse);
    }
    
    /**
     * @return if high gear
     */
    public boolean isHighGear()
    {
    	return (m_shifter.get() == DoubleSolenoid.Value.kForward);
    }
    
    /**
     * @return left encoder distance in ticks
     */
    public double getLeftEncoderDistance() {
    	return m_lEncoder.getRaw();
    }
    
    /**
     * @return right encoder distance in ticks
     */
    public double getRightEncoderDistance() {
    	return m_rEncoder.getRaw();
    }
    
    /**
     * @return average encoder position
     */
    public double getCurrentPosition() {
    	return (getLeftEncoderDistance() + getRightEncoderDistance()) / 2;
    }
    
    /**
     * @return yaw from NavX
     */
    public double getYaw() {
    	return m_nav.getYaw();
    }
    
    /**
     * Stop drivetrain movement and reset sensors
     */
    public void stopDrive() {
    	setSpeed(0, 0);
    	resetSensors();
    }
    
    /**
     * Reset gyro and encoders
     */
    public void resetSensors() {
    	resetGyro();
    	m_lEncoder.reset();
    	m_rEncoder.reset();
    }
    
    public void resetGyro() {
    	m_nav.reset();
    }
    
}