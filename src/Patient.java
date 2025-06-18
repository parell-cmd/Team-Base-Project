import java.io.*;
import java.util.*;

public class Patient {
    public int id;
    public String name;
    public int age;
    public String address;
    public String phone;
    public List<String> records = new ArrayList<>();

    public static LinkedList<Patient> patientList = new LinkedList<>();
    public static BST patientBST = new BST();
    static final String PATIENT_FILE = "d:\\VSCode Files\\Praktikum_SDA\\DaisukeClinic\\src\\PatientRecord.txt";

    public Patient(int id, String name, int age, String address, String phone) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("PatientID: ").append(id)
          .append("\nName: ").append(name)
          .append("\nAge: ").append(age)
          .append("\nAddress: ").append(address)
          .append("\nPhone: ").append(phone);
        for (int i = 0; i < records.size(); i++) {
            sb.append("\nRecord-").append(i + 1).append(": ").append(records.get(i));
        }
        return sb.toString();
    }

    public static void loadPatients() {
        patientList.clear();
        patientBST = new BST();
        try (BufferedReader br = new BufferedReader(new FileReader(PATIENT_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("//") || line.trim().isEmpty()) continue;
                if (line.startsWith("PatientID:")) {
                    int id = Integer.parseInt(line.split(":")[1].trim());
                    String name = br.readLine().split(":")[1].trim();
                    int age = Integer.parseInt(br.readLine().split(":")[1].trim());
                    String address = br.readLine().split(":")[1].trim();
                    String phone = br.readLine().split(":")[1].trim();
                    List<String> records = new ArrayList<>();
                    String recLine;
                    while ((recLine = br.readLine()) != null && recLine.startsWith("Record-")) {
                        records.add(recLine.substring(recLine.indexOf(":") + 1).trim());
                    }
                    // skip ----
                    Patient p = new Patient(id, name, age, address, phone);
                    p.records = records;
                    patientList.add(p);
                    patientBST.insertPatient(p);
                }
            }
        } catch (Exception e) {
            
        }
    }

    public static void savePatients() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(PATIENT_FILE))) {
            for (Patient p : patientList.toList()) {
                pw.println("PatientID: " + p.id);
                pw.println("Name: " + p.name);
                pw.println("Age: " + p.age);
                pw.println("Address: " + p.address);
                pw.println("Phone: " + p.phone);
                for (int i = 0; i < p.records.size(); i++) {
                    pw.println("Record-" + (i + 1) + ": " + p.records.get(i));
                }
                pw.println("----");
            }
        } catch (Exception e) {
            System.out.println("Error saving patients.");
        }
    }

    public static void addPatient(Scanner sc) {
        try {
            System.out.print("Enter Patient ID: ");
            int id = Integer.parseInt(sc.nextLine());
            if (patientList.find(p -> ((Patient)p).id == id) != null) {
                System.out.println("Patient ID already exists.");
                return;
            }
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Age: ");
            int age = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Address: ");
            String address = sc.nextLine();
            System.out.print("Enter Phone: ");
            String phone = sc.nextLine();
            Patient p = new Patient(id, name, age, address, phone);
            patientList.add(p);
            patientBST.insertPatient(p);
            savePatients();
            System.out.println("Patient added.");
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

    public static void removePatientById(Scanner sc) {
        System.out.print("Enter Patient ID to remove: ");
        int id = Integer.parseInt(sc.nextLine());
        boolean removed = patientList.remove(p -> ((Patient)p).id == id);
        savePatients();
        loadPatients();
        if (removed) System.out.println("Patient removed.");
        else System.out.println("Patient not found.");
    }

    public static void findPatientByName(Scanner sc) {
        System.out.print("Enter Patient Name: ");
        String name = sc.nextLine();
        boolean found = false;
        for (Patient p : patientList.toList()) {
            if (p.name.equalsIgnoreCase(name)) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) System.out.println("Patient not found.");
    }

    public static void displayAllPatients() {
        for (Patient p : patientList.toList()) {
            System.out.println(p);
            System.out.println("----");
        }
    }

    public static void searchPatientByIdBST(Scanner sc) {
        System.out.print("Enter Patient ID: ");
        int id = Integer.parseInt(sc.nextLine());
        Patient p = patientBST.searchPatient(id);
        if (p == null) System.out.println("Patient not found.");
        else System.out.println(p);
    }

    public static void displayAllPatientsBST() {
        patientBST.inOrderDisplay();
    }
}