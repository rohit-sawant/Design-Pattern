package org.designPatterns.abstractFactory.right;

interface Button {
    void render();
}

interface Checkbox {
    void check();
}

class LightButton implements Button {
    public void render() {
        System.out.println("Rendering light button");
    }
}

class LightCheckbox implements Checkbox {
    public void check() {
        System.out.println("Checking light checkbox");
    }
}

class DarkButton implements Button {
    public void render() {
        System.out.println("Rendering dark button");
    }
}

class DarkCheckbox implements Checkbox {
    public void check() {
        System.out.println("Checking dark checkbox");
    }
}

interface GUIFactory {
    Button createButton();
    Checkbox createCheckbox();
}

class LightThemeFactory implements GUIFactory {
    public Button createButton() {
        return new LightButton();
    }
    public Checkbox createCheckbox() {
        return new LightCheckbox();
    }
}

class DarkThemeFactory implements GUIFactory {
    public Button createButton() {
        return new DarkButton();
    }
    public Checkbox createCheckbox() {
        return new DarkCheckbox();
    }
}


class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void renderUI() {
        button.render();
        checkbox.check();
    }
}


public class UiFactory {
}
