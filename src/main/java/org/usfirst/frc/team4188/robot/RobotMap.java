package org.usfirst.frc.team4188.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;

	public static WPI_TalonSRX frontRight, frontLeft, rearLeft, rearRight, midRight, midLeft;
	
	public static DifferentialDrive robotBase;
	public static SpeedControllerGroup leftGroup, rightGroup;
	
	public static DoubleSolenoid gearShift;
	
	public static void init() {
		
		frontRight = new WPI_TalonSRX(17);
		frontLeft = new WPI_TalonSRX(14);
		
		/*
		midRight = new WPI_TalonSRX(13);
		midLeft = new WPI_TalonSRX(16);
		midRight.setInverted(true);
		midLeft.setInverted(true);
		*/
		
		rearRight = new WPI_TalonSRX(19);
		rearLeft = new WPI_TalonSRX(12);
		/*
		rearLeft.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative,0, 0);
		rearLeft.setSensorPhase(true);		
		rearRight.configSelectedFeedbackSensor(com.ctre.phoenix.motorcontrol.FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		rearRight.setSensorPhase(true);
		*/
		//leftGroup = new SpeedControllerGroup(frontLeft, midLeft, rearLeft);
		//rightGroup = new SpeedControllerGroup(frontRight, midRight, rearRight);
		
		leftGroup = new SpeedControllerGroup(frontLeft, rearLeft);
		rightGroup = new SpeedControllerGroup(frontRight, rearRight);
		
		robotBase = new DifferentialDrive(leftGroup, rightGroup);
	
		//gearShift = new DoubleSolenoid(0,1);
	}
	
}
