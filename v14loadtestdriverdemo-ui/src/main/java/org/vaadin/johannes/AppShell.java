package org.vaadin.johannes;

import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Inline;
import com.vaadin.flow.server.AppShellSettings;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;

/**
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 */
@PWA(name = "V22 LoadTestDriver demo", shortName = "LoadTestDriver Demo")
@Theme("my-theme")
public class AppShell implements AppShellConfigurator {

}


