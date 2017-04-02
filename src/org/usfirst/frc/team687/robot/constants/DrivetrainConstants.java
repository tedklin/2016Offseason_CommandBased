package org.usfirst.frc.team687.robot.constants;

/**
 * Drivetrain constants
 * 
 * @author tedfoodlin
 */

public class DrivetrainConstants {
	
	public final static double kDriveRotationP		= 0.04444444;
	public final static double kDriveRotationI		= 0.00044444;
	public final static double kDriveRotationD		= 0;
	public final static double kDriveVisionP		= 0.007;
	public final static double kDriveVisionI		= 0.0005;
	public final static double kDriveVisionD		= 0.15;
	public final static double kDriveTranslationP	= 0;
	public final static double kDriveTranslationI	= 0;
	public final static double kDriveTranslationD	= 0;
	
	public final static double kDriveMaxVelocity = 21;
	public final static double kDriveMaxAccel = 1.5;
	public final static double kDriveMaxDecel = -1.5;
	public final static double kDriveAlpha = 0.1;
	
	public final static double kV = 1.0 / kDriveMaxVelocity;
	public final static double kA = 0.24;
	
	public final static double kDriveRotationTolerance = 1;
	public final static int kDriveRotationOscillationCount = 5;
	
	public final static double kSensitivityHigh = 0.85;
	public final static double kSensitivityLow = 0.75;
	
	public final static double wheelDeadband = 0.02;
	public final static double throttleDeadband = 0.02;
	
	public final static double reachDefenseDist = 20;
	public final static double crossDefenseDist = 75;
	
	public enum Path {
		CategoryA, CategoryA2, CategoryB, CategoryC, CategoryD, LowBar
	}
	
	/**
	 * Get first angle to turn to given path
	 * 
	 * @param Path path
	 * @return angle to turn in degrees
	 */
	public static double getFirstAngleToTurn(Path path) {
		if (path == Path.CategoryD) {
			return -80;
		} else if (path == Path.CategoryC) {
			return -10;
		} else if (path == Path.CategoryB) {
			return 50;
		} else if (path == Path.CategoryA) {
			return 100;
		} else if (path == Path.CategoryA2) {
			return 0;
		} else if (path == Path.LowBar) {
			return 0;
		} else {
			return 0;
		}
	}
	
	/**
	 * Get middle segment to travel given path
	 * 
	 * @param Path path
	 * @return distance in ticks
	 */
	public static double getMidSegment(Path path) {
		if (path == Path.CategoryD) {
			return 75;
		} else if (path == Path.CategoryC) {
			return 10;
		} else if (path == Path.CategoryB) {
			return 20;
		} else if (path == Path.CategoryA) {
			return 85;
		} else if (path == Path.CategoryA2) {
			return 0;
		} else if (path == Path.LowBar) {
			return 125;
		} else {
			return 125;
		}
	}
	
	/**
	 * Get second angle to turn to given path
	 * 
	 * @param Path path
	 * @return angle to turn in degrees
	 */
	public static double getSecondAngleToTurn(Path path) {
		if (path == Path.CategoryD) {
			return 180-(-80);
		} else if (path == Path.CategoryC) {
			return 180-(-10);
		} else if (path == Path.CategoryB) {
			return 180-50;
		} else if (path == Path.CategoryA) {
			return 180-100;
		} else if (path == Path.CategoryA2) {
			return 60;
		} else if (path == Path.LowBar) {
			return 60;
		} else {
			return 60;
		}
	}
	
}
