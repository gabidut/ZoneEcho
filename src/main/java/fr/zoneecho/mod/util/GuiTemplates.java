package fr.zoneecho.mod.util;

import fr.aym.acsguis.component.panel.GuiPanel;
import fr.aym.acsguis.component.textarea.GuiLabel;

public class GuiTemplates {

    public static class Template {
        private final GuiPanel right = new GuiPanel();
        private final GuiPanel left = new GuiPanel();
        GuiPanel panel = new GuiPanel();
        public Template(String title) {

            GuiLabel titleLabel = new GuiLabel(title);
            titleLabel.setCssClass("title");

            this.left.setCssClass("left");
            this.left.add(titleLabel);
            this.right.setCssClass("right");


            this.panel.setCssClass("creation");
            this.panel.add(this.left);
            this.panel.add(this.right);
        }

        public GuiPanel getPanel() {
            return this.panel;
        }

        public GuiPanel getLeft() {
            return this.left;
        }

        public GuiPanel getRight() {
            return this.right;
        }

        public void addToLeft(GuiPanel panel) {
            this.left.add(panel);
        }

        public void addToRight(GuiPanel panel) {
            this.right.add(panel);
        }
    }

    public static class Template1 {
        private final GuiPanel right = new GuiPanel();
        private final GuiPanel left = new GuiPanel();
        GuiPanel panel = new GuiPanel();
        public Template1(String title) {

            GuiLabel titleLabel = new GuiLabel(title);
            titleLabel.setCssClass("title");

            this.left.setCssClass("left");
            this.left.add(titleLabel);
            this.right.setCssClass("right");


            this.panel.setCssClass("creation");
            this.panel.add(this.left);
            this.panel.add(this.right);
        }

        public GuiPanel getPanel() {
            return this.panel;
        }

        public GuiPanel getLeft() {
            return this.left;
        }

        public GuiPanel getRight() {
            return this.right;
        }

        public void addToLeft(GuiPanel panel) {
            this.left.add(panel);
        }

        public void addToRight(GuiPanel panel) {
            this.right.add(panel);
        }
    }
}
