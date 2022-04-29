package classes.views;

import classes.databaseclasses.entity.Legitarsasag;
import classes.databaseclasses.repository.LegitarsasagRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.Collection;

@PageTitle("osszes legitarsasag")
@Route(value = "legitarsasagokList")
public class LegitarsasagokListing extends VerticalLayout {
    @Autowired
    LegitarsasagRepository legitarsasagRepository;

    Grid<Legitarsasag> legitarsasagGrid;

    public LegitarsasagokListing(){
        add(new Menu());
        add(legitarsasagGrid = new Grid<>());
        legitarsasagGrid.addColumn(Legitarsasag::getNev).setHeader("name");
    }

    @PostConstruct
    void init(){
        legitarsasagGrid.setItems((Collection<Legitarsasag>) legitarsasagRepository.findAll());
    }
}
