package org.usfirst.frc.team687.robot.commands.auto;

import org.usfirst.frc.team687.robot.commands.*;
import org.usfirst.frc.team687.robot.constants.DrivetrainConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Reach Moat and breach
 * 
 * @author tedfoodlin
 *
 */

public class BreachMoat extends CommandGroup{

	public BreachMoat() {
		addSequential(new ShooterCompress(true));
		addSequential(new ShiftLow());
		addSequential(new DriveDistance(DrivetrainConstants.reachDefenseDist));
		addSequential(new ShiftHigh());
		addSequential(new DriveStraightDistancePID(DrivetrainConstants.crossDefenseDist));
	}
}
