package robot.commands.drive;

import robot.Robot;
import robot.RobotMap;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class ManualDrive extends Command {
	
	XboxController pilot = Robot.m_oi.pilot;

    public ManualDrive() {
    	requires(Robot.m_drivetrain);
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        RobotMap.left.set(-pilot.getY(Hand.kLeft));
        RobotMap.right.set(-pilot.getY(Hand.kRight));
        System.out.println("Left joy: " + -pilot.getY(Hand.kLeft) + " right joy: " + -pilot.getY(Hand.kRight));
    	//Robot.m_drivetrain.arcadeDrive(-pilot.getY(Hand.kLeft), pilot.getX(Hand.kRight), 1.0);
	}

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
