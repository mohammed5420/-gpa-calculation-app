import javax.swing.*;
public class GPACalculatorV2 {
    public static int TotalHoursPerSemester = 0;
    public static int currentHours = 0;
    public static double TotalSubjectsPoints = 0;
    public static double currentSemesterPoint = 0;
    public static double CGPA = 0;
    //subjects degrees per semester
    public static String semesterSubjectsDegree = ""; 

    //reports and warnings
    public static short reports = 0;

    // failure subjects
    public static String [] failureSubjects = new String[4];
    public static String [] semesterOneSubjects = {"Arabic1","English1","Islamic Culture1","Basic Mathematics","Physics","Introduction To Computer Science"};  
    public static String [] semesterTwoSubjects = {"Arabic2","English2","Islamic Culture 2","Calculus","Principles Of Management","Human Communications","Programming Fundamentals"};  
    public static String [] semesterThreeSubjects = {"Sudanese Studies","Linear Algebra","Programming Methods1","Discrete Structures","Information Technology Hardware","Accounting Principles"};  
    public static String [] semesterFourSubjects = {"Programming Methods2","DataStructures And Algorithms","Probability And Statistics","Human Computer Interaction1","Information Technology Fundamentals","Economics"};  
    public static String [] semesterFiveSubjects = {"Computer Architecture","Artificial Intelligence","Operating Systems","Database Systems1","Software Engineering1","Human Computer Interaction2"};  
    public static String [] semesterSixSubjects = {"Cryptography And Information Security","Computer Networks And Communication","Database  Systems2","Algorithm Analysis And Design","Software Engineering2","Practical Training"};  
    public static String [] semesterSevenSubjects = {"Research Methods","Information Security Applications","Web Application Development1","Information Technology Project Management","Database Systems3","Elective Subject 1"};  
    public static String [] semesterEightSubjects = {"Professional And Ethical Issues","Web Application Development2","Mobile Computing","Elective Subject2","Graduation Project2"};  

    public static void main (String[] args){
        //init 
        printResults(calcGpa("one",semesterOneSubjects),gpaCumulative(),"one");
        printResults(calcGpa("two",semesterTwoSubjects),gpaCumulative(),"two");
        printResults(calcGpa("three",semesterThreeSubjects),gpaCumulative(),"three");
        printResults(calcGpa("four",semesterFourSubjects),gpaCumulative(),"four");
        printResults(calcGpa("five",semesterFiveSubjects),gpaCumulative(),"five");
        printResults(calcGpa("six",semesterSixSubjects),gpaCumulative(),"six");
        printResults(calcGpa("seven",semesterSevenSubjects),gpaCumulative(),"seven");
        printResults(calcGpa("eight",semesterEightSubjects),gpaCumulative(),"eight");
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
        double gpaCumulative = round(TotalSubjectsPoints / currentHours);
        if(gpaCumulative < 2.0){
            reports++;
        }
        return gpaCumulative;
    }   

    //pass all the subjects including the failure subjects to calculate the gpa
    public static double calcGpa (String semster, String[] subjects) {
        String[] totalSubjects = new String[subjects.length + failureSubjects.length];
        System.arraycopy(subjects, 0, totalSubjects, 0, subjects.length);
        System.arraycopy(failureSubjects, 0, totalSubjects, subjects.length, failureSubjects.length);

        for(String subject: totalSubjects){
            if(subject != null)
            subjectPoints(subject ,getResults(subject ,semster));
        } 

        double gpa = round(currentSemesterPoint / TotalHoursPerSemester);
        return gpa;
    }

    //print the result of the student 
    public static void printResults(double gpa ,double gpaCumulative ,String semester) {
        JOptionPane.showMessageDialog(null,semesterSubjectsDegree + "\n \n \n GPA: " + gpa + " CGPA is: " + gpaCumulative + " status : n" + reports + " Result : (" + printFailureSubjects() + ")");
        setReport(gpaCumulative , semester);
        clearSemesterData();
    }
    //get user input
    public static int getResults(String sub , String semester){
        boolean faileValidation = true;
        int marks = 0;
        String input= JOptionPane.showInputDialog(null, "please type your marks in " + sub +" ","semester " +semester,JOptionPane.PLAIN_MESSAGE);
        while(faileValidation){
            try {
                marks = Integer.parseInt(input);
                if(marks <= 100 && marks >= 0){
                    faileValidation = false;
                } else{
                    input = JOptionPane.showInputDialog(null, "please enter valid marks in " + sub +" ","Semester " +semester,JOptionPane.PLAIN_MESSAGE);
                }
            } catch(NumberFormatException e){
                if(input == null){
                    System.exit(1);
                }
                 input = JOptionPane.showInputDialog(null, "please enter valid marks in " + sub +" ","Semester " +semester,JOptionPane.PLAIN_MESSAGE);
            }
        }
        return marks;
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
        for(int subjectIndex = 0; subjectIndex < failureSubjects.length; subjectIndex++){
            if(failureSubjects[subjectIndex] == null){
                failureSubjects[subjectIndex] = sub;
                break;
            }
        }
    }

    //clear the failure subject if the student pass the exam
    public static void clearFailureSubjects(String sub){
        for(int subjectIndex = 0; subjectIndex < failureSubjects.length; subjectIndex++){
            if(failureSubjects[subjectIndex] == sub){
                failureSubjects[subjectIndex] = null;
                break;
            }
        }
    }    
    
