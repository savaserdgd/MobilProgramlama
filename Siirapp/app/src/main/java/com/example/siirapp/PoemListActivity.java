package com.example.siirapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class PoemListActivity extends AppCompatActivity {
    private List<Poem> allPoems;
    private PoemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_list);

        ImageButton backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this, DashboardUserActivity.class));
            finish();
        });


        EditText searchBox = findViewById(R.id.searchBox);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        allPoems = getAllPoems();
        adapter = new PoemAdapter(this, allPoems);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        searchBox.addTextChangedListener(new TextWatcher() {
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                List<Poem> filtered = new ArrayList<>();
                for (Poem p : allPoems) {
                    if (p.getAuthor().toLowerCase().contains(s.toString().toLowerCase())) {
                        filtered.add(p);
                    }
                }
                adapter.updateList(filtered);
            }
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void afterTextChanged(Editable s) {}
        });
    }

    private List<Poem> getAllPoems() {
        List<Poem> poems = new ArrayList<>();

        // 1. Attila İlhan - Ben Sana Mecburum
        poems.add(new Poem("Ben Sana Mecburum",
                "Ben sana mecburum bilemezsin\nAdını mıh gibi aklımda tutuyorum\nBüyüdükçe büyüyor gözlerin\n\nBen sana mecburum bilemezsin\nİçimi seninle ısıtıyorum\nAğaçlardan denize atıyorum\n\nGözlerim gözlerinde dinleniyor\nEllerim ellerinde çözülüyor\nBen sana mecburum bilemezsin\n\nBir yangın gibi yanıyorum\nSöndürmeye kimse gelmiyor\nBen sana mecburum bilemezsin",
                "Attila İlhan"));

        // 2. Nazım Hikmet - Ceviz Ağacı
        poems.add(new Poem("Ceviz Ağacı",
                "Başım köpük köpük bulut, içim dışım deniz\nBen bir ceviz ağacıyım Gülhane Parkı'nda\n\nNe sen bunun farkındasın, ne polis farkında\nBen bir ceviz ağacıyım Gülhane Parkı'nda\n\nYapraklarım suda balık gibi kıvıl kıvıl\nYapraklarım ellerimdir tam yüz bin elim\n\nYapraklarımde bir şarkı bir şarkı bir şarkı\nYapraklarım Gülhane Parkı'nı tutar",
                "Nazım Hikmet"));

        // 3. Necip Fazıl - Kaldırımlar
        poems.add(new Poem("Kaldırımlar",
                "Sokaktayım, kimsesiz bir sokak ortasında\nYürüyorum, arkama bakmadan yürüyorum\n\nYolumun karanlığa saplanan noktasında\nSanki beni bekleyen bir hayal görüyorum\n\nKara gökler kül rengi bulutlarla kapanık\nEvlerin bacasını kolluyor yıldırımlar\n\nBu gece yarısında iki kişi uyanık\nBiri benim, biri de uzayan kaldırımlar",
                "Necip Fazıl Kısakürek"));

        // 4. Cemal Süreya - Üvercinka
        poems.add(new Poem("Üvercinka",
                "Seni çok özledim\nÜşüyorum bu gece\nSeni düşünüyorum\nÜşüyorum bu gece\n\nBir yangın var kanımda\nSöndürmeye gelmiştim\nKendimi yaktım seni\nSöndürmeye gelmiştim\n\nSeninle bir kuş oluyorum\nSeninle bir deniz oluyorum\nSeninle bir ağaç oluyorum\nSeninle bir dünya oluyorum",
                "Cemal Süreya"));

        // 5. Ahmet Arif - Hasretinden Prangalar Eskittim
        poems.add(new Poem("Hasretinden Prangalar Eskittim",
                "Hasretinden prangalar eskittim\nSaçlarına kan gülleri takayım\nBir o yana bir bu yana\n\nBeni böyle koyup gideceksen\nGözlerimden öperek gideceksen\nSürüne sürüne gideceksen\nÖlümü çağıracağım",
                "Ahmet Arif"));

        // 6. Turgut Uyar - Dünyanın En Güzel Arabistanı
        poems.add(new Poem("Dünyanın En Güzel Arabistanı",
                "Seni seviyorum\nSeni kırk defa seviyorum\nSeni bin defa seviyorum\nSeni sonsuz seviyorum\n\nSeninle bir kuş oluyorum\nSeninle bir deniz oluyorum\nSeninle bir ağaç oluyorum\nSeninle bir dünya oluyorum",
                "Turgut Uyar"));

        // 7. Edip Cansever - Mendilimde Kan Sesleri
        poems.add(new Poem("Mendilimde Kan Sesleri",
                "Bir yangın ormanından püskürmüş genç fidanlardı\nGözlerin, gözlerin buğulu birer camdı\nSen gittin yağmur çiselemeye başladı\n\nBir mendilim vardı kan içinde\nBir mendilim vardı gözyaşında\nBir mendilim vardı yüreğimde",
                "Edip Cansever"));

        // 8. Özdemir Asaf - Lavinia
        poems.add(new Poem("Lavinia",
                "Seni bir kere öpsem\nİki gözüm iki çeşme\nÖperim de ağlarım\nÖperim de ağlarım\n\nSeni bir kere öpsem\nİki gözüm iki çeşme\nÖperim de ağlarım\nÖperim de ağlarım",
                "Özdemir Asaf"));

        // 9. Behçet Necatigil - Sevgilerde
        poems.add(new Poem("Sevgilerde",
                "Sevgileri yarınlara bıraktım\nBir çift söz bulamadım\nŞimdi oturup yazsam\nKendimden başka\n\nBütün sevgileri yarınlara bıraktım\nBir çift söz bulamadım\nŞimdi oturup yazsam\nKendimden başka",
                "Behçet Necatigil"));

        // 10. Faruk Nafiz Çamlıbel - Han Duvarları
        poems.add(new Poem("Han Duvarları",
                "Yağız atlar kişnedi, meşin kırbaç şakladı\nBir dakika araba yerinde durakladı\nNeden sonra sarsıldı altımda demir yaylar\nGözlerimin önünden geçti kervansaraylar\n\nGidiyordum, gurbeti gönlümle duya duya\nUlukışla yolundan Orta Anadolu'ya\nİlk sevgiye benzeyen ilk acı, ilk ayrılık\nYüreğimin yaktığı ateşle hava ılık",
                "Faruk Nafiz Çamlıbel"));

        // 11. Orhan Veli - İstanbul'u Dinliyorum
        poems.add(new Poem("İstanbul'u Dinliyorum",
                "İstanbul'u dinliyorum, gözlerim kapalı\nÖnce hafiften bir rüzgar esiyor\nYavaş yavaş sallanıyor\nYapraklar ağaçlarda\n\nUzaklarda, çok uzaklarda\nSucuların hiç durmayan çıngırakları\nİstanbul'u dinliyorum, gözlerim kapalı",
                "Orhan Veli Kanık"));

        // 12. Yahya Kemal - Endülüs'te Raks
        poems.add(new Poem("Endülüs'te Raks",
                "Zil, şal ve gül. Bu bahçede raksın bütün hızı...\nŞevk akşamında Endülüs üç defa kırmızı...\nAşkın sihirli şarkısı yüzlerce dildedir.\nİspanya neş'esiyle bu akşam bu zildedir.\n\nYelpaze çevrilir gibi birden dönüşleri,\nİşveyle devriliş, saçılış, örtünüşleri...\nHer rengi istemez gözümüz şimdi aldadır;\nİspanya dalga dalga bu akşam bu şaldadır.",
                "Yahya Kemal Beyatlı"));

        // 13. Ahmet Haşim - Merdiven
        poems.add(new Poem("Merdiven",
                "Ağır ağır çıkacaksın bu merdivenlerden,\nEteklerinde güneş rengi bir yığın yaprak,\nVe bir zaman bakacaksın semaya ağlayarak...\n\nSular sarardı... yüzün perde perde solmakta,\nKızıl havaları seyret ki akşam olmakta...\n\nEğilmiş arza, kanar, muttasıl kanar güller;\nDurur alev gibi dallarda kanlı bülbüller,\nSular mı yandı? Neden tunca benziyor mermer?",
                "Ahmet Haşim"));

        // 14. Tevfik Fikret - Sis
        poems.add(new Poem("Sis",
                "Sarmış yine âfâkını bir dûd-ı muannid,\nBir zulmet-i beyzâ ki peyâpey mütezâyid.\nTazyîkının altında silinmiş gibi eşbâh,\nBir tozlu kesâfetten ibâret bütün elvâh;\n\nBürümüş ufuklarını artık derin, sıkı bir\nTül şehri örter gibi tabiatı, sisler iner;\nHer şerde, o şâmil, o muğber, o mütemerrid,\nDâimâ kötülenmiş, fakat hep merd ü müfid.",
                "Tevfik Fikret"));

        // 15. Mehmet Akif - Bülbül
        poems.add(new Poem("Bülbül",
                "Bütün dünyâya küskündüm, dün akşam pek bunalmıştım:\nNihâyet bir zaman kırlarda gezmiş, köyde kalmıştım.\nŞehâdet parmağıdır göklerden bize uzanan,\nO müstakîm parmak ki, doğruluk rehberimizdir.\n\nNe mümkün zulmü alkışlayamam, zalimi asla sevemem;\nGelenin keyfi için geçmişe kalkıp sövemem.\nBiri ecdadıma saldırdı mı, hattâ boğarım...\n-Boğamazsın ki! -Hiç olmazsa yanımdan kovarım.",
                "Mehmet Akif Ersoy"));

        // 16. Cahit Sıtkı - Otuz Beş Yaş
        poems.add(new Poem("Otuz Beş Yaş",
                "Yaş otuz beş! Yolun yarısı eder.\nDante gibi ortasındayız ömrün.\nDelikanlı çağımızdaki cevher,\nYalvarmak, yakarmak nafile bugün,\n\nGözünün yaşına bakmadan gider.\nŞakaklarıma kar mı yağdı ne var?\nBenim mi Allah'ım bu çizgili yüz?\nYa gözler altındaki mor halkalar?\n\nNeden böyle düşman görünürsünüz,\nYıllar yılı dost bildiğim aynalar?",
                "Cahit Sıtkı Tarancı"));

        // 17. Fazıl Hüsnü - Dağlarca
        poems.add(new Poem("Çocuk ve Allah",
                "Çocuk, Allah'ı sordu bir gün bana\nGöster dedi, gökyüzünde onu\nNasıl göstereyim, nasıl anlatayım\nBilmem ki ben bile görmedim hiç\n\nÇocuk inat etti, ağladı, güldü\nSonra bir kuş uçtu dalından\nİşte dedim, Allah'ın elçisi\nÇocuk sustu, düşündü, anladı",
                "Fazıl Hüsnü Dağlarca"));

        // 18. Can Yücel - Hayatta Ben En Çok Babamı Sevdim
        poems.add(new Poem("Hayatta Ben En Çok Babamı Sevdim",
                "Hayatta ben en çok babamı sevdim.\nKaraçalılar gibi yardan bitme bir çocuk\nÇarpı bacaklarıyla ha düştü ha düşecek...\nNasıl koşarsa ardından bir devin\n\nO çapkın babamı ben öyle sevdim.\nBilmezdi ki oturduğumuz semti\nGeldi mi de gidici hep, hep acele işi!\nÇağın en güzel gözlü maarif müfettişi\n\nAtlastan bakardım nereye gitti\nÖyle öyle ezber ettim gurbeti.",
                "Can Yücel"));

        // 19. İlhan Berk - Galata
        poems.add(new Poem("Galata",
                "Galata'nın kuleleri ıslaktır her zaman\nYağmur yağar da yağmur yağar da\nBir gemi geçer uzaklardan\nBir kadın geçer kaldırımdan\n\nGalata'nın kuleleri ıslaktır her zaman\nBen orada yağmuru beklerim\nBir şarkı söylerim usulca\nBir sigara yakarım\n\nGalata'nın kuleleri ıslaktır her zaman\nKimseler bilmez bunu\nYağmur yağar da yağmur yağar da\nBen orada yağmuru beklerim",
                "İlhan Berk"));

        // 20. Ece Ayhan - Meçhul Öğrenci Anıtı
        poems.add(new Poem("Meçhul Öğrenci Anıtı",
                "Buraya bakın, burada, bu kara mermerin altında\nBir teneffüs daha yaşasaydı\nTabiattan tahtaya kalkacak bir çocuk gömülüdür\nDevlet dersinde öldürülmüştür.\n\nÖldürülmüş öğrenciler görmüşüm rüyalarımda\nHani kurşunla kovalarlar ya hani\nHani koşarken düşersiniz ya\nİşte öyle bir rüya",
                "Ece Ayhan"));

        // 21. Ülkü Tamer - Gök Onları Yanıltmaz
        poems.add(new Poem("Gök Onları Yanıltmaz",
                "Gök onları yanıltmaz, yollarını şaşırtmaz\nDeniz onları yutmaz, ateş onları yakmaz\nOnlar ki ölümün gözlerine bakarlar\nVe gülümserlerdi ölümün gözlerine\n\nGök onları yanıltmaz, yollarını şaşırtmaz\nOnlar ki yıldızlar gibi kayarlar\nVe bir sabah uyanırlar\nDenizlerin dibinde",
                "Ülkü Tamer"));

        // 22. Metin Altıok - Kendinin Avcısı
        poems.add(new Poem("Kendinin Avcısı",
                "Ben kendimin avcısıyım\nPeşindeyim kendi kendimin\nDağlarda, ormanlarda\nŞehirlerin kalabalığında\n\nAvım benim, peşimdeyim\nBir gün yakalayacağım kendimi\nBir gün vuracağım kendimi\nVe öleceğim kendi elimle",
                "Metin Altıok"));

        // 23. Ahmet Telli - Hüznün İsyan Olur
        poems.add(new Poem("Hüznün İsyan Olur",
                "Hüznün isyan olur bende\nYalnızlık bir dağ gibi büyür\nGeceler uzar, günler kısalır\nVe ben susarım, susarım, susarım\n\nHüznün isyan olur bende\nBir yangın olur, bir fırtına\nBir deprem olur, bir sel\nVe ben susarım, susarım, susarım",
                "Ahmet Telli"));

        // 24. Küçük İskender - Bir Çift Siyah Deri Eldiven
        poems.add(new Poem("Bir Çift Siyah Deri Eldiven",
                "Bir çift siyah deri eldivenim vardı\nKaybettim onu bir kış günü\nBelki de unuttum bir yerde\nBelki de çaldırdım\n\nBir çift siyah deri eldivenim vardı\nOnunla tutardım ellerini\nOnunla okşardım saçlarını\nOnunla sarılırdım sana\n\nBir çift siyah deri eldivenim vardı\nKaybettim onu bir kış günü\nBelki de unuttum bir yerde\nBelki de çaldırdım",
                "Küçük İskender"));

        // 25. Sunay Akın - Kaza Süsü
        poems.add(new Poem("Kaza Süsü",
                "Bir kaza süsü verilmiş hayata\nDüşmüşüm yollara, toz olmuşum\nBir resimde kalmışım yarım\nBir şiirde eksik\n\nBir kaza süsü verilmiş hayata\nDüşmüşüm yollara, toz olmuşum\nKimseler bilmez neredeyim\nKimseler sormaz halimi",
                "Sunay Akın"));

        // 26. Haydar Ergülen - Nar
        poems.add(new Poem("Nar",
                "Bir nar tanesi düşmüş yüreğime\nAcısıyla tatlısıyla\nÇatlamış orda duruyor\nİçimde bir sızı\n\nBir nar tanesi düşmüş yüreğime\nAcısıyla tatlısıyla\nKimseler bilmez halimi\nKimseler duymaz sızımı",
                "Haydar Ergülen"));

        // 27. Birhan Keskin - Soğuk Kazı
        poems.add(new Poem("Soğuk Kazı",
                "Soğuk bir kazı bu\nKazdıkça derinleşen\nKazdıkça genişleyen\nBir çukur bu\n\nSoğuk bir kazı bu\nİçine düşenler\nBir daha çıkamaz\nBir daha göremez güneşi",
                "Birhan Keskin"));

        // 28. Gülten Akın - Kestim Kara Saçlarımı
        poems.add(new Poem("Kestim Kara Saçlarımı",
                "Kestim kara saçlarımı\nN'olurdu saçlarım uzun olsaydı\nBir çocuk oyuncağı için\nKestim kara saçlarımı\n\nKestim kara saçlarımı\nN'olurdu saçlarım uzun olsaydı\nBir aşk uğruna\nKestim kara saçlarımı",
                "Gülten Akın"));

        // 29. Didem Madak - Ah'lar Ağacı
        poems.add(new Poem("Ah'lar Ağacı",
                "Bir ah'lar ağacıdır büyür içimde\nDalları gökyüzüne değer\nYaprakları yıldızları okşar\nKökleri yüreğime sarılır\n\nBir ah'lar ağacıdır büyür içimde\nMeyveleri acıdır\nYenmez, tadılmaz\nAma koparılmaz da",
                "Didem Madak"));

        // 30. Nilgün Marmara - Kırmızı Kahverengi Defter
        poems.add(new Poem("Kırmızı Kahverengi Defter",
                "Bir kırmızı kahverengi defterim vardı\nYazdım durdum içime düşenleri\nSonra yırttım attım\nSonra pişman oldum\n\nBir kırmızı kahverengi defterim vardı\nYazdım durdum içime düşenleri\nSonra yırttım attım\nSonra pişman oldum",
                "Nilgün Marmara"));

        // 31. Sennur Sezer - Aşk Bitti
        poems.add(new Poem("Aşk Bitti",
                "Aşk bitti dediler\nGitti dediler\nÖldü dediler\nBen inanmadım\n\nAşk bitti dediler\nGitti dediler\nÖldü dediler\nBen inanmadım",
                "Sennur Sezer"));

        // 32. Enver Gökçe - Dörtlükler
        poems.add(new Poem("Dörtlükler",
                "Bir sevda yüklüsün yüreğimde\nBir umut, bir direnç, bir inanç\nYürürsün sessizce düşlerimde\nBir sevda yüklüsün yüreğimde\n\nBir sevda yüklüsün yüreğimde\nBir umut, bir direnç, bir inanç\nYürürsün sessizce düşlerimde\nBir sevda yüklüsün yüreğimde",
                "Enver Gökçe"));

        // 33. Ahmet Erhan - Şehirde Tek Başına
        poems.add(new Poem("Şehirde Tek Başına",
                "Şehirde tek başına yürürken\nBir sokak lambasının altında durdum\nYağmur yağıyordu hafiften\nVe ben seni düşündüm\n\nŞehirde tek başına yürürken\nBir sokak lambasının altında durdum\nYağmur yağıyordu hafiften\nVe ben seni düşündüm",
                "Ahmet Erhan"));

        // 34. Murathan Mungan - Kum Saati
        poems.add(new Poem("Kum Saati",
                "Bir kum saatidir zaman\nAkar durur\nDurmaz, akar\nBir kum saatidir zaman\n\nBir kum saatidir zaman\nAkar durur\nDurmaz, akar\nBir kum saatidir zaman",
                "Murathan Mungan"));

        // 35. Cevat Çapan - Dönüşü Yok
        poems.add(new Poem("Dönüşü Yok",
                "Dönüşü yok bu yolun\nGidiyorum işte\nNe bir ses, ne bir soluk\nGidiyorum işte\n\nDönüşü yok bu yolun\nGidiyorum işte\nNe bir ses, ne bir soluk\nGidiyorum işte",
                "Cevat Çapan"));

        // 36. Salah Birsel - Haydar Haydar
        poems.add(new Poem("Haydar Haydar",
                "Haydar Haydar, diye diye\nÇıktım yücesine baktım geceye\nYıldızlar kaydı, ay battı\nHaydar Haydar, diye diye\n\nHaydar Haydar, diye diye\nÇıktım yücesine baktım geceye\nYıldızlar kaydı, ay battı\nHaydar Haydar, diye diye",
                "Salah Birsel"));

        // 37. Oktay Rifat - Aşk Merdiveni
        poems.add(new Poem("Aşk Merdiveni",
                "Bir aşk merdiveni kurmuşum göğe\nÇıkıyorum yavaş yavaş\nBulutlar geçiyor ayağımın altından\nYıldızlar geçiyor\n\nBir aşk merdiveni kurmuşum göğe\nÇıkıyorum yavaş yavaş\nBulutlar geçiyor ayağımın altından\nYıldızlar geçiyor",
                "Oktay Rifat"));

        // 38. Melih Cevdet - İstanbul
        poems.add(new Poem("İstanbul",
                "İstanbul'u dinliyorum, gözlerim kapalı\nÖnce hafiften bir rüzgar esiyor\nYavaş yavaş sallanıyor\nYapraklar ağaçlarda\n\nUzaklarda, çok uzaklarda\nSucuların hiç durmayan çıngırakları\nİstanbul'u dinliyorum, gözlerim kapalı",
                "Melih Cevdet Anday"));

        // 39. Sabahattin Ali - Leylim Ley
        poems.add(new Poem("Leylim Ley",
                "Leylim ley, leylim ley\nDöndüm daldan düşen kuru yaprağa\nSeher yeli dağıt beni\nKır beni, savur beni\n\nLeylim ley, leylim ley\nDöndüm daldan düşen kuru yaprağa\nSeher yeli dağıt beni\nKır beni, savur beni",
                "Sabahattin Ali"));

        // 40. Rıfat Ilgaz - Karartma Geceleri
        poems.add(new Poem("Karartma Geceleri",
                "Karartma geceleri, korku dolu sokaklar\nKimse kimseye güvenmez olmuş\nHerkes kendi kabuğuna çekilmiş\nKarartma geceleri, korku dolu sokaklar\n\nKarartma geceleri, korku dolu sokaklar\nKimse kimseye güvenmez olmuş\nHerkes kendi kabuğuna çekilmiş\nKarartma geceleri, korku dolu sokaklar",
                "Rıfat Ilgaz"));

        // 41. Bedri Rahmi - Karadut
        poems.add(new Poem("Karadut",
                "Karadutum, çatal karam, çingenem\nNar tanem, nur tanem, bir tanem\nAğaç isem dalımsın salkım saçak\nPetek isem balımsın ağulum\n\nKaradutum, çatal karam, çingenem\nNar tanem, nur tanem, bir tanem\nAğaç isem dalımsın salkım saçak\nPetek isem balımsın ağulum",
                "Bedri Rahmi Eyüboğlu"));

        // 42. Orhon Murat - İşte Gidiyorum
        poems.add(new Poem("İşte Gidiyorum",
                "İşte gidiyorum çeşmi siyahım\nÖnümde dağlar denizler\nArkamda bıraktıklarım\nİşte gidiyorum çeşmi siyahım\n\nİşte gidiyorum çeşmi siyahım\nÖnümde dağlar denizler\nArkamda bıraktıklarım\nİşte gidiyorum çeşmi siyahım",
                "Orhon Murat Arıburnu"));

        // 43. Metin Eloğlu - Sultan Palamut
        poems.add(new Poem("Sultan Palamut",
                "Sultan palamut sardı yine\nDenizlerin efendisi\nDalgalar köpük köpük\nSultan palamut sardı yine\n\nSultan palamut sardı yine\nDenizlerin efendisi\nDalgalar köpük köpük\nSultan palamut sardı yine",
                "Metin Eloğlu"));

        // 44. Kemal Özer - Kavaklar
        poems.add(new Poem("Kavaklar",
                "Kavaklar, kavaklar\nRüzgarda sallanan kavaklar\nBoynu bükük, hüzünlü\nKavaklar, kavaklar\n\nKavaklar, kavaklar\nRüzgarda sallanan kavaklar\nBoynu bükük, hüzünlü\nKavaklar, kavaklar",
                "Kemal Özer"));

        // 45. Ataol Behramoğlu - Yaşadıklarımdan Öğrendiğim Bir Şey Var
        poems.add(new Poem("Yaşadıklarımdan Öğrendiğim Bir Şey Var",
                "Yaşadıklarımdan öğrendiğim bir şey var:\nYaşadın mı büyük yaşayacaksın, ırmaklara, göğe, bütün evrene karışırcasına\nÇünkü ömür dediğimiz şey, hayata sunulmuş bir armağandır\nVe hayat, sunulmuş bir armağandır insana\n\nYaşadıklarımdan öğrendiğim bir şey var:\nYaşadın mı büyük yaşayacaksın, ırmaklara, göğe, bütün evrene karışırcasına\nÇünkü ömür dediğimiz şey, hayata sunulmuş bir armağandır\nVe hayat, sunulmuş bir armağandır insana",
                "Ataol Behramoğlu"));

        // 46. Refik Durbaş - İki Sevda Arasında
        poems.add(new Poem("İki Sevda Arasında",
                "İki sevda arasında kalmışım\nBiri geçmiş, biri gelecek\nHangisine varsam\nDiğerini özleyeceğim\n\nİki sevda arasında kalmışım\nBiri geçmiş, biri gelecek\nHangisine varsam\nDiğerini özleyeceğim",
                "Refik Durbaş"));

        // 47. Tuğrul Tanyol - Aşkın Tarihçesi
        poems.add(new Poem("Aşkın Tarihçesi",
                "Aşkın bir tarihçesi var\nTaş devrinden beri süren\nMağara duvarlarına çizilen\nKil tabletlere kazınan\n\nAşkın bir tarihçesi var\nTaş devrinden beri süren\nMağara duvarlarına çizilen\nKil tabletlere kazınan",
                "Tuğrul Tanyol"));

        // 48. Sina Akyol - Şiir Çıkmazda
        poems.add(new Poem("Şiir Çıkmazda",
                "Şiir çıkmazda şimdi\nKelime bulamıyorum\nDizeler kurulamıyor\nŞiir çıkmazda şimdi\n\nŞiir çıkmazda şimdi\nKelime bulamıyorum\nDizeler kurulamıyor\nŞiir çıkmazda şimdi",
                "Sina Akyol"));

        // 49. Lale Müldür - Divanü Lügati't-Türk
        poems.add(new Poem("Divanü Lügati't-Türk",
                "Divanü Lügati't-Türk'te kalmışım\nKelime kelime arayışta\nHarfler uçuyor gözümün önünden\nDivanü Lügati't-Türk'te kalmışım\n\nDivanü Lügati't-Türk'te kalmışım\nKelime kelime arayışta\nHarfler uçuyor gözümün önünden\nDivanü Lügati't-Türk'te kalmışım",
                "Lale Müldür"));

        // 50. Seyhan Erözçelik - Gül ve Telve
        poems.add(new Poem("Gül ve Telve",
                "Gül ve telve kaldı avuçlarımda\nBirinde güzellik, birinde acı\nHangisini tutsam\nDiğeri dökülecek\n\nGül ve telve kaldı avuçlarımda\nBirinde güzellik, birinde acı\nHangisini tutsam\nDiğeri dökülecek",
                "Seyhan Erözçelik"));

        return poems;
    }
}
