package org.usfirst.frc.team687.robot.commands.auto;

import org.usfirst.frc.team687.robot.commands.*;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Reach Rough Terrain and breach
 * 
 * @author tedfoodlin
 *
 */

public class BreachRoughTerrain extends CommandGroup{

	public BreachRoughTerrain() {
		addSequential(new ShooterCompress(true));
		addSequential(new ShiftLow());
		addSequential(new DriveDistance(20));
		addSequential(new ShiftHigh());
		addSequential(new DriveStraightDistancePID(100));
	}
	
}
