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


public MainPage(){
    add(new Menu());
}


}
