import com.lowagie.text.DocumentException;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Main
 *
 * @version 0.1
 */
public class Main {

    public static void main(String [] args) {
        final String ok = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <title>OK</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<div style=\"color:red; border: 1px solid green\">Ok</div>\n" +
                "</body>\n" +
                "</html>";
        final String html = "<html>\n" +
                "    <head>\n" +
                "        <title>Colossal (movie)</title>\n" +
                "        <style>\n" +
                "            .poster { width: 120px;float: right; }\n" +
                "            .director { font-style: italic; }\n" +
                "            .description { font-family: serif; }\n" +
                "            .imdb { font-size: 0.8em; }\n" +
                "            a { color: red; }\n" +
                "        </style>\n" +
                "    </head>\n" +
                "    <body>\n" +
                "        <img src=\"img/colossal.jpg\" class=\"poster\" />\n" +
                "        <h1>Lorem ipsum</h1>\n" +
                "        <div class=\"director\">Lorem ipsum dolor sit amet</div>\n" +
                "        <div class=\"description\">Vel ocurreret intellegam an. Debet eloquentiam definitiones eos in, delectus verterem temporibus est ei, iuvaret feugiat accusam his te. Option legimus per te. At mel tempor veritus, sint latine aperiam in quo. Mel possim albucius facilisi ea, ex adhuc putent ius.\n" +
                "        </div>\n" +
                "        <div class=\"imdb\">Vel ocurreret intellegam an\n" +
                "            <a href=\"www.imdb.com/title/tt4680182\">IMDB</a>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html><html xmlns=\"http://www.w3.org/1999/xhtml\">\n" +
                "<head>\n" +
                "    <title></title>4\n" +
                "</head>\n" +
                "<body class=\"bodyCopy\" id=\"body\" style=\"background-color: #f4f4f4; margin: 0 auto; text-align: center;\">\n" +
                "    <div class=\"preheader\" style=\"font-size: 1px; display: none !important;\">  Hello</div>\n" +
                "\n" +
                "<table align=\"center\" bgcolor=\"#f4f4f4\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"wrapper\" style=\"padding: 10px 18px 0px 18px;\" width=\"600\">\n" +
                "    <tr>\n" +
                "        <td bgcolor=\"#f4f4f4\">        \n" +
                "        <table bgcolor=\"#ffffff\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" class=\"content\" style=\"margin: 0 auto; margin: 0px 0px 0px 0px; max-width: 600px; width: 100%;\" width=\"100%\">\n" +
                "\n" +
                "            <tr>\n" +
                "                                <td bgcolor=\"#2480FE\" width=\"100%\" style=\"background-color: #2480FE; border: 0; border-color: transparent;\">\n" +
                "                    <table align=\"center\" bgcolor=\"#2480FE\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"margin: 0 auto;  max-height: 100px; max-width: 600px; padding: 0px; width: 100%; min-width: 100%;\" width=\"100%\">\n" +
                "    <tr>\n" +
                "        <td align=\"center\" bgcolor=\"#2480FE\" style=\"background-color: #2480FE; margin: 0; padding: 18px 0px 18px 0px; line-height: normal; width: 100%;\" width=\"100%\">\n" +
                "                \n" +
                "        <img alt=\"Progressive\" src=\"http://image.e.progressive.com/lib/fe9b137075640c7974/m/1/logo_progressive_direct_responsive.png\" border=\"0\" style=\"display: block; max-width: 175px; overflow: hidden; width: 175px;\" width=\"175\" />\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "\n" +
                "</body></html>";
        try {
            // final byte[] bytes = generatePDFFrom(ok); // works!
            final byte[] bytes = generatePDFFrom(html); // does work :(
            try(FileOutputStream fos = new FileOutputStream("sample-file.pdf")) {
                fos.write(bytes);
            }

        } catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
    }

    private static byte[] generatePDFFrom(String html) throws IOException, DocumentException {
        final ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        renderer.layout();
        try (ByteArrayOutputStream fos = new ByteArrayOutputStream(html.length())) {
            renderer.createPDF(fos);
            return fos.toByteArray();
        }
    }
}
