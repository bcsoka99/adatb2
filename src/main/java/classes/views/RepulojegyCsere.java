package classes.views;

import classes.databaseclasses.entity.Jegy;
import classes.databaseclasses.entity.Repulojaratok;
import classes.databaseclasses.entity.Utasok;
import classes.databaseclasses.repository.JegyRepository;
import classes.databaseclasses.repository.UtasokRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@PageTitle("repulojegy csere")
@Route(value = "repulojegyCsere")
public class RepulojegyCsere extends VerticalLayout {
    @Autowired
    JegyRepository jegyRepository;
    @Autowired
    UtasokRepository utasokRepository;
    ComboBox<String> utasok;
    Grid<Jegy> regiJegyek;
    Grid<Jegy> ujJegyek;
    Button change;

    Jegy oldTicket;
    Jegy newTicket;

    public RepulojegyCsere(){
        add(new Menu());
        add(utasok = new ComboBox<>("utasok"));
        utasok.setReadOnly(false);
        add(regiJegyek = new Grid<>());
        regiJegyek.addColumn(Jegy::repulojarat).setHeader("repulojarat");
        regiJegyek.addColumn(Jegy::getSor).setHeader("sor");
        regiJegyek.addColumn(Jegy::getSzek).setHeader("szek");
        add(ujJegyek = new Grid<>());
        ujJegyek.addColumn(Jegy::getSor).setHeader("sor");
        ujJegyek.addColumn(Jegy::getSzek).setHeader("szek");
        add(change = new Button("switch ticket"));

        utasok.addValueChangeListener(event -> fillInOldTickets());
        regiJegyek.addItemClickListener(item -> fillInAvailableTickets(item.getItem().getRepulojaratok(), item.getItem()));
        ujJegyek.addItemClickListener(item -> setNewTicket(item.getItem()));
        change.addClickListener(event -> chageTicket());
    }

    @PostConstruct
    void init(){
        List<Utasok> utasokList = (List<Utasok>) utasokRepository.findAll();
        List<String> utasNev = new ArrayList<>();
        for (Utasok u : utasokList){
            utasNev.add(u.getNev());
        }
        utasok.setItems(utasNev);
    }

    private void fillInOldTickets(){
        Utasok utas = utasokRepository.findByNev(utasok.getValue());
        regiJegyek.setItems(jegyRepository.findAllByUtasok(utas));
    }

    private void fillInAvailableTickets(Repulojaratok repulojaratok, Jegy jegy){
        oldTicket = jegy;
        ujJegyek.setItems(jegyRepository.findAllByRepulojaratokAndFoglalt(repulojaratok, false));
    }

    private void setNewTicket(Jegy jegy){
        newTicket = jegy;
    }

    private void chageTicket(){
        Utasok utas = utasokRepository.findByNev(utasok.getValue());
        oldTicket.setUtasok(null);
        oldTicket.setFoglalt(false);
        newTicket.setUtasok(utas);
        newTicket.setFoglalt(true);
    }
}
