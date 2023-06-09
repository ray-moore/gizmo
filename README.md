# Gizmo

Java library to render HTML content. This is **not** a template engine.

## Example

The following Java code...

```java
public class Example {
    public static void main(String[] args) {
        HeaderElement title = new HeaderElement(HeaderElement.Level.h1);
        title.setText("Bookmark List");

        ContainerElement list = new ContainerElement();
        list.addClass("bookmark-list");

        ContainerElement div;
        AnchorElement a;
        ParagraphElement p;

        List<Bookmark> bookmarks = MockSchema.use().getBookmarkHome().findAll();  // Mock database
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

        Document html = new Document("Demo").init();
        html.setContent(content);

        System.out.println(html);  // Mock page response
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
    <div id="example">
      <h1>Bookmark List</h1>
      <div class="bookmark-list">
        <div class="bookmark" data-bookmark-id="cf44e750-962c-452f-bf8e-1337f8688fbc">
          <p>
            Click 
            <a class="bold" href="http://google.com" target="_blank">here</a>
             to navigate to the Google website
          </p>
          <p>This is another paragraph with no inline elements for demo purposes</p>
        </div>
        <div class="bookmark" data-bookmark-id="4070b81a-e2e7-40a5-9e8a-5a775481eb81">
          <p>
            Click 
            <a class="bold" href="http://netflix.com" target="_blank">here</a>
             to navigate to the Netflix website
          </p>
          <p>This is another paragraph with no inline elements for demo purposes</p>
        </div>
        <div class="bookmark" data-bookmark-id="1fb34c8a-89bf-4631-9727-11d99f1d192f">
          <p>
            Click 
            <a class="bold" href="http://testcase.dev" target="_blank">here</a>
             to navigate to the &lt;This&gt; &amp; &lt;That&gt; website
          </p>
          <p>This is another paragraph with no inline elements for demo purposes</p>
        </div>
      </div>
    </div>
  </body>
</html>
```

... which is viewable using the link below.

https://htmlpreview.github.io/?https://github.com/ray-moore/gizmo/blob/master/example.html
