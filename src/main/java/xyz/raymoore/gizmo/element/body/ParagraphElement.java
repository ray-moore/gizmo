package xyz.raymoore.gizmo.element.body;

import xyz.raymoore.gizmo.Element;

public class ParagraphElement extends Element {
    public ParagraphElement() {
        super(Type.block, "p");
    }

    public void setText(String text) {
        setContent(text);
    }
}
