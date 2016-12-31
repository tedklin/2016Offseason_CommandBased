package org.usfirst.frc.team687.robot.commands;

import org.usfirst.frc.team687.robot.constants.IntakeConstants;
import org.usfirst.frc.team687.robot.constants.ShooterConstants.Location;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Shooting sequence
 * 
 * @author tedfoodlin
 *
 */

public class ShootCommand extends CommandGroup {

	public ShootCommand(Location location) {
		addParallel(new SetFlywheelSpeed(location));
		addSequential(new SnapToTarget());
		addParallel(new DriveBaseLock());
		addSequential(new SetIntakeAngle(IntakeConstants.kIntakeGround));
		addSequential(new SetShooterAngle(location));
		addSequential(new ShooterCompress(false));
		addSequential(new ShooterPunch());
		addParallel(new SetFlywheelSpeed(Location.Resting));
		addParallel(new SetShooterAngle(Location.Resting));
		addSequential(new SetIntakeAngle(IntakeConstants.kIntakeTucked));
	}
}
