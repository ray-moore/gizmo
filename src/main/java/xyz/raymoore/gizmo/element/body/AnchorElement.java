package xyz.raymoore.gizmo.element.body;

import xyz.raymoore.gizmo.Element;

public class AnchorElement extends Element {
    public AnchorElement() {
        super(Type.inline, "a");
    }

    public void setReference(String path) {
        setAttribute("href", path);
    }

    public void setTarget(String target) {
        setAttribute("target", target);
    }

    public void setText(String text) {
        setContent(text);
    }
}
