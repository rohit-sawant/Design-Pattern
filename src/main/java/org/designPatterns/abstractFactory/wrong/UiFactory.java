package org.designPatterns.abstractFactory.wrong;

class Button {
    public void render() {
        System.out.println("Rendering default button");
    }
}

class LightButton extends Button {
    public void render() {
        System.out.println("Rendering light button");
    }
}

class DarkButton extends Button {
    public void render() {
        System.out.println("Rendering dark button");
    }
}

class Checkbox {
    public void check() {
        System.out.println("Default checkbox");
    }
}

class LightCheckbox extends Checkbox {
    public void check() {
        System.out.println("Light checkbox checked");
    }
}

class DarkCheckbox extends Checkbox {
    public void check() {
        System.out.println("Dark checkbox checked");
    }
}

class UIFactory {
    public static Button getButton(String theme) {
        if (theme.equals("dark")) {
            return new DarkButton();
        } else if (theme.equals("light")) {
            return new LightButton();
        }
        return new Button();
    }

    public static Checkbox getCheckbox(String theme) {
        if (theme.equals("dark")) {
            return new DarkCheckbox();
        } else if (theme.equals("light")) {
            return new LightCheckbox();
        }
        return new Checkbox();
    }

    public static void main(String[] args) {
        Button button = UIFactory.getButton("dark");
        Checkbox checkbox = UIFactory.getCheckbox("dark");

        button.render();
        checkbox.check();
    }
}
