package robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class RobotMap {

    //Constants
    public static final double MAX_VELOCITY = 0; //ft/s
    public static final double MAX_ACCELERATION = 0; //ft/s^2
    public static final double MAX_JERK = 0; //ft/s^3
    public static final double WHEELBASE_WIDTH = 0; //ft
    public static final double WHEEL_DIAMETER = (6.0 / 12.0); //ft
    public static final int TICKS_PER_REV = 4096; //talon units

    // Device Declarations
    public static WPI_TalonSRX left, right, rightSlave, leftSlave;
    public static DifferentialDrive drive;
    public static DoubleSolenoid gearShift;
    public static ADXRS450_Gyro gyro;

    //Device Initialization and Configuration
    public static void init() {
        right = new WPI_TalonSRX(7);
        left = new WPI_TalonSRX(6);
        rightSlave = new WPI_TalonSRX(8);
        leftSlave = new WPI_TalonSRX(5);

        rightSlave.follow(right);
        leftSlave.follow(left);
        left.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        right.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		
        drive = new DifferentialDrive(left, right);
        gyro = new ADXRS450_Gyro();
        gearShift = new DoubleSolenoid(0,1);
	}
}
