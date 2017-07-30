package com.nileshgule;

import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.api.StructurizrClientException;
import com.structurizr.componentfinder.ComponentFinder;
import com.structurizr.componentfinder.NameSuffixTypeMatcher;
import com.structurizr.componentfinder.TypeBasedComponentFinderStrategy;
import com.structurizr.model.*;
import com.structurizr.view.*;

import java.util.Collection;

public class StructurizerParent {
    public static void main(String[] args) throws Exception {
        System.out.println("Stsrting structurizr");

        Workspace workspace = new Workspace("Big Data Workspace", "This is a model of a demo big data software system.");
        Model model = workspace.getModel();

        Person user1 = model.addPerson("Trader1", "FO trader");
        SoftwareSystem tradingSystem = model.addSoftwareSystem("Trading System", "Trading system.");
        user1.uses(tradingSystem, "Uses");

        Person user2 = model.addPerson("RM", "Risk Manager");
//        SoftwareSystem softwareSystem = model.addSoftwareSystem("Software System", "My software system.");
        user2.uses(tradingSystem, "Uses");

        SoftwareSystem cartography = model.addSoftwareSystem("Carto", "My software systems cartography.");
        user2.interactsWith(user1, "Intraction between user 1 & user 2");
        user2.uses(cartography, "User uses cartography");

        SoftwareSystem dictionary = model.addSoftwareSystem("Dicto", "My software systems dictionary.");


        SoftwareSystem userInterface = model.addSoftwareSystem("GUI", "My software systems GUI.");
        userInterface.uses(dictionary, "Maps business entities to sources");
        userInterface.uses(cartography, "Maps business relationships");

        ViewSet views = workspace.getViews();
        SystemContextView contextView = views.createSystemContextView(tradingSystem, "SystemContext", "System Context diagram.");
        contextView.addAllSoftwareSystems();
        contextView.addAllPeople();

        Container webApplication = userInterface.addContainer("Web Application", "Description", "Apache Tomcat 7.x");

        ComponentFinder componentFinder = new ComponentFinder(
                webApplication, "com.nileshgule", new TypeBasedComponentFinderStrategy(new NameSuffixTypeMatcher("Validator", "Validator implementations", "Java")));

        Collection<Component> validators = componentFinder.findComponents();

        ComponentView componentView = views.createComponentView(webApplication, "Components", "Component diagram for web applications");
        componentView.addAllComponents();

        Styles styles = views.getConfiguration().getStyles();
        styles.addElementStyle(Tags.SOFTWARE_SYSTEM).background("#1168bd").color("#ffffff");
        styles.addElementStyle(Tags.PERSON).background("#08427b").color("#ffffff").shape(Shape.Person);


        final String apiKey = "f1a2638e-8365-42b9-a5e9-a41230ca921a";
        final String apiSecret = "5da4c2d1-1ea8-4eff-9639-173dc183d4cb";
        final long workSpaceId = 36812;

        StructurizrClient structurizrClient = new StructurizrClient(apiKey, apiSecret);
        try {
            structurizrClient.putWorkspace(workSpaceId, workspace);
        } catch (StructurizrClientException e) {
            e.printStackTrace();
        }
    }
}