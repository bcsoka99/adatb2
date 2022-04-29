package classes.views;

import classes.databaseclasses.entity.Szallodak;
import classes.databaseclasses.repository.SzallodakRepository;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@PageTitle("szallodak")
@Route(value = "szallodak")
public class SzallodakInVaros extends VerticalLayout {

    @Autowired
    SzallodakRepository szallodakRepository;

    Grid<Szallodak> szallodakGrid;
    ComboBox<String> varosok;

    public SzallodakInVaros(){
        add(new Menu());
        add(varosok = new ComboBox<>("varosok"));
        add(szallodakGrid = new Grid<>());
        szallodakGrid.addColumn(Szallodak::getNev).setHeader("nev");
        szallodakGrid.addColumn(Szallodak::getAr).setHeader("ar");
        szallodakGrid.addColumn(Szallodak::getKategoria).setHeader("kategoria");
        varosok.setReadOnly(false);
        varosok.addValueChangeListener(change -> fillInGrid(change.getValue()));
    }

    @PostConstruct
    void init(){
        varosok.setItems(szallodakRepository.findAllTelepules());
    }

    private void fillInGrid(String varos){
        szallodakGrid.setItems(szallodakRepository.findAllByTelepules(varos));
    }
}
