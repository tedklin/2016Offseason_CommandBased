package org.usfirst.frc.team687.robot.commands.auto;

import org.usfirst.frc.team687.robot.commands.*;

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
		addSequential(new DriveDistance(20));
		addSequential(new ShiftHigh());
		addSequential(new DriveStraightDistancePID(100));
	}
	
}
