# Team-Base-Project

---

## Deskripsi Program
Proyek "Daisuke Clinic" adalah aplikasi berbasis konsol yang mensimulasikan sistem manajemen data untuk sebuah klinik kecil. Sistem ini dirancang untuk mengelola catatan pasien, riwayat janji temu, dan log dokter menggunakan berbagai implementasi struktur data seperti BST, Linkedlist, stack dan queque.

---

## Anggota Kelompok
1. Ardina Vidya Suryaningtyas (L0124041)
2. Dafandi Fadil Darmawan (L0124046)
3. Velengio Deriksen Charles (L0124080)
4. Farrel Rozaq Ramadhan (L0124099)

---

## Manajemen Data

Program "Daisuke Clinic" menggunakan file .txt sebagai media penyimpanan data yang persisten. Mekanisme ini memastikan bahwa data yang dimasukkan atau dimodifikasi selama program berjalan akan tetap tersedia di kemudian hari.
Berikut adalah alur manajemen datanya:
Pemuatan Data Awal: Saat program Main.java dimulai, data pasien dan dokter akan secara otomatis dibaca dari file PatientRecord.txt dan DoctorData.txt. Data ini kemudian dimuat ke dalam struktur data yang sesuai di memori, yaitu LinkedList untuk daftar pasien dan dokter, serta BST untuk pencarian pasien yang efisien. Informasi jadwal dokter juga dimuat ke dalam Map.
Operasi dalam Memori: Selama program berjalan, semua operasi seperti penambahan pasien, penghapusan pasien, login/logout dokter, dan penjadwalan/pemrosesan janji temu akan diproses dan dimanipulasi langsung pada struktur data yang ada di memori (misalnya patientList, patientBST, doctorList, loggedInDoctors, doctorLoginStack, appointmentQueue, dan doctorTimeMap). Hal ini dilakukan untuk kinerja yang lebih cepat karena tidak setiap operasi harus berinteraksi langsung dengan file.
Penyimpanan Data Akhir: Ketika pengguna memilih opsi '0' (Exit) dari menu utama, semua perubahan yang telah dilakukan pada data di memori akan disimpan kembali (ditulis ulang) ke file PatientRecord.txt dan DoctorData.txt. Proses penyimpanan ini menjamin bahwa semua data terbaru tersimpan dengan aman dan dapat dimuat kembali saat program dijalankan di lain waktu.


# Penjelasan Code

1. Main.java
File Main.java berfungsi sebagai titik masuk utama (entry point) dari aplikasi "Daisuke Clinic". Ini menginisialisasi program, memuat data awal, menampilkan menu interaktif kepada pengguna, dan mengarahkan aliran eksekusi berdasarkan pilihan pengguna.
Fungsi 
Mengatur alur eksekusi aplikasi dari awal hingga akhir.
Memuat data pasien dan dokter saat startup.
Menyimpan data pasien dan dokter saat program keluar.
Menampilkan menu utama dan menerima input pilihan pengguna.
Mengarahkan ke fungsi-fungsi terkait di kelas lain berdasarkan pilihan menu.
Struktur 
Kelas Main sendiri tidak secara langsung mengimplementasikan struktur data kustom, namun bertindak sebagai koordinator yang menggunakan instance dari :
LinkedList<Patient> (patientList) untuk mengelola daftar pasien.
BST (patientBST) untuk pencarian pasien yang efisien berdasarkan ID.
LinkedList<Doctor> (doctorList, loggedInDoctors) untuk mengelola daftar dokter dan dokter yang sedang login.
Stack<Doctor> (doctorLoginStack) untuk melacak riwayat login dokter.
Queue<Appointment> (appointmentQueue) untuk mengelola antrian janji temu.
Map<Integer, List<String>> (doctorTimeMap) untuk menyimpan jadwal waktu yang tersedia per dokter.

2. Patient.java
Kelas Patient.java merepresentasikan entitas pasien dalam sistem "Daisuke Clinic", menyimpan detail informasi pasien dan riwayat medisnya. Kelas ini juga menyediakan fungsionalitas statis untuk mengelola data pasien secara keseluruhan, termasuk interaksi dengan penyimpanan persisten dan struktur data di memori.
fungsi : 
Menambah, menghapus, mencari pasien berdasarkan ID atau nama.
Menampilkan semua data pasien.
Memuat dan menyimpan data pasien ke/dari file PatientRecord.txt.
Struktur
LinkedList<Patient> patientList: Untuk menyimpan semua pasien secara berurutan.
BST patientBST: Untuk pencarian pasien yang cepat berdasarkan ID.
ArrayList<String> records: Riwayat medis untuk setiap pasien.
Fitur : 
Manajemen Rekam Medis Pasien: Tambah, Hapus, Cari (berdasarkan ID/Nama), Tampil.
Pencarian cepat menggunakan Binary Search Tree.
Penyimpanan data pasien persisten ke file .txt.

