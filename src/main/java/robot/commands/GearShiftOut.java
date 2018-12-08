package robot.commands;

import robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class GearShiftOut extends Command {

    public GearShiftOut() {
    }

    protected void initialize() {
    }

    protected void execute() {
    	Robot.m_drivetrain.shiftGearOut();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
