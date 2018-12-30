package robot.commands.tuning;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Command;
import robot.Robot;
import robot.RobotMap;

public class CalcTrackwidth extends Command {

    ADXRS450_Gyro gyro = RobotMap.gyro;
    WPI_TalonSRX left = RobotMap.left;
    WPI_TalonSRX right = RobotMap.right;
    double initialAngle, state, rotations, nativeDist, distance, trackwidth;

    /**
     * Spins in circle 10 times to calculates robot trackwidth.
     */
    public CalcTrackwidth() {
        requires(Robot.m_drivetrain);
    }

    @Override
    protected void initialize() {
        left.setNeutralMode(NeutralMode.Brake);
        right.setNeutralMode(NeutralMode.Brake);
        initialAngle = Math.abs(gyro.getAngle());
        nativeDist = 0;
        distance = 0;
        trackwidth = 0;
        state = 0;
        left.setSelectedSensorPosition(0, 0, 10);
        right.setSelectedSensorPosition(0, 0, 10);
    }

    @Override
    protected void execute() {
        rotations = (Math.abs(gyro.getAngle()) - initialAngle) / 360.0;
        left.set(1.0);
        right.set(1.0);
        if(rotations > 10) state = 2;
    }

    @Override
    protected boolean isFinished() {
        return state == 2;
    }

    @Override
    protected void end() {
        left.set(0);
        right.set(0);

        nativeDist = (left.getSelectedSensorPosition(0) + right.getSelectedSensorPosition(0)) / 2;
        System.out.println("Native distance: " + nativeDist + " talon units");
        distance = (nativeDist / RobotMap.TICKS_PER_REV) * RobotMap.WHEEL_DIAMETER * Math.PI;
        trackwidth = distance / (rotations * Math.PI);
        System.out.println("Empirical Trackwidth: " + trackwidth + " feet.");
    }

    @Override
    protected void interrupted() {
    }
}