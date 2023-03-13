package xyz.raymoore.gizmo;

import xyz.raymoore.gizmo.element.head.MetaElement;
import xyz.raymoore.gizmo.element.head.TitleElement;

import java.util.Collections;

public class Document extends Element {
    private Head head;
    private Body body;

    public Document(String title) {
        super(Type.block, "html");
        this.head = new Head(title);
        appendChild(head);
        this.body = new Body();
        appendChild(body);
    }

    // --------------------
    //  Methods
    // --------------------

    /**
     * Initializes document with various <meta> information added to the <head> element
     * The equivalent of the Visual Studio Code "!" HTML boilerplate
     * @return this
     */
    public Document init() {
        setAttribute("lang", "en");

        MetaElement meta;
        meta = new MetaElement();
        meta.setAttribute("charset", "UTF-8");
        addMeta(meta);
        meta = new MetaElement();
        meta.setAttribute("http-equiv", "X-UA-Compatible");
        meta.setAttribute("content", "IE=edge");
        addMeta(meta);
        meta = new MetaElement();
        meta.setAttribute("name", "viewport");
        meta.setAttribute("content", "width=device-width, initial-scale=1.0");
        addMeta(meta);

        return this;
    }

    public void addMeta(MetaElement element) {
        this.head.appendChild(element);
    }

    public void setContent(Element parent) {
        body.children = Collections.singletonList(parent);  // Body has one and only one child
    }

    @Override
    public String render(int padding) {
        return " ".repeat(padding) +
                "<!DOCTYPE html>" +
                '\n' +
                super.render(padding);
    }

    // --------------------
    //  Inner Classes
    // --------------------

    public static class Body extends Element {
        public Body() {
            super(Type.block, "body");
        }
    }

    public static class Head extends Element {
        public Head(String title) {
            super(Type.block, "head");
            this.appendChild(new TitleElement(title));
        }
    }
}
