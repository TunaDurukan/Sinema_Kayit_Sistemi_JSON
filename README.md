<h1>🎥 Sinema Kayıt Sistemi</h1>
<p>Bu proje, sinema salonları, filmler ve müşteriler arasında ilişkileri yönetmek için geliştirilmiş bir konsol uygulamasıdır. Sistem, müşteri, film ve salon yönetimi gibi temel işlevsellikler sunar. Ek olarak, yapılan tüm ekleme, silme ve güncelleme işlemleri JSON dosyalarına kaydedilir ve bu veriler JSON dosyalarından okunur.</p>

<h2>🛠️ Özellikler</h2>
<ul>
    <li><strong>Müşteri Yönetimi:</strong> Müşteri kaydetme, silme, güncelleme ve listeleme. Müşteriye film atama.</li>
    <li><strong>Sinema Salonu Yönetimi:</strong> Salon ekleme, silme, güncelleme ve listeleme. Salona film atama.</li>
    <li><strong>Film Yönetimi:</strong> Film ekleme, silme, güncelleme ve listeleme. Filmleri salona atama.</li>
    <li><strong>JSON Desteği:</strong> Tüm veriler JSON dosyalarına kaydedilir ve JSON dosyalarından okunur.</li>
</ul>

<h2>📂 Proje Yapısı</h2>
<h3>Sınıflar ve Arayüzler</h3>
<ol>
    <li><strong>BaseEntity.java:</strong> <code>id</code> ve <code>adi</code> özelliklerini içeren temel sınıf. Tüm diğer varlıklar bu sınıfı genişletir.</li>
    <li><strong>Musteri.java:</strong> Müşteri bilgilerini (ad, telefon, e-posta) ve müşterinin izlemek istediği filmi içerir. Müşteri yönetimi işlevlerini (ekleme, silme, güncelleme) uygular. Veriler JSON dosyasına kaydedilir ve oradan okunur.</li>
    <li><strong>Film.java:</strong> Film bilgilerini (ad, tür, süre) içerir. Filmlerin hangi salonda oynatıldığını takip eder. Film yönetimi işlevlerini (ekleme, silme, güncelleme) uygular. Veriler JSON dosyasına kaydedilir ve oradan okunur.</li>
    <li><strong>Salon.java:</strong> Salon bilgilerini (kapasite, oynatılan film) içerir. Salon yönetimi işlevlerini (ekleme, silme, güncelleme) uygular. Veriler JSON dosyasına kaydedilir ve oradan okunur.</li>
    <li><strong>IKayit.java:</strong> Kayıt ekleme, silme ve güncelleme işlemleri için standart bir arayüz sağlar.</li>
    <li><strong>Main.java:</strong> Tüm işlevlerin birleştirildiği ana sınıf. Kullanıcı etkileşimini sağlar ve menüler aracılığıyla yönetimi gerçekleştirir.</li>
</ol>

<h2>🚀 Kullanım</h2>
<ol>
    <li>Proje dosyalarını bilgisayarınıza indirin.</li>
    <li>Bir Java IDE'sinde açın (ör. IntelliJ IDEA, Eclipse).</li>
    <li><code>Main.java</code> dosyasını çalıştırın.</li>
    <li>Konsolda sunulan menülerden seçim yaparak sistemi kullanın.</li>
</ol>

<h3>Ana Menü</h3>
<ul>
    <li><strong>1- Müşteri Yönetimi:</strong> Müşteri ekleme, silme, güncelleme ve listeleme.</li>
    <li><strong>2- Sinema Salonu Yönetimi:</strong> Salon ekleme, silme, güncelleme ve listeleme.</li>
    <li><strong>3- Film Yönetimi:</strong> Film ekleme, silme, güncelleme ve listeleme.</li>
    <li><strong>0- Çıkış:</strong> Uygulamadan çıkış.</li>
</ul>

<h2>📌 Amaç</h2>
<p>
    Bu proje, Piri Reis Üniversitesi'nde almış olduğum Nesneye Dayalı Programlama dersinin vize projesidir. Sistem, dinamik bir yapı üzerine kurulmuş olup, gerçek hayattaki yönetim ihtiyaçlarına yönelik bir simülasyon sağlar. JSON desteği, verilerin kalıcılığını sağlar ve projeyi daha gerçekçi hale getirir.
</p>
