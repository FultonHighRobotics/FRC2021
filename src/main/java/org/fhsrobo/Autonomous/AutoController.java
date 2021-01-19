
package org.fhsrobo.Autonomous;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import org.ejml.simple.UnsupportedOperation;



 public class AutoController implements IAutonomous{
    private final DifferentialDrive drive;
    public AutoController(DifferentialDrive DifDrive){
        this.drive = DifDrive;
    }

     @Override
     public void Forwards(double speed) {

        if ((speed <= 0) || (speed > 1)){
            throw new UnsupportedOperation("That's not a supported speed");
        }
         drive.arcadeDrive(speed, 0);
     }
     @Override
     public void Backwards(double speed){
        if(speed > 0 || speed < -1){
            throw new UnsupportedOperation("That's not a supported speed");
        }
        drive.arcadeDrive(speed, 0);
     }
     public void Stop(){
        drive.stopMotor();
     }
 }