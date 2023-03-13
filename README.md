# Gizmo
Java library to render HTML content

## Example

The following Java code...

```java
public class Example {
    public static void main(String[] args) {
        ContainerElement content = new ContainerElement();
        content.setId("content");

        HeaderElement title = new HeaderElement(HeaderElement.Level.h1);
        title.setText("Bookmark List");
        content.appendChild(title);

        ContainerElement list = new ContainerElement();
        list.addClass("bookmark-list");
        content.appendChild(list);

        List<Bookmark> bookmarks = mockData();  // Mock database retrieval
        for (Bookmark bookmark : bookmarks) {
            AnchorElement a;
            ParagraphElement p;

            ContainerElement div = new ContainerElement();
            div.addClass("bookmark");
            list.appendChild(div);

            a = new AnchorElement();
            a.addClass("bold");
            a.setText("here");
            a.setReference(bookmark.URL);
            a.setTarget("_blank");

            // This demonstrates a mix & match of Gizmo '@@@' wildcard and Java '%s' formatting
            p = new ParagraphElement();
            p.setContent(String.format("Click @@@ to navigate to the %s website", bookmark.NAME), a);
            div.appendChild(p);

            p = new ParagraphElement();
            p.setText("This is another paragraph with no inline elements");
            div.appendChild(p);
        }

        Document html = new Document("Example").init();
        html.setContent(content);

        System.out.println(html);
    }
    
    public static List<Bookmark> mockData() {
        List<Bookmark> data = new ArrayList<>();
        data.add(new Bookmark("Apple", "https://apple.com"));
        data.add(new Bookmark("Google", "http://google.com"));
        data.add(new Bookmark("Netflix", "http://netflix.com"));
        data.add(new Bookmark("<This> & <That>", "http://testcase.dev"));

        return data;
    }
}
```

... produces the following HTML content...

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <title>Example</title>
    <meta charset="UTF-8">
    <meta content="IE=edge" http-equiv="X-UA-Compatible">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
  </head>
  <body>
    <div id="content">
      <h1>Bookmark List</h1>
      <div class="bookmark-list">
        <div class="bookmark">
          <p>Click <a class="bold" href="https://apple.com" target="_blank">here</a> to navigate to the Apple website</p>
          <p>This is another paragraph with no inline elements</p>
        </div>
        <div class="bookmark">
          <p>Click <a class="bold" href="http://google.com" target="_blank">here</a> to navigate to the Google website</p>
          <p>This is another paragraph with no inline elements</p>
        </div>
        <div class="bookmark">
          <p>Click <a class="bold" href="http://netflix.com" target="_blank">here</a> to navigate to the Netflix website</p>
          <p>This is another paragraph with no inline elements</p>
        </div>
        <div class="bookmark">
          <p>Click <a class="bold" href="http://testcase.dev" target="_blank">here</a> to navigate to the &lt;This&gt; &amp; &lt;That&gt; website</p>
          <p>This is another paragraph with no inline elements</p>
        </div>
      </div>
    </div>
  </body>
</html>
```

... which may be previewed using the link below.

https://htmlpreview.github.io/?https://github.com/ray-moore/gizmo/blob/master/example.html
