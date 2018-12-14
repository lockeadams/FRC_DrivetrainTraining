package robot.commands.drive;

import robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ShiftGear extends Command {

    public enum Gear{HIGH, LOW}
    Gear gear;

    public ShiftGear(Gear gear) {
        this.gear = gear;
    }

    @Override
    protected void initialize() {
    }

    @Override
    protected void execute() {
        if(gear == Gear.HIGH) Robot.m_drivetrain.shiftGearIn();
        if(gear == Gear.LOW) Robot.m_drivetrain.shiftGearOut();
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
