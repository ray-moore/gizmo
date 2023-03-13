package xyz.raymoore.gizmo.element.body;

import xyz.raymoore.gizmo.Element;

public class HeaderElement extends Element {
    public enum Level {
        h1,
        h2,
        h3,
        h4,
        h5,
        h6
    }

    public HeaderElement(Level level) {
        super(Type.block, level.name());
    }

    public void setText(String text) {
        setContent(text);
    }
}
