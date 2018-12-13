package robot.commands.drive;

import robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ShiftGear extends Command {

    public enum Gear{HIGH, LOW}
    Gear gear;

    public ShiftGear(Gear gear) {
        this.gear = gear;
    }

    protected void initialize() {
    }

    protected void execute() {
        if(gear == Gear.HIGH) Robot.m_drivetrain.shiftGearIn();
        if(gear == Gear.LOW) Robot.m_drivetrain.shiftGearOut();
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
