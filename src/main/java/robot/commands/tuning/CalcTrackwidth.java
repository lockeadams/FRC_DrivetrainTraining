package robot.commands.tuning;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Command;
import robot.RobotMap;

public class CalcTrackwidth extends Command {

    ADXRS450_Gyro gyro = RobotMap.gyro;
    WPI_TalonSRX left = RobotMap.left;
    WPI_TalonSRX right = RobotMap.right;
    double initialAngle, state;

    /**
     * Spins in circle 10 times to calculates robot trackwidth.
     */
    public CalcTrackwidth() {
    }

    @Override
    protected void initialize() {
        initialAngle = Math.abs(gyro.getAngle());
    }

    @Override
    protected void execute() {
        double rotations = (Math.abs(gyro.getAngle()) - initialAngle) / 360.0;
        if(state == 1) {
            left.set(1.0);
            right.set(-1.0);
            if(rotations > 10) state = 2;
        } else if(state == 2) {
            left.set(0);
            right.set(0);

            double nativeDist = (left.getSelectedSensorPosition(0) + right.getSelectedSensorPosition(0)) / 2;
            double distance = (nativeDist / RobotMap.TICKS_PER_REV) * RobotMap.WHEEL_DIAMETER * Math.PI;
            double trackwidth = distance / (rotations * Math.PI);
            System.out.println("Empirical Trackwidth: " + trackwidth + " feet.");
            state = 3;
        }
    }

    @Override
    protected boolean isFinished() {
        return state == 3;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}