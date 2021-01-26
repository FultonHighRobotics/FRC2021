
package org.fhsrobo.Autonomous;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import org.ejml.simple.UnsupportedOperation;


public class AutoController implements IAutonomous{
    private DifferentialDrive drive;
    private Robot robot;
    public AutoController(DifferentialDrive DifDrive, Robot robo){
        this.drive = DifDrive;
        this.robot = robo;
    }
    private boolean CheckIfValid(AutoDirection state, double speed){
        switch (state){
            case FORWARDS:
                if ((speed <= 0) || (speed > 1)){
                    throw new UnsupportedOperation("That's not a supported speed");
                }
                drive.arcadeDrive(speed, 0);
            case BACKWARDS:
                if(speed > 0 || speed < -1){
                    throw new UnsupportedOperation("That's not a supported speed");
                }

                
        }
        if (!robot.isAutonomous()){
            throw new RuntimeException("Robot isn't autonomous!");
        }
        else {
            return true;
        }
    }

     @Override
     public void Forwards(double speed) {


     }
     @Override
     public void Backwards(double speed){
        if(speed > 0 || speed < -1){
            throw new UnsupportedOperation("That's not a supported speed");
        }
        else if (!robot.isAutonomous()){
            throw new RuntimeException("Robot isn't autonomous!");
        }
        drive.arcadeDrive(speed, 0);
     }
     public void Stop(){
        drive.stopMotor();
     }
 }