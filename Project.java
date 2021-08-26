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

    // failure subjects  
    public static String subject1 = "";
    public static String subject2 = "";
    public static String subject3 = "";
    public static String subject4 = "";
    public static String subject5 = "";
    public static String subject6 = "";

    public static void main (String[] args){
         
        printResults(calcGpa("one","Arabic1","English1","Islamic Culture1","Basic Mathematics","Physics","Introduction To Computer Science","",subject1 ,subject2,subject3,subject4),gpaCumulative());

        printResults(calcGpa("tow","Arabic2","English2","Islamic Culture 2","Calculus","Principles Of Management","Human Communications","Programming Fundamentals",subject1 ,subject2,subject3,subject4),gpaCumulative());

        printResults(calcGpa("three","Sudanese Studies","Linear Algebra","Programming Methods1","Discrete Structures","Information Technology Hardware","Accounting Principles","",subject1 ,subject2,subject3,subject4),gpaCumulative()); 
        
        printResults(calcGpa("four","Programming Methods2","DataStructures And Algorithms","Probability And Statistics","Human Computer Interaction1","Information Technology Fundamentals","Economics","",subject1 ,subject2,subject3,subject4),gpaCumulative());

        printResults(calcGpa("five","Computer Architecture","Artificial Intelligence","Operating Systems","Database Systems1","Software Engineering1","Human Computer Interaction2","",subject1 ,subject2,subject3,subject4),gpaCumulative());

        printResults(calcGpa("six","Cryptography And Information Security","Computer Networks And Communication","Database  Systems2","Algorithm Analysis And Design","Software Engineering2","Practical Training","",subject1 ,subject2,subject3,subject4),gpaCumulative());

        printResults(calcGpa("seven","Research Methods","Information Security Applications","Web Application Development1","Information Technology Project Management","Database Systems3","Elective Subject 1","",subject1 ,subject2,subject3,subject4),gpaCumulative());

        printResults(calcGpa("eight","Professional And Ethical Issues","Web Application Development2","Mobile Computing","Elective Subject2","Graduation Project2","","",subject1 ,subject2,subject3,subject4),gpaCumulative());

    }

    public static void subjectPoints (String subject ,float subjectDegree){

        if((subjectDegree / 25) >= 2.4){

            //3hours
            if(
                subject == "Arabic1" || subject == "English1" || subject == "Islamic Culture1" || subject == "Basic Mathematics" || subject == "Calculus" || subject == "Islamic Culture2" || subject == "English2" || subject == "Arabic2" || subject == "programming Fundamentals" || subject == "Linear Algebra" || subject == "Probability And Statistics" || subject == "Human Computer Interaction1" || subject == "Information Technology Fundamentals" || subject == "Economics" || subject == "Computer Architecture" || subject == "Database Systems1" || subject == "Software Engineering1" || subject == "Human Computer Interaction2" || subject == "Cryptography And Information Security" || subject == "Algorithm Analysis And Design" || subject == "Software Engineering2" || subject == "Practical Training" || subject == "Database Systems3" || subject == "Information Security Applications"
                ){
                currentSemesterPoint += ((subjectDegree / 25) * 3);
            }

            //4hours
            else if(
                subject == "Physics" || subject == "Introduction To Computer Science" || subject == "Accounting Principles" || subject == "Information Technology Hardware" || subject == "Programming Methods1" || subject == "Programming Methods2" || subject == "DataStructures And Algorithms" || subject == "Operating Systems" || subject == "Artificial Intelligence" || subject == "Computer Networks And Communication" || subject == "Database  Systems2" || subject == "Web Application Development1" || subject == "Elective Subject 1" || subject == "Information Technology Project Management" || subject == "Web Application Development2" || subject == "Mobile Computing" || subject == "Elective Subject2"
                ){
                currentSemesterPoint += (subjectDegree / 25) * 4;
            }

            //2hours
            else if(subject == "Human Communications" || subject == "Principles Of Management" || subject == "Sudanese Studies" || subject == "Research Methods" || subject == "Professional And Ethical Issues"){
                currentSemesterPoint += ((subjectDegree / 25) * 2); 
            }

            //6hours
            else if(subject == "Graduation Project2"){
                currentSemesterPoint += ((subjectDegree / 25) * 6); 
            }
        } else {
            setFailure(subject);
        }
        setDegreeSubject(subject , (subjectDegree / 25));
        TotalSubjectsPoints += currentSemesterPoint;
        currentSemesterPoint = 0;
    }

    public static float gpaCumulative (){
        float gpaCumulative = TotalSubjectsPoints / currentHours;
        return gpaCumulative;
    }   


    public static float calcGpa (String semster, String sub1,String sub2,String sub3,String sub4,String sub5,String sub6,String sub7,String sub8 , String sub9,String sub10 ,String sub11) {

        subjectPoints(sub1 ,getResults("please type your " + sub1 +" degree in semester "+semster+": "));

        subjectPoints(sub2 ,getResults("please type your " + sub2 +" degree: "));

        subjectPoints(sub3 ,getResults("please type your " + sub3 +" degree: "));

        subjectPoints(sub4 ,getResults("please type your " + sub4 +" degree: "));

        subjectPoints(sub5 ,getResults("please type your " + sub5 +" degree: "));

        subjectPoints(sub6 ,getResults("please type your " + sub6 +" degree: "));
        if(sub7 != ""){
            subjectPoints(sub7 ,getResults("please type your " + sub7 +" in semester "+semster+": "));
        }
        if(sub8 != ""){
            subjectPoints(sub8 ,getResults("please type your " + sub8 +" in semester "+semster+": "));
        }
        if(sub9 != ""){
            subjectPoints(sub9 ,getResults("please type your " + sub9 +" in semester "+semster+": "));
        }
        if(sub10 != ""){
            subjectPoints(sub10 ,getResults("please type your " + sub10 +" in semester "+semster+": "));
        }       
         if(sub11 != ""){
            subjectPoints(sub11 ,getResults("please type your " + sub11 +" in semester "+semster+": "));
        }
        currentHours += TotalHoursPerSemester;
        return TotalSubjectsPoints / TotalHoursPerSemester;
    }




    public static void printResults(float gpa ,float gpaCumulative) {
        System.out.print(semesterSubjectsDegree);
        JOptionPane.showMessageDialog(null,semesterSubjectsDegree);
        JOptionPane.showMessageDialog(null, "your GPA is: " + gpa + " and your cumulative GPA is: " + gpaCumulative);
  
    }

    public static int getResults(String placeHolder){
        int input = Integer.parseInt( JOptionPane.showInputDialog(null, placeHolder));
        return input;
    }
    public static void setDegreeSubject (String subject ,float degree){
        if(degree >= 3.6){
            semesterSubjectsDegree += " \n " + subject + " " + "A+";
        } else if(degree >= 3.2){
            semesterSubjectsDegree += " \n " + subject + " " + "B+";
        } else if(degree >= 2.8){
            semesterSubjectsDegree += " \n " + subject + " " + "B";
        } else if(degree >= 2.6){
            semesterSubjectsDegree += " \n " + subject + " " + "C+";
        }else if(degree >= 2.4){
            semesterSubjectsDegree += " \n " + subject + " " + "C";
        } else {
            semesterSubjectsDegree += " \n " + subject + " " + "F";
        } 
    }

    public static void setFailure(String sub){
        if(subject1 == "")
            subject1 = sub;
        else if (subject2 =="")
            subject2 = sub;
        else if(subject3 == "")
            subject3 = sub;
        else 
            subject4 = sub;
        
    }
}
