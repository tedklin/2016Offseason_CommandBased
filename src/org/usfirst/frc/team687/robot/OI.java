package org.usfirst.frc.team687.robot;

import org.usfirst.frc.team687.robot.commands.*;
import org.usfirst.frc.team687.robot.commands.auto.*;
import org.usfirst.frc.team687.robot.constants.IntakeConstants;
import org.usfirst.frc.team687.robot.constants.ShooterConstants;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * @author tedfoodlin
 * 
 */

public class OI {
	
	public Joystick driveJoyLeft = new Joystick(0);
	public Joystick driveJoyRight = new Joystick(1);
	public Joystick articJoy = new Joystick(2);
	
	// buttons on driveJoyRight
	public JoystickButton shiftUp_1;
	public JoystickButton shiftDown_6;
	public JoystickButton zeroGyro_7;
	public JoystickButton snapToTarget_8;
	
	// articJoy
	public JoystickButton intake_9;
	public JoystickButton outtake_7; 
	public JoystickButton intakeOff_1;
	
	public JoystickButton intakeReset_12; // press this everytime you want to set a new intake angle
	
	public JoystickButton intakeTuck_11; 
	public JoystickButton intakeBall_8;
	
	public JoystickButton intakeResting_6;
	public JoystickButton intakeManual_10;
	
	public JoystickButton batterShot_2;
	public JoystickButton outerworksShot_5;
	public JoystickButton lowgoalShot_4;
	
	public JoystickButton shotReset_3;
	
