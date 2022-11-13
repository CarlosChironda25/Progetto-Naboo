module com.example.nabo {
    requires javafx.controls;
    requires javafx.fxml;
        requires javafx.web;
            
        requires org.controlsfx.controls;
            requires com.dlsc.formsfx;
            requires validatorfx;
            requires org.kordamp.ikonli.javafx;
            requires org.kordamp.bootstrapfx.core;
            requires eu.hansolo.tilesfx;
            requires com.almasb.fxgl.all;
    requires com.rometools.rome;
    requires com.google.gson;
    requires java.net.http;
    requires telegrambots;
    requires telegrambots.meta;
    requires com.jfoenix;
    requires de.jensd.fx.glyphs.fontawesome;
    requires org.apache.commons.io;
    requires java.sql;
    requires json.simple;

    opens com.example.nabo to javafx.fxml;
    exports com.example.nabo;
}