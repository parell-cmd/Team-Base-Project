import java.io.*;
import java.util.*;

public class Doctor {
    public int id;
    public String name;
    public String specialty;

    public static LinkedList<Doctor> doctorList = new LinkedList<>();
    public static LinkedList<Doctor> loggedInDoctors = new LinkedList<>();
    public static Stack<Doctor> doctorLoginStack = new Stack<>();
    public static Map<Integer, List<String>> doctorTimeMap = new HashMap<>();
    static final String DOCTOR_FILE = "d:\\VSCode Files\\Praktikum_SDA\\DaisukeClinic\\src\\DoctorData.txt";
    static final List<String> DEFAULT_TIMES = Arrays.asList("09.00", "10.00", "12.00", "14.00", "18.00");

    public Doctor(int id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    @Override
    public String toString() {
        return "DoctorID: " + id + "\nName: " + name + "\nSpecialty: " + specialty;
    }

    public static void loadDoctors() {
        doctorList.clear();
        doctorTimeMap.clear();
        try (BufferedReader br = new BufferedReader(new FileReader(DOCTOR_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("//") || line.trim().isEmpty()) continue;
                if (line.startsWith("DoctorID:")) {
                    int id = Integer.parseInt(line.split(":")[1].trim());
                    String name = br.readLine().split(":")[1].trim();
                    String specialty = br.readLine().split(":")[1].trim();
                    br.readLine(); // skip ----
                    Doctor d = new Doctor(id, name, specialty);
                    doctorList.add(d);
                    doctorTimeMap.put(id, new ArrayList<>(DEFAULT_TIMES));
                }
            }
        } catch (Exception e) {
            
        }
    }

    public static void saveDoctors() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(DOCTOR_FILE))) {
            for (Doctor d : doctorList.toList()) {
                pw.println("DoctorID: " + d.id);
                pw.println("Name: " + d.name);
                pw.println("Specialty: " + d.specialty);
                pw.println("----");
            }
        } catch (Exception e) {
            System.out.println("Error saving doctors.");
        }
    }

    public static void registerDoctor(Scanner sc) {
        try {
            System.out.print("Enter Doctor ID: ");
            int id = Integer.parseInt(sc.nextLine());
            if (doctorList.find(d -> ((Doctor)d).id == id) != null) {
                System.out.println("Doctor ID already exists.");
                return;
            }
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Specialty: ");
            String specialty = sc.nextLine();
            Doctor d = new Doctor(id, name, specialty);
            doctorList.add(d);
            doctorTimeMap.put(id, new ArrayList<>(DEFAULT_TIMES));
            saveDoctors();
            System.out.println("Doctor registered.");
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    public static void deleteDoctor(Scanner sc) {
        System.out.print("Enter Doctor ID to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean removed = doctorList.remove(d -> ((Doctor)d).id == id);
        doctorTimeMap.remove(id);
        saveDoctors();
        if (removed) System.out.println("Doctor deleted.");
        else System.out.println("Doctor not found.");
    }

    public static void loginDoctor(Scanner sc) {
        System.out.print("Enter Doctor ID to login: ");
        int id = Integer.parseInt(sc.nextLine());
        Doctor d = doctorList.find(doc -> ((Doctor)doc).id == id);
        if (d == null) {
            System.out.println("Doctor not found.");
            return;
        }
        if (loggedInDoctors.find(doc -> ((Doctor)doc).id == id) != null) {
            System.out.println("Doctor already logged in.");
            return;
        }
        System.out.print("Enter login time (e.g. 08.00): ");
        String time = sc.nextLine();
        loggedInDoctors.add(d);
        doctorLoginStack.push(d);
        System.out.println("Doctor logged in at " + time);
    }

    public static void logoutDoctor(Scanner sc) {
        System.out.print("Enter Doctor ID to logout: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean removed = loggedInDoctors.remove(doc -> ((Doctor)doc).id == id);
        Stack<Doctor> temp = new Stack<>();
        boolean found = false;
        while (!doctorLoginStack.isEmpty()) {
            Doctor d = doctorLoginStack.pop();
            if (!found && d.id == id) {
                found = true;
                continue;
            }
            temp.push(d);
        }
        while (!temp.isEmpty()) doctorLoginStack.push(temp.pop());
        if (removed) System.out.println("Doctor logged out.");
        else System.out.println("Doctor not logged in.");
    }

    public static void viewLastLoggedInDoctor() {
        Doctor d = doctorLoginStack.peek();
        if (d == null) System.out.println("No doctor logged in.");
        else System.out.println(d);
    }
}