package org.usfirst.frc.team687.robot.commands.auto;

import org.usfirst.frc.team687.robot.commands.*;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants;
import org.usfirst.frc.team687.robot.constants.ShooterConstants;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants.Path;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Breach Low Bar, drive to position in left of courtyard, vision align, shoot into left goal
 * 
 * @author tedfoodlin
 *
 */

public class BreachLowBarShoot extends CommandGroup{

	public BreachLowBarShoot() {
		Path path = DrivetrainConstants.Path.LowBar;
		addSequential(new BreachLowBar());
		addSequential(new DriveTurnToAngle(DrivetrainConstants.getFirstAngleToTurn(path)));
		addSequential(new DriveDistance(DrivetrainConstants.getMidSegment(path)));
		addSequential(new DriveTurnToAngle(DrivetrainConstants.getSecondAngleToTurn(path)));
		addSequential(new ShootCommand(ShooterConstants.Location.OffBatter));
	}
}
