import java.util.*;

public class Main {
    static LinkedList<Patient> patientList = new LinkedList<>();
    static BST patientBST = new BST();
    static LinkedList<Doctor> doctorList = new LinkedList<>();
    static LinkedList<Doctor> loggedInDoctors = new LinkedList<>();
    static Stack<Doctor> doctorLoginStack = new Stack<>();
    static Queue<Appointment> appointmentQueue = new Queue<>();
    static Map<Integer, List<String>> doctorTimeMap = new HashMap<>(); // doctorId -> available times

    static final String PATIENT_FILE = "d:\\VSCode Files\\Praktikum_SDA\\DaisukeClinic\\src\\PatientRecord.txt";
    static final String DOCTOR_FILE = "d:\\VSCode Files\\Praktikum_SDA\\DaisukeClinic\\src\\DoctorData.txt";
    static final List<String> DEFAULT_TIMES = Arrays.asList("09.00", "10.00", "12.00", "14.00", "18.00");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Patient.loadPatients();
        Doctor.loadDoctors();

        while (true) {
            System.out.println("==== Daisuke Clinic Manager ====");
            System.out.println("1. Add New Patient");
            System.out.println("2. Remove Patient by ID");
            System.out.println("3. Search Patient by Name");
            System.out.println("4. Display All Patients");
            System.out.println("5. Register Doctor");
            System.out.println("6. Delete Doctor");
            System.out.println("7. Doctor Login");
            System.out.println("8. Doctor Logout");
            System.out.println("9. View Last Logged-in Doctor");
            System.out.println("10. Schedule Appointment");
            System.out.println("11. Process Appointment");
            System.out.println("12. Display Upcoming Appointments");
            System.out.println("13. Search Patient by ID (BST)");
            System.out.println("14. Display All Patients (BST Inorder)");
            System.out.println("0. Exit");
            System.out.println("========================");
            System.out.print("Choose: ");
            int choice = -1;
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input.");
                continue;
            }
            switch (choice) {
                case 1: Patient.addPatient(sc); break;
                case 2: Patient.removePatientById(sc); break;
                case 3: Patient.findPatientByName(sc); break;
                case 4: Patient.displayAllPatients(); break;
                case 5: Doctor.registerDoctor(sc); break;
                case 6: Doctor.deleteDoctor(sc); break;
                case 7: Doctor.loginDoctor(sc); break;
                case 8: Doctor.logoutDoctor(sc); break;
                case 9: Doctor.viewLastLoggedInDoctor(); break;
                case 10: Appointment.scheduleAppointment(sc); break;
                case 11: Appointment.processNextAppointment(sc); break;
                case 12: Appointment.viewUpcomingAppointments(); break;
                case 13: Patient.searchPatientByIdBST(sc); break;
                case 14: Patient.displayAllPatientsBST(); break;
                case 0:
                    Patient.savePatients();
                    Doctor.saveDoctors();
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
