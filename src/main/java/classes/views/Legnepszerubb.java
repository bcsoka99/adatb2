package classes.views;

import classes.databaseclasses.entity.Repulojaratok;
import classes.databaseclasses.repository.RepulojaratokRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;

@PageTitle("legnepszerubb jaratok")
@Route(value = "legnepszerubb")
public class Legnepszerubb extends VerticalLayout {
    @Autowired
    RepulojaratokRepository repulojaratokRepository;

    Grid<Repulojaratok> repulojaratokGrid;

    public Legnepszerubb(){
        add(new Menu());
        add(repulojaratokGrid = new Grid<>());
        repulojaratokGrid.addColumn(Repulojaratok::getErkezes).setHeader("erkezes");
        repulojaratokGrid.addColumn(Repulojaratok::getIndulas).setHeader("indulas");
        repulojaratokGrid.addColumn(Repulojaratok::getHonnan).setHeader("honnan");
        repulojaratokGrid.addColumn(Repulojaratok::getHova).setHeader("hova");
        repulojaratokGrid.addColumn(Repulojaratok::foglalasSzam).setHeader("foglalt jegyek szama");
    }

    @PostConstruct
    void init(){
        repulojaratokGrid.setItems((Collection<Repulojaratok>) repulojaratokRepository.findAll());
    }
}
