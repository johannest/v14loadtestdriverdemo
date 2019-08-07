package org.vaadin.johannes.authentication;

import org.junit.Assert;
import org.junit.Test;

import com.vaadin.flow.theme.lumo.Lumo;
import org.vaadin.johannes.AbstractViewTest;
import org.vaadin.johannes.MainLayoutElement;

public class LoginScreenIT extends AbstractViewTest {

    @Test
    public void loginForm_isLumoThemed() {
        LoginFormElement loginForm = $(LoginFormElement.class).first();
        assertThemePresentOnElement(loginForm, Lumo.class);
    }

    @Test
    public void loginAsAdmin_hasAdminViewLink() {
        // when authenticating as admin
        $(LoginFormElement.class).first().login("admin", "admin");

        // then there is a link to admin's view
        Assert.assertTrue("Expected link to admin view",
                $(MainLayoutElement.class).first().hasMenuLink("admin"));
    }

    @Test
    public void loginAsUser_noAdminViewLink() {
        // when authenticating as a regular user
        $(LoginFormElement.class).first().login("user", "user");

        // then there is no link to admin's view
        Assert.assertFalse("Expected no link to admin view",
                $(MainLayoutElement.class).first().hasMenuLink("admin"));
    }
}
