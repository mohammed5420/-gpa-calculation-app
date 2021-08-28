import javax.swing.*;
public class Project {

    public static int TotalHoursPerSemester = 0;
    public static int currentHours = 0;
    public static double TotalSubjectsPoints = 0;
    public static double currentSemesterPoint = 0;

    //subjects degrees per semester
    public static String semesterSubjectsDegree = ""; 

    //reports and warnings
    public static short reports = 0;

    // failure subjects  
    public static String subject1 = "";
    public static String subject2 = "";
    public static String subject3 = "";
    public static String subject4 = "";
    public static void main (String[] args){
        //init 
        printResults(semesterOne(),gpaCumulative());
        printResults(semesterTow(),gpaCumulative());
        printResults(semesterThree(),gpaCumulative());
        printResults(semesterFour(),gpaCumulative());
        printResults(semesterFive(),gpaCumulative());
        printResults(semesterSix(),gpaCumulative());
        printResults(semesterSeven(),gpaCumulative());
        printResults(semseterEight(),gpaCumulative());
    }
    //calc the subject point and degree
    public static void subjectPoints (String subject ,double subjectDegree){
        if((subjectDegree / 25) >= 2.0){
            //3hours subjects
            if(
                subject == "Arabic1" || subject == "English1" || subject == "Islamic Culture1" || subject == "Basic Mathematics" || subject == "Calculus" || subject == "Islamic Culture2" || subject == "English2" || subject == "Arabic2" || subject == "programming Fundamentals" || subject == "Linear Algebra" || subject == "Probability And Statistics" || subject == "Human Computer Interaction1" || subject == "Information Technology Fundamentals" || subject == "Economics" || subject == "Computer Architecture" || subject == "Database Systems1" || subject == "Software Engineering1" || subject == "Human Computer Interaction2" || subject == "Cryptography And Information Security" || subject == "Algorithm Analysis And Design" || subject == "Software Engineering2" || subject == "Practical Training" || subject == "Database Systems3" || subject == "Information Security Applications"
                ){
                currentSemesterPoint += ((subjectDegree / 25) * 3);
                TotalHoursPerSemester += 3;
            }

            //4hours subjects
            else if(
                subject == "Physics" || subject == "Introduction To Computer Science" || subject == "Accounting Principles" || subject == "Information Technology Hardware" || subject == "Programming Methods1" || subject == "Programming Methods2" || subject == "DataStructures And Algorithms" || subject == "Operating Systems" || subject == "Artificial Intelligence" || subject == "Computer Networks And Communication" || subject == "Database  Systems2" || subject == "Web Application Development1" || subject == "Elective Subject 1" || subject == "Information Technology Project Management" || subject == "Web Application Development2" || subject == "Mobile Computing" || subject == "Elective Subject2"
                ){
                currentSemesterPoint += (subjectDegree / 25) * 4;
                TotalHoursPerSemester += 4;
            }

            //2hours subjects
            else if(subject == "Human Communications" || subject == "Principles Of Management" || subject == "Sudanese Studies" || subject == "Research Methods" || subject == "Professional And Ethical Issues"){
                currentSemesterPoint += ((subjectDegree / 25) * 2); 
                TotalHoursPerSemester += 2;

            }
            //6hours subjects
            else if(subject == "Graduation Project2"){
                currentSemesterPoint += ((subjectDegree / 25) * 6); 
                TotalHoursPerSemester += 6;
            }
            //remove this subject if it was a failure subjecet
            clearFailureSubjects(subject);
        } else {
            //set the current subject as failure subject
            setFailure(subject);
        }
        //add the current subject degree
        setDegreeSubject(subject , (subjectDegree / 25));
        //update global vars
        TotalSubjectsPoints += currentSemesterPoint;
        currentHours += TotalHoursPerSemester;
    }
    //calculate the CGPA
    public static double gpaCumulative (){
        double gpaCumulative = TotalSubjectsPoints / currentHours;
        return round(gpaCumulative);
    }   

