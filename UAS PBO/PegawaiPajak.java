// c. Inheritance (Sub Class)
public class PegawaiPajak extends Pegawai {
    private String namaJabatan;
    private double tunjangan;

    public PegawaiPajak(String nip, String nama, double gajiDasar, String jabatan, double tunjangan) {
        super(nip, nama, gajiDasar);
        this.namaJabatan = jabatan;
        this.tunjangan = tunjangan;
    }

    @Override
    public double hitungTotalPenghasilan() {
        // d. Perhitungan Matematis
        return gajiDasar + tunjangan;
    }

    @Override
    public void tampilkanProfil() {
        super.tampilkanProfil();
        System.out.println("Jabatan      : " + namaJabatan);
        System.out.println("Total THP    : Rp" + String.format("%,.0f", hitungTotalPenghasilan()));
        System.out.println("------------------------------------");
    }
}