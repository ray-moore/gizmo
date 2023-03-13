package xyz.raymoore.gizmo.element.head;

import xyz.raymoore.gizmo.Element;

public class MetaElement extends Element {
    public MetaElement() {
        super(Element.Type.block, "meta");
        this.isVoid = true;
    }
}
