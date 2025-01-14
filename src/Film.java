import java.io.*;
import java.util.*;

public class Film implements IKayit {
    private int filmId;
    private String filmAdi;
    private String tur;
    private int sure;
    private int salonId;
    private Salon oynatildigiSalon;

    private static final String dosyaAdi = "filmler.json";
    private static final List<Film> filmler = new ArrayList<>();

    public Film(String filmAdi, String tur, int sure) {
        this.filmId = filmler.isEmpty() ? 1 : filmler.stream().mapToInt(Film::getFilmId).max().orElse(0) + 1;
        this.filmAdi = filmAdi;
        this.tur = tur;
        this.sure = sure;
        this.salonId = -1;
        this.oynatildigiSalon = null;
    }

    public int getFilmId() {
        return filmId;
    }

    public String getFilmAdi() {
        return filmAdi;
    }

    public void setFilmAdi(String filmAdi) {
        this.filmAdi = filmAdi;
    }

    public String getTur() {
        return tur;
    }

    public void setTur(String tur) {
        this.tur = tur;
    }

    public int getSure() {
        return sure;
    }

    public void setSure(int sure) {
        this.sure = sure;
    }

    public Salon getOynatildigiSalon() {
        if (oynatildigiSalon == null && salonId != -1) {
            oynatildigiSalon = Salon.getSalonlar().stream()
                    .filter(s -> s.getId() == salonId)
                    .findFirst()
                    .orElse(null);
        }
        return oynatildigiSalon;
    }

    public void setOynatildigiSalon(Salon oynatildigiSalon) {
        this.oynatildigiSalon = oynatildigiSalon;
        this.salonId = oynatildigiSalon != null ? oynatildigiSalon.getId() : -1;
    }

    @Override
    public void kayitEkle() {
        filmler.add(this);
        jsonDosyayaYaz();
    }

    @Override
    public void kayitSil() {
        filmler.remove(this);
        jsonDosyayaYaz();
    }

    @Override
    public void kayitGuncelle() {
        for (int i = 0; i < filmler.size(); i++) {
            if (filmler.get(i).getFilmId() == this.getFilmId()) {
                filmler.set(i, this);
                break;
            }
        }
        jsonDosyayaYaz();
    }

    public static void filmListesiGoruntule() {
        if (filmler.isEmpty()) {
            System.out.println("Kayıtlı film yok.");
        } else {
            for (Film film : filmler) {
                String salonBilgisi = film.getOynatildigiSalon() != null ? film.getOynatildigiSalon().getAdi() : "Henüz atanmadı";
                System.out.println("ID: " + film.getFilmId() + ", Adı: " + film.getFilmAdi() +
                        ", Tür: " + film.getTur() + ", Süre: " + film.getSure() + " dakika, Salon: " + salonBilgisi);
            }
        }
    }

    public static List<Film> getFilmler() {
        return filmler;
    }

    public static void jsonDosyayaYaz() {
        try (FileWriter writer = new FileWriter(dosyaAdi)) {
            writer.write("[");
            for (int i = 0; i < filmler.size(); i++) {
                Film film = filmler.get(i);
                String json = String.format(
                        "{\"filmId\":%d,\"filmAdi\":\"%s\",\"tur\":\"%s\",\"sure\":%d,\"salonId\":%d}",
                        film.getFilmId(), film.getFilmAdi(), film.getTur(), film.getSure(),
                        film.salonId
                );
                writer.write(json);
                if (i < filmler.size() - 1) {
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
            try {
                int id = Integer.parseInt(getValue(alanlar[0]));
                String adi = getValue(alanlar[1]);
                String tur = getValue(alanlar[2]);
                int sure = Integer.parseInt(getValue(alanlar[3]));
                int salonId = Integer.parseInt(getValue(alanlar[4]));

                Film film = new Film(adi, tur, sure);
                film.filmId = id;
                film.salonId = salonId;
                filmler.add(film);
            } catch (Exception e) {
                System.out.println("JSON ayrıştırılırken hata oluştu: \"" + obje + "\"");
            }
        }
    }

    private static String getValue(String keyValuePair) {
        return keyValuePair.split(":")[1].replace("\"", "").trim();
    }
}
