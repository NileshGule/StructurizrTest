package com.nileshgule;

import com.structurizr.Workspace;
import com.structurizr.api.StructurizrClient;
import com.structurizr.api.StructurizrClientException;
import com.structurizr.model.Model;
import com.structurizr.model.Person;
import com.structurizr.model.SoftwareSystem;
import com.structurizr.model.Tags;
import com.structurizr.view.Shape;
import com.structurizr.view.Styles;
import com.structurizr.view.SystemContextView;
import com.structurizr.view.ViewSet;

public class StructurizerParent {
    public static void main(String[] args) {
        System.out.println("Stsrting structurizr");

        Workspace workspace = new Workspace("Big Data Workspace", "This is a model of a demo big data software system.");
        Model model = workspace.getModel();

        Person user = model.addPerson("Trader1", "FO trader");
        SoftwareSystem softwareSystem = model.addSoftwareSystem("Software System", "My software system.");
        user.uses(softwareSystem, "Uses");

        ViewSet views = workspace.getViews();
        SystemContextView contextView = views.createSystemContextView(softwareSystem, "SystemContext", "System Context diagram.");
        contextView.addAllSoftwareSystems();
        contextView.addAllPeople();

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