package org.usfirst.frc.team687.robot.commands.auto;

import org.usfirst.frc.team687.robot.commands.*;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants;
import org.usfirst.frc.team687.robot.constants.ShooterConstants;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants.Path;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Breach Moat, drive to center of courtyard, vision align, shoot into center goal
 * 
 * @author tedfoodlin
 *
 */

public class BreachMoatShoot extends CommandGroup{

	public BreachMoatShoot() {
		Path path = DrivetrainConstants.Path.CategoryB;
		addSequential(new BreachCheval());
		addSequential(new DriveTurnToAngle(DrivetrainConstants.getFirstAngleToTurn(path)));
		addSequential(new DriveDistance(DrivetrainConstants.getMidSegment(path)));
		addSequential(new DriveTurnToAngle(DrivetrainConstants.getSecondAngleToTurn(path)));
		addSequential(new ShootCommand(ShooterConstants.Location.OffBatter));
	}
}
