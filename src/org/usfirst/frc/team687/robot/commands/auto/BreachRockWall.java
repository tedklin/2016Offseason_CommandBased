package org.usfirst.frc.team687.robot.commands.auto;

import org.usfirst.frc.team687.robot.commands.*;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Reach Rock Wall and breach
 * 
 * @author tedfoodlin
 *
 */

public class BreachRockWall extends CommandGroup{

	public BreachRockWall() {
		addSequential(new ShooterCompress(true));
		addSequential(new ShiftLow());
		addSequential(new DriveDistance(DrivetrainConstants.reachDefenseDist));
		addSequential(new ShiftHigh());
		addSequential(new DriveStraightDistancePID(DrivetrainConstants.crossDefenseDist));
	}
	
}
