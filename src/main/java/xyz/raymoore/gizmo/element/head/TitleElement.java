package xyz.raymoore.gizmo.element.head;

import xyz.raymoore.gizmo.Element;

public class TitleElement extends Element {
    public TitleElement(String title) {
        super(Type.block, "title");
        setText(title);
    }
}
