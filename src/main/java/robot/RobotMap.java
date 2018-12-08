package robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {

	public static WPI_TalonSRX frontRight, frontLeft, rearLeft, rearRight;
	public static DifferentialDrive drive;
	public static DoubleSolenoid gearShift;
	
	public static void init() {
		
		frontRight = new WPI_TalonSRX(8);
		frontLeft = new WPI_TalonSRX(5);
		rearRight = new WPI_TalonSRX(7);
		rearLeft = new WPI_TalonSRX(6);

		frontRight.follow(rearRight);
		frontLeft.follow(rearLeft);
		
		drive = new DifferentialDrive(rearLeft, rearRight);
	
		gearShift = new DoubleSolenoid(0,1);
	}
	
}
