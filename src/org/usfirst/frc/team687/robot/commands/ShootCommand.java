package org.usfirst.frc.team687.robot.commands;

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
		addSequential(new SnapToTarget());
		addParallel(new SetFlywheelSpeed(location));
		addSequential(new SetShooterAngle(location));
		addSequential(new ShooterCompress(false));
		addSequential(new ShooterPunch());
	}
}
