package classes.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Fooldal")
@Route(value = "")
public class MainPage extends VerticalLayout {
Grid<String> grid;

public MainPage(){
    add(grid = new Grid<>());
}
}
