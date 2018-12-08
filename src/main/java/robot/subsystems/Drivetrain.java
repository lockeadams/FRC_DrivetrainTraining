package robot.subsystems;

import robot.RobotMap;
import robot.commands.drive.ManualDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends Subsystem {

    DifferentialDrive drive = RobotMap.drive;
    DoubleSolenoid gearShift = RobotMap.gearShift;
        
	public void initDefaultCommand() {
		setDefaultCommand(new ManualDrive());
    }
    
    public void arcadeDrive(double x, double twist, double throttle){
    	drive.arcadeDrive(x*throttle, twist*throttle);
    }

    public void shiftGearIn() {
    	gearShift.set(Value.kForward);
    }
    
    public void shiftGearOut() {
    	gearShift.set(Value.kReverse);
    }

}

