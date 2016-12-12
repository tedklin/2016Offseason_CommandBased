package org.usfirst.frc.team687.robot.commands.auto;

import org.usfirst.frc.team687.robot.commands.*;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants;
import org.usfirst.frc.team687.robot.constants.ShooterConstants;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants.Path;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Breach Rock Wall, drive to center of courtyard, vision align, shoot into center goal
 * 
 * @author tedfoodlin
 *
 */

public class BreachRockWallShoot extends CommandGroup{

	public BreachRockWallShoot() {
		Path path = DrivetrainConstants.Path.CategoryD;
		addSequential(new BreachCheval());
		addSequential(new DriveTurnToAngle(DrivetrainConstants.getFirstAngleToTurn(path)));
		addSequential(new DriveDistance(DrivetrainConstants.getMidSegment(path)));
		addSequential(new DriveTurnToAngle(DrivetrainConstants.getSecondAngleToTurn(path)));
		addSequential(new ShootCommand(ShooterConstants.Location.OffBatter));
	}
}
