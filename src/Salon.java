import java.io.*;
import java.util.*;

public class Salon extends BaseEntity implements IKayit {
    private int kapasite;
    public Film oynatilanFilm;

    public static final String dosyaAdi = "salonlar.json";
    public static final List<Salon> salonlar = new ArrayList<>();

    public Salon(String adi, int kapasite) {
        super(salonlar.isEmpty() ? 1 : salonlar.stream().mapToInt(Salon::getId).max().orElse(0) + 1, adi);
        this.kapasite = kapasite;
    }

    public int getKapasite() {
        return kapasite;
    }

    public void setKapasite(int kapasite) {
        this.kapasite = kapasite;
    }

    public Film getOynatilanFilm() {
        return oynatilanFilm;
    }

    public void setOynatilanFilm(Film oynatilanFilm) {
        this.oynatilanFilm = oynatilanFilm;
    }

    @Override
    public void kayitEkle() {
        salonlar.add(this);
        jsonDosyayaYaz();
    }

    @Override
    public void kayitSil() {
        salonlar.remove(this);
        jsonDosyayaYaz();
    }

    @Override
    public void kayitGuncelle() {
        for (int i = 0; i < salonlar.size(); i++) {
            if (salonlar.get(i).getId() == this.getId()) {
                salonlar.set(i, this);
                break;
            }
        }
        jsonDosyayaYaz();
    }

    public static List<Salon> getSalonlar() {
        return salonlar;
    }

    public static void salonListesiGoruntule() {
        if (salonlar.isEmpty()) {
            System.out.println("Kayıtlı salon yok.");
        } else {
            for (Salon salon : salonlar) {
                String filmAdi = salon.getOynatilanFilm() != null ? salon.getOynatilanFilm().getFilmAdi() : "Henüz atanmadı";
                System.out.println("ID: " + salon.getId() + ", Adı: " + salon.getAdi() + ", Kapasite: " + salon.getKapasite() + ", Film: " + filmAdi);
            }
        }
    }

    public static void jsonDosyayaYaz() {
        try (FileWriter writer = new FileWriter(dosyaAdi)) {
            writer.write("[");
            for (int i = 0; i < salonlar.size(); i++) {
                Salon salon = salonlar.get(i);
                String json = String.format(
                        "{\"salonId\":%d,\"adi\":\"%s\",\"kapasite\":%d,\"filmId\":%d}",
                        salon.getId(), salon.getAdi(), salon.getKapasite(),
                        salon.getOynatilanFilm() != null ? salon.getOynatilanFilm().getFilmId() : -1
                );
                writer.write(json);
                if (i < salonlar.size() - 1) {
                    writer.write(",");
                }
            }
            writer.write("]");
        } catch (IOException e) {
            System.out.println("JSON dosyasına yazılırken hata oluştu: " + e.getMessage());
        }
    }

    public static void jsonDosyasindanOku() {
        try (BufferedReader reader = new BufferedReader(new FileReader(dosyaAdi))) {
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
            int kapasite = Integer.parseInt(alanlar[2].split(":")[1].trim());
            int filmId = Integer.parseInt(alanlar[3].split(":")[1].trim());

            Salon salon = new Salon(adi, kapasite);
            salon.setId(id);
            salonlar.add(salon);

            if (filmId != -1) {
                Film film = Film.getFilmler().stream()
                        .filter(f -> f.getFilmId() == filmId)
                        .findFirst()
                        .orElse(null);
                salon.setOynatilanFilm(film);
            }
        }
    }
}