    //repeat
    public static void repeat(String semester){
        //clear all the faliuer subjects and previos semester's hours
        
        JOptionPane.showMessageDialog(null, "Rebeat Semester: " + semester);
        clearSemesterData();

        switch(semester){
            case "one":
            init();
                printResults(calcGpa("one",semesterOneSubjects),gpaCumulative(),"one");
                printResults(calcGpa("two",semesterTwoSubjects),gpaCumulative(),"two");
                printResults(calcGpa("three",semesterThreeSubjects),gpaCumulative(),"three");
                printResults(calcGpa("four",semesterFourSubjects),gpaCumulative(),"four");
                printResults(calcGpa("five",semesterFiveSubjects),gpaCumulative(),"five");
                printResults(calcGpa("six",semesterSixSubjects),gpaCumulative(),"six");
                printResults(calcGpa("seven",semesterSevenSubjects),gpaCumulative(),"seven");
                printResults(calcGpa("eight",semesterEightSubjects),gpaCumulative(),"eight");
                break;
            case "tow":
                init();
                printResults(calcGpa("two",semesterTwoSubjects),gpaCumulative(),"two");
                printResults(calcGpa("three",semesterThreeSubjects),gpaCumulative(),"three");
                printResults(calcGpa("four",semesterFourSubjects),gpaCumulative(),"four");
                printResults(calcGpa("five",semesterFiveSubjects),gpaCumulative(),"five");
                printResults(calcGpa("six",semesterSixSubjects),gpaCumulative(),"six");
                printResults(calcGpa("seven",semesterSevenSubjects),gpaCumulative(),"seven");
                printResults(calcGpa("eight",semesterEightSubjects),gpaCumulative(),"eight");
                break;
            case "three":
                init();
                printResults(calcGpa("three",semesterThreeSubjects),gpaCumulative(),"three");
                printResults(calcGpa("four",semesterFourSubjects),gpaCumulative(),"four");
                printResults(calcGpa("five",semesterFiveSubjects),gpaCumulative(),"five");
                printResults(calcGpa("six",semesterSixSubjects),gpaCumulative(),"six");
                printResults(calcGpa("seven",semesterSevenSubjects),gpaCumulative(),"seven");
                printResults(calcGpa("eight",semesterEightSubjects),gpaCumulative(),"eight");
                break;
            case "four":
                init();
                printResults(calcGpa("four",semesterFourSubjects),gpaCumulative(),"four");
                printResults(calcGpa("five",semesterFiveSubjects),gpaCumulative(),"five");
                printResults(calcGpa("six",semesterSixSubjects),gpaCumulative(),"six");
                printResults(calcGpa("seven",semesterSevenSubjects),gpaCumulative(),"seven");
                printResults(calcGpa("eight",semesterEightSubjects),gpaCumulative(),"eight");
                break;
            case "five":
                init();
                printResults(calcGpa("five",semesterFiveSubjects),gpaCumulative(),"five");
                printResults(calcGpa("six",semesterSixSubjects),gpaCumulative(),"six");
                printResults(calcGpa("seven",semesterSevenSubjects),gpaCumulative(),"seven");
                printResults(calcGpa("eight",semesterEightSubjects),gpaCumulative(),"eight");
                break;   
            case "six":
                init();
                printResults(calcGpa("six",semesterSixSubjects),gpaCumulative(),"six");
                printResults(calcGpa("seven",semesterSevenSubjects),gpaCumulative(),"seven");
                printResults(calcGpa("eight",semesterEightSubjects),gpaCumulative(),"eight");
                break;  
            case "seven":
                init();
                printResults(calcGpa("seven",semesterSevenSubjects),gpaCumulative(),"seven");
                printResults(calcGpa("eight",semesterEightSubjects),gpaCumulative(),"eight");
                break;
            case "eight":
                init();
                printResults(calcGpa("eight",semesterEightSubjects),gpaCumulative(),"eight");
                break;         
        }
    }

    public static void setReport(double gpa ,String semester){
        boolean isFaile = true;
        System.out.print(String.join(",",failureSubjects));
        for(String failSubject: failureSubjects){
            if(failSubject == null){
                isFaile = false;
            }
            break;
        }

        if(reports > 2){
            reports = 0;
            JOptionPane.showMessageDialog(null, "Sorry you have two wornings and this is the maximum number of wornigs and you have been canceld!");
            System.exit(1);
        } else if(isFaile){
            repeat(semester);
        }  

    }
    public static double round(double value){
        double roundedValue = Math.round(value * 100.0) / 100.0;
        return roundedValue;
    }
    public static String printFailureSubjects (){
        boolean isSuccess = true;
        for(String subject: failureSubjects){
            if(subject != null){
                isSuccess = false;
            }
            break;
        }
        if(isSuccess){
            return "success";
        }
        else{
            String joinedString = String.join(", ", failureSubjects);
            return "F " + joinedString;
        }
    }
    public static void init (){
        for(int subjectIndex = 0; subjectIndex < failureSubjects.length; subjectIndex++){
                failureSubjects[subjectIndex] = null;
        }
    }
    public static void clearSemesterData () {
        TotalHoursPerSemester = 0;
        currentSemesterPoint = 0;
        semesterSubjectsDegree = "";
    }
}
