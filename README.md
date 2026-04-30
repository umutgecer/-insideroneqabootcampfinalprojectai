## Umut Geçer

### umutgecer@outlook.com

## Test Senaryosu

1. InsiderOne ana sayfasına gidilir ve sayfadaki bölümler kontrol edilir.
2. Kariyer sayfasına gidilir ve `See All` butonuna tıklanır.
3. QA iş kartı aranır ve üstüne tıklanır.
4. QA iş listesinin var olup olmadığı kontrol edilir.
5. Lokasyon filtresi açılır ve `Istanbul` seçeneği seçilir.
6. Listelenen iş başlıklarının `Quality Assurance` veya `QA` tanımları içerdiği kontrol edilir.
7. Listelenen iş başlıklarının konumlarının `Istanbul` olduğu kontrol edilir.
8. Listedeki bir iş ilanına ait `Apply` butonuna tıklanır.
9. Açılan sayfada `Apply For This Job` butonuna tıklanır.
10. Form sayfasının açıldığı başlık ve `Submit` butonuyla kontrol edilir.

# Insider One AI Bootcamp Final Projesi

Bu proje, Insider One kariyer akisina yonelik bir UI test otomasyon odevidir.  
Teknoloji olarak Java + Selenium + TestNG + Maven kullanir ve calisma sonunda otomatik test raporu uretir.

## Proje Amaci

Test senaryosu temel olarak su adimlari dogrular:

- Insider One anasayfasinin acilmasi
- Career sayfasina gecis
- QA departmanina filtreleme
- Job listesi iceriklerinin (title/location) dogrulanmasi
- Apply akisiyla form sayfasina ulasilmasi

## Kullanilan Teknolojiler

- Java 21
- Maven
- Selenium 4
- TestNG
- WebDriverManager
- Log4j2
- ExtentReports (raporlama)

## Proje Yapisi

`src/test/java` altinda ana yapi:

- `base`
    - `BaseTest`: Driver kurulum/teardown ve listener baglantisi
    - `BaseStep`: Ortak Selenium helper metodlari (wait, click, visibility vb.)
- `page`
    - `HomePage`: Anasayfa element ve aksiyonlari
    - `CareerPage`: Career akisi element ve aksiyonlari
- `tests`
    - `insideroneCase`: Uctan uca TestNG test senaryosu
- `reporting`
    - `TestReportListener`: TestNG listener ile durum/sure/hata/kanit kaydi
    - `ReportManager`: ExtentReports konfigurasyonu

## Raporlama Mekanizmasi

Projede TestNG listener tabanli ozel raporlama bulunur.

Her test icin raporda:

- Pass / Fail / Skip durumu
- Test suresi (ms)
- Hata detayi (exception + stack trace)
- Kanit dosyalari
    - Ekran goruntusu (`.png`)
    - Sayfa kaynak kodu (`.html`)

Uretilen dosyalar:

- Ana rapor: `test-output/custom-report/test-report.html`
- Kanitlar: `test-output/custom-report/evidence/`

## CI/CD Pipeline (Pull Request)

Proje, GitHub Actions ile pull request odakli bir pipeline'a entegre edilmistir.

- Workflow dosyasi: `.github/workflows/pr-test-automation.yml`
- Tetiklenme durumlari:
    - Pull request acilinca (`opened`)
    - Pull request guncellenince (`synchronize`)
    - Pull request yeniden acilinca (`reopened`)
    - Draft durumundan cikinca (`ready_for_review`)
- Calisan adimlar:
    - Java 21 kurulumu
    - `mvn -B clean test` ile test kosumu
    - Maven Surefire JUnit XML sonucunun PR check olarak yayinlanmasi (
      `target/surefire-reports/junitreports/TEST-*.xml`)
    - `target/surefire-reports` ve `test-output` artefact olarak yuklenmesi
- Sonuclar PR dogrulamasinda kullanilir; test job'i fail olursa PR check fail olur.

## AI Destegi Ozeti

- Bu çalışmada, AI desteğinden öncelikli olarak verilen URL sayfalarının test otomasyonu bakış açısıyla incelenmesinde yararlanıldı. Sonrasında raporlama ve CI/CD süreçleri için örnekler üretme ve  adımlar için bir roadmap oluşturma konularında destek sağlanmıştır.
