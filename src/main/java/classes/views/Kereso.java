package classes.views;

import classes.databaseclasses.entity.Repulojaratok;
import classes.databaseclasses.repository.RepulojaratokRepository;
import classes.util.Converter;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@PageTitle("repulojaratkereso datum szerint")
@Route(value = "kereso")
public class Kereso extends VerticalLayout {
    @Autowired
    RepulojaratokRepository repulojaratokRepository;

    Grid<Repulojaratok> repulojaratokGrid;
    DatePicker beforeDate;
    DatePicker afterDate;

    public Kereso(){
        add(new Menu());
        add(beforeDate = new DatePicker("mettol"));
        add(afterDate = new DatePicker("meddig"));
        add(repulojaratokGrid = new Grid<>());
        repulojaratokGrid.addColumn(Repulojaratok::getHonnan).setHeader("honnan");
        repulojaratokGrid.addColumn(Repulojaratok::getHova).setHeader("hova");
        repulojaratokGrid.addColumn(Repulojaratok::getIndulas).setHeader("indulas");
        repulojaratokGrid.addColumn(Repulojaratok::getErkezes).setHeader("erkezes");
        repulojaratokGrid.addColumn(Repulojaratok::legitarsasagNev).setHeader("legitarsasag");
        beforeDate.addValueChangeListener(change -> getAllBetweenDates());
        afterDate.addValueChangeListener(change -> getAllBetweenDates());
    }

    private void getAllBetweenDates(){
        LocalDate before = beforeDate.getValue();
        LocalDate after = afterDate.getValue();
        List<Repulojaratok> repulojaratokList = (List<Repulojaratok>) repulojaratokRepository.findAll();
        List<Repulojaratok> repulojaratokFiltered = new ArrayList<>();
        for (Repulojaratok r : repulojaratokList){
            if (r.getIndulas().after(Converter.convertToDateViaInstant(before)) &&
            r.getErkezes().after(Converter.convertToDateViaInstant(before)) &&
            r.getIndulas().before(Converter.convertToDateViaInstant(after)) &&
            r.getErkezes().before(Converter.convertToDateViaInstant(after))){
                repulojaratokFiltered.add(r);
            }
        }
        repulojaratokGrid.setItems(repulojaratokFiltered);
    }

}
