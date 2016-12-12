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
		addSequential(new SetFlywheelSpeed(location));
		addSequential(new SetShooterAngle(location));
		addSequential(new Wait(1));
		addSequential(new ShooterCompress(false));
		addSequential(new ShooterPunch());
	}
}
