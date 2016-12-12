package org.usfirst.frc.team687.robot.constants;

/**
 * Shooter constants
 * 
 * @author tedfoodlin
 */

public class ShooterConstants {

	public final static double kFlywheelF			= 0;
	public final static double kFlywheelP			= 0.08204;
	public final static double kFlywheelI			= 0.0005;
	public final static double kFlywheelD			= 0.375;
	public final static double kLiftF				= 0;
	public final static double kLiftP				= 3;
	public final static double kLiftI				= 0.015625;
	public final static double kLiftD				= 0;
	public final static double kLiftAlpha			= 0.05;
	public final static double kShootTime			= 0.5;
	
	public final static int kOuterworksSpeed		= 4000;
	public final static int kOffBatterSpeed			= 2500;
	public final static int kBatterSpeed			= 2000;
	public final static int kLowGoalSpeed			= 1000;

	public static double kOuterworksAngle			= 0.800;
	public static double kOffBatterAngle			= 0.850;
	public static double kBatterAngle				= 0.900;
	public static double kLowGoalAngle				= 0.450;
	
	public final static double kMinHeight			= 0.652;
	public final static double kMaxHeight			= 0.823;
	
	public enum Location {
		Outerworks, Batter, OffBatter, LowGoal, Resting
	}

	/**
	 * Get shooter angle given location
	 * 
	 * @param Location location
	 * @return shooter angle
	 */
	public static double getShooterAngle(Location location) {
		if (location == Location.Outerworks) {
			return kOuterworksAngle;
		} else if (location == Location.OffBatter) {
			return kOffBatterAngle;
		} else if (location == Location.Batter) {
			return kBatterAngle;
		} else if (location == Location.LowGoal) {
			return kLowGoalAngle;
		} else if (location == Location.Resting) {
			return kMinHeight;
		} else {
			return kOuterworksAngle;
		}
	}
	
	/**
	 * Get flywheel speed given location
	 * 
	 * @param Location location
	 * @return flywheel speed
	 */
	public static int getFlywheelSpeed(Location location) {
		if (location == Location.Outerworks) {
			return kOuterworksSpeed;
		} else if (location == Location.OffBatter) {
			return kOffBatterSpeed;
		} else if (location == Location.Batter) {
			return kBatterSpeed;
		} else if (location == Location.LowGoal) {
			return kLowGoalSpeed;
		} else if (location == Location.Resting) {
			return 0;
		} else {
			return kOuterworksSpeed;
		}
	}
}
