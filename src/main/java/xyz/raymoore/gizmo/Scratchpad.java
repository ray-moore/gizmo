package xyz.raymoore.gizmo;

import xyz.raymoore.gizmo.element.body.AnchorElement;
import xyz.raymoore.gizmo.element.body.ContainerElement;
import xyz.raymoore.gizmo.element.body.HeaderElement;
import xyz.raymoore.gizmo.element.body.ParagraphElement;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class is used as a "scratchpad" and is constantly in flux.
 */
public class Scratchpad {
    public static void main(String[] args) {
        BookmarkDemo.execute();
    }

    public static class BookmarkDemo {
        public static void execute() {
            HeaderElement title = new HeaderElement(HeaderElement.Level.h1);
            title.setText("Bookmark List");

            ContainerElement list = new ContainerElement();
            list.addClass("bookmark-list");

            ContainerElement div;
            AnchorElement a;
            ParagraphElement p;

            List<Bookmark> bookmarks = mockData();  // Mock database retrieval as fixed list
            for (Bookmark bookmark : bookmarks) {
                div = new ContainerElement();
                div.addClass("bookmark");
                div.setData("bookmarkId", bookmark.ID);  // Mock primary key as random UUID
                list.add(div);

                a = new AnchorElement();
                a.addClass("bold");
                a.setReference(bookmark.URL);
                a.setTarget("_blank");
                a.setText("here");

                // This demonstrates complex element content with raw text as well as inline element(s)
                p = new ParagraphElement();
                p.add("Click ");
                p.add(a);
                p.add(" to navigate to the %s website", bookmark.NAME);
                div.add(p);

                p = new ParagraphElement();
                p.setText("This is another paragraph with no inline elements for demo purposes");
                div.add(p);
            }

            ContainerElement content = new ContainerElement();
            content.setId("example");
            content.add(title);
            content.add(list);

            Document html = new Document("Example").init();
            html.setContent(content);

            System.out.println(html);  // Mock page response as console output
        }

        public static List<Bookmark> mockData() {
            List<Bookmark> data = new ArrayList<>();
            data.add(new Bookmark("Google", "http://google.com"));
            data.add(new Bookmark("Netflix", "http://netflix.com"));
            data.add(new Bookmark("<This> & <That>", "http://testcase.dev"));

            return data;
        }

        public static class Bookmark {
            String ID;
            String NAME;
            String URL;

            Bookmark(String name, String url) {
                this.ID = UUID.randomUUID().toString();
                this.NAME = name;
                this.URL = url;
            }
        }
    }
}
