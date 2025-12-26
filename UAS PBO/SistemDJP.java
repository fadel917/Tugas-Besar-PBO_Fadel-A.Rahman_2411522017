
import java.sql.*;
import java.util.*;

public class SistemDJP {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // d. Perulangan (while)
        while (running) {
            try { // f. Exception Handling
                System.out.println("\n=== SISTEM MANAJEMEN PEGAWAI DJP ===");
                System.out.println("1. Tambah Pegawai (Create)");
                System.out.println("2. Tampilkan Semua (Read)");
                System.out.println("3. Update Nama (Update)");
                System.out.println("4. Hapus Pegawai (Delete)");
                System.out.println("5. Keluar");
                System.out.print("Pilih Menu [1-5]: ");
                
                int pilihan = scanner.nextInt();
                scanner.nextLine();

                // d. Percabangan (switch)
                switch (pilihan) {
                    case 1:
                        System.out.print("Masukkan NIP: "); String nip = scanner.nextLine();
                        System.out.print("Masukkan Nama: "); String nama = scanner.nextLine();
                        System.out.print("ID Jabatan (1:Fungsional, 2:Struktural): "); int idJab = scanner.nextInt();
                        createPegawai(nip, nama, idJab);
                        break;
                    case 2:
                        readPegawai();
                        break;
                    case 3:
                        System.out.print("ID Pegawai yang diupdate: "); int idUp = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Nama Baru: "); String namaBaru = scanner.nextLine();
                        updatePegawai(idUp, namaBaru);
                        break;
                    case 4:
                        System.out.print("ID Pegawai yang dihapus: "); int idDel = scanner.nextInt();
                        deletePegawai(idDel);
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Menu tidak tersedia.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Error: Input harus angka!");
                scanner.nextLine(); 
            } catch (Exception e) {
                System.err.println("Terjadi kesalahan: " + e.getMessage());
            }
        }
        scanner.close();
        System.out.println("Program selesai.");
    }

    // h. CRUD: Create
    public static void createPegawai(String nip, String nama, int idJabatan) {
        String sql = "INSERT INTO pegawai (nip, nama, id_jabatan, tgl_masuk) VALUES (?, ?, ?, NOW())";
        try (Connection conn = DatabaseConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nip);
            pstmt.setString(2, nama);
            pstmt.setInt(3, idJabatan);
            pstmt.executeUpdate();
            System.out.println("Data Berhasil Ditambahkan!");
        } catch (SQLException e) {
            System.err.println("Gagal Tambah Data: " + e.getMessage());
        }
    }

    // g. Collection & h. CRUD: Read
    public static void readPegawai() {
        List<PegawaiPajak> listPegawai = new ArrayList<>(); // g. Collection Framework
        String sql = "SELECT p.nip, p.nama, j.nama_jabatan, j.tunjangan_kinerja " +
                     "FROM pegawai p JOIN jabatan j ON p.id_jabatan = j.id_jabatan";
        try (Connection conn = DatabaseConfig.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            System.out.println("\n--- DAFTAR PEGAWAI DJP ---");
            while (rs.next()) { 
                PegawaiPajak p = new PegawaiPajak(
                    rs.getString("nip"),
                    rs.getString("nama"),
                    5000000, 
                    rs.getString("nama_jabatan"),
                    rs.getDouble("tunjangan_kinerja")
                );
                listPegawai.add(p);
            }

            for (PegawaiPajak pgw : listPegawai) {
                pgw.tampilkanProfil();
            }
        } catch (SQLException e) {
            System.err.println("Gagal Baca Data: " + e.getMessage());
        }
    }

    // h. CRUD: Update
    public static void updatePegawai(int id, String namaBaru) {
        String sql = "UPDATE pegawai SET nama = ? WHERE id_pegawai = ?";
        try (Connection conn = DatabaseConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, namaBaru);
            pstmt.setInt(2, id);
            int k = pstmt.executeUpdate();
            if (k > 0) System.out.println("Nama berhasil diupdate!");
        } catch (SQLException e) {
            System.err.println("Gagal Update: " + e.getMessage());
        }
    }

    // h. CRUD: Delete
    public static void deletePegawai(int id) {
        String sql = "DELETE FROM pegawai WHERE id_pegawai = ?";
        try (Connection conn = DatabaseConfig.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Data berhasil dihapus!");
        } catch (SQLException e) {
            System.err.println("Gagal Hapus: " + e.getMessage());
        }
    }
}