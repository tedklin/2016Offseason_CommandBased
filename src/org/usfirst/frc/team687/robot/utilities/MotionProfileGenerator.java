package org.usfirst.frc.team687.robot.utilities;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Trapezoidal motion profile generator
 * 
 * @author tedfoodlin
 *
 */

public class MotionProfileGenerator {
    
	private double m_cruiseVelocity;
	private double m_accel;
	private double m_decel;
	
	private double m_clkInMs = 10;
	private double m_clkInMinutes = m_clkInMs/60000;
	
	private ArrayList<Double> m_timeProfile = new ArrayList<Double>();
	private ArrayList<Double> m_velocityProfile = new ArrayList<Double>();
	private ArrayList<Double> m_positionProfile = new ArrayList<Double>();
	private ArrayList<Double> m_accelerationProfile = new ArrayList<Double>();
	
	/**
	 * Motion Profile Generator
	 * 
	 * @param cruise velocity in RPM
	 * @param acceleration in R/M^2
	 * @param deceleration in R/M^2
	 */
    public MotionProfileGenerator(double cruiseVelocity, double accel, double decel) { 
    	m_cruiseVelocity = cruiseVelocity;
    	m_accel = accel;
    	m_decel = decel;
    }

	/**
	 * Generate a trapezoidal motion profile using basic kinematic equations
	 * 
	 * @param distance desired in rotations
	 */
	public void generateProfile(double distance) {
		double time;
		double x;
		double v = 0;
		
		double accelTime = m_cruiseVelocity/m_accel;
		SmartDashboard.putNumber("Acceleration Time", accelTime);
		double cruiseTime = (distance - (accelTime * m_cruiseVelocity)) / m_cruiseVelocity;
		SmartDashboard.putNumber("Cruise Time", cruiseTime);
		double accelAndCruiseTime = accelTime + cruiseTime;
		SmartDashboard.putNumber("Acceleration + Cruise Time", accelAndCruiseTime);
		double decelTime = -m_cruiseVelocity/m_decel;
		SmartDashboard.putNumber("Deceleration Time", decelTime);
		double totalTime = accelTime + cruiseTime + decelTime;
		SmartDashboard.putNumber("Expected End Time", + totalTime);
		
		for (time = 0; time < accelTime; time += m_clkInMinutes){
			x = (0.5 * m_accel * Math.pow(time, (double)2));
			v = m_accel * time;
			addData(time, v, x, m_accel);
		}
		for (time = accelTime; time < accelAndCruiseTime; time += m_clkInMinutes){
			x = (0.5 * (Math.pow(m_cruiseVelocity, 2) / m_accel)) + (m_cruiseVelocity * (time - (m_cruiseVelocity/m_accel)));
			v = (m_cruiseVelocity);
			addData(time, v, x, 0);
		}
		for (time = accelAndCruiseTime; time <= totalTime; time += m_clkInMinutes){
			x = (double)(distance + 0.5 * m_decel * Math.pow((time-totalTime), 2));
			v = -m_accel * time + (m_cruiseVelocity + m_accel * accelAndCruiseTime);
			addData(time, v, x, m_decel);
		}
		SmartDashboard.putNumber("Calculated Acutal End Time", time);
	}
	
	/**
	 * Add data to array lists
	 * 
	 * @param time index
	 * @param velocity
	 * @param distance
	 * @param acceleration
	 */
	private void addData(double time, double v, double x, double acceleration) {
		m_timeProfile.add(time);
		m_velocityProfile.add(v);
		m_positionProfile.add(x);
		m_accelerationProfile.add(acceleration);
	}

	/**
	 * @param time index
	 * @return goal velocity
	 */
	public double readVelocity(double time) {
		return m_velocityProfile.get((int)(time/m_clkInMinutes));
	}
	
	/**
	 * @param time index
	 * @return goal distance
	 */
	public double readPosition(double time) {
		return m_positionProfile.get((int)(time/m_clkInMinutes));
	}
	
	/**
	 * @param time index
	 * @return goal acceleration
	 */
	public double readAcceleration(double time) {
		return m_accelerationProfile.get((int)(time/m_clkInMinutes));
	}
}
