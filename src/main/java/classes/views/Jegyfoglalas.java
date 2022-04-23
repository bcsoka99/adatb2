package classes.views;

import classes.databaseclasses.entity.Jegy;
import classes.databaseclasses.entity.Repulojaratok;
import classes.databaseclasses.entity.Utasok;
import classes.databaseclasses.repository.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@PageTitle("jegyfoglalas")
@Route(value = "jegyfoglalas")
public class Jegyfoglalas extends VerticalLayout {
    @Autowired
    RepulojaratokRepository repulojaratokRepository;
    @Autowired
    UtasokRepository utasokRepository;
    @Autowired
    JegyRepository jegyRepository;
    @Autowired
    RepulokRepository repulokRepository;

    Grid<Repulojaratok> repulojaratokGrid;
    Grid<Jegy> jegyGrid;
    ComboBox<String> utasNev;
    Button foglalas;
    TextField ar;

    public Jegyfoglalas(){
        add(new Menu());
        add(ar = new TextField("ar"));
        ar.setReadOnly(true);
        add(utasNev = new ComboBox<>("utas"));
        add(repulojaratokGrid = new Grid<>());
        repulojaratokGrid.addColumn(Repulojaratok::getHonnan).setHeader("honnan");
        repulojaratokGrid.addColumn(Repulojaratok::getHova).setHeader("hova");
        repulojaratokGrid.addColumn(Repulojaratok::getIndulas).setHeader("indulas");
        repulojaratokGrid.addColumn(Repulojaratok::getErkezes).setHeader("erkezes");
        repulojaratokGrid.addColumn(Repulojaratok::getEtkezes).setHeader("etkezes");
        repulojaratokGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        repulojaratokGrid.addItemClickListener(selected -> findAllJegy(selected.getItem()));
        add(jegyGrid = new Grid<>());
        jegyGrid.addColumn(Jegy::getAr).setHeader("ar");
        jegyGrid.addColumn(Jegy::getDatum).setHeader("datum");
        jegyGrid.addColumn(Jegy::getSor).setHeader("sor");
        jegyGrid.addColumn(Jegy::getSzek).setHeader("szek");
        jegyGrid.setSelectionMode(Grid.SelectionMode.SINGLE);
        jegyGrid.addItemClickListener(event -> ar());
        add(foglalas = new Button("foglalas"));
        foglalas.addClickListener(event -> reserve());
    }

    @PostConstruct
    public void init(){
        List<Utasok> utasok = (List<Utasok>) utasokRepository.findAll();
        List<String> utasnevek = new ArrayList<>();
        for (Utasok u : utasok){
            utasnevek.add(u.getNev());
        }
        utasNev.setItems(utasnevek);
        repulojaratokGrid.setItems((List<Repulojaratok>) repulojaratokRepository.findAll());
    }

    private void findAllJegy(Repulojaratok repulojaratok){
        List<Jegy> availableTickets = jegyRepository.findAllByRepulojaratokAndFoglalt(repulojaratok, Boolean.FALSE);
        jegyGrid.setItems(availableTickets);
    }

    private void reserve(){
        Jegy jegy = (Jegy) jegyGrid.getSelectedItems().toArray()[0];
        jegy.setFoglalt(true);
        jegy.setUtasok(utasokRepository.findByNev(utasNev.getValue()));
        jegyRepository.save(jegy);
    }

    private void ar(){
        Jegy jegy = (Jegy) jegyGrid.getSelectedItems().toArray()[0];
        Utasok utas = utasokRepository.findByNev(utasNev.getValue());
        if (utas.getGyerek()){
            ar.setValue(String.valueOf(jegy.getAr()*0.7));
        }else {
            ar.setValue(String.valueOf(jegy.getAr()));
        }
    }



}
