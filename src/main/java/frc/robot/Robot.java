package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import org.fhsrobo.Autonomous.*;
import javax.annotation.OverridingMethodsMustInvokeSuper;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.VideoSink;


public class Robot extends TimedRobot {

  UsbCamera UsbCamera1;
  UsbCamera  UsBCamera2;
  VideoSink server;

  WPI_TalonSRX frontleft = new WPI_TalonSRX(1);
  WPI_TalonSRX rearleft = new WPI_TalonSRX(3);
  WPI_TalonSRX frontright = new WPI_TalonSRX(0);
  WPI_TalonSRX rearright = new WPI_TalonSRX(2);
  
  WPI_TalonSRX intakeH = new WPI_TalonSRX(4);
  WPI_TalonSRX intakeV = new WPI_TalonSRX(5);
  WPI_TalonSRX door = new WPI_TalonSRX(6);
  WPI_TalonSRX deploy = new WPI_TalonSRX(7);
  WPI_TalonSRX hook = new WPI_TalonSRX(8);

  DigitalInput back = new DigitalInput(9);
  DigitalInput front = new DigitalInput(8);
  

  DifferentialDrive maindrive = new DifferentialDrive(frontleft, frontright);
  AutoController autoController = new AutoController(maindrive, this);

  protected final Joystick js1 = new Joystick(0);
  protected final JoystickButton intake = new JoystickButton(js1,1);
  protected final JoystickButton outtake = new JoystickButton(js1,2);
  protected final JoystickButton dopen = new JoystickButton(js1,3);
  protected final JoystickButton din = new JoystickButton(js1,4);
  protected final JoystickButton deployer = new JoystickButton(js1,5);
  protected final JoystickButton antideploy = new JoystickButton(js1,6);
  protected final JoystickButton hooky = new JoystickButton(js1,11);
  protected final JoystickButton kooh = new JoystickButton(js1,12);
  
  public void robotInit() {
    rearleft.follow(frontleft);
    rearright.follow(frontright);

    UsbCamera1 = CameraServer.getInstance().startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture(1);
    server = CameraServer.getInstance().getServer();
    
  }


  @Override
  public void robotPeriodic() {
    
  }
  @Override
  public void autonomousPeriodic() {

  }
  
  
  @Override
  public void teleopPeriodic() {
  maindrive.arcadeDrive(js1.getY()*-1,js1.getZ());

  if (intake.get() == true) {
  
  intakeH.set(0.5);
  } else if (outtake.get() == true) {
    intakeH.set(-0.5);
  } else {
  intakeH.set(0);
  }
  if (intake.get() == true) {
    intakeV.set(0.75);
    } else if (outtake.get() == true) {
      intakeV.set(-0.75);
    } else {
    intakeV.set(0);
    }


  if (dopen.get() == true && front.get() == true){
        System.out.println("Opening door");
        door.set(0.75);
  } else if (din.get() && back.get() == true){
        door.set(-0.75);
   }else {
        door.set(0);
    }



  if (deployer.get() == true) {
    deploy.set(0.5);
  } else if (antideploy.get() == true){
    deploy.set(-0.5);
  } else {
      deploy.set(0);
  }
  
  if (hooky.get() == true) {
    hook.set(-1);
  } else if (kooh.get() == true){
    hook.set(1);
  } else {
    hook.set(0);
  }

} 
}