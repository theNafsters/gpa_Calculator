package gpa_Calculator;
import java.util.Scanner; //for reading standard input

public class gpa_Calculator {
    public static void main(String[] args) {
        final int MAX_COURSES = 100; // Max number of courses allowed
        double [] credits = new double [MAX_COURSES]; // array of credits
        String[] grades = new String[MAX_COURSES]; //array of letter grades
        String[] courses = new String[MAX_COURSES]; //array of course names
        System.out.println("********************************************************");
        System.out.println("*** Welcome to the bested GPA calculator ever...     ***");
        System.out.println("*** The program will ask you first to enter the      ***");
        System.out.println("*** number of credits you have accumulated so far    ***");
        System.out.println();
        System.out.println("*** If this is your very first semester, please      ***");
        System.out.println("*** enter 0.                                         ***");
        System.out.println("********************************************************");
        Scanner sc = new Scanner(System.in); //scanner
        double prevCredits;
        while(true){
            System.out.print("Please enter how many credits you have so far not including your current semester: ");
            prevCredits = sc.nextDouble();
            if(prevCredits > -1) break; // breaking out of the loop since the number of credits is valid 
            else System.out.println("ERROR: number of past credits has to be a positive number.");    
        }
        double prevGpa = 0;
        if(prevCredits!=0){
            while(true){
                System.out.print("Please enter your current GPA: ");
                prevGpa = sc.nextDouble(); 
                if((prevGpa >= 0)&&(prevGpa <= 4.3)) break; // breaking out of the loop since the integer of the GPA is valid
                else System.out.println("ERROR: a valid GPA is between 0 and 4.3 inclusive.");
            }
        }
        int numCourses;
        while(true){
            System.out.print("Please enter how many courses do you want to add to the GPA calculator: ");
            numCourses = sc.nextInt();
            sc.nextLine();
            if(numCourses > MAX_COURSES) System.out.println("ERROR: number of courses has to be less than equal to max courses limit.");
            else if(numCourses > 0) break; // breaking out of the loop since the number of courses is valid (bigger than zero and less than the limit)
            else System.out.println("ERROR: number of courses has to be a positive number.");
        }
        double totalCredits = 0;
        for(int i=0;i<numCourses;i++){ // looping until the number of classes entered by the user
            System.out.print("Please enter the name of the course number " + (i+1)+ ": ");
            courses[i] = sc.nextLine();
            while(true){
                System.out.print("Please enter the number of credits of course number " + (i+1)+ ": ");
                credits[i] = sc.nextDouble();
                sc.nextLine();
                if(credits[i]>0) break; // breaking out of the loop when user enters valid number of courses (positive number)
                else System.out.println("ERROR: course has to be a positive number.");
            }
            String[] validGrades = {"A+", "A", "A-", "B+", "B", "B-", "C+", "C", "C-", "D+", "D", "D-", "FNS"};// this part is for checking valid grades
            while(true){
                System.out.print("Please enter the letter grade of course number " + (i+1)+ ": ");
                grades[i] = sc.nextLine();
                boolean isValidGrade = false;
                for(int j=0;j<validGrades.length;j++){
                    if(validGrades[j].equals(grades[i])){
                        isValidGrade = true;
                        break;
                    }
                }
                if(isValidGrade) break;
                else { 
                    System.out.println("ERROR: course grade has to be one of the following...");
                    System.out.println();
                    System.out.println("A+, A, A-, B+, B, B-, C+, C, C-, D+, D, D-, FNS");
                }
            }
            totalCredits += credits[i];
        }
        while(true){
            System.out.println("Please choose one of the following options");
            System.out.println("Please 1: to display the courses in the system ");
            System.out.println("Please 2: to display the new GPA");
            System.out.println("Please 3: to delete a course ");
            System.out.println("Please 4: to add a course ");
            System.out.println("Please 5: to exit the program ");
            int opt = sc.nextInt();
            sc.nextLine();
            switch(opt){
                case 1:
                    System.out.println("Your current GPA is: " + prevGpa);
                    System.out.println("Your accumulated credits so far: " + prevCredits);
                    System.out.println("Course Number\tCourse Name\tCourse Credit\tCourse Grade");
                    for(int i=0;i<numCourses;i++) 
                    	System.out.println((i+1)+"\t\t"+courses[i]+"\t\t"+credits[i]+"\t\t"+grades[i]); 
                    break;
                case 2:
                    double weightedAvg = 0;
                    for(int i=0;i<numCourses;i++){ // loop for every course
                        switch(grades[i]){
                            case "A+":
                                weightedAvg += credits[i]*4.3;
                                break;
                            case "A":
                                weightedAvg += credits[i]*4.0;
                                break;
                            case "A-":
                                weightedAvg += credits[i]*3.7;
                                break;
                            case "B+":
                                weightedAvg += credits[i]*3.3;
                                break;
                            case "B":
                                weightedAvg += credits[i]*3.0;
                                break;
                            case "B-":
                                weightedAvg += credits[i]*2.7;
                                break;
                            case "C+":
                                weightedAvg += credits[i]*2.3;
                                break;    
                            case "C":
                                weightedAvg += credits[i]*2.0;
                                break;
                            case "C-":
                                weightedAvg += credits[i]*1.7;
                                break;
                            case "D+":
                                weightedAvg += credits[i]*1.3;
                                break;
                            case "D":
                                weightedAvg += credits[i]*1.0;
                                break;
                            case "D-":
                                weightedAvg += credits[i]*0.7;
                                break;    
                            case "FNS":
                                weightedAvg += 0;
                                break;
                            default:
                                break;
                        }
                    }
                    double gpa = (prevGpa*prevCredits+weightedAvg)/(totalCredits+prevCredits);
                    System.out.println("Your new GPA is: " + gpa);
                    break;
                case 3:
                    System.out.println("Course Number\tCourse Name\tCourse Credit\tCourse Grade");
                    for(int i=0;i<numCourses;i++) System.out.println((i+1)+"\t\t"+courses[i]+"\t\t"+credits[i]+"\t\t"+grades[i]);
                    System.out.print("Please enter the course number you want to delete: ");
                    int courseNumber = sc.nextInt()-1;
                    sc.nextLine();
                    if(courseNumber<numCourses)
                    	totalCredits -= credits[courseNumber];
                    for(int i=courseNumber;(i+1)<numCourses;i++){
                        grades[i] = grades[i+1];
                        courses[i] = courses[i+1];
                        credits[i] = credits[i+1];
                    }
                    numCourses--;
                    break;
                case 4:
                    System.out.print("Please enter the name of the course number " + (numCourses+1) + ": ");
                    courses[numCourses] = sc.nextLine();
                    System.out.print("Please enter the number of credits of course number " + numCourses + ": ");
                    credits[numCourses] = sc.nextDouble();
                    sc.nextLine();
                    System.out.print("Please enter the letter grade of course number " + numCourses + ": ");
                    grades[numCourses] = sc.nextLine();
                    totalCredits += credits[numCourses];
                    numCourses++;
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Menu option has to be between 1 and 5 inclusive");
                    break;   
            }
        }
    }
}