    //pass all the subjects including the failure subjects to calculate the gpa
    public static double calcGpa (String semster, String sub1,String sub2,String sub3,String sub4,String sub5,String sub6,String sub7,String sub8 , String sub9,String sub10 ,String sub11) {
        subjectPoints(sub1 ,getResults(sub1 ,semster));
        subjectPoints(sub2 ,getResults(sub2 ,semster));
        subjectPoints(sub3 ,getResults(sub3 ,semster));
        subjectPoints(sub4 ,getResults(sub4 ,semster));
        subjectPoints(sub5 ,getResults(sub5 ,semster));
        subjectPoints(sub6 ,getResults(sub6,semster));

        if(sub7 != "")
            subjectPoints(sub7 ,getResults(sub7 ,semster));
        if(sub8 != "")
            subjectPoints(sub8 ,getResults(sub8 , semster));
        if(sub9 != "")
            subjectPoints(sub9 ,getResults(sub9 , semster));
        if(sub10 != "")
            subjectPoints(sub10 ,getResults(sub10 , semster));
        if(sub11 != "")
            subjectPoints(sub11 ,getResults(sub11 , semster));

        double gpa = round(currentSemesterPoint / TotalHoursPerSemester);
        setReport(gpa , semster);
        return gpa;
    }

    //print the result of the student 
    public static void printResults(double gpa ,double gpaCumulative) {
        JOptionPane.showMessageDialog(null,semesterSubjectsDegree + "\n \n \n GPA: " + gpa + " CGPA is: " + gpaCumulative + " status : n" + reports + " Result : (" + failureSubjects() + ")");

        TotalHoursPerSemester = 0;
        currentSemesterPoint = 0;
        semesterSubjectsDegree = "";
    }
    //get user input
    public static int getResults(String sub , String semester){
        int input = Integer.parseInt( JOptionPane.showInputDialog(null, "please type your " + sub +" degree in semester "+semester+": "));

        //simple validation to secure the app
        while(input > 100 && input > 0){
            JOptionPane.showMessageDialog(null,"please enter vallid mark 100 <= mark >= 0");
            input = Integer.parseInt( JOptionPane.showInputDialog(null, "please type your " + sub +" degree in semester "+semester+": "));
        }

        return input;
    }

    
    public static void setDegreeSubject (String subject ,double degree){
        if(degree >= 3.6)
            semesterSubjectsDegree += " \n " + subject + " " + "A+";
        else if(degree >= 3.2)
            semesterSubjectsDegree += " \n " + subject + " " + "A";
         else if(degree >= 2.8)
            semesterSubjectsDegree += " \n " + subject + " " + "B+";
        else if(degree >= 2.6)
            semesterSubjectsDegree += " \n " + subject + " " + "B";
        else if(degree >= 2.4)
            semesterSubjectsDegree += " \n " + subject + " " + "C+";
        else if(degree >= 2.0)
            semesterSubjectsDegree += " \n " + subject + " " + "C";
         else 
            semesterSubjectsDegree += " \n " + subject + " " + "F";  
    }

    //set the name of the failure subject to variable
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

    //clear the failure subject if the student pass the exam
    public static void clearFailureSubjects(String sub){
        if(subject1 == sub)
            subject1 = "";
        else if (subject2 == sub)
            subject2 = "";
        else if(subject3 == sub)
            subject3 = "";
        else if (subject4 == sub)
            subject4 = ""; 
    }    
    
