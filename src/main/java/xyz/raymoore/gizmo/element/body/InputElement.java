package xyz.raymoore.gizmo.element.body;

import xyz.raymoore.gizmo.Element;

public class InputElement extends Element {
    public enum Type {
        button,
        checkbox,
        color,
        date,
        datetime_local,
        email,
        file,
        image,
        number,
        password,
        radio,
        range,
        search,
        submit,
        tel,
        text,
        time,
        url
    }

    public InputElement(Type type) {
        super(Element.Type.block, "input");
        this.isVoid = true;

        this.setAttribute("type", type.name().replace('_', '-'));
    }
}
