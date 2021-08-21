import java.util.Scanner; 
import javax.swing.*;
public class Project {

    public static int TotalAllSemestersHours = 160;
    public static int TotalHoursPerSemester = 20;
    public static int currentHours = 0;
    public static float TotalSubjectsPoints = 0;
    public static float currentSemesterPoint = 0;
    public static Scanner userInput = new Scanner(System.in);
    public static String semesterSubjectsDegree = ""; 
    public static void main (String[] args){
         
        printResults(calcGpa("one","Arabic1","Eng1","Islamic1","BasicMathematics","Physics","IntroComputer",""),gpaCumulative());
        printResults(calcGpa("tow","Arabic2","Eng2","Islamic2","BasicMathematics","Physics","IntroComputer",""),gpaCumulative());
        printResults(calcGpa("three","Arabic1","Eng1","Islamic1","BasicMathematics","Physics","IntroComputer",""),gpaCumulative());
        printResults(calcGpa("four","Arabic1","Eng1","Islamic1","BasicMathematics","Physics","IntroComputer",""),gpaCumulative());
        printResults(calcGpa("five","Arabic1","Eng1","Islamic1","BasicMathematics","Physics","IntroComputer",""),gpaCumulative());
        printResults(calcGpa("six","Arabic1","Eng1","Islamic1","BasicMathematics","Physics","IntroComputer",""),gpaCumulative());
        printResults(calcGpa("seven","Arabic1","Eng1","Islamic1","BasicMathematics","Physics","IntroComputer",""),gpaCumulative());
        printResults(calcGpa("eight","Arabic1","Eng1","Islamic1","BasicMathematics","Physics","IntroComputer",""),gpaCumulative());
            
        //semester one
            //arabic1 1hour
            //.....  
            //.....

        //semester tow
            //.....
            //
            //
        //semsester three  
            //..... 
    }

    public static void subjectPoints (String subject ,float subjectDegree){

        if((subjectDegree / 25) >= 2.4){
            if(subject == "Arabic1" || subject == "Eng1" || subject == "Islamic1" || subject == "BasicMathematics" || subject == "Calculus" || subject == "Islami2" || subject == "Eng2" || subject == "Arabic2" || subject == "programmingFundamentals"){
                currentSemesterPoint += ((subjectDegree / 25) * 3);
            } else if(subject == "Physics" || subject == "IntroComputer"){
                currentSemesterPoint += (subjectDegree / 25) * 4;
            } else if(subject == "Management" || subject == "Communications"){
                currentSemesterPoint += ((subjectDegree / 25) * 2); 
            }
        } else {
            currentSemesterPoint += ((subjectDegree / 25) * 2); 
            setDegreeSubject(subject , (subjectDegree / 25));
        }
        TotalSubjectsPoints += currentSemesterPoint;
        currentSemesterPoint = 0;
    }

    public static float gpaCumulative (){
        float gpaCumulative = TotalSubjectsPoints / currentHours;
        return gpaCumulative;
    }   


    public static float calcGpa (String semster, String sub1,String sub2,String sub3,String sub4,String sub5,String sub6,String sub7) {

        subjectPoints(sub1 ,getResults("please type your " + sub1 +" degree in semester "+semster+": "));

        System.out.print("please type your " + sub2 +"  in semester "+semster+": ");
        subjectPoints(sub2 ,getResults("please type your " + sub2 +" degree: "));

        System.out.print("please type your " + sub3 +"  in semester "+semster+": ");
        subjectPoints(sub3 ,getResults("please type your " + sub3 +" degree: "));

        System.out.print("please type your " + sub4 +"  in semester "+semster+": ");
        subjectPoints(sub4 ,getResults("please type your " + sub4 +" degree: "));

        System.out.print("please type your " + sub5 +"  in semester "+semster+": ");
        subjectPoints(sub5 ,getResults("please type your " + sub5 +" degree: "));

        System.out.print("please type your " + sub6 +"  in semester "+semster+": ");
        subjectPoints(sub6 ,getResults("please type your " + sub6 +" degree: "));
        if(sub7 != ""){
            System.out.print("please type your " + sub7 +"  in semester "+semster+": ");
            subjectPoints(sub7 ,getResults("please type your " + sub7 +" in semester "+semster+": "));
        }
        currentHours += TotalHoursPerSemester;

        return TotalSubjectsPoints / TotalHoursPerSemester;
    }




    public static void printResults(float gpa ,float gpaCumulative) {
        JFrame frame = new JFrame("GPA computing");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        JOptionPane.showMessageDialog(frame,semesterSubjectsDegree);
        JOptionPane.showMessageDialog(frame, "your GPA is: " + gpa + " and your cumulative GPA is: " + gpaCumulative);
        semesterSubjectsDegree = "";
    }

    public static int getResults(String placeHolder){
        JFrame frame = new JFrame("GPA computing");
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        int input = Integer.parseInt( JOptionPane.showInputDialog(null, placeHolder));
        return input;
    }
    public static String setDegreeSubject (String subject ,float degree){
        String subjectDegree = "";
        if(degree >= 3.6){
            subjectDegree = "A+";
        } else if(degree >= 3.2){
            subjectDegree = "B+";
        } else if(degree >= 2.8){
            subjectDegree = "B";
        } else if(degree >= 2.6){
            subjectDegree = "C+";
        }else if(degree >= 2.4){
            subjectDegree = "C";
        } else {
            subjectDegree = "F";
        } 
        semesterSubjectsDegree += " \n "+subject + " " +subjectDegree;
        return subjectDegree;
    }
}
