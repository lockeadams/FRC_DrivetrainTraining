package org.usfirst.frc.team4188.robot.subsystems;


import org.usfirst.frc.team4188.robot.RobotMap;

import org.usfirst.frc.team4188.robot.commands.ManualDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    WPI_TalonSRX frontRight = RobotMap.frontRight;
    WPI_TalonSRX frontLeft = RobotMap.frontLeft;
    WPI_TalonSRX midRight = RobotMap.midRight;
    WPI_TalonSRX midLeft = RobotMap.midLeft;
    WPI_TalonSRX rearRight = RobotMap.rearRight;
    WPI_TalonSRX rearLeft = RobotMap.rearLeft;
    DifferentialDrive robotBase = RobotMap.robotBase;
    DoubleSolenoid gearShift = RobotMap.gearShift;
	public final double SENSOR_UNITS = 1.0/4096.0;
	public final double SENSOR_UNITS_PER_ROTATION = (3600.0/SENSOR_UNITS);
    
    
    public void arcadeDrive(double x, double twist, double throttle){
    	robotBase.arcadeDrive(x*throttle, twist*throttle);
    }

    public void shiftGearIn() {
    	gearShift.set(Value.kForward);
    }
    
    public void shiftGearOut() {
    	gearShift.set(Value.kReverse);
    }
    
    public void shiftGearOff() {
    	gearShift.set(Value.kOff);
    }
    
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new ManualDrive());
    }
}