    //repeat
    public static void repeat(String semester){
        //clear all the faliuer subjects and previos semester's hours
        init();
        JOptionPane.showMessageDialog(null, "Rebeat Semester: " + semester);

        switch(semester){
            case "one":
                printResults(semesterOne(),gpaCumulative());
                printResults(semesterTow(),gpaCumulative());
                printResults(semesterThree(),gpaCumulative());
                printResults(semesterFour(),gpaCumulative());
                printResults(semesterFive(),gpaCumulative());
                printResults(semesterSix(),gpaCumulative());
                printResults(semesterSeven(),gpaCumulative());
                printResults(semseterEight(),gpaCumulative());
                break;
            case "tow":
                printResults(semesterTow(),gpaCumulative());
                printResults(semesterThree(),gpaCumulative());
                printResults(semesterFour(),gpaCumulative());
                printResults(semesterFive(),gpaCumulative());
                printResults(semesterSix(),gpaCumulative());
                printResults(semesterSeven(),gpaCumulative());
                printResults(semseterEight(),gpaCumulative());
                break;
            case "three":
                printResults(semesterThree(),gpaCumulative());
                printResults(semesterFour(),gpaCumulative());
                printResults(semesterFive(),gpaCumulative());
                printResults(semesterSix(),gpaCumulative());
                printResults(semesterSeven(),gpaCumulative());
                printResults(semseterEight(),gpaCumulative());
                break;
            case "four":
                printResults(semesterFour(),gpaCumulative());
                printResults(semesterFive(),gpaCumulative());
                printResults(semesterSix(),gpaCumulative());
                printResults(semesterSeven(),gpaCumulative());
                printResults(semseterEight(),gpaCumulative());
                break;
            case "five":
                printResults(semesterFive(),gpaCumulative());
                printResults(semesterSix(),gpaCumulative());
                printResults(semesterSeven(),gpaCumulative());
                printResults(semseterEight(),gpaCumulative());
                break;   
            case "six":
                printResults(semesterSix(),gpaCumulative());
                printResults(semesterSeven(),gpaCumulative());
                printResults(semseterEight(),gpaCumulative());
                break;  
            case "seven":
                printResults(semesterSeven(),gpaCumulative());
                printResults(semseterEight(),gpaCumulative());
                break; 
            case "eight":
                printResults(semseterEight(),gpaCumulative());
                break;         
        }
    }

    public static double semesterOne (){
        return calcGpa("one","Arabic1","English1","Islamic Culture1","Basic Mathematics","Physics","Introduction To Computer Science","",subject1 ,subject2,subject3,subject4);
    }
    public static double semesterTow (){
        return calcGpa("tow","Arabic2","English2","Islamic Culture 2","Calculus","Principles Of Management","Human Communications","Programming Fundamentals",subject1 ,subject2,subject3,subject4);
    }
    public static double semesterThree (){
        return calcGpa("three","Sudanese Studies","Linear Algebra","Programming Methods1","Discrete Structures","Information Technology Hardware","Accounting Principles","",subject1 ,subject2,subject3,subject4);
    }
    public static double semesterFour (){
        return calcGpa("four","Programming Methods2","DataStructures And Algorithms","Probability And Statistics","Human Computer Interaction1","Information Technology Fundamentals","Economics","",subject1 ,subject2,subject3,subject4);
    }
    public static double semesterFive (){
        return calcGpa("five","Computer Architecture","Artificial Intelligence","Operating Systems","Database Systems1","Software Engineering1","Human Computer Interaction2","",subject1 ,subject2,subject3,subject4);
    }
    public static double semesterSix (){
        return calcGpa("six","Cryptography And Information Security","Computer Networks And Communication","Database  Systems2","Algorithm Analysis And Design","Software Engineering2","Practical Training","",subject1 ,subject2,subject3,subject4);
    }
    public static double semesterSeven (){
        return calcGpa("seven","Research Methods","Information Security Applications","Web Application Development1","Information Technology Project Management","Database Systems3","Elective Subject 1","",subject1 ,subject2,subject3,subject4);
    }
    public static double semseterEight (){
        return calcGpa("eight","Professional And Ethical Issues","Web Application Development2","Mobile Computing","Elective Subject2","Graduation Project2","","",subject1 ,subject2,subject3,subject4);
    }

    public static void setReport(double gpa ,String semester){
        if(subject1 != "" && subject2 != "" && subject3 != "" && subject4 != "")
            repeat(semester);
        else if (gpa <= 2.0) {
            reports += 1;
            if(reports >= 2){
                reports = 0;
                repeat(semester);
            } 
        }

    }
    public static double round(double value){
        value = Math.round(value * 100.0) / 100.0;
        return value;
    }
    public static String failureSubjects (){
        if(subject1 == "" && subject2 == "" && subject3 == "" && subject4 == "")
            return "success";
        else
            return "F " + subject1 + " " + subject2 + " " +  subject3 + " " +  subject4;
    }
    public static void init (){
        subject1 = "";
        subject2 = "";
        subject3 = "";
        subject4 = "";
    }
 
}
