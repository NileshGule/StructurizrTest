# Structurizr Test
This repository is for a quick test of [structurizr](https://structurizr.com) features. Specifically to test the automatic code discovery.

To run the program ensure that the bintray repository is added to maven repositories list. In my case I added it to the settings.xml file

```xml
<repository>
    <snapshots>
        <enabled>false</enabled>
    </snapshots>
    <id>bintray-central</id>
    <name>bintray</name>
    <url>http://jcenter.bintray.com</url>
</repository>
```

The program creates a simple [System Context diagram](Images/structurizr-36812-SystemContext.png) for a sample system. It also creates a [Component diagram](Images/structurizr-36812-Components.png) by dynamically finding all the classes with "validator" suffix.
