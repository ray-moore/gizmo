package xyz.raymoore.gizmo;

import java.util.*;

public abstract class Element implements Renderable {
    public enum Type {
        block,
        inline
    }

    // --------------------
    //  Fields
    // --------------------

    protected String tag;
    protected Type type;
    protected boolean isVoid;  // Void elements do not have a closing tag
    protected Map<String, String> attributes;

    protected List<Element> children;

    protected String content;
    protected List<Element> args;  // Used with '@@@' placeholder

    public static final int BLOCK_ELEMENT_PADDING_CHARS = 2;
    public static final String INLINE_ELEMENT_WILDCARD = "@@@";


    // --------------------
    //  Constructors
    // --------------------

    public Element(Type type, String tag) {
        this.type = type;
        this.tag = tag;
        this.attributes = new TreeMap<>();
        this.children = new ArrayList<>();
    }

    // --------------------
    //  Methods
    // --------------------

    public Type getType() {
        return type;
    }

    public String getTag() {
        return tag;
    }

    public void appendChild(Element element) {
        children.add(element);
    }

    public void setAttribute(String key) {
        attributes.put(key, null);
    }

    public void setAttribute(String key, String value) {
        attributes.put(key, value);
    }

    public void setContent(String text) {
        this.content = text;
    }

    public void setContent(String placeholder, Element... elements) {
        this.content = placeholder;
        this.args = List.of(elements);
    }

    /**
     * Global HTML Attributes
     */

    public void setId(String name) {
        setAttribute("id", name);
    }

    public Element addClass(String... classes) {
        for (String cls : classes) {
            if (attributes.get("class") == null) {
                setAttribute("class", cls);
            } else {
                setAttribute("class", String.format("%s %s", attributes.get("class"), cls));
            }
        }

        return this;
    }

    /**
     * This returns the HTML as a string
     * @return HTML content
     */

    public String render() {
        return render(0);
    }

    public String render(int padding) {
        StringBuilder builder = new StringBuilder();

        // Start tag
        builder.append(" ".repeat(padding));
        builder.append("<");
        builder.append(tag);
        for (String key : attributes.keySet()) {
            // Insert tag attributes
            builder.append(" ");
            builder.append(key);
            if (attributes.get(key) == null) {
                continue;  // Used for 'checked', 'hidden', etc.
            }
            builder.append("=\"");
            builder.append(attributes.get(key));
            builder.append("\"");
        }
        builder.append(">");

        // Handle void elements
        if (isVoid) {
            return builder.toString();
        }

        // Add inner content with inline element '@@@' wildcard handling
        if (children.size() > 0) {
            for (Element child : children) {
                builder.append('\n');
                builder.append(child.render(padding + BLOCK_ELEMENT_PADDING_CHARS));
            }
            builder.append('\n');
            builder.append(" ".repeat(padding));
        } else {
            // @formatter:off
            String innerHTML = content
                    .replace("&", "&amp;")  // REPLACE '&' FIRST!
                    .replace("<", "&lt;")
                    .replace(">", "&gt;");
            // @formatter:on
            if (args != null) {
                for (Element inline : args) {
                    if (inline.getType() == Type.block) {
                        String msg = String.format("Cannot use block level '%s' element for inline content", inline.getTag());
                        throw new IllegalArgumentException(msg);
                    }
                    // TODO: Optimize?
                    innerHTML = innerHTML.replaceFirst(INLINE_ELEMENT_WILDCARD, inline.render(0));
                }
            }
            builder.append(innerHTML);
        }

        // Close tag
        builder.append("</");
        builder.append(tag);
        builder.append(">");

        return builder.toString();
    }

    @Override
    public String toString() {
        return render(0);
    }
}
