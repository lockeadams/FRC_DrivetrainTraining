package robot.commands.drive;

import robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class GearShiftIn extends Command {

    public GearShiftIn() {
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.m_drivetrain.shiftGearIn();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