	public OI() {
		shiftUp_1 = new JoystickButton(driveJoyRight, 1);
		shiftUp_1.whenPressed(new ShiftHigh());
		shiftDown_6 = new JoystickButton(driveJoyRight, 6);
		shiftDown_6.whenPressed(new ShiftLow());
		zeroGyro_7 = new JoystickButton(driveJoyRight, 7);
		zeroGyro_7.whenPressed(new ResetGyro());
		snapToTarget_8 = new JoystickButton(driveJoyRight, 8);
		snapToTarget_8.whenPressed(new SnapToTarget());
		
		intake_9 = new JoystickButton(articJoy, 9);
		intake_9.whenPressed(new IntakeRollersOn());
		outtake_7 = new JoystickButton(articJoy, 7);
		outtake_7.whenPressed(new IntakeRollersReverse());
		
		intakeReset_12 = new JoystickButton(articJoy, 12);
		
		intakeTuck_11 = new JoystickButton(articJoy, 11);
		intakeTuck_11.whenPressed(new SetIntakeAngle(IntakeConstants.kIntakeTucked));
		intakeBall_8 = new JoystickButton(articJoy, 8);
		intakeBall_8.whenPressed(new SetIntakeAngle(IntakeConstants.kIntakeBallPickup));
		
		intakeResting_6 = new JoystickButton(articJoy, 6);
		intakeResting_6.whenPressed(new SetIntakeAngle(IntakeConstants.kIntakeResting));
		intakeManual_10 = new JoystickButton(articJoy, 10);
		intakeManual_10.whenPressed(new ManualIntakeAngle());
		
		batterShot_2 = new JoystickButton(articJoy, 2);
		batterShot_2.whenPressed(new ShootCommand(ShooterConstants.Location.Batter));
		outerworksShot_5 = new JoystickButton(articJoy, 5);
		outerworksShot_5.whenPressed(new ShootCommand(ShooterConstants.Location.Outerworks));
		lowgoalShot_4 = new JoystickButton(articJoy, 4);
		lowgoalShot_4.whenPressed(new ShootCommand(ShooterConstants.Location.LowGoal));
		
		shotReset_3 = new JoystickButton(articJoy, 3);
		shotReset_3.whenPressed(new SetFlywheelSpeed(0));
		shotReset_3.whenReleased(new SetShooterAngle(ShooterConstants.Location.Resting));
		shotReset_3.whenReleased(new ShooterCompress(false));
		
		// Smart Dashboard buttons
		SmartDashboard.putData("Tank Drive", new TankDrive());
		SmartDashboard.putData("Shift Up", new ShiftHigh());
		SmartDashboard.putData("Shift Down", new ShiftLow());
		SmartDashboard.putData("Zero Gyro", new ResetGyro());
		SmartDashboard.putData("Snap to Target", new SnapToTarget());
		
		SmartDashboard.putData("Intake", new IntakeRollersOn());
		SmartDashboard.putData("Outtake", new IntakeRollersReverse());
		SmartDashboard.putData("Manual Intake Control", new ManualIntakeAngle());
		SmartDashboard.putData("Intake Angle Rest", new SetIntakeAngle(IntakeConstants.kIntakeResting));
		SmartDashboard.putData("Intake Angle Ball Pickup", new SetIntakeAngle(IntakeConstants.kIntakeBallPickup));
		SmartDashboard.putData("Intake Angle Tuck", new SetIntakeAngle(IntakeConstants.kIntakeTucked));
		
		SmartDashboard.putData("Compress Ball", new ShooterCompress(true));
		SmartDashboard.putData("Decompress Ball", new ShooterCompress(false));
		SmartDashboard.putData("Start Flywheels", new SetFlywheelSpeed(ShooterConstants.Location.OffBatter));
		SmartDashboard.putData("Stop Flywheels", new SetFlywheelSpeed(ShooterConstants.Location.Resting));
		SmartDashboard.putData("Adjust to Shooting Angle", new SetShooterAngle(ShooterConstants.Location.OffBatter));
		SmartDashboard.putData("Adjust to Resting Angle", new SetShooterAngle(ShooterConstants.Location.Resting));
		SmartDashboard.putData("Shooter Punch", new ShooterPunch());
		SmartDashboard.putData("Shoot Off Batter Sequence", new ShootCommand(ShooterConstants.Location.OffBatter));
		SmartDashboard.putData("Shoot Batter Sequence", new ShootCommand(ShooterConstants.Location.Batter));
		SmartDashboard.putData("Shoot Outerworks Sequence", new ShootCommand(ShooterConstants.Location.Outerworks));
		SmartDashboard.putData("Shoot Low Goal Sequence", new ShootCommand(ShooterConstants.Location.LowGoal));
		
		SmartDashboard.putData("Breach Cheval", new BreachCheval());
		SmartDashboard.putData("Breach Cheval Shoot A", new BreachChevalShootA());
		SmartDashboard.putData("Breach Cheval Shoot B", new BreachChevalShootB());
		SmartDashboard.putData("Breach Low Bar", new BreachLowBar());
		SmartDashboard.putData("Breach Low Bar Shoot", new BreachLowBarShoot());
		SmartDashboard.putData("Breach Moat", new BreachMoat());
		SmartDashboard.putData("Breach Moat Shoot", new BreachMoatShoot());
		SmartDashboard.putData("Breach Ramparts", new BreachRamparts());
		SmartDashboard.putData("Breach Ramparts Shoot", new BreachRampartsShoot());
		SmartDashboard.putData("Breach Rock Wall", new BreachRockWall());
		SmartDashboard.putData("Breach Rock Wall Shoot", new BreachRockWallShoot());
		SmartDashboard.putData("Breach Rough Terrain", new BreachRoughTerrain());
		SmartDashboard.putData("Breach Rough Terrain Shoot", new BreachRoughTerrainShoot());
	}
	
	/**
	 * @return input power from left drive joystick (-1.0 to +1.0)
	 */
	public double getDriveJoyL() {
		return driveJoyLeft.getY();
	}
	
	/**
	 * @return input power from right drive joystick (-1.0 to +1.0)
	 */
	public double getDriveJoyR() {
		return driveJoyRight.getY();
	}
	
	/**
	 * @return input power from artic joystick (-1.0 to +1.0)
	 */
	public double getArticJoy() {
		return articJoy.getY();
	}
}
