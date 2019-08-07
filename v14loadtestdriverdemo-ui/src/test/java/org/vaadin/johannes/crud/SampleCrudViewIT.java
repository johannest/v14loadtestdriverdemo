package org.vaadin.johannes.crud;

import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

import com.vaadin.flow.component.button.testbench.ButtonElement;
import com.vaadin.flow.component.grid.testbench.GridElement;
import org.vaadin.johannes.AbstractViewTest;
import org.vaadin.johannes.MainLayoutElement;
import org.vaadin.johannes.authentication.LoginFormElement;

public class SampleCrudViewIT extends AbstractViewTest {

    @Test
    public void adminCreatesNewProduct_productIsAvailableInGird() throws InterruptedException {
        // given authenticated as an admin
        $(LoginFormElement.class).first().login("admin", "admin");

        // given "Inventory" is selected from the sidebar menu
        final MainLayoutElement mainElem = $(MainLayoutElement.class).first();
        mainElem.clickMenuLink("Inventory");

        // when clicking the "New product" button
        $(ButtonElement.class).attribute("theme","primary").first()
                .click();

        // when entering new product data and saving the product
        final ProductFormElement prodForm = $(ProductFormElement.class).first();
        final String newTitle = "Cronan's Guide to Nanomixology, 2nd ed.";
        prodForm.getProductNameElement().setValue(newTitle);
        prodForm.getSaveButtonElement().click();

        // then the new title is in the grid
        GridElement grid = $(GridElement.class).first();
        final boolean foundInGrid = IntStream.range(0, grid.getRowCount())
                .mapToObj(row -> grid.getCell(row, 0).getText())
                .anyMatch(title -> newTitle.equals(title));
        Assert.assertTrue("Title not found in grid", foundInGrid);
    }
}
