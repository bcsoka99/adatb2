package classes.views;

import classes.databaseclasses.entity.Utasok;
import classes.databaseclasses.repository.UtasokRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.List;

@PageTitle("Fooldal")
@Route(value = "")
public class MainPage extends VerticalLayout {
Grid<Utasok> grid;
@Autowired
UtasokRepository utasokRepository;

public MainPage(){
    add(grid = new Grid<>());
    grid.addColumn(Utasok::getNev).setHeader("nev");
}

@PostConstruct
    void init(){
    List<Utasok> utasok = (List<Utasok>) utasokRepository.findAll();
    grid.setItems(utasok);
}
}
