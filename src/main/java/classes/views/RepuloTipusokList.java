package classes.views;

import classes.databaseclasses.entity.Repulok;
import classes.databaseclasses.repository.RepulokRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;

@PageTitle("repulo tipusok")
@Route(value = "repuloTipusok")
public class RepuloTipusokList extends VerticalLayout {
    @Autowired
    RepulokRepository repulokRepository;

    Grid<Repulok> repulokGrid;

    public RepuloTipusokList(){
        add(new Menu());
        add(repulokGrid = new Grid());
        repulokGrid.addColumn(Repulok::getTipus).setHeader("tipus");
        repulokGrid.addColumn(Repulok::getFerohely).setHeader("ferohely");
    }

    @PostConstruct
    void init(){
        repulokGrid.setItems((Collection<Repulok>) repulokRepository.findAll());
    }
}
