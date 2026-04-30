## Umut Geçer

### umutgecer@outlook.com

# Insider One AI Bootcamp Test Automation Projesi

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

## Kurulum

Gereksinimler:

- JDK 21
- Maven 3.8+
- Google Chrome

Kurulum adimlari:

```bash
mvn clean install -DskipTests
```

## Test Calistirma

Tum testleri calistirmak icin:

```bash
mvn test
```

Belirli test sinifini calistirmak icin:

```bash
mvn -Dtest=tests.insideroneCase test
```

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
    - Maven Surefire JUnit XML sonucunun PR check olarak yayinlanmasi (`target/surefire-reports/junitreports/TEST-*.xml`)
    - `target/surefire-reports` ve `test-output` artefact olarak yuklenmesi
- Sonuclar PR dogrulamasinda kullanilir; test job'i fail olursa PR check fail olur.

## Notlar

- Testler su an headless Chrome ile calisacak sekilde ayarlidir.
- Log kayitlari `log4j2` ile alinmaktadir.

## Gelistirme Onerileri

- Headless/non-headless secimini Maven parametresiyle yonetmek
- Locatorlari daha dayanikli hale getirmek
- Test verisi ve environment konfiglerini disaridan okumak
