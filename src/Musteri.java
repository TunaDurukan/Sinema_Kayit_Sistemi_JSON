import java.io.*;
import java.util.*;

public class Musteri extends BaseEntity implements IKayit {
    private String telefon;
    private String email;
    private Film kayitliFilm;

    private static final String DOSYA_ADI = "musteriler.json";
    private static List<Musteri> musteriler = new ArrayList<>();

    public Musteri(int id, String adi, String telefon, String email, Film kayitliFilm) {
        super(id, adi);
        this.telefon = telefon;
        this.email = email;
        this.kayitliFilm = kayitliFilm;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Film getKayitliFilm() {
        return kayitliFilm;
    }

    @Override
    public void kayitEkle() {
        musteriler.add(this);
        jsonDosyayaYaz();
    }

    @Override
    public void kayitSil() {
        musteriler.remove(this);
        jsonDosyayaYaz();
    }

    @Override
    public void kayitGuncelle() {
        for (int i = 0; i < musteriler.size(); i++) {
            if (musteriler.get(i).getId() == this.getId()) {
                musteriler.set(i, this);
                break;
            }
        }
        jsonDosyayaYaz();
    }

    public static void musteriListesiGoruntule() {
        if (musteriler.isEmpty()) {
            System.out.println("Kayıtlı müşteri yok.");
        } else {
            for (Musteri musteri : musteriler) {
                String filmBilgisi = musteri.getKayitliFilm() != null ? musteri.getKayitliFilm().getFilmAdi() : "Henüz kayıtlı değil";
                System.out.println("ID: " + musteri.getId() +
                        ", Adı: " + musteri.getAdi() +
                        ", Telefon: " + musteri.getTelefon() +
                        ", E-posta: " + musteri.getEmail() +
                        ", Film: " + filmBilgisi);
            }
        }
    }

    public static List<Musteri> getMusteriler() {
        return musteriler;
    }

    public static void jsonDosyayaYaz() {
        try (FileWriter writer = new FileWriter(DOSYA_ADI)) {
            writer.write("[");
            for (int i = 0; i < musteriler.size(); i++) {
                Musteri musteri = musteriler.get(i);
                String json = String.format(
                        "{\"id\":%d,\"adi\":\"%s\",\"telefon\":\"%s\",\"email\":\"%s\",\"filmId\":%d}",
                        musteri.getId(), musteri.getAdi(), musteri.getTelefon(), musteri.getEmail(),
                        musteri.getKayitliFilm() != null ? musteri.getKayitliFilm().getFilmId() : -1
                );
                writer.write(json);
                if (i < musteriler.size() - 1) {
                    writer.write(",");
                }
            }
            writer.write("]");
        } catch (IOException e) {
            System.out.println("JSON dosyasına yazılırken hata oluştu: " + e.getMessage());
        }
    }

    public static void jsonDosyasindanOku() {
        try (BufferedReader reader = new BufferedReader(new FileReader(DOSYA_ADI))) {
            StringBuilder jsonBuilder = new StringBuilder();
            String satir;
            while ((satir = reader.readLine()) != null) {
                jsonBuilder.append(satir);
            }
            String json = jsonBuilder.toString();
            parseJson(json);
        } catch (IOException e) {
            System.out.println("JSON dosyasından okunurken hata oluştu: " + e.getMessage());
        }
    }

    private static void parseJson(String json) {
        json = json.replace("[", "").replace("]", "");
        if (json.trim().isEmpty()) return;

        String[] objeler = json.split("},");
        for (String obje : objeler) {
            obje = obje.replace("}", "").replace("{", "");
            String[] alanlar = obje.split(",");
            int id = Integer.parseInt(alanlar[0].split(":")[1].trim());
            String adi = alanlar[1].split(":")[1].replace("\"", "").trim();
            String telefon = alanlar[2].split(":")[1].replace("\"", "").trim();
            String email = alanlar[3].split(":")[1].replace("\"", "").trim();
            int filmId = Integer.parseInt(alanlar[4].split(":")[1].trim());

            Film kayitliFilm = Film.getFilmler().stream()
                    .filter(f -> f.getFilmId() == filmId)
                    .findFirst()
                    .orElse(null);

            Musteri musteri = new Musteri(id, adi, telefon, email, kayitliFilm);
            musteriler.add(musteri);
        }
    }
}
