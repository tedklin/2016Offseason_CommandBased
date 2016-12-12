package org.usfirst.frc.team687.robot.commands.auto;

import org.usfirst.frc.team687.robot.commands.*;
import org.usfirst.frc.team687.robot.constants.IntakeConstants;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Drive to cheval and breach 
 * 
 * @author tedfoodlin
 *
 */

public class BreachCheval extends CommandGroup{

	public BreachCheval() {
		addSequential(new ShooterCompress(true));
		addSequential(new ShiftLow());
		addSequential(new DriveDistance(20));
		addSequential(new SetIntakeAngle(IntakeConstants.kIntakeGround));
		addSequential(new ShiftHigh());
		addSequential(new DriveDistance(50));
		addSequential(new SetIntakeAngle(IntakeConstants.kIntakeTucked));
		addSequential(new DriveDistance(50));
	}
	
}
