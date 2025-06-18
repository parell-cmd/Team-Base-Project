import java.util.*;

public class Appointment implements Comparable<Appointment> {
    public int appointmentId;
    public int patientId;
    public int doctorId;
    public String time;

    public static Queue<Appointment> appointmentQueue = new Queue<>();

    public Appointment(int appointmentId, int patientId, int doctorId, String time) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.time = time;
    }

    @Override
    public int compareTo(Appointment other) {
        return this.time.compareTo(other.time);
    }

    @Override
    public String toString() {
        return "AppointmentID: " + appointmentId + "\nPatientID: " + patientId +
               "\nDoctorID: " + doctorId + "\nTime: " + time;
    }

    public static void scheduleAppointment(Scanner sc) {
        try {
            System.out.print("Enter Appointment ID: ");
            int appId = Integer.parseInt(sc.nextLine());
            System.out.print("Enter Patient ID: ");
            int patientId = Integer.parseInt(sc.nextLine());
            Patient p = Patient.patientList.find(pt -> ((Patient)pt).id == patientId);
            if (p == null) {
                System.out.println("Patient not found.");
                return;
            }
            System.out.print("Enter Doctor ID: ");
            int doctorId = Integer.parseInt(sc.nextLine());
            Doctor d = Doctor.loggedInDoctors.find(doc -> ((Doctor)doc).id == doctorId);
            if (d == null) {
                System.out.println("Doctor must be logged in to schedule appointment.");
                return;
            }
            List<String> times = Doctor.doctorTimeMap.get(doctorId);
            if (times == null || times.isEmpty()) {
                System.out.println("No available times for this doctor.");
                return;
            }
            System.out.println("Available times: " + times);
            System.out.print("Choose time: ");
            String time = sc.nextLine();
            if (!times.contains(time)) {
                System.out.println("Invalid time.");
                return;
            }
            times.remove(time);
            Appointment a = new Appointment(appId, patientId, doctorId, time);
            appointmentQueue.enqueue(a);
            System.out.println("Appointment scheduled.");
        } catch (Exception e) {
            System.out.println("Invalid input.");
        }
    }

public static void processNextAppointment(Scanner sc) {
    List<Appointment> all = appointmentQueue.toList();
    if (all.isEmpty()) {
        System.out.println("No appointments to process.");
        return;
    }
    String earliest = all.stream()
        .map(a -> a.time)
        .min(String::compareTo)
        .orElse(null);

    List<Appointment> toProcess = new ArrayList<>();
    for (Appointment a : all) {
        if (a.time.equals(earliest)) toProcess.add(a);
    }
    if (toProcess.isEmpty()) {
        System.out.println("No appointments to process.");
        return;
    }

    for (Appointment a : toProcess) {
        System.out.println("Processing appointment:");
        System.out.println(a);
        System.out.print("Enter Disease: ");
        String disease = sc.nextLine();
        System.out.print("Enter Treatment: ");
        String treatment = sc.nextLine();
        System.out.print("Enter Medicines: ");
        String medicines = sc.nextLine();

        Patient p = Patient.patientList.find(pt -> ((Patient)pt).id == a.patientId);
        Doctor doc = Doctor.doctorList.find(d -> ((Doctor)d).id == a.doctorId);

        if (p != null && doc != null) {
            String record = "Disease: " + disease + ", Treatment: " + treatment +
                ", Medicines: " + medicines + ", Appointment Time: " + a.time +
                ", Doctor Name: " + doc.name;
            p.records.add(record);
        }

        List<String> times = Doctor.doctorTimeMap.get(a.doctorId);
        if (times != null && !times.contains(a.time)) {
            times.add(a.time);
            Collections.sort(times);
        }
    }

    appointmentQueue.removeIf(ap -> ap.time.equals(earliest));

    Patient.savePatients();

    System.out.println("All earliest-time appointments processed.");
}

    public static void viewUpcomingAppointments() {
        List<Appointment> apps = appointmentQueue.toList();
        apps.sort(Comparator.naturalOrder());
        for (Appointment a : apps) {
            System.out.println(a);
            System.out.println("----");
        }
    }
}