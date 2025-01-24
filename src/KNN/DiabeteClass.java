package KNN;

/**
 * This class represents a patient's data for diabetes prediction.
 */
public class DiabeteClass {
	
    public int pregnancies; // Number of pregnancies 
    public int glucose; // Glucose concentration
    public int bloodpressure; // Blood pressure                   
    public int skinthickness; // Skin thickness 
    public int insulin; // Insulin level                   )
    public double BMI; // Body Mass Index (BMI)                                  
    public double diabetesPedigreeFunction; // A function that assesses the likelihood of diabetes based on family history
    public int age; // Age of the patient
    public int outcome; // Outcome: 0 (no diabetes) or 1 (has diabetes)

  
    public DiabeteClass(int pregnancies, int glucose, int bloodpressure, int skinthickness, 
                        int insulin, double BMI, double diabetesPedigreeFunction, 
                        int age, int outcome) {

        this.pregnancies = pregnancies;
        this.glucose = glucose;
        this.bloodpressure = bloodpressure;
        this.skinthickness = skinthickness;
        this.insulin = insulin;
        this.BMI = BMI;
        this.diabetesPedigreeFunction = diabetesPedigreeFunction;
        this.age = age;
        this.outcome = outcome;
    }

}
