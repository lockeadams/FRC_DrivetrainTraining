package robot.commands;

import robot.Robot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;

public class ManualDrive extends Command {
	
	XboxController pilot = Robot.m_oi.pilot;

    public ManualDrive() {
    	requires(Robot.m_drivetrain);
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.m_drivetrain.arcadeDrive(-pilot.getY(Hand.kLeft), pilot.getX(Hand.kRight), 1.0);
	}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
