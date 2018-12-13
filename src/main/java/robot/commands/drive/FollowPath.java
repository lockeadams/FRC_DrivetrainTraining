package robot.commands.drive;

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
  EncoderFollower left, right;
  final double MAX_VELOCITY, MAX_ACCELERATION, MAX_JERK;
  final double WHEELBASE_WIDTH, WHEEL_DIAMETER;
  final int TICKS_PER_REV;
  final double kP, kI, kD, kV, kA;

  public FollowPath(Waypoint[] points) {
    requires(Robot.m_drivetrain);
    this.points = points;

    MAX_VELOCITY = 0;
    MAX_ACCELERATION = 0;
    MAX_JERK = 0;

    WHEELBASE_WIDTH = 0;
    WHEEL_DIAMETER = 6;
    TICKS_PER_REV = 4600;

    kP = 0;
    kI = 0;
    kD = 0;
    kV = 1 / MAX_VELOCITY;
    kA = 0;
    //https://www.chiefdelphi.com/forums/showthread.php?t=161828 for tuning help

  }

  @Override
  protected void initialize() {
    config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, MAX_VELOCITY, MAX_ACCELERATION, MAX_JERK);
    trajectory = Pathfinder.generate(points, config);
    modifier = new TankModifier(trajectory).modify(WHEELBASE_WIDTH);
    left = new EncoderFollower(modifier.getLeftTrajectory());
    right = new EncoderFollower(modifier.getRightTrajectory());
    left.configureEncoder(RobotMap.rearLeft.getSelectedSensorPosition(0), TICKS_PER_REV, WHEEL_DIAMETER);
    right.configureEncoder(RobotMap.rearRight.getSelectedSensorPosition(0), TICKS_PER_REV, WHEEL_DIAMETER);
    left.configurePIDVA(kP, kI, kD, kV, kA);
    right.configurePIDVA(kP, kI, kD, kV, kA);
  }

  @Override
  protected void execute() {
    double l = left.calculate(RobotMap.rearLeft.getSelectedSensorPosition(0));
    double r = right.calculate(RobotMap.rearRight.getSelectedSensorPosition(0));

    double gyroHeading = RobotMap.gyro.getAngle();
    double desiredHeading = Pathfinder.r2d(left.getHeading());

    double angleDifference = Pathfinder.boundHalfDegrees(desiredHeading - gyroHeading);
    double turn = 0.8 * (-1.0/80.0) * angleDifference; //simple P loop with kP from 254 presentation

    RobotMap.rearLeft.set(l + turn);
    RobotMap.rearRight.set(r - turn);
  }

  @Override
  protected boolean isFinished() {
    return left.isFinished();
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
