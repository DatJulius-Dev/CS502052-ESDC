// DO NOT USE PACKAGE

import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class StudentManagementImpl implements StudentManagement {
    
    ArrayList<Student> students;

    public StudentManagementImpl()
    {
        this.students = new ArrayList<>();
    }

    public StudentManagementImpl(String dataPath)
    {
        this.students = new ArrayList<>();
        readData(dataPath);
    }

    private void readData(String filePath)
    {
        try
        {
            File file = new File(filePath);
            BufferedReader br = new BufferedReader(new FileReader(file));
            
            String readLine = "";
            while ((readLine = br.readLine()) != null)
            {
                String[] info = readLine.split("\t");
                Student s = new Student(info[0], info[1], info[2], Double.parseDouble(info[3]),info[4]);
                this.students.add(s);
            }
            
            br.close();
        } catch (IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public int getNoOfStudent() throws RemoteException
    {
        return students.size();
    }

    @Override
    public int getNoOfStudent_byGender(String gender) throws RemoteException
    {
        int countGender = 0;
        for (Student student : students){
            if(student.getGender().equalsIgnoreCase(gender)){
                countGender++;
            }
        }
        return countGender;
    }

    @Override
    public int getNoOfStudent_byMajor(String major) throws RemoteException
    {
        int countMajor = 0;
        for (Student student: students){
            if(student.getMajor().equalsIgnoreCase(major)){
                countMajor++;
            }
        }
        return countMajor;
    }

    @Override
    public int getNoOfStudent_byGPA(double gpa) throws RemoteException
    {
        int countGPA = 0;
        for (Student student : students){
            if(student.getGpa() < gpa){
                countGPA++;
            }
        }
        return countGPA;
    }

    @Override
    public Student findStudent_byName(String name) throws RemoteException
    {
        Student student = new Student();
        for (Student s : students){
            if(s.getName().equalsIgnoreCase(name)){
                student = s;
            }
        }
        if (!student.getName().equalsIgnoreCase(name)){
            return null;
        }
        return student;
    }

    @Override
    public Student findStudent_byID(String id) throws RemoteException
    {
        Student student = new Student();
        for (Student s : students){
            if(s.getId().equalsIgnoreCase(id)){
                student = s;
            }
        }
        if (!student.getId().equalsIgnoreCase(id)){
            return null;
        }
        return student;
    }

    @Override
    public ArrayList<Student> findStudent_byMajor(String major) throws RemoteException
    {
        ArrayList<Student> a = new ArrayList<>();
        for (Student s:students){
            if(s.getMajor().equalsIgnoreCase(major)){
                a.add(s);
            }
        }
        return a;
    }

    @Override
    public ArrayList<Student> findStudent_byGPA(double GPA) throws RemoteException
    {
        ArrayList<Student> b = new ArrayList<>();
        for (Student s:students){
            if(s.getGpa() < GPA){
                b.add(s);
            }
        }
        return b;
    }

    @Override
    public double getAvgGPA() throws RemoteException
    {
        double totalGPA = 0.0;
        for(Student s:students){
            totalGPA += s.getGpa();
        }
        return totalGPA/getNoOfStudent();
    }

    @Override
    public Student getHighestGPA_byGender(String gender) throws RemoteException {
        Student highestGPAStudent = null;
        double highestGPA = -1.0;
        
        for (Student s : students) {
            if (s.getGender().equalsIgnoreCase(gender) && s.getGpa() > highestGPA) {
                highestGPA = s.getGpa();
                highestGPAStudent = s;
            }
        }

        return highestGPAStudent;
    }

    @Override
    public Student getHighestGPA_byFName(String fname) throws RemoteException
    {
        Student student = null;
        double highestGPA = -1;
        for (Student s : students) {
            String[] names = s.getName().split(" ");
            String firstname = names[0];
            if (fname.equalsIgnoreCase(firstname)) {
                if(s.getGpa() > highestGPA) {
                    highestGPA = s.getGpa();
                    student = s;
                }
            }
        }
        return student;
    }

    @Override
    public Student getHighestGPA_byLName(String lname) throws RemoteException {
        Student student = null;
        double highestGPA = -1;

        for (Student s : students) {
            String[] names = s.getName().split(" ");
            String lastName = names[names.length - 1];
            if (lname.equalsIgnoreCase(lastName) && s.getGpa() > highestGPA) {
                highestGPA = s.getGpa();
                student = s;
            }
        }
        return student;
    }

    @Override
    public Student getHighestGPA_byMajor(String major) throws RemoteException {
        Student student = null;
        double highestGPA = -1;

        for (Student s : students) {
            if (s.getMajor().equalsIgnoreCase(major) && s.getGpa() > highestGPA) {
                highestGPA = s.getGpa();
                student = s;
            }
        }
        return student;
    }


    @Override
    public Student getLowestGPA_byMajor(String major) throws RemoteException {
        Student student = null;
        double lowestGPA = 11;

        for (Student s : students) {
            if (s.getMajor().equalsIgnoreCase(major) && s.getGpa() < lowestGPA) {
                lowestGPA = s.getGpa();
                student = s;
            }
        }
        return student;
    }


    @Override
	public ArrayList<Student> getTop10_byGPA() throws RemoteException
	{
		ArrayList<Student> a  = new ArrayList<>(students);
        a.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s1.getGpa(), s2.getGpa());
            }
        });
        return new ArrayList<>(a.subList(0, Math.min(10, a.size())));
	}

	@Override
	public ArrayList<Student> getTop10GPA_byGender(String gender) throws RemoteException
	{
		ArrayList<Student> a  = new ArrayList<>();
        for (Student s : students) {
            if(s.getGender().equalsIgnoreCase(gender)) {
                a.add(s);
            }
        }
        a.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s1.getGpa(), s2.getGpa());
            }
        });
        return new ArrayList<>(a.subList(0, Math.min(10, a.size())));
	}

	@Override
	public ArrayList<Student> getTop10GPA_byMajor(String major) throws RemoteException
	{
		ArrayList<Student> a  = new ArrayList<>();
        for (Student s : students) {
            if(s.getMajor().equalsIgnoreCase(major)) {
                a.add(s);
            }
        }
        a.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s1.getGpa(), s2.getGpa());
            }
        });
        return new ArrayList<>(a.subList(0, Math.min(10, a.size())));
	}

	@Override
	public ArrayList<Student> getLast10GPA_byGender(String gender) throws RemoteException
	{
		ArrayList<Student> a  = new ArrayList<>();
        for (Student s : students) {
            if(s.getGender().equalsIgnoreCase(gender)) {
                a.add(s);
            }
        }
        a.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.getGpa(), s1.getGpa());
            }
        });
        return new ArrayList<>(a.subList(0, Math.min(10, a.size())));
	}

	@Override
	public ArrayList<Student> getLast10GPA_byMajor(String major) throws RemoteException
	{
		ArrayList<Student> a  = new ArrayList<>();
        for (Student s : students) {
            if(s.getMajor().equalsIgnoreCase(major)) {
                a.add(s);
            }
        }
        a.sort(new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                return Double.compare(s2.getGpa(), s1.getGpa());
            }
        });
        return new ArrayList<>(a.subList(0, Math.min(10, a.size())));
	}

    @Override
    public ArrayList<String> getMajors() throws RemoteException
    {
        ArrayList<String> major = new ArrayList<>();
        for (Student s : students) {
            if(!major.contains(s.getMajor())) {
                major.add(s.getMajor());
            }
        }
        return major;
    }
}