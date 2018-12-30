package robot;

import robot.commands.drive.*;
import robot.commands.drive.ShiftGear.Gear;
import robot.commands.tuning.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import jaci.pathfinder.Waypoint;

public class OI {

	public XboxController pilot;
	public JoystickButton pilotA, pilotB, pilotLS, pilotRS;
	
	public OI() {
		pilot = new XboxController(0);
		pilotA = new JoystickButton(pilot, 1); 
		pilotB = new JoystickButton(pilot, 2);
		pilotLS = new JoystickButton(pilot, 9);
		pilotRS = new JoystickButton(pilot, 10);

		Waypoint[] points = new Waypoint[] {
			new Waypoint(0, 0, 0.001),
			new Waypoint(1, 0, 0.001),
			new Waypoint(1, 1, 0.001)
		};
		pilotB.whenPressed(new FollowPath(points));

		pilotA.whenPressed(new CalcTrackwidth());
		
		//pilotLS.whenPressed(new ShiftGear(Gear.HIGH));
		//pilotRS.whenPressed(new ShiftGear(Gear.LOW));
	}
	
	
	
}
