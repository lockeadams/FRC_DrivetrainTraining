package org.usfirst.frc.team4188.robot.commands;

import org.usfirst.frc.team4188.robot.Robot;
import org.usfirst.frc.team4188.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class ManualDrive extends Command {
	
	XboxController pilotXboxController = Robot.oi.pilotXboxController;
	WPI_TalonSRX rearLeft = RobotMap.rearLeft;
	WPI_TalonSRX rearRight = RobotMap.rearRight;

	public static boolean autoTrans = false;
	//double leftVelocity = rearLeft.getSelectedSensorVelocity(0);
	//double rightVelocity = rearRight.getSelectedSensorPosition(0);
    public ManualDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.driveTrain.arcadeDrive(-pilotXboxController.getY(Hand.kLeft), pilotXboxController.getX(Hand.kRight)*.7, 1.0);
    	/*
	    if(autoTrans = true) {
	    	if (-Math.signum(leftVelocity) == Math.signum(rightVelocity)) {
	    		if((RobotMap.rearRight.getSelectedSensorVelocity(0)*Robot.driveTrain.SENSOR_UNITS_PER_ROTATION) > 80.0 ){
	    			Robot.driveTrain.shiftGearOut();
	    			Robot.driveTrain.shiftGearOff();
	    		} else {
	    			Robot.driveTrain.shiftGearIn();
	    			Robot.driveTrain.shiftGearOff();
	    		}
	    	}
        }
        */
	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