3. Appointmen.java
Kelas Appointment.java mendefinisikan struktur untuk janji temu pasien dengan dokter dan mengelola antrian janji temu. Ini mencakup fungsionalitas untuk menjadwalkan, memproses, dan melihat janji temu yang akan datang, serta berinteraksi dengan data pasien dan dokter.
Fungsi
Menjadwalkan janji temu baru.
Memproses janji temu yang paling awal.
Melihat daftar janji temu yang akan datang.
Struktur 
Queue<Appointment> appointmentQueue: Implementasi antrian kustom (FIFO) untuk mengelola janji temu.
Fitur:
Penjadwalan Janji Temu: Menambahkan janji temu ke antrian.
Pemrosesan Janji Temu: Mengambil janji temu berikutnya dari antrian dan mencatat ke rekam medis pasien.
Tampilan Janji Temu: Menampilkan semua janji temu yang akan datang.

4. Doctor.java
Deskripsi Kode: Kelas Doctor.java merepresentasikan entitas dokter dalam sistem. Kelas ini bertanggung jawab untuk mengelola detail informasi dokter, daftar dokter yang terdaftar, dokter yang sedang login, dan ketersediaan waktu praktik mereka. Selain itu, ia juga menangani operasi I/O untuk memuat dan menyimpan data dokter secara persisten.
Fungsi Inti:
Mendaftar dan menghapus dokter.
Mengelola proses login dan logout dokter.
Melacak dokter yang sedang login dan yang terakhir login.
Memuat dan menyimpan data dokter ke/dari file DoctorData.txt.
Struktur Penting:
LinkedList<Doctor> doctorList: Untuk menyimpan semua dokter yang terdaftar.
LinkedList<Doctor> loggedInDoctors: Untuk melacak dokter yang sedang login.
Stack<Doctor> doctorLoginStack: Untuk melacak riwayat login dokter (terakhir login).
Map<Integer, List<String>> doctorTimeMap: Untuk mengelola ketersediaan waktu dokter.
Fitur:
Manajemen Data Dokter: Pendaftaran dan penghapusan dokter.
Sistem Login/Logout Dokter: Mencatat aktivitas login dan logout.
Log Dokter: Menampilkan dokter terakhir yang login.
Penyimpanan data dokter persisten ke file .txt.

5. LinkedList.java
Fungsi: Implementasi linked list sederhana (single-linked list) yang mendukung operasi penambahan, penghapusan, pencarian elemen, dan konversi ke List standar Java. Digunakan untuk menyimpan data pasien dan dokter.

6. BST.java
Fungsi: Implementasi Binary Search Tree (BST) untuk penyimpanan data terstruktur, memungkinkan penyisipan objek Patient dan pencarian Patient yang efisien berdasarkan ID, serta tampilan data secara terurut (inorder traversal).

7. Stack.java
Fungsi: Implementasi struktur data Stack (LIFO - Last In, First Out) yang mendukung operasi push (menambah ke puncak), pop (menghapus dari puncak), dan peek (melihat puncak). Digunakan untuk melacak log dokter terakhir yang login.

8. Queue.java
Fungsi: Implementasi struktur data Queue (FIFO - First In, First Out) yang mendukung operasi enqueue (menambah ke belakang), dequeue (menghapus dari depan), dan penghapusan elemen berdasarkan kriteria tertentu. Digunakan untuk mengelola antrian janji temu.

---

### Cara Menjalankan Program 
1. Kompilasi Semua program .java 
2. caranya di terminal adalah dengan menggunakan javac namafile.java
3. Jalankan program dengan cara menekan tombol run apabila di ( vscode ) atau dengan java Main apabila di terminal
4. program yang dijalankan adalah main.java
5. setelah itu pengguna dapat menggunakan program tersebut dengan berbagai fitur yang ada seperti manajemen pasien, dokter dan appointment.

---

### Pembagian Tugas
1. Ardina Vidya Suryaningtyas (L0124041) 
 membuat doctor.java dan edit video
2. Dafandi Fadil Darmawan (L0124046) 
membuat Struktur datannya ( queque, stack, linkedlist) dan readme.md
3. Velengio Deriksen Charles (L0124080)
Menghimpun kesemuanya dan menjadikan satu semua code, membuat bst dan patient.java
4. Farrel Rozaq Ramadhan (L0124099)
membuat appointmen.java dan readme.md
