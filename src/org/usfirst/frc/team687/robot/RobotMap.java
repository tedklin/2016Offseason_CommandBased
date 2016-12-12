package org.usfirst.frc.team687.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

/**
 * Map of ports
 * 
 * @author tedfoodlin
 *
 */

public class RobotMap {
	
	public final static int articJoyID = 3;
	public final static int driveJoyLeftID = 1;
	public final static int driveJoyRightID = 2;
	
	public final static int punchID1 = 1;
	public final static int punchID2 = 6;
	public final static int compressID1 = 2;
	public final static int compressID2 = 5;
	public final static int shifterID1 = 3;
	public final static int shifterID2 = 4;
	
	public final static int rDriveID1 = 0;
	public final static int rDriveID2 = 1;
	public final static int rDriveID3 = 2;
	public final static int lDriveID1 = 3;
	public final static int lDriveID2 = 4;
	public final static int lDriveID3 = 5;
	
	public final static int rFlyID = 1;
	public final static int lFlyID = 2;
	public final static int shooterLiftID = 3;
	
	public final static int intakeAngleID = 4;
	public final static int intakeRollersID = 5;
	
	public final static Port AHRSPort = SerialPort.Port.kMXP;
	
	public final static int lEncoderID1 = 0;
	public final static int lEncoderID2 = 1;
	public final static int rEncoderID1 = 2;
	public final static int rEncoderID2 = 3;
	
	public final static int lFlyEncoderID1 = 4;
	public final static int lFlyEncoderID2 = 5;
	public final static int rFlyEncoderID1 = 6;
	public final static int rFlyEncoderID2 = 7;
	public final static int liftEncoderID1 = 8;
	public final static int liftEncoderID2 = 9;
	
	public final static int intakeRollersEncoderID1 = 10;
	public final static int intakeRollersEncoderID2 = 11;
	public final static int intakeAngleEncoderID1 = 12;
	public final static int intakeAngleEncoderID2 = 13;
	
	public final static int lCompressID = 1;
	public final static int rCompressID = 2;
	
}
