package Projeto.Controller.LimiteField;

import javafx.scene.control.PasswordField;

public class SenhaLoginTextField extends PasswordField {

    private final int limit;

    public SenhaLoginTextField(int limit) {
        this.limit = limit;
    }

    @Override
    public void replaceText(int start, int end, String text) {
        super.replaceText(start, end, text);
        verify();
    }

    @Override
    public void replaceSelection(String text) {
        super.replaceSelection(text);
        verify();
    }

    private void verify() {
        if (getText().length() > limit) {
            setText(getText().substring(0, limit));
        }
    }
}
