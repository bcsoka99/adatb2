package classes.databaseclasses.repository;

import classes.databaseclasses.entity.Biztositasok;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;

public class EditBiztositasok extends VerticalLayout {
    @Autowired
    BiztositasokRepository biztositasokRepository;

    Grid<Biztositasok> biztositasokGrid;
    HorizontalLayout editFields;
    TextField biztositasNev;
    TextField biztositasAr;
    Button accept;

    Biztositasok biztositasok;

    public EditBiztositasok(){
        add(biztositasokGrid = new Grid<>());
        biztositasokGrid.addColumn(Biztositasok::biztositoNev).setHeader("biztosito nev");
        biztositasokGrid.addColumn(Biztositasok::getNev).setHeader("nev");
        biztositasokGrid.addColumn(Biztositasok::getDij).setHeader("dij");
        add(editFields = new HorizontalLayout());
        add(biztositasNev = new TextField("biztositas neve"));
        add(biztositasAr = new TextField("biztositas ara"));
        add(accept = new Button("accept"));

        biztositasokGrid.addItemClickListener(item -> setEditor(item.getItem()));
        accept.addClickListener(click -> updateBiztositas());
    }

    private void setEditor(Biztositasok biztositasok){
        biztositasNev.setValue(biztositasok.getNev());
        biztositasAr.setValue(biztositasok.getDij().toString());
        this.biztositasok = biztositasok;
    }

    private void updateBiztositas(){
        biztositasok.setDij(Integer.parseInt(biztositasAr.getValue()));
        biztositasok.setNev(biztositasNev.getValue());
        biztositasokRepository.save(biztositasok);
    }
}
