import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FicbookSavingTextApp {
public static void FindName (String fanFicName) throws NameException{
    String name = "Гарри";
    if (fanFicName.indexOf(name)!=-1) {
        throw new NameException("Я не буду это сохранять!");

    }
}

    private static String getChapterText(String url) {
        url = "https://ficbook.net" + url;
        Document document = null;
        try {
            document = Jsoup.connect(url).get();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ну все хуйня,не получил я нихуя");
        }
        return document.select("div#content.jsPartText.part_text.urlize.clearfix.public_beta").text();
    }
    public static void main(String[] args) {
        Document document = null;
        String fanFicName=null;
        try {
            document = Jsoup.connect("https://ficbook.net/readfic/9736193").get();
            fanFicName = document.select("div.fanfic-main-info > h1").text();
            FindName(fanFicName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("invalid link or no connection");
        } catch (NameException e) {
            e.printStackTrace();
        }
        try ( FileWriter writer = new FileWriter(fanFicName + ".txt") ) {
            List<String> chapterUrls = document.select("div.part-info > a").eachAttr("href");
            for (String chapterUrl : chapterUrls) {
                writer.write(getChapterText(chapterUrl));
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Райтер наебнувся");
        }
    }


}

