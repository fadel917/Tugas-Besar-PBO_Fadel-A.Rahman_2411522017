
import java.util.Date;
import java.text.SimpleDateFormat;

// a. Class & c. Inheritance (Super Class) 
public class Pegawai implements OperasiPegawai {
    protected String nip;
    protected String nama;
    protected double gajiDasar;
    protected Date tglMasuk; 

    public Pegawai(String nip, String nama, double gajiDasar) {
        this.nip = nip;
        this.nama = nama;
        this.gajiDasar = gajiDasar;
        this.tglMasuk = new Date(); // e. Manipulasi Date
    }

    @Override
    public double hitungTotalPenghasilan() {
        return gajiDasar;
    }

    @Override
    public void tampilkanProfil() {
        // e. Manipulasi Method String
        System.out.println("Nama Pegawai : " + nama.toUpperCase());
        System.out.println("NIP          : " + nip);
        
        SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Tanggal Join : " + ft.format(tglMasuk));
    }
}
