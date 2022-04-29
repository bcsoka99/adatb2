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

@PageTitle("osszes repulojarat")
@Route(value = "repulojaratok")
public class RepulojaratokListing extends VerticalLayout {
    @Autowired
    RepulojaratokRepository repulojaratokRepository;

    Grid<Repulojaratok> repulojaratokGrid;

    public RepulojaratokListing(){
        add(new Menu());
        add(repulojaratokGrid = new Grid<>());
        repulojaratokGrid.addColumn(Repulojaratok::getHonnan).setHeader("honnan");
        repulojaratokGrid.addColumn(Repulojaratok::getHova).setHeader("hova");
        repulojaratokGrid.addColumn(Repulojaratok::getIndulas).setHeader("indulas");
        repulojaratokGrid.addColumn(Repulojaratok::getErkezes).setHeader("erkezes");
        repulojaratokGrid.addColumn(Repulojaratok::legitarsasagNev).setHeader("legitarsasag");
    }

    @PostConstruct
    void init(){
        repulojaratokGrid.setItems((Collection<Repulojaratok>) repulojaratokRepository.findAll());
    }
}
