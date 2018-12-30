package robot.commands.drive;

import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Command;
import jaci.pathfinder.*;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;
import robot.Robot;
import robot.RobotMap;

public class FollowPath extends Command {

    Waypoint[] points;
    Trajectory.Config config;
    Trajectory trajectory;
    TankModifier modifier;
    EncoderFollower leftFollower, rightFollower;
    final double kP, kI, kD, kV, kA;

    public FollowPath(Waypoint[] points) {
        requires(Robot.m_drivetrain);
        this.points = points;

        kP = 1.0;
        kI = 0;
        kD = 0;
        kV = 1 / RobotMap.MAX_VELOCITY;
        kA = 0;
        //https://www.chiefdelphi.com/forums/showthread.php?t=161828 for tuning help
    }

    @Override
    protected void initialize() {
        config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.005, RobotMap.MAX_VELOCITY, RobotMap.MAX_ACCELERATION, RobotMap.MAX_JERK);
        trajectory = Pathfinder.generate(points, config);
        modifier = new TankModifier(trajectory).modify(RobotMap.WHEELBASE_WIDTH);

        leftFollower = new EncoderFollower(modifier.getLeftTrajectory());
        rightFollower = new EncoderFollower(modifier.getRightTrajectory());

        leftFollower.configureEncoder(RobotMap.left.getSelectedSensorPosition(0), RobotMap.TICKS_PER_REV, RobotMap.WHEEL_DIAMETER);
        rightFollower.configureEncoder(RobotMap.right.getSelectedSensorPosition(0), RobotMap.TICKS_PER_REV, RobotMap.WHEEL_DIAMETER);
        leftFollower.configurePIDVA(kP, kI, kD, kV, kA);
        rightFollower.configurePIDVA(kP, kI, kD, kV, kA);

        RobotMap.left.setSelectedSensorPosition(0, 0, 10);
        RobotMap.right.setSelectedSensorPosition(0, 0, 10);
        RobotMap.left.setNeutralMode(NeutralMode.Brake);
        RobotMap.right.setNeutralMode(NeutralMode.Brake);

        System.out.println("Following path");
    }

    @Override
    protected void execute() {
        double l = leftFollower.calculate(RobotMap.left.getSelectedSensorPosition(0));
        double r = rightFollower.calculate(RobotMap.right.getSelectedSensorPosition(0));

        //System.out.println("l: " + l + " r: " + r);

        double gyroHeading = RobotMap.gyro.getAngle();
        double desiredHeading = Pathfinder.r2d(leftFollower.getHeading());

        double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
        double turn = 0.8 * (-1.0/80.0) * angleDifference; //simple P loop with kP from 254 presentation

        RobotMap.left.set(l + turn);
        RobotMap.right.set(r - turn);
    }

    @Override
    protected boolean isFinished() {
        return leftFollower.isFinished();
    }

    @Override
    protected void end() {
        System.out.println("Done following path.");
    }

    @Override
    protected void interrupted() {
    }
}
