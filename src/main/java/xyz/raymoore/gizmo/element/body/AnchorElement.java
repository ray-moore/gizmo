package xyz.raymoore.gizmo.element.body;

import xyz.raymoore.gizmo.Element;

public class AnchorElement extends Element {
    public AnchorElement() {
        super(Type.inline, "a");
    }

    public void setReference(String path) {
        this.setAttribute("href", path);
    }

    public void setTarget(String target) {
        this.setAttribute("target", target);
    }
}
