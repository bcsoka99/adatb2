package classes.views;

import classes.databaseclasses.entity.Repulojaratok;
import classes.databaseclasses.repository.RepulojaratokRepository;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@PageTitle("menetrend")
@Route(value = "menetrend")
public class Menetrend extends VerticalLayout {
    @Autowired
    RepulojaratokRepository repulojaratokRepository;

    Grid<Repulojaratok> repulojaratokGrid;
    ComboBox<String> honnanBox;

    public Menetrend(){
        add(new Menu());
        add(honnanBox = new ComboBox<>());
        add(repulojaratokGrid = new Grid<>());
        repulojaratokGrid.addColumn(Repulojaratok::getHonnan).setHeader("honnan");
        repulojaratokGrid.addColumn(Repulojaratok::getHova).setHeader("hova");
        repulojaratokGrid.addColumn(Repulojaratok::getIndulas).setHeader("indulas");
        repulojaratokGrid.addColumn(Repulojaratok::getErkezes).setHeader("erkezes");
        honnanBox.addValueChangeListener(event -> findRepuloByHonnan());
    }

    @PostConstruct
    void init(){
        List<Repulojaratok> repulojaratokList = (List<Repulojaratok>) repulojaratokRepository.findAll();
        List<String> honnanList = new ArrayList<>();
        for (Repulojaratok r : repulojaratokList){
            honnanList.add(r.getHonnan());
        }
        honnanBox.setItems(honnanList);
    }

    private void findRepuloByHonnan(){
        repulojaratokGrid.setItems(repulojaratokRepository.findAllByHonnan(honnanBox.getValue()));
    }
}
