<h1>📘 Öğrenci ve Ders Yönetim Sistemi</h1>
<p>
    Bu proje, öğrenciler, öğretim görevlileri ve dersler arasında ilişkileri yönetmek amacıyla geliştirilmiş bir uygulamadır. 
    Kullanıcılar, öğrenciler ve öğretim görevlileri ekleyebilir, ders oluşturabilir ve atamalar yapabilir. 
    Konsol tabanlı bu uygulama, C# dilindeki temel kavramları pratiğe dökme fırsatı sunar.
</p>

<h2>🛠️ Uygulama Özellikleri</h2>
<ul>
    <li><strong>Öğrenci Yönetimi:</strong> Öğrenci ekleme, silme, listeleme ve derslere kayıt işlemleri. Tüm veriler XML dosyasına kaydedilir ve XML'den okunur.</li>
    <li><strong>Öğretim Görevlisi Yönetimi:</strong> Öğretim görevlisi ekleme, silme ve ders atama işlemleri. Bu veriler de XML dosyasında saklanır.</li>
    <li><strong>Ders Yönetimi:</strong> Ders ekleme, silme, detay görüntüleme ve XML'e kaydetme işlemleri.</li>
</ul>

<h2>📂 Proje Yapısı</h2>
<ul>
    <li><strong>Interfaces:</strong> 
        <ul>
            <li><code>IPerson</code>: Kişi sınıfları için temel arayüz.</li>
        </ul>
    </li>
    <li><strong>Models:</strong>
        <ul>
            <li><code>Person</code>: Kişilerin temel özelliklerini tanımlar.</li>
            <li><code>Student</code>: Öğrenciye özgü özellikleri içerir.</li>
            <li><code>Instructor</code>: Öğretim görevlilerine ait detayları içerir.</li>
            <li><code>Courses</code>: Ders bilgilerini ve ilişkili verileri yönetir.</li>
        </ul>
    </li>
    <li><strong>Data:</strong>
        <ul>
            <li><code>XmlHelper</code>: Verilerin XML dosyasına kaydedilmesi ve XML'den okunması için gerekli yardımcı sınıf.</li>
        </ul>
    </li>
    <li><strong>Program:</strong> Ana uygulama dosyası, tüm işlevselliği bir araya getirir ve kullanıcı etkileşimlerini yönetir.</li>
</ul>

<h2>📋 Kullanım</h2>
<ol>
    <li>Proje dosyalarını indirin ve bir IDE'de açın (ör. Visual Studio).</li>
    <li><code>Program.cs</code> dosyasını çalıştırın.</li>
    <li>Konsolda sunulan menüden işlemlerinizi seçerek sistemi kullanın.</li>
</ol>

<h2>🎯 Amaç</h2>
<p>
    Bu proje, Piri Reis Üniversitesi'nde almış olduğum Görsel Programlama dersinin vize projesidir. 
    Sistem, dinamik bir yapı üzerine kurulmuş olup, gerçek hayattaki yönetim ihtiyaçlarına yönelik bir simülasyon sunar. 
    XML tabanlı veri kaydetme ve okuma işlevselliği ile yazılım geliştirme pratiklerini gerçekleştirme imkânı sağlar.
</p>
