package org.usfirst.frc.team687.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team687.robot.commands.*;
import org.usfirst.frc.team687.robot.commands.auto.*;
import org.usfirst.frc.team687.robot.subsystems.*;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Much OO, much wow
 * 
 * @author tedfoodlin
 *
 */

public class Robot extends IterativeRobot {

	public static Drive drive;
	public static Intake intake;
	public static Flywheels flywheels;
	public static Shooter shooter;
	public static PowerDistributionPanel pdp;
	
	public static OI oi;

    Command autonomousCommand;
    static SendableChooser autoProgram;
    static SendableChooser autoStartPosition;
    static SendableChooser autoDefense;
    
    public void robotInit() {
		oi = new OI();
		
		drive = new Drive();
		intake = new Intake();
		flywheels = new Flywheels();
		shooter = new Shooter();
		pdp = new PowerDistributionPanel();
		
        autoProgram = new SendableChooser();
        autoProgram.addObject("Do Nothing", new DoNothing());
        autoProgram.addObject("Breach Cheval de Frises", new BreachCheval());
        autoProgram.addObject("Breach Ramparts", new BreachRamparts());
        autoProgram.addObject("Breach Moat", new BreachMoat());
        autoProgram.addObject("Breach Rock Wall", new BreachRockWall());
        autoProgram.addObject("Breach Rough Terrain", new BreachRoughTerrain());
        autoProgram.addObject("Breach Low Bar", new BreachLowBar());
        autoProgram.addObject("Breach Moat and Shoot", new BreachMoatShoot());
        autoProgram.addObject("Breach Rough Terrain and Shoot", new BreachRoughTerrainShoot());
        autoProgram.addObject("Breach Rock Wall and Shoot", new BreachRockWallShoot());
        autoProgram.addObject("Breach Ramparts and Shoot", new BreachRampartsShoot());
        autoProgram.addObject("Breach Cheval de Frises and Shoot V1", new BreachChevalShootA());
        autoProgram.addObject("Breach Cheval de Frises and Shoot V2", new BreachChevalShootB());
        autoProgram.addDefault("Breach Low Bar and Shoot", new BreachLowBarShoot());
        
        SmartDashboard.putData("Auto Program", autoProgram);
        
        SmartDashboard.putData(drive);
        SmartDashboard.putData(intake);
        SmartDashboard.putData(flywheels);
        SmartDashboard.putData(shooter);
    }
	
    public void disabledInit() {

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        autonomousCommand = (Command) autoProgram.getSelected();
    	
        if (autonomousCommand != null) {
        	autonomousCommand.start();
        }
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        drive.reportToSmartDashboard();
        intake.reportToSmartDashboard();
        flywheels.reportToSmartDashboard();
        shooter.reportToSmartDashboard();
    }

    public void teleopInit() {
        if (autonomousCommand != null) {
        	autonomousCommand.cancel();
        }
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        
        drive.reportToSmartDashboard();
        intake.reportToSmartDashboard();
        flywheels.reportToSmartDashboard();
        shooter.reportToSmartDashboard();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
        
        drive.reportToSmartDashboard();
        intake.reportToSmartDashboard();
        flywheels.reportToSmartDashboard();
        shooter.reportToSmartDashboard();
    }
}
