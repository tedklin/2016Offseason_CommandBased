package org.usfirst.frc.team687.robot.commands.auto;

import org.usfirst.frc.team687.robot.commands.*;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants;
import org.usfirst.frc.team687.robot.constants.ShooterConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team687.robot.constants.DrivetrainConstants.Path;

/**
 * Breach Cheval de Frises, drive to left batter, vision align, shoot into left goal
 * 
 * @author tedfoodlin
 *
 */

public class BreachChevalShootB extends CommandGroup {
	
	public BreachChevalShootB() {
		Path path = DrivetrainConstants.Path.CategoryA2;
		addSequential(new BreachCheval());
		addSequential(new DriveTurnToAngle(DrivetrainConstants.getFirstAngleToTurn(path)));
		addSequential(new DriveDistance(DrivetrainConstants.getMidSegment(path)));
		addSequential(new DriveTurnToAngle(DrivetrainConstants.getSecondAngleToTurn(path)));
		addSequential(new ShootCommand(ShooterConstants.Location.OffBatter));
	}

}
