import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Film.jsonDosyasindanOku();
        Salon.jsonDosyasindanOku();
        Musteri.jsonDosyasindanOku();

        Scanner scanner = new Scanner(System.in);
        boolean devam = true;

        while (devam) {
            System.out.println("\n=== Sinema Yönetim Sistemi ===");
            System.out.println("1- Müşteri Yönetimi");
            System.out.println("2- Sinema Salonu Yönetimi");
            System.out.println("3- Film Yönetimi");
            System.out.println("0- Çıkış");
            System.out.print("Seçiminiz: ");

            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    musteriYonetimi(scanner);
                    break;
                case 2:
                    salonYonetimi(scanner);
                    break;
                case 3:
                    filmYonetimi(scanner);
                    break;
                case 0:
                    devam = false;
                    System.out.println("Çıkış yapılıyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim, tekrar deneyin.");
            }
        }

        scanner.close();

        Salon.jsonDosyayaYaz();
        Film.jsonDosyayaYaz();
        Musteri.jsonDosyayaYaz();
    }

    private static void musteriYonetimi(Scanner scanner) {
        boolean devam = true;

        while (devam) {
            System.out.println("\n=== Müşteri Yönetimi ===");
            System.out.println("1- Müşteri Kaydet");
            System.out.println("2- Kayıtlı Müşteri Sil");
            System.out.println("3- Müşteri Güncelle");
            System.out.println("4- Müşteri Listesini Görüntüle");
            System.out.println("0- Geri");
            System.out.print("Seçiminiz: ");

            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    musteriKaydet(scanner);
                    break;
                case 2:
                    musteriSil(scanner);
                    break;
                case 3:
                    musteriGuncelle(scanner);
                    break;
                case 4:
                    Musteri.musteriListesiGoruntule();
                    break;
                case 0:
                    devam = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim, tekrar deneyin.");
            }
        }
    }

    private static void musteriKaydet(Scanner scanner) {
        System.out.print("Müşteri Adı: ");
        String adi = scanner.nextLine();
        System.out.print("Müşteri Soyadı: ");
        String soyadi = scanner.nextLine();
        System.out.print("Telefon: ");
        String telefon = scanner.nextLine();
        System.out.print("E-posta: ");
        String email = scanner.nextLine();

        System.out.println("\n=== Mevcut Filmler ===");
        Film.filmListesiGoruntule();

        System.out.print("İzlemek istediğiniz filmin ID'sini girin: ");
        int filmId = scanner.nextInt();
        scanner.nextLine();

        Film secilenFilm = Film.getFilmler().stream()
                .filter(f -> f.getFilmId() == filmId)
                .findFirst()
                .orElse(null);

        if (secilenFilm == null) {
            System.out.println("Belirtilen ID'ye sahip film bulunamadı. Müşteri kaydedilmedi.");
            return;
        }

        int yeniId = Musteri.getMusteriler().size() + 1;

        Musteri yeniMusteri = new Musteri(yeniId, adi + " " + soyadi, telefon, email, secilenFilm);
        yeniMusteri.kayitEkle();
        System.out.println("Müşteri başarıyla kaydedildi. İzlemek istediği film: " + secilenFilm.getFilmAdi());
    }

    private static void musteriSil(Scanner scanner) {
        System.out.print("Silmek istediğiniz müşterinin ID'sini girin: ");
        int id = scanner.nextInt();

        Musteri silinecek = Musteri.getMusteriler().stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);

        if (silinecek != null) {
            silinecek.kayitSil();
        } else {
            System.out.println("Belirtilen ID'ye sahip müşteri bulunamadı.");
        }
    }

    private static void musteriGuncelle(Scanner scanner) {
        System.out.print("Güncellemek istediğiniz müşterinin ID'sini girin: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Musteri guncellenecek = Musteri.getMusteriler().stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElse(null);

        if (guncellenecek != null) {
            System.out.print("Yeni Telefon: ");
            String yeniTelefon = scanner.nextLine();
            System.out.print("Yeni E-posta: ");
            String yeniEmail = scanner.nextLine();

            guncellenecek.setTelefon(yeniTelefon);
            guncellenecek.setEmail(yeniEmail);
            guncellenecek.kayitGuncelle();
        } else {
            System.out.println("Belirtilen ID'ye sahip müşteri bulunamadı.");
        }
    }

    private static void salonYonetimi(Scanner scanner) {
        boolean devam = true;

        while (devam) {
            System.out.println("\n=== Sinema Salonu Yönetimi ===");
            System.out.println("1- Salon Ekle");
            System.out.println("2- Salon Sil");
            System.out.println("3- Salon Güncelle");
            System.out.println("4- Salon Bilgilerini Görüntüle");
            System.out.println("5- Salona Film Ata");
            System.out.println("0- Geri");
            System.out.print("Seçiminiz: ");

            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    salonEkle(scanner);
                    break;
                case 2:
                    salonSil(scanner);
                    break;
                case 3:
                    salonGuncelle(scanner);
                    break;
                case 4:
                    Salon.salonListesiGoruntule();
                    break;
                case 5:
                    salonaFilmAta(scanner);
                    break;
                case 0:
                    devam = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim, tekrar deneyin.");
            }
        }
    }

    private static void salonEkle(Scanner scanner) {
        System.out.print("Salon Adı: ");
        String adi = scanner.nextLine();

        System.out.print("Salon Kapasitesi: ");
        int kapasite;
        while (!scanner.hasNextInt()) {
            System.out.println("Lütfen geçerli bir sayı girin!");
            scanner.next();
        }
        kapasite = scanner.nextInt();
        scanner.nextLine();

        Salon yeniSalon = new Salon(adi, kapasite);
        yeniSalon.kayitEkle();
        System.out.println("Salon başarıyla eklendi: " + adi);
    }

    private static void salonSil(Scanner scanner) {
        System.out.print("Silmek istediğiniz salonun ID'sini girin: ");
        int id = scanner.nextInt();

        Salon silinecek = Salon.getSalonlar().stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);

        if (silinecek != null) {
            silinecek.kayitSil();
        } else {
            System.out.println("Belirtilen ID'ye sahip salon bulunamadı.");
        }
    }

    private static void salonGuncelle(Scanner scanner) {
        System.out.print("Güncellemek istediğiniz salonun ID'sini girin: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Salon guncellenecek = Salon.getSalonlar().stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);

        if (guncellenecek != null) {
            System.out.print("Yeni Kapasite: ");
            int yeniKapasite = scanner.nextInt();
            guncellenecek.setKapasite(yeniKapasite);
            guncellenecek.kayitGuncelle();
        } else {
            System.out.println("Belirtilen ID'ye sahip salon bulunamadı.");
        }
    }

    private static void salonaFilmAta(Scanner scanner) {
        System.out.println("\n=== Mevcut Salonlar ===");
        Salon.salonListesiGoruntule();

        System.out.print("Salon ID: ");
        int salonId = scanner.nextInt();
        scanner.nextLine();

        Salon salon = Salon.getSalonlar().stream()
                .filter(s -> s.getId() == salonId)
                .findFirst()
                .orElse(null);

        if (salon == null) {
            System.out.println("Belirtilen ID'ye sahip salon bulunamadı.");
            return;
        }

        System.out.println("\n=== Mevcut Filmler ===");
        Film.filmListesiGoruntule();

        System.out.print("Film ID: ");
        int filmId = scanner.nextInt();
        scanner.nextLine();

        Film film = Film.getFilmler().stream()
                .filter(f -> f.getFilmId() == filmId)
                .findFirst()
                .orElse(null);

        if (film != null) {
            salon.setOynatilanFilm(film);
            film.setOynatildigiSalon(salon);
            salon.kayitGuncelle();
            System.out.println("Film \"" + film.getFilmAdi() + "\" salona atandı: " + salon.getAdi());
        } else {
            System.out.println("Belirtilen ID'ye sahip film bulunamadı.");
        }
    }

    private static void filmYonetimi(Scanner scanner) {
        boolean devam = true;

        while (devam) {
            System.out.println("\n=== Film Yönetimi ===");
            System.out.println("1- Film Ekle");
            System.out.println("2- Film Sil");
            System.out.println("3- Film Güncelle");
            System.out.println("4- Film Listesini Görüntüle");
            System.out.println("0- Geri");
            System.out.print("Seçiminiz: ");

            int secim = scanner.nextInt();
            scanner.nextLine();

            switch (secim) {
                case 1:
                    filmEkle(scanner);
                    break;
                case 2:
                    filmSil(scanner);
                    break;
                case 3:
                    filmGuncelle(scanner);
                    break;
                case 4:
                    Film.filmListesiGoruntule();
                    break;
                case 0:
                    devam = false;
                    break;
                default:
                    System.out.println("Geçersiz seçim, tekrar deneyin.");
            }
        }
    }

    private static void filmEkle(Scanner scanner) {
        System.out.print("Film Adı: ");
        String adi = scanner.nextLine();

        System.out.print("Film Türü: ");
        String tur = scanner.nextLine();

        System.out.print("Film Süresi (dakika): ");
        int sure;
        while (!scanner.hasNextInt()) {
            System.out.println("Lütfen geçerli bir sayı girin!");
            scanner.next();
        }
        sure = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Salon ID: ");
        int salonId;
        while (!scanner.hasNextInt()) {
            System.out.println("Lütfen geçerli bir sayı girin!");
            scanner.next();
        }
        salonId = scanner.nextInt();
        scanner.nextLine();

        Film yeniFilm = new Film(adi, tur, sure);
        yeniFilm.kayitEkle();

        System.out.println("Film başarıyla eklendi: " + adi);
    }



    private static void filmSil(Scanner scanner) {
        System.out.print("Silmek istediğiniz filmin ID'sini girin: ");
        int id = scanner.nextInt();

        Film silinecek = Film.getFilmler().stream()
                .filter(f -> f.getFilmId() == id)
                .findFirst()
                .orElse(null);

        if (silinecek != null) {
            silinecek.kayitSil();
        } else {
            System.out.println("Belirtilen ID'ye sahip film bulunamadı.");
        }
    }

    private static void filmGuncelle(Scanner scanner) {
        System.out.print("Güncellemek istediğiniz filmin ID'sini girin: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Film guncellenecek = Film.getFilmler().stream()
                .filter(f -> f.getFilmId() == id)
                .findFirst()
                .orElse(null);

        if (guncellenecek != null) {
            System.out.print("Yeni Film Adı: ");
            String yeniAdi = scanner.nextLine();
            System.out.print("Yeni Tür: ");
            String yeniTur = scanner.nextLine();
            System.out.print("Yeni Süre (dakika): ");
            int yeniSure = scanner.nextInt();

            guncellenecek.setFilmAdi(yeniAdi);
            guncellenecek.setTur(yeniTur);
            guncellenecek.setSure(yeniSure);
            guncellenecek.kayitGuncelle();
        } else {
            System.out.println("Belirtilen ID'ye sahip film bulunamadı.");
        }
    }
}